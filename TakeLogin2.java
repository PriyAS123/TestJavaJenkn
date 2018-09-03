package Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import com.loginTest.*;

public class TakeLogin2 {
	/**
	 * @author SVSP595
	 *
	 */
	
	
  @Test
  public void callLoginpage() {
	  
	  FirefoxProfile fp=new FirefoxProfile();
	  WebDriver driver=new FirefoxDriver(fp);
	  
	  driver.get("http://172.20.20.159/expenseuat");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  String win1=driver.getWindowHandle();
	  for(String win2 : driver.getWindowHandles())
	  {
		  driver.switchTo().window(win2);
	  }
	  
	  
	  
	  LoginPage obj=new LoginPage(driver);
	  
	  obj.enterUid("shfy019");
	  obj.enterPass("stfc@123");
	  obj.Submit();
	  
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.quit();
	  
  }
  
  
}
