public class FullyConnectedLayer extends Layer{
    public FullyConnectedLayer(int n_in, int n_out, Activation fn, double p_dropout){
	this.n_in = n_in;
	this.n_out = n_out;
	this.fn = fn;
	this.p_dropout = p_dropout;
    }
}
