package com.tarrega.clashroyale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by vayne on 2016. 9. 25..
 */
@Getter
@Builder
@EqualsAndHashCode
public class Card {
	@JsonIgnore @NonNull String id;
	@JsonIgnore @NonNull CardType type; // 카드 타입
	@JsonIgnore @NonNull Rarity rarity; // 카드 희귀도
	@JsonIgnore @NonNull Target target; // 공격 대상
	@JsonIgnore @NonNull AttackGrade attackGrade; // 카드 공격성 등급
	String name;
	String nameKr;
	@JsonIgnore int cost;
	@JsonIgnore boolean range; // 원거리 공격 유무
	@JsonIgnore boolean areaDamage; // 범위 공격 유무
	@JsonIgnore String partnerCard; // 조합시 잘 맞는 카드. Deck 카드 선택시 가점을 준다.

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

	/*@JsonValue
	public String toJson() {
		return String.format("%s(%s)", name, nameKr);
	}*/
}