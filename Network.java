import lib.MyMath.*;

public class Network{
    private int num_layers;
    private int[] sizes;
    private Matrix[] biases;
    private Matrix[] weights;
    private Cost cost;

    public Network(int... sizes){
	num_layers = sizes.length;
	this.sizes = sizes;
	default_weight_initializer();
	cost = new CrossEntropyCost();
    }

    public void set_quoadratic_cost(){
	cost = new QuadraticCost();
    }
    public void default_weight_initializer(){
	biases = new Matrix[sizes.length - 1];
	weights = new Matrix[sizes.length - 1];
	for(int i = 0; i < sizes.length - 1; i++){
	    biases[i] = new Matrix(sizes[i + 1], 1);
	    weights[i] = new Matrix(sizes[i + 1], sizes[i]);
	    weights[i] = Matrix.matdiv(weights[i], Math.sqrt(sizes[i]));
	}
    }
    public void large_weight_initializer(){
	biases = new Matrix[sizes.length - 1];
	weights = new Matrix[sizes.length - 1];
	for(int i = 0; i < sizes.length - 1; i++){
	    biases[i] = new Matrix(sizes[i + 1], 1);
	    weights[i] = new Matrix(sizes[i + 1], sizes[i]);
	}
    }
    public Matrix feedforward(Matrix a){
	for(int i = 0; i < num_layers - 1; i++)
	    a = Function.sigmoid(Matrix.matadd(Matrix.dot(weights[i], a), biases[i]));
	return a;
    }
    public void SGD(Pair[] training_data, int epochs, int mini_batch_size,
		    double eta, double lambda, Pair[] test_data){
	int n_test = 0;
	if(test_data != null) n_test = test_data.length;
	int n = training_data.length;
	for(int i = 0; i < epochs; i++){
	    Random.shuffle(training_data);
	    Pair[] mini_batch = new Pair[mini_batch_size];
	    for(int j = 0; j < n; j += mini_batch_size){
		for(int k = 0; k < mini_batch_size; k++)
		    mini_batch[k] = training_data[j + k];
		update_mini_batch(mini_batch, eta, lambda, n);
	    }
	    if(test_data != null)
		System.out.println("Epoch " + i + ": " +
				   evaluate(test_data) + " / " + n_test);
	    else
		System.out.println("Epoch " + i + " complete");
	}
    }
    public void update_mini_batch(Pair[] mini_batch, double eta,
				  double lambda, int n){
	Matrix[] nabla_b = new Matrix[biases.length];
	for(int i = 0; i < biases.length; i++)
	    nabla_b[i] = new Matrix(biases[i].getRow(), biases[i].getColumn(), 0.0);
	Matrix[] nabla_w = new Matrix[weights.length];
	for(int i = 0; i < weights.length; i++)
	    nabla_w[i] = new Matrix(weights[i].getRow(), weights[i].getColumn(), 0.0);
	for(int i = 0; i < mini_batch.length; i++){
	    Pair[] deltas = backprop(mini_batch[i].getFirst(), mini_batch[i].getSecond());
	    for(int j = 0; j < biases.length; j++)
		nabla_b[j] = Matrix.matadd(nabla_b[j], deltas[j].getFirst());
	    for(int j = 0; j < weights.length; j++)
		nabla_w[j] = Matrix.matadd(nabla_w[j], deltas[j].getSecond());
	}
	for(int i = 0; i < biases.length; i++)
	    biases[i] = Matrix.matadd(biases[i], Matrix.matmul(- eta / mini_batch.length, nabla_b[i]));
	for(int i = 0; i < weights.length; i++)
	    weights[i] = Matrix.matadd(Matrix.matmul(1.0 - eta * (lambda / n), weights[i]), Matrix.matmul(- eta / mini_batch.length, nabla_w[i]));
    }
    public Pair[] backprop(Matrix x, Matrix y){
	Matrix[] nabla_b = new Matrix[biases.length];
	for(int i = 0; i < biases.length; i++)
	    nabla_b[i] = new Matrix(biases[i].getRow(), biases[i].getColumn(), 0.0);
	Matrix[] nabla_w = new Matrix[weights.length];
	for(int i = 0; i < weights.length; i++)
	    nabla_w[i] = new Matrix(weights[i].getRow(), weights[i].getColumn(), 0.0);
	Matrix activation = x;
	Matrix[] activations = new Matrix[num_layers];
	activations[0] = x;
	Matrix[] zs = new Matrix[num_layers];
	for(int i = 0; i < num_layers - 1; i++){
	    Matrix z = Matrix.matadd(Matrix.dot(weights[i], activation), biases[i]);
	    zs[i + 1] = z;
	    activation = Function.sigmoid(z);
	    activations[i + 1] = activation;
	}
	Matrix delta = cost.delta(zs[zs.length - 1], activations[activations.length - 1], y);
	nabla_b[nabla_b.length - 1] = delta;
	nabla_w[nabla_w.length - 1] =
	    Matrix.dot(delta, activations[activations.length - 2].transpose());
	for(int i = 2; i < num_layers; i++){
	    Matrix z = zs[zs.length - i];
	    Matrix spv = Function.sigmoid_prime(z);
	    delta = Matrix.matmul(Matrix.dot(weights[weights.length - i + 1].transpose(), delta), spv);
	    nabla_b[nabla_b.length - i] = delta;
	    nabla_w[nabla_w.length - i] = 
		Matrix.dot(delta, activations[activations.length - i - 1].transpose());
	}
	Pair[] nablas = new Pair[num_layers - 1];
	for(int i = 0; i < nablas.length; i++)
	    nablas[i] = new Pair(nabla_b[i], nabla_w[i]);
	return nablas;
    }
    public int evaluate(Pair[] test_data){
	Pair[] test_results = new Pair[test_data.length];
	for(int i = 0; i < test_data.length; i++)
	    test_results[i] = new Pair(feedforward(test_data[i].getFirst()), test_data[i].getSecond());
	int sum = 0;
	for(int i = 0; i < test_results.length; i++){
	    test_results[i].getFirst().zero_one(0.5);
	    if(Matrix.equal(test_results[i].getFirst(), test_results[i].getSecond())) sum++;
	}
     	return sum;
    }
    public Matrix cost_derivative(Matrix output_activations, Matrix y){
	return Matrix.matsub(output_activations, y);
    }
}

abstract class Cost{
    abstract public double fn(Matrix a, Matrix y);
    abstract public Matrix delta(Matrix z, Matrix a, Matrix y);
}
class QuadraticCost extends Cost{
    public double fn(Matrix a, Matrix y){
	return 0.5 * Math.pow(Matrix.matsub(a, y).norm(), 2.0);
    }
    public Matrix delta(Matrix z, Matrix a, Matrix y){
	return Matrix.matmul(Matrix.matsub(a, y), Function.sigmoid_prime(z));
    }
}
class CrossEntropyCost extends Cost{
    public double fn(Matrix a, Matrix y){
	return Matrix.matsub(Matrix.matmul(Matrix.matmul(-1.0, y), Function.log(a)), Matrix.matmul(Matrix.matsub(1.0, y), Function.log(Matrix.matsub(1.0, a)))).sum();
    }
    public Matrix delta(Matrix z, Matrix a, Matrix y){
	return Matrix.matsub(a, y);
    }
}
