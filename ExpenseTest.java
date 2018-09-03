package Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.loginTest.LoginPageFact;

public class ExpenseTest {
	
	WebDriver we;
	//LoginPageFact obj=new LoginPageFact(we);
	LoginPageFact obj=  PageFactory.initElements(we, LoginPageFact.class);
	@BeforeTest
	public void start()
	{
		 FirefoxProfile fp=new FirefoxProfile();
		   we=new FirefoxDriver(fp);
		  
		  we.get("http://172.20.20.159/expenseuat");
		  we.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		  
		  String win1=we.getWindowHandle();
		  for(String win2 : we.getWindowHandles())
		  {
			  we.switchTo().window(win2);
		  }
		  
		  we.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  we.manage().window().maximize();
		  
	}
	
  @Test
  public void login() {
	  
	  obj.login1("shfy019", "stfc2123");
	  we.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }
  
  @Test
  public void openEmpExp() {
	  
	  obj.ExpEmp();
  }
}
