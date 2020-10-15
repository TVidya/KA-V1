package com.BB

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
//import com.sun.org.apache.xpath.internal.operations.String
import com.kms.katalon.keyword.excel.ExcelKeywords
import java.lang.String
import java.lang.Number
import java.lang.Double
import java.lang.Integer as Integer
import java.util.concurrent.TimeUnit

public class Login {



	@Keyword
	//Function created on 23-09-20
	//Logout from Kanvas and relogin with other user
	def kanvasRelogin(String url,String userName,String pwd)
	{
		//Call function to Click on welcome user context menu
		(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()
		(new generalFunc.AllgenralFunc()).shortDelay()

		//Make an object for Logout and click on the same
		TestObject logOut = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@id="logout-kanvas"]')
		(new generalFunc.AllgenralFunc()).clickUsingJS(logOut,10)
		(new generalFunc.AllgenralFunc()).shortDelay()

		//Validate any warning messages or browser alerts
		//alerts handling
		(new generalFunc.AllgenralFunc()).alertHandling()

		//Warning message check
		//(new generalFunc.AllgenralFunc()).warningMsgChk()
		//(new generalFunc.AllgenralFunc()).shortDelay()

		//Call login function and login with different user based on inputs given
		kanvasLogin(url,userName,pwd)



	}//kanvasRelogin()

	@Keyword
	//Function created on 23-07-20
	//Function updated on 10-08-20
	//Read URL,Uersname and Password from data file
	//Check if let's proceed exists click and continue
	def kanvasLogin(String url,String userName,String pwd){
		WebUI.navigateToUrl(url)
		//Create object for username and password and set values from data file
		TestObject userNMinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@class="input-tag" and @id="user" and @name="user"]')
		TestObject pwdinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@class="input-tag" and @id="encPass" and @name="encPass"]')
		WebUI.setText(userNMinput, userName)
		WebUI.setText(pwdinput, pwd)

		// Create object and Click on Sign In from Login screen
		TestObject signInBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="btn grayBtn" and @id="submitBtn"]')
		(new generalFunc.AllgenralFunc()).clickUsingJS(signInBtn,10)
		WebUI.delay(10)
		//Create lets proceed check
		TestObject letsProceed = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@id="submitBtn" and @class="confirm"]')

		if(WebUI.verifyElementPresent(letsProceed,10,FailureHandling.OPTIONAL))
		{
			WebUI.click(letsProceed)
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyElementPresent(LicenseOK,10,FailureHandling.OPTIONAL))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(LicenseOK,10)
				KeywordUtil.markPassed("Clicked on License button")
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
				KeywordUtil.markPassed("License Expiry info message not displayed")
			}

		} else{
			KeywordUtil.markPassed("Continue on signin")
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyElementPresent(LicenseOK,10,FailureHandling.OPTIONAL))
			{
				WebUI.click(LicenseOK)
				KeywordUtil.markPassed("Clicked on Licensse OK button")
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
				KeywordUtil.markPassed("License Expiry info message not displayed")
			}
		}
	}//kanvasLogin



	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KLogin(){
		WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
		//WebUI.getUrl('https://kanvas-staging.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.demo_usn)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.demo_pwd)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KLogin
	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KLoginSYSTST(){
		WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.User64)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.User64PWD)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KLoginSYSTST
	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KLoginUser(){
		WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.User63)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.PWD63)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))
			(new generalFunc.AllgenralFunc()).shortDelay()
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyTextPresent(LicenseOK,10,FailureHandling.OPTIONAL))
			{
				WebUI.click(LicenseOK)
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
			}

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KLoginUser

	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KproductionLogin(){
		WebUI.navigateToUrl('https://kanvas.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.pUser)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.pPWD)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KproductionLogin
	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KLoginBenjaminuser43(){
		WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.BUser43)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.BUser43PWD)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KLoginBenjimanuser43

}//public KanvasLoginCustomFunctions
