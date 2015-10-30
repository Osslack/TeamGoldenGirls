// Create by Simon on  30/10
// Worked on by:
// Simon
public class Powerbar{
	public static double MAX_POWER = 100.0;
	public double mPower;
	public double mSpeed;
	private double mTotalPower;
	
	public Powerbar(){
		mPower = 0.0;
		mSpeed = 0.0;
		mTotalPower = 0.0;
	}
	public Powerbar(double power,double speed){
		mPower = power;
		mSpeed = speed;
		mTotalPower = power;
	}
	public void computePower(){
		mTotalPower += mSpeed;
		if(mTotalPower%(2*MAX_POWER) > MAX_POWER){
			mPower = 2*MAX_POWER-mTotalPower%(2*MAX_POWER); 
		}
		else{
			mPower = mTotalPower%(2*MAX_POWER);
		}
	}
	@Override 
	public String toString(){
		return "Powerbar ( Power " + mPower + " | Speed " + mSpeed + " | TotalPower " + mTotalPower + " )";
	}
	public static void main(String[] args){
		Powerbar pb = new Powerbar(0.0,10.0);
		int i = 0;
		while(i<1000){
			System.out.println(pb);
			pb.computePower();
			++i;
		}
	}
}