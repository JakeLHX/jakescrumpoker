package uk.co.jake.scrumpoker.simulator;

import java.util.*;

import uk.co.jake.scrumpoker.generator.VoteGenerator;
import uk.co.jake.scrumpoker.domain.Developer;
import uk.co.jake.scrumpoker.domain.PokerDeck;
import uk.co.jake.scrumpoker.domain.Ticket;
import uk.co.jake.scrumpoker.domain.Vote;

public class Poker {

	private VoteGenerator voteGenerator;
	private PokerDeck deck;
	private List<Developer> devs;

	public List<Ticket> play(List<Ticket> tickets) {
		// TODO: Q3. Simulate a scrum poker session
		for(Ticket tik : tickets) {
			vote(tik);
		}
		return tickets;
	}

	// TODO: Q2. Create a method to simulate voting for a single ticket
	public Ticket vote(Ticket tik) {
		//Initially wanted to use a set but removing Dups doesn't help me when averaging
		List<Integer> points = new ArrayList<>() ;
		Integer x = 0;
		do {
			points.clear();
			List<Vote> votes = voteGenerator.getVotes(tik, deck, devs);
			for (Vote vote : votes) {
				points.add(vote.getPoints());
			}
			Set<Integer> pointSet = new HashSet<>(points);
			if (pointSet.size() == 1) {
				for (Integer p : pointSet) {
						tik.setStoryPoints(p);
				}
				//Tested with 100 iterations to make sure it did hit this
				System.out.println("Golden unicorn of agreement on iteration" + x);
				x=1000000;
				return tik;
			}
			x++;
		} while (x<4);
		//Average is calculated
		Integer average = (int) Math.round(points.stream().mapToDouble(d -> d).average().getAsDouble());
		Integer closest = deck.getCards().stream().min(Comparator.comparingInt(c -> Math.abs(c.getValue() - average))).get().getValue();

		tik.setStoryPoints(closest);
		return tik;
	}

	public void setVoteGenerator(VoteGenerator voteGenerator) {
		this.voteGenerator = voteGenerator;
	}

	public void setDeck(PokerDeck deck) {
		this.deck = deck;
	}

	public void setDevs(List<Developer> devs) {
		this.devs = devs;
	}
}