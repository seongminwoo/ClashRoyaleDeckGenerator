package com.tarrega.clashroyale.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Getter
@Builder
public class Card {
	@NonNull String id;
	@NonNull CardType type; // 카드 타입
	@NonNull Rarity rarity; // 카드 희귀도
	@NonNull Target target; // 공격 대상
	@NonNull AttackGrade attackGrade; // 카드 공격성 등급
	String name;
	String nameKr;
	int cost;
	boolean range; // 원거리 공격 유무
	boolean areaDamage; // 범위 공격 유무
	Card partnerCard; // 조합시 잘 맞는 카드. Deck 카드 선택시 가점을 준다.

	public static class CardBuilder {
		private String id = UUID.randomUUID().toString();
		private CardType type = CardType.TROOP;
		private Rarity rarity = Rarity.COMMON;
		private Target target = Target.NONE;
		private AttackGrade attackGrade = AttackGrade.MEDIUM;
	}

	@Override
	public String toString() {
		return name;
	}

	@JsonValue
	public String toJson() {
		return String.format("%s(%s)", name, nameKr);
	}
}