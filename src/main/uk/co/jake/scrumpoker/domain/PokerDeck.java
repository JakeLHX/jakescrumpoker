package uk.co.jake.scrumpoker.domain;

import java.util.List;

public class PokerDeck {
    private List<PokerDeckCard> Cards;

    public PokerDeck(List<PokerDeckCard> cards) {
        Cards = cards;
    }

    public List<PokerDeckCard> getCards() {
        return Cards;
    }
}
