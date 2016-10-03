package com.tarrega.clashroyale.domain;

import java.util.Set;

/**
 * Created by vayne on 2016. 9. 25..
 */
public interface CardSelector {
	Set<Card> cards(Deck deck);
	Set<Card> cards(Set<Card> preservedCards, Deck deck);

}
