package com.tarrega.clashroyale.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Slf4j
public class DeckTest {
	@Test
	public void buildCard() {
		Set<Card> cards = new HashSet<>();
		IntStream.range(0, 8).forEach(value -> cards.add(Card.builder().build()));
		Deck deck = Deck.builder().cards(cards).build();
		log.debug("deck : {}", deck);
		log.debug("deck cards number : {}", deck.getCards().size());
		Assert.assertNotNull(deck);
		Assert.assertThat("card number should be 8", 8, is(deck.getCards().size()));
	}
}