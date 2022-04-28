package uk.co.jake.scrumpoker;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.jake.scrumpoker.domain.Developer;
import uk.co.jake.scrumpoker.domain.PokerDeck;
import uk.co.jake.scrumpoker.domain.PokerDeckCard;
import uk.co.jake.scrumpoker.domain.Ticket;
import uk.co.jake.scrumpoker.generator.RandomVoteGenerator;
import uk.co.jake.scrumpoker.simulator.Poker;

public class Main {

	public static void main(String[] args) throws IOException {

		List<Developer> developers = initDevelopers();

		Poker poker = initPoker(7, developers);

		List<Ticket> tickets = initTickets();

		poker.play(tickets);

		generateJson(tickets);
	}

	private static Poker initPoker(int numberOfCards, List<Developer> developers) {

		List<Integer> values = CardValues(numberOfCards);

		List<PokerDeckCard> cards = new ArrayList<>();

		for (Integer val : values) {
			PokerDeckCard card = new PokerDeckCard(val);
			cards.add(card);
		};

		PokerDeck deck = new PokerDeck(cards);

		Poker poker = new Poker();

		poker.setDeck(deck);
		poker.setDevs(developers);

		poker.setVoteGenerator(new RandomVoteGenerator());

		return poker;
	}

	private static List<Developer> initDevelopers() {
		return new ArrayList<>(List.of(
				new Developer("Jake"),
				new Developer("Mike"),
				new Developer("Renan"),
				new Developer("Niamh"))
		);
	}

	private static List<Ticket> initTickets() {
		Date currentDate = new Date();
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		return new ArrayList<>(List.of(
				new Ticket("Task 1", "Jake", "Task", "Critical"),
				new Ticket("Task 2", "Renan", "Task", "Major"),
				new Ticket("Bug 1", "Renan", "Bug", "Critical", Date.from(localDateTime.plusDays(14).atZone(ZoneId.systemDefault()).toInstant())),
				new Ticket("Bug 2", "Niamh", "Bug", "Major", Date.from(localDateTime.plusDays(24).atZone(ZoneId.systemDefault()).toInstant())),
				new Ticket("Feature 1", "Renan", "Feature", "Critical", Date.from(localDateTime.plusDays(1).atZone(ZoneId.systemDefault()).toInstant())),
				new Ticket("Feature 2", "Mike", "Feature", "Minor", Date.from(localDateTime.plusDays(80).atZone(ZoneId.systemDefault()).toInstant())),
				new Ticket("Feature 3", "Mike", "Feature", "Critical", Date.from(localDateTime.plusDays(23).atZone(ZoneId.systemDefault()).toInstant())),
				new Ticket("Feature 4", "Jake", "Feature", "Minor", Date.from(localDateTime.plusDays(35).atZone(ZoneId.systemDefault()).toInstant())),
				new Ticket("Task 3", "Mike", "Task", "Minor"),
				new Ticket("Task 4", "Niamh", "Task", "Major")
				)
		);
	}

	private static void generateJson(List<Ticket> tickets) throws IOException {
		JSONArray jsArray = new JSONArray(tickets);
		FileWriter file = new FileWriter("output.json");
		file.write(String.valueOf(jsArray));
		file.close();
	}

	public static List<Integer> CardValues(Integer args) {
		List<Integer> list = new ArrayList();
		int n = args, firstTerm = 1, secondTerm = 2;

		for (int i = 1; i <= n; ++i) {
			list.add(firstTerm);

			// compute the next term
			int nextTerm = firstTerm + secondTerm;
			firstTerm = secondTerm;
			secondTerm = nextTerm;
		}
		return list;
	}
}