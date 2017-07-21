public class MyMath{
    /* array function */
    public static int sum(int[] array){
	int sum = 0;
	for(int i = 0; i < array.length; i++)
	    sum += array[i];
	return sum;
    }
    public static double sum(double[] array){
	double sum = 0.0;
	for(int i = 0; i < array.length; i++)
	    sum += array[i];
	return sum;
    }
    public static double mean(double[] array){
	double mean = sum(array) / array.length;
	return mean;
    }
    public static double std(double[] array){
	double std = 0.0;
	for(int i = 0; i < array.length; i++)
	    std += array[i] * array[i];
	double mean = mean(array);
	std = Math.sqrt(std / array.length - mean * mean);
	return std;
    }
    public static int argmax(int[] array){
	int max = array[0]; int index = 0;
	for(int i = 1; i < array.length; i++)
	    if(array[i] > max){
		max = array[i]; index = i;
	    }
	return index;
    }
    public static int argmax(double[] array){
	double max = array[0]; int index = 0;
	for(int i = 1; i < array.length; i++)
	    if(array[i] > max){
		max = array[i]; index = i;
	    }
	return index;
    }
    public static int argmin(int[] array){
	int min = array[0]; int index = 0;
	for(int i = 1; i < array.length; i++)
	    if(array[i] < min){
		min = array[i]; index = i;
	    }
	return index;
    }
    public static int argmin(double[] array){
	double min = array[0]; int index = 0;
	for(int i = 1; i < array.length; i++)
	    if(array[i] < min){
		min = array[i]; index = i;
	    }
	return index;
    }
    public static int max(int[] array){
	return array[argmax(array)];
    }
    public static double max(double[] array){
	return array[argmax(array)];
    }
    public static int min(int[] array){
	return array[argmin(array)];
    }
    public static double min(double[] array){
	return array[argmin(array)];
    }
    public static void swap(int[] m, int i, int j){
	int tmp = m[i];
	m[i] = m[j];
	m[j] = tmp;
    }
    public static void swap(double[] m, int i, int j){
	double tmp = m[i];
	m[i] = m[j];
	m[j] = tmp;
    }
    public static void swap(String[] m, int i, int j){
	String tmp = m[i];
	m[i] = m[j];
	m[j] = tmp;
    }
    public static void swap(Pair[] m, int i, int j){
	Pair tmp = m[i];
	m[i] = m[j];
	m[j] = tmp;
    }

    /* math function */
    public static class Function{
	public static Matrix exp(Matrix x){
	    Matrix e = new Matrix(x.row, x.column, 0.0);
	    for(int i = 0; i < x.row; i++)
		for(int j = 0; j < x.column; j++)
		    e.mat[i][j] = Math.exp(x.mat[i][j]);
	    return e;
	}
	public static Matrix log(Matrix x){
	    Matrix l = new Matrix(x.row, x.column, 0.0);
	    for(int i = 0; i < x.row; i++)
		for(int j = 0; j < x.column; j++)
		    l.mat[i][j] = Math.log(x.mat[i][j]);
	    return l;
	}
	public static double sigmoid(double x){
	    return 1.0 / (1.0 + Math.exp(-x));
	}
	public static Matrix sigmoid(Matrix x){
	    Matrix s = new Matrix(x.row, x.column, 0.0);
	    for(int i = 0; i < x.row; i++)
		for(int j = 0; j < x.column; j++)
		    s.mat[i][j] = sigmoid(x.mat[i][j]);
	    return s;
	}
	public static double sigmoid_prime(double x){
	    return sigmoid(x) * (1 - sigmoid(x));
	}
	public static Matrix sigmoid_prime(Matrix x){
	    Matrix s = new Matrix(x.row, x.column, 0.0);
	    for(int i = 0; i < x.row; i++)
		for(int j = 0; j < x.column; j++)
		    s.mat[i][j] = sigmoid_prime(x.mat[i][j]);
	    return s;
	}
    }
	
    /* random function */
    public static class Random{
	public static double gauss(double mean, double std){
	    double r = 0.0;
	    for(int i = 0; i < 12; i++)
		r += Math.random();
	    r = std * (r - 6.0) + mean;
	    return r;
	}
	public static int[] randint(int size){
	    int[] d = new int[size];
	    for(int i = 0; i < size; i++)
		d[i] = (int)(gauss(0.0, 1.0) * 100);
	    return d;
	}
	public static double[] randn(int size){
	    double[] d = new double[size];
	    for(int i = 0; i < size; i++)
		d[i] = gauss(0.0, 1.0);
	    return d;
	}
	public static int[] shuffle(int[] m){
	    for(int i = 0; i < m.length; i++){
		int index1 = new java.util.Random().nextInt(m.length);
		int index2 = new java.util.Random().nextInt(m.length);
		swap(m, index1, index2);
	    }
	    return m;
	}
	public static double[] shuffle(double[] m){
	    for(int i = 0; i < m.length; i++){
		int index1 = new java.util.Random().nextInt(m.length);
		int index2 = new java.util.Random().nextInt(m.length);
		swap(m, index1, index2);
	    }
	    return m;
	}
	public static String[] shuffle(String[] m){
	    for(int i = 0; i < m.length; i++){
		int index1 = new java.util.Random().nextInt(m.length);
		int index2 = new java.util.Random().nextInt(m.length);
		swap(m, index1, index2);
	    }
	    return m;
	}
	public static Pair[] shuffle(Pair[] m){
	    for(int i = 0; i < m.length; i++){
		int index1 = new java.util.Random().nextInt(m.length);
		int index2 = new java.util.Random().nextInt(m.length);
		swap(m, index1, index2);
	    }
	    return m;
	}
    }
    
    /* matrix */
    public static class Matrix{
	int row;
	int column;
	double[][] mat;
	
	/* create matrix and initialize randomly */
	public Matrix(int row, int column){
	    this.row = row;
	    this.column = column;
	    mat = new double[row][column];
	    for(int i = 0; i < row; i++)
		for(int j = 0; j < column; j++)
		    mat[i][j] = Random.gauss(0.0, 1.0);
	}
	/* create matrix and initialize specific number */
	public Matrix(int row, int column, double num){
	    this.row = row;
	    this.column = column;
	    mat = new double[row][column];
	    for(int i = 0; i < row; i++)
		for(int j = 0; j < column; j++)
		    mat[i][j] = num;
	}
	/* create identity matrix */
	public Matrix(int dim){
	    row = column = dim;
	    mat = new double[dim][dim];
	    for(int i = 0; i < dim; i++)
		for(int j = 0; j < dim; j++)
		    mat[i][j] = i == j ? 1.0 : 0.0;
	}
	/* create matrix from array */
	public Matrix(double[] d){
	    row = d.length; column = 1;
	    mat = new double[row][column];
	for(int i = 0; i < row; i++)
	    mat[i][0] = d[i];
	}
	/* create matrix from 2d array */
	public Matrix(double[][] d){
	    row = d.length; column = d[0].length;
	    mat = new double[row][column];
	    for(int i = 0; i < row; i++)
		for(int j = 0; j < column; j++)
		    mat[i][j] = d[i][j];
	}
	
	public int getRow(){
	    return row;
	}
	public int getColumn(){
	    return column;
	}
	public double getMat(int i, int j){
	    return mat[i][j];
	}
	public void setMat(int i, int j, double num){
	    mat[i][j] = num;
	}
	/* create transpose matrix */
	public Matrix transpose(){
	    Matrix t = new Matrix(column, row);
	    for(int i = 0; i < column; i++)
		for(int j = 0; j < row; j++)
		    t.mat[i][j] = mat[j][i];
	    return t;
	}
	/* calculate norm */
	public double norm(){
	    if(row != 1 && column != 1){
		System.out.println("undefined form"); return -1;
	    }
	    return Math.sqrt(matmul(this, this).sum());
	}
	/* convert array to matrix */
	public static Matrix toMatrix(int[] d){
	    Matrix m = new Matrix(d.length, 1, 0.0);
	    for(int i = 0; i < d.length; i++)
		m.mat[i][0] = (double)d[i];
	    return m;
	}
	/* convert 2d array to matrix */
	public static Matrix toMatrix(int[][] d){
	    Matrix m = new Matrix(d.length, d[0].length, 0.0);
	    for(int i = 0; i < d.length; i++)
		for(int j = 0; j < d[0].length; j++)
		    m.mat[i][j] = (double)d[i][j];
	    return m;
	}
	/* convert array to matrix */
	public static Matrix toMatrix(double[] d){
	    Matrix m = new Matrix(d.length, 1, 0.0);
	    for(int i = 0; i < d.length; i++)
		m.mat[i][0] = d[i];
	    return m;
	}
	/* convert 2d array to matrix */
	public static Matrix toMatrix(double[][] d){
	    Matrix m = new Matrix(d.length, d[0].length, 0.0);
	    for(int i = 0; i < d.length; i++)
		for(int j = 0; j < d[0].length; j++)
		    m.mat[i][j] = d[i][j];
	    return m;
	}
	/* determine equalization */
	public static boolean equal(Matrix a, Matrix b){
	    if(a.row != b.row || a.column != b.column) return false;
	    double eps = 0.0001;
	    for(int i = 0; i < a.row; i++)
		for(int j = 0; j < a.column; j++)
		    if(Math.abs(a.mat[i][j] -  b.mat[i][j]) > eps) return false;
	    return true;
	}
	/* matrix addition */
	public static Matrix matadd(Matrix a, Matrix b){
	    if(a.row != b.row || a.column != b.column){
		System.out.println("undefined form"); return null;
	    }
	    Matrix c = new Matrix(a.row, a.column, 0.0);
	    for(int i = 0; i < a.row; i++)
		for(int j = 0; j < a.column; j++)
		    c.mat[i][j] = a.mat[i][j] + b.mat[i][j];
	    return c;
	}
	public static Matrix matadd(double d, Matrix a){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matadd(a, b);
	}
	public static Matrix matadd(Matrix a, double d){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matadd(a, b);
	}
	/* matrix subtraction */
	public static Matrix matsub(Matrix a, Matrix b){
	    if(a.row != b.row || a.column != b.column){
		System.out.println("undefined form"); return null;
	    }
	    Matrix c = new Matrix(a.row, a.column, 0.0);
	    for(int i = 0; i < a.row; i++)
		for(int j = 0; j < a.column; j++)
		c.mat[i][j] = a.mat[i][j] - b.mat[i][j];
	    return c;
	}
	public static Matrix matsub(double d, Matrix a){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matsub(b, a);
	}
	public static Matrix matsub(Matrix a, double d){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matsub(a, b);
	}
	/* matrix multiplication */
	public static Matrix matmul(Matrix a, Matrix b){
	    if(a.row != b.row || a.column != b.column){
		System.out.println("undefined form"); return null;
	    }
	    Matrix c = new Matrix(a.row, a.column, 0.0);
	    for(int i = 0; i < a.row; i++)
		for(int j = 0; j < a.column; j++)
		    c.mat[i][j] = a.mat[i][j] * b.mat[i][j];
	    return c;
	}
	public static Matrix matmul(double d, Matrix a){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matmul(a, b);
	}
	public static Matrix matmul(Matrix a, double d){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matmul(a, b);
	}
	/* matrix division */
	public static Matrix matdiv(Matrix a, Matrix b){
	    if(a.row != b.row || a.column != b.column){
		System.out.println("undefined form"); return null;
	    }
	    Matrix c = new Matrix(a.row, a.column, 0.0);
	    for(int i = 0; i < a.row; i++)
		for(int j = 0; j < a.column; j++)
		    c.mat[i][j] = a.mat[i][j] / b.mat[i][j];
	    return c;
	}
	public static Matrix matdiv(double d, Matrix a){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matdiv(b, a);
	}
	public static Matrix matdiv(Matrix a, double d){
	    Matrix b = new Matrix(a.row, a.column, d);
	    return matdiv(a, b);
	}
	/* calculate dot */
	public static Matrix dot(Matrix a, Matrix b){
	    if(a.column != b.row){
		System.out.println("undefined form"); return null;
	    }
	    Matrix c = new Matrix(a.row, b.column, 0.0);
	    for(int i = 0; i < a.row; i++)
		for(int j = 0; j < b.column; j++)
		    for(int k = 0; k < a.column; k++)
			c.mat[i][j] += a.mat[i][k] * b.mat[k][j];
	    return c;
	}	
	/* create copy */
	public Matrix copy(){
	    Matrix c = new Matrix(row, column);
	    for(int i = 0; i < row; i++)
		for(int j = 0; j < column; j++)
		    c.mat[i][j] = mat[i][j];
	    return c;
	}
	/* sum of factors */
	public double sum(){
	    double sum = 0.0;
	    for(int i = 0; i < row; i++)
		for(int j = 0; j < column; j++)
		    sum += mat[i][j];
	    return sum;
	}
	public void zero_one(double threshold){
	    for(int i = 0; i < row; i++)
		for(int j = 0; j < column; j++){
		    if(mat[i][j] >= threshold) mat[i][j] = 1.0;
		    else mat[i][j] = 0.0;
		}
	}
	/* print format */
	public String toString(){
	    String s = "";
	    for(int i = 0; i < row; i++){
		if(i == 0) s += "[";
		else s += " ";
		for(int j = 0; j < column; j++)
		    s += " " + mat[i][j] + " ";
		if(i == row - 1) s += "]";
		s += "\n";
	    }
	    return s;
	}
    }

    /* pair */
    public static class Pair{
	Matrix fst;
	Matrix snd;
	
	/* make pair */
	public Pair(Matrix fst, Matrix snd){
	    this.fst = fst; this.snd = snd;
	}
	
	public Matrix getFirst(){
	    return fst;
	}
	public Matrix getSecond(){
	    return snd;
	}
	/* print format */
	public String toString(){
	    return "( " + fst + ", " + snd + ")";
	}
    }
}   
