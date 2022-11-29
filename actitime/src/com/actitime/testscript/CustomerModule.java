package com.actitime.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.generic.FileLib;
import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)
public class CustomerModule extends BaseClass {
@Test
public void testCreateCustomer() throws EncryptedDocumentException, IOException, InterruptedException {
	Reporter.log("CreateCustomer", true);
	FileLib f=new FileLib();
	String expCustName = f.getExcelData("CreateCustomer", 1, 2);
	String custDesp = f.getExcelData("CreateCustomer", 1, 3);
	EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
	e.setTaskTab();
	TaskListPage t=new TaskListPage(driver);
	t.getAddNewBtn().click();
	t.getNewCustOption().click();
	JavascriptExecutor j= (JavascriptExecutor)driver;
	t.getEnterCustTbx().sendKeys(expCustName);
	
	t.getEnterCustDesp().sendKeys(custDesp);
	
	t.getSelectCustDD().click();
	WebElement data = t.getOurCompanyTx();
	Thread.sleep(4000);
	j.executeScript("arguments[0].scrollIntoView(true);",data );
	Thread.sleep(4000);
	t.getOurCompanyTx().click();
	
	int y = driver.findElement(By.xpath("//div[text()='                Copy Projects from an Existing Customer:            ']")).getLocation().getY();
	j.executeScript("window.scrollBy(0,"+y+")");

	t.getCreateCustBtn().click();
	Thread.sleep(4000);
	String actualCustName = t.getActualCustCreateTx().getText();
	Assert.assertEquals(actualCustName, expCustName);
}
@Test(priority=1)
public void deleteCreateCustomer() throws InterruptedException {
	Reporter.log("DeleteCustomer", true);
	EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
	e.setTaskTab();
	Thread.sleep(3000);
	TaskListPage t=new TaskListPage(driver);
	t.getEditBtn().click();
	Thread.sleep(3000);
	//Alert a= driver.switchTo().alert();
	//a.accept();
	
	t.getActionBtn().click();
	Thread.sleep(3000);
	t.getDeleteBtn().click();
	Thread.sleep(3000);
	t.getDeletePermaBtn().click();
	Thread.sleep(3000);
	
	//String Name = t.getActualCustCreateTx().getText();
	//Assert.assertNull(Name);
	
}
}
