public class PoolingLayer extends Layer{
    private Pair image_shape;
    private Pair pooling_shape;

    public PoolingLayer(Pair image_shape, Pair pooling_shape){
	this.image_shape = image_shape;
	this.pooling_shape = pooling_shape;
    }
}
