package com.github.rsallar.testrailapi.sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.github.rsallar.testrailapi.testrail.APIClient;

public class Sender {
	private static final Logger logger = LogManager.getLogger(Sender.class);
	
	public boolean addResultsFrom(TestRun testRun){
		
		if(!validate(testRun)){
			return false;
		}
		
		APIClient client = new APIClient(testRun.authUrl, testRun.authUser, testRun.authPwd);
		
		
		try {
			Map<String, Object> data1 = generateDataForAddRun(testRun);
			JSONObject result1 = (JSONObject) client.sendPost("add_run/"+testRun.projectId, data1);	
			Long runId = (Long) result1.get("id");
			
			
			Map<String, Object> data2 = generateDataForAddResults(testRun.results);		
			JSONArray result2 = (JSONArray) client.sendPost("add_results_for_cases/"+runId, data2);
			for(Object json: result2){
				logger.debug("{}", json.toString());
				
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	
	}

	

	private boolean validate(TestRun testRun) {
		
		if(testRun.results.size()<=0){
			return false;
		}
		
		
		return true;
	}



	private Map<String, Object> generateDataForAddRun(TestRun testRun) {
		Map<String,Object> data1 = new HashMap<>();
		data1.put("suite_id", testRun.suiteId);
		data1.put("name", testRun.name);
		
		return data1;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> generateDataForAddResults(List<TestResult> results) {
		
		JSONArray jsonResults = new JSONArray();
	
		for(TestResult result: results){
			Map<String,Object> resultData = new HashMap<>();
			resultData.put("status_id", result.status.getStatus());
			resultData.put("comment", result.comment);
			resultData.put("elapsed", RoundTime.round(result.elapsedTime)+"s"); //http://stackoverflow.com/questions/8753959/round-a-floating-point-number-to-the-next-integer-value-in-java
			resultData.put("case_id", result.caseId); //if case does not exist will fail.
			
			jsonResults.add(resultData);
		}
		
		Map<String,Object> resultsData = new HashMap<String, Object>();
		
		resultsData.put("results", jsonResults);
		return resultsData;
	}
	

}
