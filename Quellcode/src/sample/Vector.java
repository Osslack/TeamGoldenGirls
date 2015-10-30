public class Vector{
	public double mX;
	public double mY; 
	public Vector(){
		mX = 0.0;
		mY = 0.0;
	}
	public Vector(double x,double y){
		mX = x;
		mY = y;
	}
	public static Vector addVector(Vector a,Vector b){
		a.mX = a.mX + b.mX;
		a.mY = a.mY + b.mY;
		return a;
	}
	public static Vector subVector(Vector a,Vector b){
		a.mX = a.mX - b.mX;
		a.mY = a.mY - b.mY;
		return a;
	}
	public static double scalarProduct(Vector a,Vector b){
		return a.mX * b.mX + a.mY * b.mY;
	}
	public static Vector scalarMultiplication(Vector a,double factor){
		a.mX = a.mX * factor;
		a.mY = a.mY * factor;
		return a;
	}
	// if Vector is a line
	public static double lengthVector(Vector a){
		return Math.sqrt(square(a.mX) + square(a.mY));
	}
	// if we have two Vectors representing positions
	public static double lengthVector(Vector a,Vector b){
		Vector ab = subVector(b,a);
		return lengthVector(ab);
	}
	@Override
	public String toString(){
		return "(" + mX + "," + mY +")";
	}
	private static double square(double a){
		return a*a;
	}
	public static void main(String[] args){
		Vector a = new Vector(0.0,0.0);
		Vector b = new Vector(10.0,10.0);
		Vector c = new Vector(0.0,1.0);
		System.out.println(lengthVector(subVector(a,b)));
		System.out.println(lengthVector(b,a));
		//System.out.println(lengthVector(b));
		System.out.println(lengthVector(a,c));
		System.out.println(lengthVector(c));
	}
}