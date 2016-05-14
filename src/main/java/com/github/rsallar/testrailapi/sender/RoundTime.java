package com.github.rsallar.testrailapi.sender;

import java.text.DecimalFormat;

public class RoundTime {
	
	private static final DecimalFormat df = new DecimalFormat("#");
	
	public static String round(Float time){
		return df.format(Math.ceil(time));
	}

}
