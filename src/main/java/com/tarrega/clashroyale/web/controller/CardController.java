package com.tarrega.clashroyale.web.controller;

import com.tarrega.clashroyale.domain.Card;
import com.tarrega.clashroyale.domain.CardService;
import com.tarrega.clashroyale.domain.CardType;
import com.tarrega.clashroyale.domain.Rarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vayne on 2016. 10. 2..
 */
@RestController
@RequestMapping("/cards")
public class CardController {

	@Autowired
	CardService cardService;

	@RequestMapping
	public Set<Card> allCards() {
		return cardService.getAllCards();
	}

	@RequestMapping("/name")
	public List<String> allCardNames() {
		return cardService.getAllCards().stream().map(card -> card.getName()).sorted().collect(Collectors.toList());
	}

	@RequestMapping("/type")
	public Map<CardType, List<Card>> allCardsByType() {
		return cardService.getAllCards().stream().collect(Collectors.groupingBy(Card::getType));
	}

	@RequestMapping("/rarity")
	public Map<Rarity, List<Card>> allCardsByRarity() {
		return cardService.getAllCards().stream().collect(Collectors.groupingBy(Card::getRarity));
	}
}