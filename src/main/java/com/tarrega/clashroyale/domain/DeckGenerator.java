package com.tarrega.clashroyale.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Component
public class DeckGenerator {

	@Autowired
	CardSelector cardSelector;

	@Autowired
	CardService cardService;

	public Deck generate(RandomType randomType) {
		Deck deck = Deck.builder().randomType(randomType).build();
		deck.setCards(cardSelector.cards(deck));
		return deck;
	}

	public Deck generateWithPreservedCards(Set<String> preservedCardNames, RandomType randomType) {
		Deck deck = Deck.builder().randomType(randomType).build();
		Set<Card> preservedCards = preservedCardNames.stream().filter(cardService::isExist).map(cardService::getCard).collect(Collectors.toSet());
		Set<Card> cards = cardSelector.cards(new LinkedHashSet(preservedCards), deck);
		deck.setCards(cards);
		return deck;
	}
}