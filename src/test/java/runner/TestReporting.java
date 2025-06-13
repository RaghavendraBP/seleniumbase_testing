	
package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.Result;

import javax.mail.MessagingException;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import org.junit.runner.notification.Failure;

import com.bmw.sch.SMTPNotificationEmailService;

@RunWith(Cucumber.class)

@CucumberOptions(

		features = "src/test/resources/features/EPDN_Admin_Role.feature",
		glue = {"stepDefinitions", "hooks"},
		plugin = {"pretty","html:target/EPDN_Admin_Role.html", "json:target/EPDN_Admin_Role.json"},
		tags = "@abc")


public class TestReporting {
	public static void main(String[] args) throws MessagingException {
		Result result = JUnitCore.runClasses(TestReporting.class);
		String bodyHTML = "<html>" + "<head></head>" + "<body>" + "<center>"+"<h1 style=\"border:sign blue; border-width:5px; border-style:solid;\"> Test Automation Results </h1> </center>"
				+ "</body>" + "</html>";


		bodyHTML = bodyHTML + "<table style=\"width: 100%;\" border=\"1\" bordercolor=\"#000000\"><tr>\n"
				+ "\n"
				+ "<th colspan=\"3\" >Test Automation Results</th>\n"
				+ "  <tr style=\"text-align: center;border-bottom: 1px solid #000000;\">\n"
				+ "    <th style=\"text-align: center;border-bottom: 1px solid #000000;\">Total Test Cases Executed</th>\n"
				+ "    <th style=\"text-align: center;border-bottom: 1px solid #000000;\">Test Cases Passed</th> \n"
				+ "    <th style=\"text-align: center;border-bottom: 1px solid #000000;\">Test Cases Failed</th>\n"
				+ "  </tr>\n"
				+ "  <tr style=\"text-align: center;border-bottom: 1px solid #000000;\">\n"
				+ "    <td style=\"text-align: center;border-bottom: 1px solid #000000;\">"+result.getRunCount()+"</td>\n"
				+ "    <td style=\"text-align: center;border-bottom: 1px solid #000000;\">"+(result.getRunCount()-result.getFailureCount())+"</td>\n"
				+ "    <td style=\"text-align: center;border-bottom: 1px solid #000000;\">"+result.getFailureCount()+"</td>\n"
				+ "  </tr>\n"
				+ "\n"
				+ "</table>\n";

		bodyHTML = bodyHTML + "<TABLE style=\"width: 100%;\" border=\"1\" bordercolor=\"#000000\"><TR style=\"border-bottom: 1px solid #000000;\"><TH style=\"text-align: center;border-bottom: 1px solid #000000;\">SI NO <TH style =\text-align: left;\">Failed Test Case Description</TR>";

		int i = 1;
		for (Failure failure : result.getFailures()) {

			bodyHTML = bodyHTML + "<TR style=\"text-align: center;border-bottom: 1px solid #000000;\"><TD>" + i + "<TD  style=\"text-align: left;border-bottom: 1px solid #000000;\"><hr>" + failure.getTestHeader();
			i= i+1;

			System.out.println(failure.toString() + "this is failure text");
			System.out.println(failure.getTestHeader() + "this is one failure");

			System.out.println(failure.getTestHeader() + "this is one failure  " + failure.getClass().getEnclosingMethod());

		}

		bodyHTML = bodyHTML + "</TABLE>";

		SMTPNotificationEmailService mails = new  SMTPNotificationEmailService();

		if(result.wasSuccessful())
		{

			System.out.println("the execution passed  "+result.wasSuccessful());
		}
		else {

			mails.sendMail(bodyHTML,  "target/cucumber-html-report.html");
			System.out.println("the execution failed  "+result.wasSuccessful());
		}

	}
}
