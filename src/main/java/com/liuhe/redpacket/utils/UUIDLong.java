/**
 * 
 */
package com.liuhe.redpacket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author rsh
 * 
 */
public class UUIDLong {
	private static SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

	public static long longUUID(){
		return UUID.randomUUID().getMostSignificantBits();
	}

	public static long absLongUUID() {
		long r;
		do {
			r = longUUID();
		} while (r <= 0L);
		return r;
	}

	public static int intUUID() {
		String date = sdf.format(new Date());
		Random rand = new Random();
		String r = (rand.nextInt(899)+100) + "";
		String uuid = date + r;
		return Integer.parseInt(uuid);
	}
	
	public static String generateUUid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDLong.absLongUUID());
	}
}
