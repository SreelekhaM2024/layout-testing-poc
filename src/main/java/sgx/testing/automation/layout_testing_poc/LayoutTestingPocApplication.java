package sgx.testing.automation.layout_testing_poc;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

@SpringBootApplication
public class LayoutTestingPocApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(LayoutTestingPocApplication.class, args);
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1200,800));
		try {
			driver.get("https://www.facebook.com/");  
			// C:\\Users\\SreelekhaMallojwala\\eclipse-workspace\\LayoutTestingPOC\\src\\main\\java\\LayoutTesting\\
			LayoutReport layoutReport = Galen.checkLayout(driver,"samplefile.gspec",Arrays.asList("chrome"));
			List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
			GalenTestInfo test = GalenTestInfo.fromString("Verifying Email width & Facebook logo visibilty on Facebook login Page");
			test.getReport().layout(layoutReport, "check layout on Facebook login Page");
			tests.add(test);
		    new HtmlReportBuilder().build(tests, "target/galen-html-reports");
		}
		finally {
			driver.quit();
		}
	}

}
