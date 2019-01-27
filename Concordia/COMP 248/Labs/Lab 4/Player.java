import java.util.Random;

public class Player {
	private String name;
	private int score;

	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setScore(int newScore) {
		this.score = newScore;
	}

	public boolean equals(Player other) {
		if (this.score != other.score)
			return false;
		if (this.name != other.name)
			return false;

		return true;
	}

	public String toString() {
		return this.name + " : " + this.score;
	}

	public void rollDie() {
		Random rng = new Random();
		int newRoll = rng.nextInt(6) + 1;
		this.score = newRoll;
	}
}
