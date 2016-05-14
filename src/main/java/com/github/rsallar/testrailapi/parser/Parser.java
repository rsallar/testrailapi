package com.github.rsallar.testrailapi.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.rsallar.testrailapi.sender.TestResult;

public class Parser {
	
	public Regex regex;
	
	public void setRegex(Regex regex){
		this.regex = regex;
	}
	

	public List<TestResult> parse(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		List<TestResult> results = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			
			Optional<TestResult> result = regex.getTestResult(line);
			if(result.isPresent()){
				results.add(result.get());
			}
		
		}
		br.close();

		return results;
	}

}
