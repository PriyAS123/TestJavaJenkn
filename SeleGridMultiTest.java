package Demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleGridMultiTest {
	 WebDriver we;
	
  @Test
  public void test1() throws MalformedURLException {
	  
	  try{
		  
	  DesiredCapabilities cap=DesiredCapabilities.chrome();
	  
	  cap.setBrowserName("chrome");
	  cap.setPlatform(Platform.WINDOWS);
	  
	  URL url=new URL("http://10.0.1.181:46899/wd/hub");
	 we=new RemoteWebDriver(url, cap);
	  
	  we.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  we.manage().deleteAllCookies();
	  we.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  ChromeOptions options = new ChromeOptions();
	  options.addArguments("--disable-extensions");
	  we.get("https://www.seleniumhq.org/download/");
	  System.out.println("Title is :" +we.getTitle());
	  we.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	  we.quit();
	  }
	  catch(MalformedURLException e)
	  {
		  System.out.println("Error msg : "+e.getMessage());
	  }
	  
  }
}
