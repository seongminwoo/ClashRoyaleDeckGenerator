package com.tarrega.clashroyale.domain;

import lombok.*;

import java.util.Set;
import java.util.UUID;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Getter
@ToString
@Builder
public class Deck {
	@NonNull String id;
	@NonNull DeckType deckType;
	RandomType randomType;
	@Setter long spellCnt;
	@Setter long buildingCnt;
	@Setter long troopCnt;
	double costAverage;
	Set<Card> cards;
	@Setter boolean airTarget;
	@Setter boolean areaDamage;

	void setCards(Set<Card> cards) {
		if(cards.isEmpty()) {
			return;
		}
		this.cards = cards;
		spellCnt = cards.stream().filter(card -> card.getType() == CardType.SPELL).count();
		buildingCnt = cards.stream().filter(card -> card.getType() == CardType.BUILDING).count();
		troopCnt = cards.stream().filter(card -> card.getType() == CardType.TROOP).count();
		costAverage = cards.stream().mapToInt(card -> card.cost).average().getAsDouble();
		costAverage = Math.round(costAverage * 10) / 10.0;
		airTarget = cards.stream().filter(card -> (card.type == CardType.TROOP && card.target == Target.AIR_GROUND)).findAny().isPresent();
		areaDamage = cards.stream().filter(card -> card.areaDamage).findAny().isPresent();
	}

	public static class DeckBuilder {
		private String id = UUID.randomUUID().toString();
		private DeckType deckType = DeckType.RANDOM;
		private RandomType randomType = RandomType.BALANCED;
	}
}