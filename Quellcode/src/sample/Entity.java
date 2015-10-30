import java.util.List;
import java.util.ArrayList;

public class Entity{
	public List<Vector2D> mPositions;
	public double mDampening;
	public Entity(){
		mPositions = new ArrayList<Vector2D> [new Vector2D()];
		//mPositions.add(Vector2D());
		mDampening = 0.0;
	}

}