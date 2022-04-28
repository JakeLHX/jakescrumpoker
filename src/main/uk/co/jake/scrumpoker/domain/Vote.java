package uk.co.jake.scrumpoker.domain;

public class Vote {

	final private Developer developer;
	final private int points;

	public Vote(Developer developer, int points) {
		this.developer = developer;
		this.points = points;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public int getPoints() {
		return points;
	}
}
