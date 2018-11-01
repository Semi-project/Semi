package util;

import java.util.Date;

public class test {
	public static void main(String[] args) {
		long unixTime = Long.parseLong("932545204") * 1000;
		Date date = new Date(unixTime);

		System.out.println(date.toString());
	}
}
