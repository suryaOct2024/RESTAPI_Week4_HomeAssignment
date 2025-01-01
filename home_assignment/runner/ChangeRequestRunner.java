package week4.home_assignment.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
					features = {"src/main/java/week4/home_assignment/feature/ChangeRequest.feature"},
					glue = {"week4.home_assignment.steps"}, //pkg where defn is available
					dryRun = false,
					publish = true,
					monochrome = true
	)
		//publish = true, //will generate report
		//monochrome = true)  //will remove junk character from report link

public class ChangeRequestRunner extends AbstractTestNGCucumberTests {

}
