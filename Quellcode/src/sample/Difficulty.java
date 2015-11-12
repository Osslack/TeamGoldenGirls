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
	}
}
