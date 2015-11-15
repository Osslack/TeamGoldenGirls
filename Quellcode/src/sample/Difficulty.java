package sample;

/**
 * @author Nils
 */
public enum Difficulty {
	EASY {
		@Override
		public String toString() {
			return "Easy";
		}
	},
	MEDIUM {
		@Override
		public String toString() {
			return "Medium";
		}
	},
	HARD {
		@Override
		public String toString() {
			return "Hard";
		}
	},
	EXTREME {
		@Override
		public String toString() {
			return "Extreme";
		}
	};

	public static Difficulty toDifficulty(String s) {
		switch (s) {
			case "Easy":
				return Difficulty.EASY;
			case "Medium":
				return Difficulty.MEDIUM;
			case "Hard":
				return Difficulty.HARD;
			case "Extreme":
				return Difficulty.EXTREME;
		}
		return null;
	}

	public static int toInteger(Difficulty d) {
		int r = 1;
		switch(d){
			case EASY:
				r = 1;
				break;
			case MEDIUM:
				r = 3;
				break;
			case HARD:
				r = 8;
				break;
			case EXTREME:
				r = 20;
				break;
		}
		return r;
	}
}
