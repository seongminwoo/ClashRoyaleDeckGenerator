package com.tarrega.clashroyale.web.controller;

import com.tarrega.clashroyale.domain.Deck;
import com.tarrega.clashroyale.domain.DeckGenerator;
import com.tarrega.clashroyale.domain.RandomType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by vayne on 2016. 10. 2..
 */
@RestController
@Slf4j
public class RandomDeckController {

	@Autowired
	DeckGenerator deckGenerator;

	@RequestMapping("/decks")
	public Deck randomDeck(@RequestParam(value = "type", required = true, defaultValue = "BALANCED") RandomType randomType
		, @RequestParam(value = "cards", required = false) Set<String> preservedCardNames) {
		if(preservedCardNames != null) {
			Deck deck = deckGenerator.generateWithPreservedCards(preservedCardNames, randomType);
			log.debug("preservedCardNames : {}, deck : {}", preservedCardNames, deck);
			return deck;
		} else {
			Deck deck = deckGenerator.generate(randomType);
			log.debug("deck : {}", deck);
			return deck;
		}
	}

	@RequestMapping("/randomTypes")
	public RandomType[] randomTypes() {
		return RandomType.values();
	}
}