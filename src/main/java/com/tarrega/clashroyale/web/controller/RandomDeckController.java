package com.tarrega.clashroyale.web.controller;

import com.tarrega.clashroyale.domain.Deck;
import com.tarrega.clashroyale.domain.DeckGenerator;
import com.tarrega.clashroyale.domain.RandomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by vayne on 2016. 10. 2..
 */
@RestController
@RequestMapping("/decks")
public class RandomDeckController {

	@Autowired
	DeckGenerator deckGenerator;

	@RequestMapping
	public Deck randomDeck(@RequestParam(value = "type", required = true, defaultValue = "BALANCED") RandomType randomType
		, @RequestParam(value = "cards", required = false) Set<String> preservedCardNames) {
		if(preservedCardNames != null) {
			return deckGenerator.generateWithPreservedCards(preservedCardNames, randomType);
		} else {
			return deckGenerator.generate(randomType);
		}
	}
}