package com.github.rsallar.testrailapi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.rsallar.testrailapi.parser.IosRegex;
import com.github.rsallar.testrailapi.parser.Parser;
import com.github.rsallar.testrailapi.sender.TestResult;

public class IosParserTest {
	
	@Test
	public void getAllTests() throws IOException{
		File resourcesDirectory = new File("src/test/resources");
		IosRegex regex = new IosRegex();
		Parser parser = new Parser();
		parser.setRegex(regex);
		File file = new File(resourcesDirectory.getPath()+"/Session-2016-05-13_16_33_05-3uMUTl.log");
		List<TestResult> list = parser.parse(file);
		assertEquals(15, list.size());	
	}
	
	
}
