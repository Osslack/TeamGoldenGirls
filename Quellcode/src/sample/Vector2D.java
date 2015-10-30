public class Vector2D2D{
	public double mX;
	public double mY; 
	public Vector2D(){
		mX = 0.0;
		mY = 0.0;
	}
	public Vector2D(double x,double y){
		mX = x;
		mY = y;
	}
	public static Vector2D2D addVector2D(Vector2D a,Vector2D b){
		a.mX = a.mX + b.mX;
		a.mY = a.mY + b.mY;
		return a;
	}
	public static Vector2D2D subVector2D(Vector2D a,Vector2D b){
		a.mX = a.mX - b.mX;
		a.mY = a.mY - b.mY;
		return a;
	}
	public static double scalarProduct(Vector2D a,Vector2D b){
		return a.mX * b.mX + a.mY * b.mY;
	}
	public static Vector2D scalarMultiplication(Vector2D a,double factor){
		a.mX = a.mX * factor;
		a.mY = a.mY * factor;
		return a;
	}
	// if Vector2D is a line
	public static double lengthVector2D(Vector2D a){
		return Math.sqrt(square(a.mX) + square(a.mY));
	}
	// if we have two Vector2Ds representing positions
	public static double lengthVector2D(Vector2D a,Vector2D b){
		Vector2D ab = subVector2D(b,a);
		return lengthVector2D(ab);
	}
	@Override
	public String toString(){
		return "(" + mX + "," + mY +")";
	}
	private static double square(double a){
		return a*a;
	}git c
	public static void main(String[] args){
		Vector2D a = new Vector2D(0.0,0.0);
		Vector2D b = new Vector2D(10.0,10.0);
		Vector2D c = new Vector2D(0.0,1.0);
		System.out.println(lengthVector2D(subVector2D(a,b)));
		System.out.println(lengthVector2D(b,a));
		//System.out.println(lengthVector2D(b));
		System.out.println(lengthVector2D(a,c));
		System.out.println(lengthVector2D(c));
	}
}