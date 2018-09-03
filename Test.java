package Demo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	static XSSFSheet sh;
	static int count = 1, count1 = 0, c = 0,k,i,f;
	static FileInputStream fi;
	static XSSFWorkbook wb;
	static FileOutputStream fo;
	static WebDriver we;static Select s; static Alert alert;
	static String AdvAdjst="No",ExcelPath = "E:\\Expenses\\Expense_new\\EE_TS_NEFT.xlsx";
	static String voucherNo = "1", NetAmt = "no", voucherNoC = "1", voucherNo1 = "1", batchNo = "1";
	static String payMode,con1, con1sts, conAp1,conAp1sts, mod, conM, conMsts, conApM, conApMsts;

	public static void main() throws InterruptedException, Exception
	{
		XSSFCell c3;
		Actions ac;
		
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		we = new ChromeDriver();
		we.get("http://172.20.20.159/Expenseuat/");
		String firstWindw = we.getWindowHandle();
		for (String secondWindw : we.getWindowHandles()) {
			we.switchTo().window(secondWindw);
		}
		we.manage().window().maximize();
		Thread.sleep(500);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F11);
		Thread.sleep(500);
		Connection db = DriverManager.getConnection("jdbc:sqlserver://172.20.20.159;databaseName=SHFL_UNOBOOKS", "uno",
				"swift");
		Statement st = db.createStatement();
		
		fi = new FileInputStream(ExcelPath);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet("Status");
		
		Thread.sleep(2000);
		System.out.println(" before In action n sts for loop");
		System.out.println(" Sheet name : "+sh.getSheetName());
		System.out.println(" sheet row : "+sh.getLastRowNum());
		
		for ( f = 1; f <= sh.getLastRowNum(); f++) {

			String TR_BkAct,TR_BkSts,apvAct,apvStsF,payPrAct,payPrSts,payExAct,payExSts,payAprAct,payAprSts,payEnAct,payEnSts;
				//, con1, con1sts, conAp1,conAp1sts, mod, con2, con2sts, conAp2, conAp2sts
			System.out.println("In action n sts for loop");
			TR_BkAct = Read_IN(f, 0); 
			System.out.println("TR_BkAct : "+TR_BkAct);
			TR_BkSts = Read_IN(f, 1);
			con1 = Read_IN(f, 2);
			con1sts = Read_IN(f, 3);
			conAp1 = Read_IN(f, 4);
			conAp1sts = Read_IN(f, 5);
			mod = Read_IN(f, 6);
			conM = Read_IN(f, 7);
			conMsts = Read_IN(f, 8);
			conApM = Read_IN(f, 9);
			conApMsts = Read_IN(f, 10);
			apvAct = Read_IN(f, 11);
			apvStsF = Read_IN(f, 12);
			payPrAct = Read_IN(f, 13);
			payPrSts = Read_IN(f, 14);
			payExAct = Read_IN(f, 15);
			payExSts = Read_IN(f, 16);
			payAprAct = Read_IN(f, 17);
			payAprSts = Read_IN(f, 18);
			payEnAct = Read_IN(f, 19);
			payEnSts = Read_IN(f, 20);
			
			  fi = new FileInputStream(ExcelPath);
				wb = new XSSFWorkbook(fi);
				sh = wb.getSheet("Login");
				
				System.out.println("last login row num : "+sh.getLastRowNum());
				for ( i = 1; i <= sh.getLastRowNum(); i++) {

					String uid, pwd, unit, ap, expn_id, apprvSts, netPayAmt,Mexpn_id;

					uid = Read_IN(i, 0);
					pwd = Read_IN(i, 1);
					unit = Read_IN(i, 2);
					expn_id = Read_IN(i, 3);
					netPayAmt = Read_IN(i, 4);
		if (expn_id.equals("1") && TR_BkAct.equals("yes")) {
			Thread.sleep(500);
			we.findElement(By.id("txtUsername")).sendKeys(uid);
			we.findElement(By.id("txtPassword")).sendKeys(pwd);
			Thread.sleep(500);
			we.findElement(By.id("txtUnit")).click();
			we.findElement(By.id("btnSubmit")).click();
			Thread.sleep(500);
			// to check whether alert box is open or not?
			WebDriverWait wait1 = new WebDriverWait(we, 2);
			try {
				wait1.until(ExpectedConditions.alertIsPresent());
				alert = we.switchTo().alert();
				alert.accept();
			} catch (Exception e) {
				System.out.println("No alert open");
			}
			Thread.sleep(1000);
			// check whether reset button is der or not?
			try {
				WebElement w1 = we.findElement(By.id("btnReset"));
				if (w1.isDisplayed()) {
					Thread.sleep(1000);
					System.out.println("Login Failed");
					// This will capture error message
					String actual_msg = we.findElement(By.id("lblError")).getText();
					Thread.sleep(1000);
					we.findElement(By.id("btnReset")).click();

				}
			} catch (Exception p) {
				System.out.println("Login Successfully");
			}Thread.sleep(2000);
		}
				}
		}
	}
	
	
	public static String Read_IN(int R, int C) {
		return sh.getRow(R).getCell(C).getStringCellValue();
	}

	public static void Write_INTO(String excel) throws Exception {
		fi.close();
		Thread.sleep(1000);
		fo = new FileOutputStream(excel);
		wb.write(fo);
		fo.close();
		Thread.sleep(1000);

	}
	
}
