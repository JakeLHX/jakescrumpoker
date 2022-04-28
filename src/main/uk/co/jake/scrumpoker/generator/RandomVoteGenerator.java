package uk.co.jake.scrumpoker.generator;

import java.util.*;

import uk.co.jake.scrumpoker.domain.Vote;
import uk.co.jake.scrumpoker.domain.*;

public class RandomVoteGenerator implements VoteGenerator {
  
	@Override
	public List<Vote> getVotes(Ticket ticket, PokerDeck pokerDeck, Collection<Developer> developers) {
		// TODO: Q2. Generate random votes for each developer
		List<Vote> votes = new ArrayList<>();
		Random rand = new Random();
		List<PokerDeckCard> cards = pokerDeck.getCards();
		String tikOwn = ticket.getOwner();
		for (Developer dev : developers) {
			if(dev.getName() != tikOwn) {
				Integer points = cards.get(rand.nextInt(cards.size())).getValue();
				Vote vote = new Vote(dev, points);
				votes.add(vote);
			}
		}
		return votes;
	}
}