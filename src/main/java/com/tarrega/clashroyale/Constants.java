package com.tarrega.clashroyale;

import com.tarrega.clashroyale.domain.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vayne on 2016. 10. 2..
 */
public class Constants {
	public static final Set<Card> cards = new HashSet<>();
	// 2016.11
	// top 25 cards
	public static final List<String> topRankerCards = Arrays.asList(
		"the log", "mega minion", "zap", "archers", "lightning", "ice spirit", "furnace", "tombstone", "Skeleton Army", "inferno tower",
		"miner", "hog rider", "princess", "minion horde", "mini P.E.K.K.A", "Golem", "arrows", "fireball", "musketeer", "ice golem",
		"minions", "goblin barrel", "giant", "Bowler", "royal giant"
	);


	// 참고할만한 사이트들
	// https://namu.wiki/w/%ED%81%B4%EB%9E%98%EC%8B%9C%20%EB%A1%9C%EC%96%84/%EC%B9%B4%EB%93%9C
	// http://m.inven.co.kr/clashroyale/card/
	// http://cr.gamekiji.com/ko/unit/unitList.jsp#cs=&rarity=&type=&tg=&spd=&at=&txt=&orType=TAS&or=1&view=1&compareCard01=&compareCard02=
	static {
		/* Troop Cards */
		// COMMON
		cards.add(Card.builder().name("Skeletons").nameKr("해골 병사").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.LOW)
			.cost(1).build());
		cards.add(Card.builder().name("knight").nameKr("기사").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.LOW)
			.cost(3).build());
		cards.add(Card.builder().name("goblins").nameKr("고블린").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM)
			.cost(2).build());
		cards.add(Card.builder().name("royal giant").nameKr("로얄 자이언트").rarity(Rarity.COMMON).target(Target.BUILDINGS).attackGrade(AttackGrade.HIGH).range(true)
			.cost(6).build());
		cards.add(Card.builder().name("barbarians").nameKr("바바리안").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM)
			.cost(5).build());
		cards.add(Card.builder().name("minions").nameKr("미니언").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).range(true)
			.cost(3).build());
		cards.add(Card.builder().name("minion horde").nameKr("미니언 패거리").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).range(true)
			.cost(5).build());
		cards.add(Card.builder().name("bomber").nameKr("폭탄병").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM).areaDamage(true).range(true)
			.cost(3).build());
		cards.add(Card.builder().name("fire spirits").nameKr("파이어 스피릿").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).areaDamage(true).range(true)
			.cost(2).build());
		cards.add(Card.builder().name("ice spirit").nameKr("얼음 정령").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).areaDamage(true).range(true)
			.cost(1).build());
		cards.add(Card.builder().name("archers").nameKr("아처").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).range(true)
			.cost(3).build());


		// RARE
		cards.add(Card.builder().name("hog rider").nameKr("호그라이더").rarity(Rarity.RARE).target(Target.BUILDINGS).attackGrade(AttackGrade.HIGH).partnerCard("goblins")
			.cost(4).build());
		cards.add(Card.builder().name("giant").nameKr("자이언트").rarity(Rarity.RARE).target(Target.BUILDINGS).attackGrade(AttackGrade.HIGH)
			.cost(5).build());
		cards.add(Card.builder().name("musketeer").nameKr("머스킷").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).range(true)
			.cost(4).build());
		cards.add(Card.builder().name("valkyrie").nameKr("발키리").rarity(Rarity.RARE).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM).areaDamage(true)
			.cost(4).build());
		cards.add(Card.builder().name("mini P.E.K.K.A").nameKr("미니 P.E.K.K.A").rarity(Rarity.RARE).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM)
			.cost(4).build());
		cards.add(Card.builder().name("wizard").nameKr("마법사").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).areaDamage(true).range(true)
			.cost(5).build());
		cards.add(Card.builder().name("three musketeers").nameKr("삼총사").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.HIGH).range(true)
			.cost(9).build());
		cards.add(Card.builder().name("mega minion").nameKr("메가 미니언").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).range(true)
			.cost(3).build());
		cards.add(Card.builder().name("ice golem").nameKr("얼음 골렘").rarity(Rarity.RARE).target(Target.BUILDINGS).attackGrade(AttackGrade.LOW).areaDamage(true)
			.cost(2).build());

		// EPIC
		cards.add(Card.builder().name("prince").nameKr("프린스").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(5).build());
		cards.add(Card.builder().name("Baby Dragon").nameKr("베이비 드래곤").rarity(Rarity.EPIC).target(Target.AIR_GROUND).attackGrade(
			AttackGrade.MEDIUM).range(true)
			.cost(4).build());
		cards.add(Card.builder().name("Skeleton Army").nameKr("해골 군대").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.LOW)
			.cost(4).build());
		cards.add(Card.builder().name("Witch").nameKr("마녀").rarity(Rarity.EPIC).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).range(true)
			.cost(5).build());
		cards.add(Card.builder().name("Giant Skeleton").nameKr("자이언트 해골").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(6).build());
		cards.add(Card.builder().name("Balloon").nameKr("해골 비행선").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH).partnerCard("giant")
			.cost(5).build());
		cards.add(Card.builder().name("P.E.K.K.A").nameKr("P.E.K.K.A").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(7).build());
		cards.add(Card.builder().name("Golem").nameKr("골렘").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(8).build());
		cards.add(Card.builder().name("Dark Prince").nameKr("다크 프린스").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM)
			.cost(4).build());
		cards.add(Card.builder().name("Guards").nameKr("가드").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.LOW)
			.cost(3).build());
		cards.add(Card.builder().name("Bowler").nameKr("볼러").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM).range(true)
			.cost(5).build());

		// LEGENDARY
		cards.add(Card.builder().name("ice wizard").nameKr("얼음 마법사").rarity(Rarity.LEGENDARY).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).areaDamage(true).range(true)
			.cost(3).build());
		cards.add(Card.builder().name("princess").nameKr("프린세스").rarity(Rarity.LEGENDARY).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).areaDamage(true).range(true)
			.cost(3).build());
		cards.add(Card.builder().name("miner").nameKr("광부").rarity(Rarity.LEGENDARY).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(3).build());
		cards.add(Card.builder().name("lumberjack").nameKr("나무꾼").rarity(Rarity.LEGENDARY).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM)
			.cost(4).build());
		cards.add(Card.builder().name("sparky").nameKr("스파키").rarity(Rarity.LEGENDARY).target(Target.GROUND).attackGrade(AttackGrade.HIGH).areaDamage(true).range(true)
			.cost(6).build());
		cards.add(Card.builder().name("lava hound").nameKr("라바 하운드").rarity(Rarity.LEGENDARY).target(Target.BUILDINGS).attackGrade(AttackGrade.HIGH)
			.cost(7).build());


		/* Spell Cards */
		cards.add(Card.builder().name("zap").nameKr("감전 마법").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).areaDamage(true)
			.cost(2).type(CardType.SPELL).build());
		cards.add(Card.builder().name("arrows").nameKr("화살").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW).areaDamage(true)
			.cost(3).type(CardType.SPELL).build());
		cards.add(Card.builder().name("fireball").nameKr("파이어 볼").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).areaDamage(true)
			.cost(4).type(CardType.SPELL).build());
		cards.add(Card.builder().name("rocket").nameKr("로켓").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.HIGH).areaDamage(true)
			.cost(6).type(CardType.SPELL).build());
		cards.add(Card.builder().name("poison").nameKr("독 마법").rarity(Rarity.EPIC).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).areaDamage(true)
			.cost(4).type(CardType.SPELL).build());
		cards.add(Card.builder().name("lightning").nameKr("번개 마법").rarity(Rarity.EPIC).target(Target.AIR_GROUND).attackGrade(AttackGrade.HIGH)
			.cost(6).type(CardType.SPELL).build());
		cards.add(Card.builder().name("goblin barrel").nameKr("고블린 통").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(3).type(CardType.SPELL).build());
		cards.add(Card.builder().name("rage").nameKr("분노 마법").rarity(Rarity.EPIC).target(Target.NONE).attackGrade(AttackGrade.HIGH)
			.cost(2).type(CardType.SPELL).build());
		cards.add(Card.builder().name("freeze").nameKr("얼음 마법").rarity(Rarity.EPIC).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM)
			.cost(4).type(CardType.SPELL).build());
		cards.add(Card.builder().name("mirror").nameKr("반사경").rarity(Rarity.EPIC).target(Target.NONE).attackGrade(AttackGrade.HIGH)
			.cost(4).type(CardType.SPELL).build());
		cards.add(Card.builder().name("the log").nameKr("통나무").rarity(Rarity.LEGENDARY).target(Target.GROUND).attackGrade(AttackGrade.LOW).areaDamage(true)
			.cost(2).type(CardType.SPELL).build());

		/* Building Cards */
		cards.add(Card.builder().name("cannon").nameKr("대포").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.LOW)
			.cost(3).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("tesla").nameKr("뇌전탑").rarity(Rarity.COMMON).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW)
			.cost(4).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("Mortar").nameKr("박격포").rarity(Rarity.COMMON).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(4).type(CardType.BUILDING).build());

		cards.add(Card.builder().name("elixir collector").nameKr("엘릭서 정제소").rarity(Rarity.RARE).target(Target.NONE).attackGrade(AttackGrade.ZERO)
			.cost(5).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("goblin hut").nameKr("고블린 오두막").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).partnerCard("barbarian hut")
			.cost(5).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("tombstone").nameKr("해골 무덤").rarity(Rarity.RARE).target(Target.GROUND).attackGrade(AttackGrade.LOW)
			.cost(3).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("bomb tower").nameKr("폭탄 타워").rarity(Rarity.RARE).target(Target.GROUND).attackGrade(AttackGrade.LOW).areaDamage(true)
			.cost(5).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("barbarian hut").nameKr("바바리안 오두막").rarity(Rarity.RARE).target(Target.GROUND).attackGrade(AttackGrade.MEDIUM).partnerCard("goblin hut")
			.cost(7).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("inferno tower").nameKr("인페르노 타워").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.LOW)
			.cost(5).type(CardType.BUILDING).build());
		cards.add(Card.builder().name("furnace").nameKr("용광로").rarity(Rarity.RARE).target(Target.AIR_GROUND).attackGrade(AttackGrade.MEDIUM).areaDamage(true)
			.cost(4).type(CardType.BUILDING).build());

		cards.add(Card.builder().name("x-bow").nameKr("대형 석궁").rarity(Rarity.EPIC).target(Target.GROUND).attackGrade(AttackGrade.HIGH)
			.cost(6).type(CardType.BUILDING).build());

	}
}
