# Neural Network
This is fully connected **only** neural network by Java.  
For understanding neural network, you can create model and tune it.

## Sample code
```java:Sample.java
import lib.MyMath.*;

public class Sample {
    public static void main(String[] args) {
        /* load train data */  
        Pair[] train_data = MnistLoader.load("train");
        /* load test data */
	Pair[] test_data = MnistLoader.load("test");

        /* create network */
	Network n = new Network(784, 30, 10);
        
        /* learn network */
	n.SGD(train_data, 30, 100, 0.1, 0.5, test_data);
    }
}
```

This sample code scores about 88% accuracy in MNIST handwritten character recognition.  
However, it will be possible to score about 98% accuracy depending on the ingenuity.  
Let's challenge how much you can increase the accuracy!

## How to use

**Compile**
```
$ javac Sample.java
```

**Execute**
```Execute
$ java Sample
```
