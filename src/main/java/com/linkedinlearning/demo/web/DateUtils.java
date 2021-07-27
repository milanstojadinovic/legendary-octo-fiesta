package com.linkedinlearning.demo.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	//private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy.");

	public static Date createDateFromString(String dateString) {
		Date date = null;
		if (dateString != null) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				date = new Date();
			}
		} else {
			date = new Date();
		}
		return date;
	}
}
