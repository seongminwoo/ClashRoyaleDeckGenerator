package com.tarrega.clashroyale.domain;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by vayne on 2016. 9. 25..
 */
public interface CardService {
	Set<Card> getAllCards();
	Card getCardFromRankerCards(Set<Card> selectedCards, Predicate<Card> predicate);
	Card getCard(Set<Card> selectedCards, Predicate<Card> predicate, boolean considerTopRanker);
	Card getCard(String cardName);
	boolean isExist(String cardName);
}