public class ConvolutionLayer extends Layer{
    private Pair image_shape;
    private Pair filter_shape;

    public ConvolutionLayer(Pair image_shape, Pair filter_shape, Activation fn){
	this.image_shape = image_shape;
	this.filter_shape = filter_shape;
	this.fn = fn;
    }
}
