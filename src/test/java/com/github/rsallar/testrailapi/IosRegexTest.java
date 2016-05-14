package com.github.rsallar.testrailapi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rsallar.testrailapi.parser.IosRegex;
import com.github.rsallar.testrailapi.sender.Status;
import com.github.rsallar.testrailapi.sender.TestResult;

public class IosRegexTest {
	
	@Test
	public void failure(){
		String txt="Test Case '-[UI_ItemDetailTest testDeleteItem_cancel]' failed (11.553 seconds).";
		IosRegex regex = new IosRegex();
		TestResult result = regex.getTestResult(txt).get();
		
		assertEquals(new Long(1), result.caseId);
		assertEquals("UI_ItemDetailTest", result.testCase);
		assertEquals("testDeleteItem_cancel", result.testMethod);
		assertEquals("testCase: UI_ItemDetailTest method: testDeleteItem_cancel",   result.comment);
		assertEquals("11.553 seconds",   result.elapsedTime + " " + result.elapsedUnit);
		assertEquals(Status.FAILED,   result.status);
		
	}
	
	@Test
	public void success(){
		String txt="Test Case '-[UN_ImageMetadataUtilsTest testGetTheNewImages4SavedItem_withPreviousImages_NoImages]' passed (1.000 seconds).";
		IosRegex regex = new IosRegex();
		TestResult result = regex.getTestResult(txt).get();
		
		assertEquals(new Long(1), result.caseId);
		assertEquals("UN_ImageMetadataUtilsTest", result.testCase);
		assertEquals("testGetTheNewImages4SavedItem_withPreviousImages_NoImages", result.testMethod);
		assertEquals("testCase: UN_ImageMetadataUtilsTest method: testGetTheNewImages4SavedItem_withPreviousImages_NoImages",   result.comment);
		assertEquals("1.0 seconds",   result.elapsedTime + " " + result.elapsedUnit);
		assertEquals(Status.PASSED,   result.status);
		
	}
	
	@Test
	public void no_match(){
		
		String txt="Test Case '-[UN_ImageMetadataUtilsTest testGetTheNewImages4SavedItem_withPreviousImages_NoImages]' anotherword passed (1.0 seconds).";
		IosRegex regex = new IosRegex();
		boolean present = regex.getTestResult(txt).isPresent();
		assertFalse(present);
	}

}
