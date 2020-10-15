package quadFunc

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
import java.lang.CharSequence

public class AllquadFunc {

	@Keyword
	// Function created on 11-09-20
	// Switch to Quad mode from standard mode
	def switchToQuadFrmStd()
	{
		//Click on Quad Tab in standard and make object for the same
		TestObject switchToquad =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//li[@id="spatialMap"]//a[@aria-controls="spatialMapTab"]')
		WebUI.click(switchToquad)
		KeywordUtil.logInfo("Clicked on Quad Tab...")
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToQuadFrmStd()
}
