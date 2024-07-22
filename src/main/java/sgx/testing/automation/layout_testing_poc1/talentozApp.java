package sgx.testing.automation.layout_testing_poc1;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

public class talentozApp {
	
	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920,1080));
		driver.get("https://solugenix.talentoz.com/");
		driver.findElement(By.id("txt_name")).sendKeys("Enter your email id");
		driver.findElement(By.id("txt_pass")).sendKeys("Enter your pwd");
		driver.findElement(By.id("btn_submit")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
		LayoutReport layoutReport = Galen.checkLayout(driver, "static\\talentoz.gspec", Arrays.asList("desktop"));
		List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
		GalenTestInfo test = GalenTestInfo.fromString("homepage layout");
		test.getReport().layout(layoutReport, "check homepage layout - Talentoz App");
		tests.add(test);
		HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
		htmlReportBuilder.build(tests, "target/AppReports");
		

//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//		driver.quit();

		
		
	}


}
