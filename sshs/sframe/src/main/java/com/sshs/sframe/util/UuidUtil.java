package com.sshs.sframe.util;

import java.util.UUID;

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static String get36UUID() {
		String uuid = UUID.randomUUID().toString().trim();
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}
