package com.tarrega.clashroyale.domain;

import java.util.List;

/**
 * Created by vayne on 2016. 9. 25..
 */
public interface CardSelector {
	List<Card> cards(Deck deck);
	List<Card> cards(List<Card> preservedCards, Deck deck);

}
