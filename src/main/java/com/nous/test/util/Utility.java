package com.nous.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public String convertDate(String dateString) {

		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");

		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return format1.format(date);
	}
}
