package com.tarrega.clashroyale.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Slf4j
public class CardTest {
	@Test
	public void buildCard() {
		Card hogrider = Card.builder().name("hogrider").rarity(Rarity.RARE).target(Target.BUILDINGS).attackGrade(AttackGrade.HIGH)
			.cost(4).build();
		log.debug("hogrider : {}", hogrider);
		Assert.assertNotNull(hogrider);
	}
}