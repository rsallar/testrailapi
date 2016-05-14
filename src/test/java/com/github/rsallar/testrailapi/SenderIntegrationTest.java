package com.github.rsallar.testrailapi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.github.rsallar.testrailapi.parser.IosRegex;
import com.github.rsallar.testrailapi.parser.Parser;
import com.github.rsallar.testrailapi.parser.Regex;
import com.github.rsallar.testrailapi.sender.Sender;
import com.github.rsallar.testrailapi.sender.TestResult;
import com.github.rsallar.testrailapi.sender.TestRun;

public class SenderIntegrationTest {
	
	private static final Logger logger = LogManager.getLogger(SenderIntegrationTest.class);
	
	@Test
	public void send() throws IOException{
		File resourcesDirectory = new File("src/test/resources");
		File file = new File(resourcesDirectory.getPath()+"/Session-2016-05-13_16_33_05-3uMUTl.log");
		
		Sender sender = new Sender();
		Regex regex = new IosRegex();
		Parser parser = new Parser();
		parser.setRegex(regex);
		List<TestResult> results = parser.parse(file);
		
		if(results.size()>0){
			logger.debug("sending {} results", results.size());
			TestRun testRun = createFakeTestRun();
			testRun.results = results;
			sender.addResultsFrom(testRun);
		}

	}
	
	
	private static TestRun createFakeTestRun() {
		TestRun testRun = new TestRun();
		testRun.projectId=1L;
		testRun.suiteId=2L;
		testRun.name = "[Android] Test Run";
		testRun.authUrl="https://testramon.testrail.net/";
		testRun.authUser = "rsallar@gmail.com";
		testRun.authPwd = "A123456b";
		return testRun;
	}

}