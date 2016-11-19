package com.tarrega.clashroyale.domain;

import com.tarrega.clashroyale.Constants;
import com.tarrega.clashroyale.Utils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Service
public class CardServiceImpl implements CardService {

	@Override
	public Set<Card> getAllCards() {
		return Constants.cards;
	}

	private List<Card> getTopRankerCards() {
		return Constants.topRankerCards.stream().map(this::getCard).collect(Collectors.toList());
	}

	@Override
	public Card getCardFromRankerCards(Set<Card> selectedCards, Predicate<Card> predicate) {
		Set<Card> sources = new HashSet<>(getTopRankerCards());
		return getCard(selectedCards, predicate, sources);
	}

	@Override
	public Card getCard(Set<Card> selectedCards, Predicate<Card> predicate, boolean considerTopRanker) {
		if(considerTopRanker) {
			if(Utils.chance(70)) { // Ranker Card 가중치 줌
				return getCardFromRankerCards(selectedCards, predicate);
			} else {
				return getCardFromAllCards(selectedCards, predicate);
			}
		} else {
			return getCardFromAllCards(selectedCards, predicate);
		}
	}

	public Card getCardFromAllCards(Set<Card> selectedCards, Predicate<Card> predicate) {
		return getCard(selectedCards, predicate, getAllCards());
	}

	private Card getCard(Set<Card> selectedCards, Predicate<Card> predicate, Set<Card> sources) {
		Set<Card> targetCards = new HashSet<>(sources);
		targetCards.removeAll(selectedCards);
		List<Card> cards = new ArrayList<>(targetCards);
		Collections.shuffle(cards);
		return cards.stream().filter(predicate).findAny().orElseGet(() -> getCard(selectedCards, predicate, getAllCards()));
	}

	@Override
	public Card getCard(String cardName) {
		return getAllCards().stream().filter(card -> card.getName().equalsIgnoreCase(cardName)).findAny().get();
	}

	@Override
	public boolean isExist(String cardName) {
		return getAllCards().stream().filter(card -> card.getName().equals(cardName)).findFirst().isPresent();
	}
}