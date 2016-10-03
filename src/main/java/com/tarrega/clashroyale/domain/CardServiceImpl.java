package com.tarrega.clashroyale.domain;

import com.tarrega.clashroyale.Constants;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Service
public class CardServiceImpl implements CardService {

	@Override
	public Set<Card> getAllCards() {
		return Constants.cards;
	}

	@Override
	public Card getCard(Set<Card> selectedCards, Predicate<Card> predicate) {
		Set<Card> targetCards = new HashSet<>(getAllCards());
		targetCards.removeAll(selectedCards);
		List<Card> cards = new ArrayList<>(targetCards);
		Collections.shuffle(cards);
		return cards.stream().filter(predicate).findAny().get();
	}

	@Override
	public Card getCard(String cardName) {
		return getAllCards().stream().filter(card -> card.getName().equals(cardName)).findFirst().get();
	}

	@Override
	public boolean isExist(String cardName) {
		return getAllCards().stream().filter(card -> card.getName().equals(cardName)).findFirst().isPresent();
	}
}