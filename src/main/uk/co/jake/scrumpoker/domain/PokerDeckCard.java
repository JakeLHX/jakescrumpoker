package uk.co.jake.scrumpoker.domain;

public class PokerDeckCard {
    private Integer Value;

    public PokerDeckCard(Integer val) {
        Value = val;
    }

    public Integer getValue() {
        return Value;
    }
}
