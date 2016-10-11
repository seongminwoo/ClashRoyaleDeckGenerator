package com.tarrega.clashroyale.domain;

import com.tarrega.clashroyale.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Component
public class RandomCardSelector implements CardSelector {
	public static final int DECK_CARD_NUMBER = 8;
	public static final double COST_AVERAGE_LIMIT = 4.0;
	public static final int LOW_COST = 3;
	public static final int LOW_COST_SPELL = 4;
	public static final int LOW_COST_HIGH_ATTACK = 5;

	final Predicate<Card> spell = predicateCard -> predicateCard.getType() == CardType.SPELL;
	final Predicate<Card> building = predicateCard -> predicateCard.getType() == CardType.BUILDING;
	final Predicate<Card> troop = predicateCard -> predicateCard.getType() == CardType.TROOP;
	final Predicate<Card> attackHigh = predicateCard -> predicateCard.getAttackGrade() == AttackGrade.HIGH;
	final Predicate<Card> attackMedium = predicateCard -> predicateCard.getAttackGrade() == AttackGrade.MEDIUM;
	final Predicate<Card> attackLow = predicateCard -> predicateCard.getAttackGrade() == AttackGrade.LOW;
	final Predicate<Card> areaDamage = predicateCard -> predicateCard.areaDamage;
	final Predicate<Card> range = predicateCard -> predicateCard.range;
	final Predicate<Card> melee = range.negate();
	final Predicate<Card> airTarget = predicateCard -> predicateCard.target == Target.AIR_GROUND;
	final Predicate<Card> lowCost = predicateCard -> predicateCard.getCost() <= LOW_COST;
	final Predicate<Card> lowCostForSpell = predicateCard -> predicateCard.getCost() <= LOW_COST_SPELL;
	final Predicate<Card> lowCostForHighAttack = predicateCard -> predicateCard.getCost() <= LOW_COST_HIGH_ATTACK;
	final Predicate<Card> rarityEpic = predicateCard -> predicateCard.rarity == Rarity.EPIC;

	@Autowired
	private CardService cardService;

	@Override
	public Set<Card> cards(Set<Card> cards, Deck deck) {
		deck.setCards(cards);
		switch (deck.randomType) {
			case BALANCED:
				while(cards.size() < DECK_CARD_NUMBER) {
					Card selected = selectBalancedRandomCard(cards, deck);
					cards.add(selected);
					if (selected.getPartnerCard() != null) {
						cards.add(cardService.getCard(selected.getPartnerCard()));
					}
					deck.setCards(cards);
				}
				break;
			case RANKERCARD:
				IntStream.range(0, DECK_CARD_NUMBER - cards.size()).forEach(value -> {
					Card selected = selectBalancedRandomCardFromRankerCards(cards, deck);
					cards.add(selected);
					deck.setCards(cards);
				});
				break;
			case SPELL: // all spell
				IntStream.range(0, DECK_CARD_NUMBER - cards.size()).forEach(value -> cards.add(cardService.getCard(cards, spell, false)));
				break;
			case BUILDING: // all building
				IntStream.range(0, DECK_CARD_NUMBER - cards.size()).forEach(value -> cards.add(cardService.getCard(cards, building, false)));
				break;
			default:
				IntStream.range(0, DECK_CARD_NUMBER - cards.size()).forEach(value -> {
					Card selected = selectConceptRandomCard(cards, deck);
					cards.add(selected);
					deck.setCards(cards);
				});
		}

		return cards;	}

	@Override
	public Set<Card> cards(Deck deck) {
		Set<Card> selectedCards = new LinkedHashSet<>();
		return cards(selectedCards, deck);
	}

	private Card selectConceptRandomCard(Set<Card> selectedCards, Deck deck) {
		Predicate<Card> condition = makeConceptSelectCondition(selectedCards, deck);
		Card card = cardService.getCard(selectedCards, condition, false);
		return card;
	}

	private Card selectBalancedRandomCard(Set<Card> cards, Deck deck) {
		Predicate<Card> condition = makeBalancedSelectCondition(cards, deck);
		Card card = cardService.getCard(cards, condition, true);
		return card;
	}

	private Card selectBalancedRandomCardFromRankerCards(Set<Card> cards, Deck deck) {
		Predicate<Card> condition = makeBalancedSelectCondition(cards, deck);
		Card card = cardService.getCardFromRankerCards(cards, condition);
		return card;
	}

	private Predicate<Card> makeConceptSelectCondition(Set<Card> cards, Deck deck) {
		// 0. Cost Average < 4.0
		// 1. Spell Card 2 or Spell Card 1, Target AIR_GROUND 1
		// 2. Concept!!

		Predicate<Card> condition;

		if(!isTwoSpellOrOneSpellOneAirTargetTroop(deck)) { // spell or air card
			// spell 카드에 가중치를 부여한다.
			if (Utils.chance(90)) {
				condition = spell;
			} else {
				condition = airTarget;
			}

			if(deck.randomType == RandomType.LOW_COST) {
				condition = condition.and(lowCostForSpell);
			} else if(deck.randomType == RandomType.DEFENSE) {
				condition = condition.and(attackHigh.negate());
			} else if(deck.randomType == RandomType.ATTACK) {
				condition = condition.and(attackLow.negate());
			} else if(deck.randomType == RandomType.AREA) {
				condition = condition.and(areaDamage);
			}
		} else { // troop card
			switch (deck.randomType) {
				case MELEE:
					condition = troop.and(melee);
					break;
				case RANGE:
					condition = troop.and(range);
					break;
				case AREA:
					condition = troop.and(areaDamage);
					break;
				case ATTACK:
					condition = troop.and(attackHigh.or(attackMedium));
					break;
				case DEFENSE:
					if(Utils.chance(90)) { // 80%
						condition = troop.and(attackLow.or(attackMedium));
					} else {
						condition = building.and(attackLow);
					}
					break;
				case LOW_COST:
					if(cards.stream().filter(card -> card.getAttackGrade() == AttackGrade.HIGH).count() < 1) {
						condition = troop.and(attackHigh).and(lowCostForHighAttack);
					} else {
						if(Utils.chance(90)) { // 90%
							condition = troop.and(lowCost);
						} else {
							condition = troop;
						}
					}
					break;
				default:
					if(cards.stream().filter(card -> card.getAttackGrade() == AttackGrade.HIGH).count() < 1) {
						condition = troop.and(attackHigh);
					} else {
						condition = troop.and(attackMedium.or(attackLow));
					}

				// Epic 카드는 우선순위를 낮춘다
				if(Utils.chance(30)) { // 30%
					condition = condition.and(rarityEpic);
				} else { // 70%
					condition = condition.and(rarityEpic.negate());
				}
			}

		}

		if(deck.getCostAverage() > COST_AVERAGE_LIMIT) {
			condition = condition.and(lowCost);
		}

		return condition;
	}

	private Predicate<Card> makeBalancedSelectCondition(Set<Card> cards, Deck deck) {
		// 0. Cost Average < 4.0
		// 1. Spell Card 2 or Spell Card 1, Target AIR_GROUND 1
		// 2. Building Card 1 or Attack Low Card 1
		// 3. Troop Card 5(Attack High 1, Medium&Low 4)
		// 4. Area attack 1

		Predicate<Card> condition;

		if(!isTwoSpellOrOneSpellOneAirTargetTroop(deck)) { // spell or air card
			// spell 카드에 가중치를 부여한다.
			if (Utils.chance(90)) {
				condition = spell;
			} else {
				condition = airTarget;
			}
		} else if(deck.getBuildingCnt() < 1) { // building card
			condition = building;
		} else { // troop card
			if(cards.stream().filter(card -> card.getAttackGrade() == AttackGrade.HIGH).count() < 1) {
				condition = troop.and(attackHigh);
			} else {
				condition = troop.and(attackMedium.or(attackLow));
			}

			// if spell or building card have no area damage, select at least one area damage troop card.
			if(!deck.isAreaDamage()) {
				condition = condition.and(areaDamage);
			}
		}

		if(deck.getCostAverage() > 4.0) {
			condition = condition.and(lowCost);
		}

		return condition;
	}

	private boolean isTwoSpellOrOneSpellOneAirTargetTroop(Deck deck) {
		return deck.getSpellCnt() >= 2 || (deck.getSpellCnt() == 1 && deck.isAirTarget());
	}
}