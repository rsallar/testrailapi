package com.github.rsallar.testrailapi.sender;

import java.util.List;

public class TestRun {
	
	public Long projectId;
	public Long suiteId;
	public String name;

	
	public String authUser;
	public String authPwd;
	public String authUrl;
	public List<TestResult> results;
}
