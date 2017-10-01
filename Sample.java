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
