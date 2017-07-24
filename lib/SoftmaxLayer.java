public class SoftmaxLayer extends Layer{
    public SoftmaxLayer(int n_in, int n_out, double p_dropout){
	this.n_in = n_in;
	this.n_out = n_out;
	this.p_dropout = p_dropout;
	fn = new Softmax();
    }
}
