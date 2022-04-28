package uk.co.jake.scrumpoker.generator;

import java.util.Collection;
import java.util.List;
import uk.co.jake.scrumpoker.domain.Ticket;
import uk.co.jake.scrumpoker.domain.Developer;
import uk.co.jake.scrumpoker.domain.PokerDeck;
import uk.co.jake.scrumpoker.domain.Vote;

/**
 * Implementations of this interface are responsible for
 * getting votes off developers for a ticket *
 */
public interface VoteGenerator {
	List<Vote> getVotes(Ticket ticket, PokerDeck pokerDeck, Collection<Developer> developers);
}
