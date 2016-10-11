package com.tarrega.clashroyale;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by vayne on 2016. 10. 4..
 */
public class Utils {
	private static Random random = new SecureRandom();

	public static boolean chance(int prob) {
		return random.nextInt(100) >= 100 - prob;
	}
}
