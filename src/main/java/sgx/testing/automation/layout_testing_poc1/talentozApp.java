package sgx.testing.automation.layout_testing_poc1;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
	private WebDriver driver;

	@Before
	public void setUp() throws InterruptedException {

		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920,1080));
		driver.get("https://solugenix.talentoz.com/");
		driver.findElement(By.id("txt_name")).sendKeys("Sreelekha.mallojwala@solugenix.com");
		driver.findElement(By.id("txt_pass")).sendKeys("August@1996");
		driver.findElement(By.id("btn_submit")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}

	@Test
	public void talentOzPageLayoutTest() throws IOException {
		LayoutReport layoutReport = Galen.checkLayout(driver, "resources\\talentoz.gspec", Arrays.asList("desktop"));
		List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
		GalenTestInfo test = GalenTestInfo.fromString("homepage layout");
		test.getReport().layout(layoutReport, "check homepage layout - Talentoz App");
		tests.add(test);
		HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
		htmlReportBuilder.build(tests, "target/AppReports");
		
		if (layoutReport.errors() > 0) {
			Assert.fail("Layout test failed");
		}

		
		
	}

	@After
	public void tearDown() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.quit();
	}
}
