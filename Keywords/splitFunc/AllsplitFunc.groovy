package splitFunc

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


public class AllsplitFunc {

	@Keyword
	// Function created on 10-09-20
	// Switch to Split mode from standard mode
	def switchToSplitFrmStd()
	{
		//Click on Split Tab in standard and make object for the same
		TestObject switchTosplit =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//li[@id="grpByIdMode"]//a[@aria-controls="groupByIdTab"]')
		WebUI.click(switchTosplit)
		KeywordUtil.logInfo("Clicked on Split Tab...")
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToSplitFrmStd()

	@Keyword
	//Updated on 17-09-20  for split by attribute accessor
	//Function created on 15-09-20
	//Do split by attribute and add measures
	def doSplit(String spltattrNM,String spltMsrNM,String nSep)
	{
		if(spltattrNM!="")
		{

			WebDriver driver = DriverFactory.getWebDriver()
			//Click on Result control Menu icon to expand split menu
			//Check whether result control menu class is checked or no
			TestObject resCtrlclass = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','//li[@id="limitSetBtn"]')
			String classNM = WebUI.getAttribute(resCtrlclass, 'class', FailureHandling.CONTINUE_ON_FAILURE)
			if(classNM == "clicked")
			{
				KeywordUtil.logInfo("Result Control Menu is already expanded")
			}else
			{

				TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
				WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("Clicked on Result Control Menu ")
				(new generalFunc.AllgenralFunc()).shortDelay()
			}

			//Click in Split by attribute
			//Click on drop down menu
			TestObject srchDDmenu = (new generalFunc.AllgenralFunc()).makeTestObject('', 'Select Split By', '', '', '', '','//span[@class="select2-chosen"]')
			//(new generalFunc.AllgenralFunc()).clickUsingJS(srchDDmenu,10)
			WebUI.click(srchDDmenu)
			(new generalFunc.AllgenralFunc()).longDelay()
			//Capture the div for attribute name
			KeywordUtil.logInfo(spltattrNM)
			TestObject attrNMForSplit = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[.="'+spltattrNM+'"]')
			//(new generalFunc.AllgenralFunc()).clickUsingJS(attrNMForSplit,10)
			KeywordUtil.logInfo("clicked on attribute name.."+spltattrNM+"..to split")
			WebUI.scrollToElement(attrNMForSplit, 10)
			WebUI.click(attrNMForSplit)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Set Measures to display
			//Click on search input for measure
			def mulMsrNM = spltMsrNM.split(nSep)
			int lenMulmsr = mulMsrNM.size()
			for(def index :(0..lenMulmsr-1))
			{
				String splitMsr = mulMsrNM[index].trim()
				KeywordUtil.logInfo("Measure name to be added to split is..."+splitMsr)
				//Make object and click for search measure Input
				TestObject searchMsrInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@id="searchGrpByIDAttr"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(searchMsrInput,10)
				WebUI.setText(searchMsrInput,splitMsr)
				WebUI.sendKeys(searchMsrInput,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object for check box for filtered measure check box
				TestObject MsrinPut = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[@class="chk-div"]//input[@data-valueof="'+splitMsr+'"]')
				TestObject MsrchkBx = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[@class="chk-div"]//input[@data-valueof="'+splitMsr+'"]/following-sibling::label[@class="lbl_cls"]')
				if(WebUI.verifyElementChecked(MsrinPut, 10, FailureHandling.OPTIONAL)==true)
				{
					KeywordUtil.logInfo("Measure name..."+splitMsr+ "...already selected for split mode")
				}
				else
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(MsrchkBx,10)
					KeywordUtil.logInfo("Selected Measure name..."+splitMsr+ "...for split mode")
				}
			}
			//Click on Apply button in Split
			TestObject splitApply = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@id="flt-aply-btn"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(splitApply,10)
			KeywordUtil.logInfo("Clicked on Apply in split mode")
			(new generalFunc.AllgenralFunc()).shortDelay()

		}
		else
		{
			KeywordUtil.logInfo("Split by attribute name info is blank")
		}

	}//doSplit()

	@Keyword
	//Created on 18-09-20
	//Validate split measure values
	def validSplitData(String splitId,String splitAttrNMVal,String splitMsrNM,String spltexpMsrVal,String nSep,String vSep)
	{
		if(splitId!="")
		{
			def mulIds = splitId.split(nSep)
			def mulattrNMs = splitAttrNMVal.split(nSep)
			def mulmsrNMs = splitMsrNM.split(nSep)
			def mulmsrVals = spltexpMsrVal.split(nSep)
			int lenmulIds = mulIds.size()

			for(def index:(0..lenmulIds-1))
			{
				String spltId = mulIds[index].trim()
				String spltAttrnm = mulattrNMs[index].trim()
				String spltMsrrnm = mulmsrNMs[index].trim()
				String spltexpMsrval = mulmsrVals[index].trim()
				KeywordUtil.logInfo("Split item Id and attribute name and measure name and expected val to validate.."+spltId+spltAttrnm+spltMsrrnm+spltexpMsrval)

				//seperate multiple measure values with vSep
				//def attrValsMul = spltAttrnm.split(vSep)
				def msrsMul = spltMsrrnm.split(vSep)
				def msrexpVal = spltexpMsrval.split(vSep)
				int lenmsrsMul = msrsMul.size()
				for(def index1:(0..lenmsrsMul-1))
				{
					//String eachAttr = attrValsMul[index1].trim()
					String eachMsr = msrsMul[index1].trim()
					String eachexpValMsr = msrexpVal[index1].trim()
					KeywordUtil.logInfo("expected msrName.. "+eachMsr+"..for the attribute"+spltAttrnm+"..with the value is.."+eachexpValMsr)

					//Get the actual value for the given measure
					TestObject msrValobj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//span[@class="span-name span-sku" and @data-itemsku="'+spltId+'"]/following-sibling::div[@class="div-attrval"]//td[@class="attr-val-td" and @data-vatd="'+eachMsr+'"]//td[@class="td-class td-lvl" and @oldtitle="'+spltAttrnm+'"]/following-sibling::td[@class="td-values"]')
					if(WebUI.verifyElementPresent(msrValobj, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
					{
						WebUI.waitForElementPresent(msrValobj, 10)
						WebUI.scrollToElement(msrValobj, 10)
						String actualMsrVal = WebUI.getText(msrValobj)
						KeywordUtil.logInfo("The actual measure value is.."+actualMsrVal)
						//Compare both expected and actual measure values
						if(eachexpValMsr==actualMsrVal)
						{
							KeywordUtil.logInfo("Both expected and actual measure values in split are same")
						}
						else
						{
							KeywordUtil.logInfo("Both expected and actual measure values are not matched")
							KeywordUtil.markFailed("Both expected and actual measure values are not matched")
						}
					}
					else
					{
						KeywordUtil.logInfo("Could not find the given split details")
					}

				}



			}

		}else
		{
			KeywordUtil.logInfo("Split Item validation information is blank")
		}


	}//validSplitData()



	@Keyword
	//Function updated with 23-09-20 with the measure expand attribute
	//Function Created on 22-09-2020
	//Filter by measures in split mode

	static splitMsrFlt(String spltfltMsrNM,String spltminVal,String spltmaxVal, String nSep, String vSep)
	{

		if(spltminVal != "" & spltmaxVal != "")
		{

			def mulSrch = spltfltMsrNM.split(nSep)
			def mulMinVals = spltminVal.split(nSep)
			def mulMaxVals = spltmaxVal.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()

				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(Sinput,10)
				//Set Search Keyword
				WebUI.setText(Sinput,Sname)
				WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Create object for msrname and click
				WebDriver driver = DriverFactory.getWebDriver()
				TestObject msrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[contains(@class, "attr-name searchAttrCls")  and @data-attrname="'+Sname+'"]//div[@class="wrap-attr-name"]')
				WebElement element = WebUiCommonHelper.findWebElement(msrN,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("msr"+Sname+"clicked on expand to set Min and Max values" )
				(new generalFunc.AllgenralFunc()).shortDelay()
				String xpathmsrMin = '//li[@class="num-attr-val" and @data-valof="'+Sname+'"]//input[@type="text" and @name="minInput"]'
				KeywordUtil.logInfo(xpathmsrMin)
				//Object for Measure minimum input
				TestObject minValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMin)
				if(WebUI.verifyElementClickable(minValI)==true)
				{
					KeywordUtil.logInfo("Input box present")
					WebUI.scrollToElement(minValI,10)
					WebUI.click(minValI)
					(new generalFunc.AllgenralFunc()).shortDelay()
					String valMin = mulMinVals[index].trim()
					KeywordUtil.logInfo("Minimum value to be set.."+valMin)
					WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(minValI, valMin)
					WebUI.sendKeys(minValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					String xpathmsrMax = '//li[@class="num-attr-val" and @data-valof="'+Sname+'"]//input[@type="text" and @name="maxInput"]'
					KeywordUtil.logInfo(xpathmsrMax)
					TestObject maxValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMax)
					WebUI.scrollToElement(maxValI,10)
					String valMax = mulMaxVals[index].trim()
					KeywordUtil.logInfo('Maximum value to be set...'+valMax)
					WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(maxValI, valMax)
					WebUI.sendKeys(maxValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()

				}
				else
				{
					KeywordUtil.logInfo("Measure Filter details are Invalid")
				}
				//Remove searched keyword
				TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close-icon-md-red resetAttributeSrch"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(resetSearch,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}//for(def index :(0..mulmsrLen-1))

		}
		else
		{
			KeywordUtil.logInfo("Search attribute name infor is not available")
		}

	}//searchMsrNdFlt

	@Keyword
	//Function Created on 22-09-20
	//Apply in split mode
	def splitApply()
	{
		TestObject applysplt = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//div[@class="btnFilter"]//button[@class="btn blueBtn mainAplyBtn"]')
		(new generalFunc.AllgenralFunc()).clickUsingJS(applysplt,10)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//splitApply()


}
