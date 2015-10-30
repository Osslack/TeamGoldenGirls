/**
 * Created by Simon on 30/10
 *
 * @author Simon, Nils Wende
 */

public class Powerbar
{
	public static final double MAX_POWER = 100.0;
	public double mPower;
	public double mSpeed;

	public Powerbar()
	{
		mPower = 0.0;
		mSpeed = 0.0;
	}

	public Powerbar(double power, double speed)
	{
		mPower = power;
		mSpeed = speed;
	}

	public void computePower()
	{
		final double totalPower = mPower + mSpeed;
		final double maxPowerDoubled = 2.0 * MAX_POWER;
		final double normalizedPower = totalPower % maxPowerDoubled;
		if (normalizedPower > MAX_POWER)
		{
			mPower = maxPowerDoubled - normalizedPower;
		}
		else
		{
			mPower = normalizedPower;
		}
	}

	@Override
	public String toString()
	{
		return "Powerbar ( Power " + mPower + " | Speed " + mSpeed + " )";
	}

	public static void main(String[] args)
	{
		Powerbar pb = new Powerbar(0.0, 10.0);
		int i = 0;
		while (i < 1000)
		{
			System.out.println(pb);
			pb.computePower();
			++i;
		}
	}
}
