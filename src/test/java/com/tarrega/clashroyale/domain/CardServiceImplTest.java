package com.tarrega.clashroyale.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceImplTest {
	@Autowired
	private CardService cardService;

	@Test
	public void getAllCards() throws Exception {
		Set<Card> allCards = cardService.getAllCards();
		log.debug("allCards : {}", allCards);
	}
}