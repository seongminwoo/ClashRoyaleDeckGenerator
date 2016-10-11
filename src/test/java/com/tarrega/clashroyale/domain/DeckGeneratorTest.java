package com.tarrega.clashroyale.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckGeneratorTest {
	@Autowired
	private DeckGenerator deckGenerator;

	@Test
	public void generateBalancedDeck() {
		Deck deck = deckGenerator.generate(RandomType.BALANCED);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
	}

	@Test
	public void generateAttackDeck() {
		Deck deck = deckGenerator.generate(RandomType.ATTACK);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
	}

	@Test
	public void generateDefenseDeck() {
		Deck deck = deckGenerator.generate(RandomType.DEFENSE);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
		Assert.assertTrue(deck.getCards().stream().allMatch(card -> card.getAttackGrade() != AttackGrade.HIGH));
	}

	@Test
	public void generateLowCostDeck() {
		Deck deck = deckGenerator.generate(RandomType.LOW_COST);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
	}

	@Test
	public void generateRangeDeck() {
		Deck deck = deckGenerator.generate(RandomType.RANGE);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
		Assert.assertTrue(deck.getCards().stream().allMatch(card -> card.type == CardType.TROOP?card.range:true));
	}

	@Test
	public void generateMeleeDeck() {
		Deck deck = deckGenerator.generate(RandomType.MELEE);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
		Assert.assertTrue(deck.getCards().stream().allMatch(card -> card.type == CardType.TROOP?!card.range:true));
	}

	@Test
	public void generateAreaDamageDeck() {
		Deck deck = deckGenerator.generate(RandomType.AREA);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
		Assert.assertTrue(deck.getCards().stream().allMatch(card -> card.type == CardType.TROOP?card.areaDamage:true));
	}

	@Test
	public void generateSpellDeck() {
		Deck deck = deckGenerator.generate(RandomType.SPELL);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
		Assert.assertTrue(deck.getCards().stream().allMatch(card -> card.getType() == CardType.SPELL));
	}

	@Test
	public void generateBuildingDeck() {
		Deck deck = deckGenerator.generate(RandomType.BUILDING);
		log.debug("deck : {}", deck);
		Assert.assertNotNull(deck);
		Assert.assertTrue(deck.getCards().stream().allMatch(card -> card.getType() == CardType.BUILDING));
	}
}