package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = ".//Feature/Admin.feature",
		glue = "stepdefination",
		dryRun =false ,
		monochrome = true,
		publish = true,
		tags = "@Sanity"
		
		
		)
public class TestRunner {

}
