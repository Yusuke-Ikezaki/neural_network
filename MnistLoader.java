import java.io.*;
import lib.MyMath.*;

public class MnistLoader{
    public static Pair[] load(String prefix){
	Pair[] mnist = null;
	try{
	    MnistDataSet data = MnistDataSet.createInstance(prefix);
	    
	    double[][] features = data.getFeatures();
	    double[][] labels = data.getLabels_onehot();
	    
	    Matrix[] input = new Matrix[data.getNumImages()];
	    for(int i = 0; i < input.length; i++)
		input[i] = new Matrix(features[i]);
	    Matrix[] output = new Matrix[data.getNumImages()];
	    for(int i = 0; i < output.length; i++)
		output[i] = new Matrix(labels[i]);
	    
	    mnist = new Pair[data.getNumImages()];
	    for(int i = 0; i < mnist.length; i++)
		mnist[i] = new Pair(input[i], output[i]);
	} catch(Exception e){
	    System.out.println(e);
	}

	return mnist;
    }
}
