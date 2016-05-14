package com.github.rsallar.testrailapi.parser;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.rsallar.testrailapi.sender.Status;
import com.github.rsallar.testrailapi.sender.TestResult;


/**
 * URL that generated this code:
 * http://txt2re.com/index-java.php3?s=Test%20Case%20%27-[UI_ItemDetailTest%20testDeleteItem_cancel]%27%20failed%20(11.553%20seconds).&15&23&14&24&-97&-101&-118&4&25&3&-119&-98&26&12&27&-99&10&28&8&-100&-103 
 * @author Ramon
 *
 */
public class IosRegex implements Regex{
	private Pattern p;
	
	private static final Logger logger = LogManager.getLogger(IosRegex.class);
	
	public IosRegex(){
		compilePattern();
	}
	
   private void compilePattern(){

	    String re1="((?:[a-z][a-z]+))";	// Word 1
	    String re2="(\\s+)";	// White Space 1Ru
	    String re3="((?:[a-z][a-z]+))";	// Word 2
	    String re4="(\\s+)";	// White Space 2
	    String re5="(')";	// Any Single Character 1
	    String re6="(-)";	// Any Single Character 2
	    String re7="(\\[)";	// Any Single Character 3
	    String re8="((?:[a-z][a-z0-9_]*))";	// Variable Name 1
	    String re9="(\\s+)";	// White Space 3
	    String re10="((?:[a-z][a-z0-9_]*))";	// Variable Name 2
	    String re11="(\\])";	// Any Single Character 4
	    String re12="(')";	// Any Single Character 5
	    String re13="(\\s+)";	// White Space 4
	    String re14="((?:[a-z][a-z]+))";	// Word 3
	    String re15="(\\s+)";	// White Space 5
	    String re16="(\\()";	// Any Single Character 6
	    String re17="([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";	// Float 1
	    String re18="(\\s+)";	// White Space 6
	    String re19="((?:[a-z][a-z]+))";	// Word 4
	    String re20="(\\))";	// Any Single Character 7
	    String re21="(\\.)";	// Any Single Character 8

	    p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13+re14+re15+re16+re17+re18+re19+re20+re21,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	  
  }
	
  @Override
  public Optional<TestResult> getTestResult(String txt) {
	TestResult result = null;
    Matcher m = p.matcher(txt);
    if (m.find())
    {

        String var1=m.group(8);
        String var2=m.group(10);
        String word3=m.group(14);
        String float1=m.group(17);
        String word4=m.group(19);
    
        result = new TestResult();
        result.caseId=4L;  //FAKE!!
        result.testCase = var1;
        result.testMethod=var2;
        result.comment="testCase: "+var1 + " method: " + var2;
        result.status=Status.valueOf(word3.toUpperCase());
        result.elapsedTime=Float.valueOf(float1);
        result.elapsedUnit = word4;
        
        logger.debug("found {} {} {}{}", result.testCase, result.testMethod, result.elapsedTime, result.elapsedUnit);
        
    }
	return Optional.ofNullable(result);
  }
}