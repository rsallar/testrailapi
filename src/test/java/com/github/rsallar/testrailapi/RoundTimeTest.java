package com.github.rsallar.testrailapi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rsallar.testrailapi.sender.RoundTime;

public class RoundTimeTest {

	@Test
	public void test(){
		
		assertEquals("1", RoundTime.round(0.666F));
		assertEquals("2", RoundTime.round(1.666F));
		assertEquals("1", RoundTime.round(1.00F));
		assertEquals("2", RoundTime.round(1.10F));
	}
}
