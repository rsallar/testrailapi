package com.github.rsallar.testrailapi.parser;

import java.util.Optional;

import com.github.rsallar.testrailapi.sender.TestResult;

public interface Regex {

	Optional<TestResult> getTestResult(String txt);

}
