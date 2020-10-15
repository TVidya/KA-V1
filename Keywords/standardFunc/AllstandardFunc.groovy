package standardFunc

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

public class AllstandardFunc {

	@Keyword
	def switchToStandard()
	{
		//Click on Standard Tab and make object for the same
		TestObject switchToStandard =(new generalFunc.AllgenralFunc()). makeTestObject('a', '', 'tab-cls-anch', '', 'aria-controls','itemsGridId' ,'')
		WebUI.click(switchToStandard)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToStandard()

	@Keyword
	//Created on 17-Apr
	def switchToLayoutFromStd()
	{
		//Click on Standard Tab and make object for the same
		TestObject switchTolayout = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'goLayout', 'data-url','/layout/layout' ,'')
		WebUI.click(switchTolayout)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToLayoutFromStd()


	@Keyword
	//Reset all Filters (Main Apply button in My selection)
	def ResetAllFlts(){
		TestObject resetAll = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("resetAll")/i[@class="reset-icon-lg-grey"]')
		WebUI.waitForElementClickable(resetAll, 10)
		WebUI.click(resetAll)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//ResetAllFlts

	@Keyword
	//Created on 03-09-20
	//Click on expand view button
	def clickOnExpandView(){
		TestObject expandBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//i[@id="resize_mysel" and @class = "expand-icon-lg-grey" ]')
		WebUI.waitForElementClickable(expandBtn, 10)
		(new generalFunc.AllgenralFunc()).clickUsingJS(expandBtn,10)
		KeywordUtil.logInfo("Clicked on expand view button")
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//clickOnExpandView

	@Keyword
	//Created on 03-09-20
	//Updated on 04-09-20
	//Click on expand attributes button
	def clickOnExpandAttrArrow(){
		TestObject expandArrowAttr = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//span[@id="attrslidebtn"]')
		(new generalFunc.AllgenralFunc()).shortDelay()
		String getClass = WebUI.getAttribute(expandArrowAttr,'class')
		KeywordUtil.logInfo(getClass)
		if(WebUI.waitForElementClickable(expandArrowAttr, 10)==true)
		{
			(new generalFunc.AllgenralFunc()).clickUsingJS(expandArrowAttr,10)
			//WebUI.click(expandArrowAttr)
			KeywordUtil.logInfo("Clicked on expand attribute arrow")
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Could not find the element")
		}
	}//clickOnExpandAttrArrow

	@Keyword
	//Created on 03-09-20
	//Updated on 04-09-20
	//get SKU form item window
	def validateSKUfromIW(String skuVal,String nSep)
	{
		//Make object and Click on Item window and check for ID
		if(skuVal != "")
		{
			KeywordUtil.logInfo("Item Id  info is available")
			def mulIDs = skuVal.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')

				WebUI.click(itemIDObj)
				//get text and Verify ItemID and name
				TestObject getskuDetails = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@id="prodhdrli"]//h4[@id="product-header" and @title="'+itemIDVal+'"]')
				TestObject getskuTitle = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@id="prodhdrli"]//h4[@id="product-header"]')
				String titleSKU = WebUI.getText(getskuTitle)
				if(WebUI.verifyElementPresent(getskuDetails,10)==true)
				{
					KeywordUtil.logInfo("SKU and Name details are matched with the given input:  "+ titleSKU)
				}
				else
				{
					KeywordUtil.logInfo("Could not find SKU and Name details")
				}
			}
		}
		else
		{
			KeywordUtil.logInfo("SKU information is blank..")
		}

	}//validateSKUfromIW


	@Keyword
	//Created on 06-05-2020
	//Click tooltip info btn in my selection
	def ClickInfoBtn()
	{
		//Click on Info button
		TestObject infoBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="lineRight infoButtonLi dropdown showInfo"]')
		WebUI.click(infoBtn)

	}//ClickInfoBtn()

	@Keyword
	//Validate is expected and actual tooltip info is matched / not matched
	def CompareTooltipInfo(def expectedToolTipInfo){
		if(expectedToolTipInfo != "")
		{
			//Click on info button (Calling function)
			ClickInfoBtn()
			(new generalFunc.AllgenralFunc()).shortDelay()

			// Get info details from kanvas
			//Create object for  getting text
			TestObject ulList = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//ul[@class="dropdown-menu" and @id="info-content"]')
			String tiActual = WebUI.getText(ulList)
			KeywordUtil.logInfo(tiActual)

			if(expectedToolTipInfo == tiActual)
			{

				KeywordUtil.markPassed('SUCCESS: Both actual and expected info is same' )

			}
			else
			{

				KeywordUtil.markFailed('ERROR: tooltip info is not same as expected and the actual filtered info is: '+tiActual + 'the expected info is:' +expectedToolTipInfo )

			}

		}
		else
		{
			KeywordUtil.logInfo("Expected tooltip info is not avaialable")
		}
	}//CompareTooltipInfo



	@Keyword
	//Function Created on 27-08-20
	//Click on Item window and compare msr values
	def validateMsriw(String miwitemID,String msrNM, String expmsrVals,String nSep,String vSep)
	{
		if( miwitemID != "")
		{
			def mulIDs = miwitemID.split(nSep)
			def msrNaMe = msrNM.split(nSep)
			def mulMVals = expmsrVals.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.click(itemIDObj)
				(new generalFunc.AllgenralFunc()).shortDelay()
				CompareMsrVals(msrNaMe[index],mulMVals[index],vSep)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Close Item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}

		}
		else
		{
			KeywordUtil.logInfo("Measure info is blank")
		}
	}//validateMsriw

	@Keyword
	//Function Created on 27-08-20
	//Click on Item window and compare attribute values
	def validateAttriw(String aiwitemID,String attrNMiw, String expattrVals,String nSep,String vSep)
	{
		if(aiwitemID != "" & attrNMiw != "")
		{
			def mulIDs = aiwitemID.split(nSep)
			def attrNaMe = attrNMiw.split(nSep)
			def mulAVals = expattrVals.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.click(itemIDObj)
				(new generalFunc.AllgenralFunc()).shortDelay()
				CompareAttrVals(attrNaMe[index], mulAVals[index],vSep)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Close Item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}
		else
		{
			KeywordUtil.logInfo("Attribute info is blank")
		}
	}//validateAttriw




	@Keyword
	//Function Created on 27-April-2020
	//Function Updated on 28-April-2020
	//Click on Item Window
	def ClickonItemWin(String itemID,String msrNM, String expmsrVals,String attrNMiw,String expattrVals,String nSep,String vSep)
	{
		if(itemID != "")

		{
			KeywordUtil.logInfo("Item Id  info is available")
			def mulIDs = itemID.split(nSep)
			def msrNaMe = msrNM.split(nSep)
			def mulMVals = expmsrVals.split(nSep)
			def attrNaMe = attrNMiw.split(nSep)
			def mulAVals = expattrVals.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()


			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				//TestObject itemID = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="CHF3"]/../preceding-sibling::a[@class="abvthumbnail"]')
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.click(itemIDObj)
				(new generalFunc.AllgenralFunc()).shortDelay()
				if(msrNaMe != "")
				{
					CompareMsrVals(msrNaMe[index],mulMVals[index],vSep)

					(new generalFunc.AllgenralFunc()).shortDelay()
				}else if(attrNaMe!= "")
				{
					CompareAttrVals(attrNaMe[index], mulAVals[index],vSep)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("MsrNDattr name is not available")
				}
				//if(msrNaMe != "")
				//{
				//String nameMsr = msrNaMe[index].trim()
				//String valMsr = mulMVals[index].trim()
				//KeywordUtil.logInfo(nameMsr+valMsr)
				//CompareMsrVals(msrNaMe[index],mulMVals[index],vSep)
				//(new generalFunc.AllgenralFunc()).shortDelay()
				//CompareAttrVals(attrNaMe[index], mulAVals[index],vSep)
				//(new generalFunc.AllgenralFunc()).shortDelay()
				//}
				//else
				//{
				//KeywordUtil.logInfo("Msr name is not available")
				//}
				//if(attrNaMe !="")
				//{
				// String NMattrMul = attrNaMe[index].trim()
				//String ValattrMul = mulAVals[index].trim()
				//KeywordUtil.logInfo(NMattrMul+ValattrMul)
				//CompareAttrVals(attrNaMe[index], mulAVals[index],vSep)
				//(new generalFunc.AllgenralFunc()).shortDelay()
				//}
				//else
				//{
				//	KeywordUtil.logInfo("Attr name is not available")
				//}


				/*
				 if(msrNM != "")
				 {
				 KeywordUtil.logInfo("msrIfblock")
				 def msrNaMe = msrNM.split(nSep)
				 def mulAVals = expmsrVals.split(nSep)
				 int mulmsrlen = msrNaMe.size()
				 for(def index1 :(0..mulmsrlen-1))
				 {
				 String nameMsr = msrNaMe[index1].trim()
				 String valMsr = mulAVals[index1].trim()
				 KeywordUtil.logInfo(nameMsr+valMsr)
				 CompareMsrVals(nameMsr,valMsr,vSep)
				 (new generalFunc.AllgenralFunc()).shortDelay()
				 }
				 }else
				 {
				 KeywordUtil.logInfo("Msr name is not available")
				 }
				 if(attrNMiw!="")
				 {
				 KeywordUtil.logInfo("attrIfblock")
				 def attrNaMe = attrNMiw.split(nSep)
				 def mulAVals = expattrVals.split(nSep)
				 int mullenattr = attrNaMe.size()
				 for(def index2 :(0..mullenattr-1))
				 {
				 String NMattrMul = attrNaMe[index2].trim()
				 String ValattrMul = mulAVals[index2].trim()
				 KeywordUtil.logInfo(NMattrMul+ValattrMul)
				 CompareAttrVals(NMattrMul, ValattrMul,vSep)
				 (new generalFunc.AllgenralFunc()).shortDelay()
				 }
				 }
				 else
				 {
				 KeywordUtil.logInfo("Attr name is not available")
				 }
				 */


				//Close Item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}


		}

		else
		{
			KeywordUtil.logInfo("Item Window info is blank")

		}

	}//ClickonItemWin()




	@Keyword
	//compare measure values in Item window
	def CompareMsrVals(String msrNM, String expmsrVals,String vSep)
	{

		def msrNaMe = msrNM.split(vSep)
		def mulVals =  expmsrVals.split(vSep)
		int mulmsrLen = msrNaMe.size()
		KeywordLogger log = new KeywordLogger()
		for(def index :(0..mulmsrLen-1))
		{
			String msrNMVal = msrNaMe[index].trim()
			String expMsrVal = mulVals[index].trim()

			KeywordUtil.logInfo(msrNMVal)


			//Click on Number group in item window
			TestObject numGrpE = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'attr-type-lbl showMe', 'num-attr-hr', 'data-ulid', 'numAttrModal','')
			//WebUI.click(numGrpE)
			(new generalFunc.AllgenralFunc()).clickUsingJS(numGrpE,10)

			//get Text of measure value
			TestObject msrValue = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[@class="listAB"]//span[contains(@class, "attrnm-div") and @data-attrnm = "'+ msrNMVal +'" ]/following-sibling::span')
			String actualMsrVal = WebUI.getAttribute(msrValue,'oldtitle')
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo(actualMsrVal)
			if(WebUI.verifyMatch(actualMsrVal, expMsrVal, false))
			{
				KeywordUtil.logInfo("Both expected and actual values are same")
			}else
			{
				KeywordUtil.logInfo("Both expected and actual values are not same...actualVal is"+actualMsrVal+"and the expVal is"+ expMsrVal)
			}
			//Click on Number group to collapse in Item window
			(new generalFunc.AllgenralFunc()).clickUsingJS(numGrpE,10)
		}


	}//CompareMsrVals()

	@Keyword
	//Updated on 27-8-20
	//Created on 26-8-20
	//compare attribute values in Item window
	def CompareAttrVals(String attrNMiw, String expattrVals,String vSep)
	{


		def attrNaMe = attrNMiw.split(vSep)
		def mulVals =  expattrVals.split(vSep)
		int mulmsrLen = attrNaMe.size()
		KeywordLogger log = new KeywordLogger()
		for(def index :(0..mulmsrLen-1))
		{
			String attrNMVal = attrNaMe[index].trim()
			String expattrVal = mulVals[index].trim()

			KeywordUtil.logInfo(attrNMVal+expattrVal)


			//Click on Text group to expand in item window
			TestObject txtGrpE = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'attr-type-lbl showMe', 'txt-attr-hr', 'data-ulid', 'txtAttrModal','')
			//WebUI.click(txtGrpE)
			(new generalFunc.AllgenralFunc()).clickUsingJS(txtGrpE,10)

			//get Text of attribute value and compare
			TestObject attrValue = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[@class="listAB"]//span[contains(@class, "attrnm-div") and @data-attrnm = "'+ attrNMVal +'" ]/following-sibling::span')
			String actualAttrVal = WebUI.getAttribute(attrValue,'oldtitle')
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo(actualAttrVal)
			if(WebUI.verifyMatch(actualAttrVal, expattrVal, false))
			{
				KeywordUtil.logInfo("Both expected and actual values are same")
			}else
			{
				KeywordUtil.logInfo("Both expected and actual values are not same...actualVal is"+actualAttrVal+"and the expVal is"+ expattrVal)
			}
			//Click on Text group to collapse in item window
			(new generalFunc.AllgenralFunc()).clickUsingJS(txtGrpE,10)
		}


	}//CompareAttrVals()


	@Keyword
	//Updated on 24-08-20 with the input combination from excel file
	//Created on 18-08-20
	//Checks text tags tagged to the item
	def compareTtags(String iIdTtags,String iwTtags,String nSep,String vSep)
	{
		if(iwTtags!="")
		{
			def muliIDs = iIdTtags.split(nSep)
			def muliwTtags = iwTtags.split(nSep)
			int muliIDsLen = muliIDs.size()

			for(def index :(0..muliIDsLen-1))
			{
				String tTagitemIDVal = muliIDs[index].trim()
				String tTagsitemwin = muliwTtags[index].trim()
				KeywordUtil.logInfo("ItemId...."+tTagitemIDVal +"....to click")

				//Make Test object to click on item window
				TestObject tTagitemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+tTagitemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.scrollToElement(tTagitemIDObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
				(new generalFunc.AllgenralFunc()).clickUsingJS(tTagitemIDObj,10)
				KeywordUtil.logInfo("Clicked on ItemId...."+tTagitemIDVal)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object to get text tag from the opened item window
				TestObject tTagslist = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//ul[@id="textTage-ul"]')
				String actTags = WebUI.getText(tTagslist)
				KeywordUtil.logInfo("list values......"+actTags+"........")

				//Compare expected and actual text tags
				def tTagsMulti = tTagsitemwin.split(vSep)
				int lentTags = tTagsMulti.size()
				for(def index1 :(0..lentTags-1))
				{
					String exptTags = tTagsMulti[index1].trim()
					KeywordUtil.logInfo("Texttag to be checked..."+exptTags)
					//Check actual text tags list contains or no
					if(actTags.contains(exptTags))
					{
						KeywordUtil.logInfo("Texttag ..."+exptTags+"....is tagged to the item")
					}
					else
					{
						KeywordUtil.logInfo("Texttag ..."+exptTags+"....is not tagged to the item..and the actual is .."+actTags+".....")
					}

				}
				//Close Item window
				TestObject clsitemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//i[@class="close close-icon-lg-blue"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(clsitemWin,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}
		}
		else
		{
			KeywordUtil.logInfo("text tags validation in the item window is blank")
		}

	}//compareTtags

	@Keyword
	//Created on 02-09-20
	//Check color tags tagged to the item
	def compareCtags(String iIdCtags,String iwCtags,String nSep,String vSep)
	{
		if(iwCtags!="")
		{
			def muliIDs = iIdCtags.split(nSep)
			def muliwCtags = iwCtags.split(nSep)
			int muliIDsLen = muliIDs.size()

			for(def index :(0..muliIDsLen-1))
			{
				String cTagitemIDVal = muliIDs[index].trim()
				String cTagsitemwin = muliwCtags[index].trim()
				KeywordUtil.logInfo("ItemId...."+cTagitemIDVal +"....to click")

				//Make Test object to click on item window
				TestObject cTagitemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+cTagitemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.scrollToElement(cTagitemIDObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
				(new generalFunc.AllgenralFunc()).clickUsingJS(cTagitemIDObj,10)
				KeywordUtil.logInfo("Clicked on ItemId...."+cTagitemIDVal)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object to get color tag from the opened item window
				TestObject cTagslist = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//ul[@id="colourTage-ul"]')
				String actTags = WebUI.getText(cTagslist)
				KeywordUtil.logInfo("list values......"+actTags+"........")

				//Compare expected and actual color tags
				def cTagsMulti = cTagsitemwin.split(vSep)
				int lentTags = cTagsMulti.size()
				for(def index1 :(0..lentTags-1))
				{
					String exptTags = cTagsMulti[index1].trim()
					KeywordUtil.logInfo("Texttag to be checked..."+exptTags)
					//Check actual color tags list contains or no
					if(actTags.contains(exptTags))
					{
						KeywordUtil.logInfo("Color ..."+exptTags+"....is tagged to the item")
					}
					else
					{
						KeywordUtil.logInfo("Color ..."+exptTags+"....is not tagged to the item..and the actual is .."+actTags+".....")
					}

				}
				//Close Item window
				TestObject clsitemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//i[@class="close close-icon-lg-blue"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(clsitemWin,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}
		}
		else
		{
			KeywordUtil.logInfo("color tags validation in the item window is blank")
		}

	}//compareCtags

	@Keyword
	//Created on 02-09-20
	//Updated on 03-09-20
	//Checks text tags  to be removed from  the item
	def removeTtags(String iIdRTtags,String iwRTtags,String nSep,String vSep)
	{
		if(iwRTtags!="")
		{
			def muliIDs = iIdRTtags.split(nSep)
			def muliwRTtags = iwRTtags.split(nSep)
			int muliIDsLen = muliIDs.size()

			for(def index :(0..muliIDsLen-1))
			{
				String rtTagitemIDVal = muliIDs[index].trim()
				String rtTagsitemwin = muliwRTtags[index].trim()
				KeywordUtil.logInfo("ItemId...."+rtTagitemIDVal +"....to click")

				//Make Test object to click on item window
				TestObject rtTagitemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+rtTagitemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.scrollToElement(rtTagitemIDObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
				(new generalFunc.AllgenralFunc()).clickUsingJS(rtTagitemIDObj,10)
				KeywordUtil.logInfo("Clicked on ItemId...."+rtTagitemIDVal)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Make remove text tag object for the given tag
				def rtTagsMulti = rtTagsitemwin.split(vSep)
				int lentrTags = rtTagsMulti.size()
				for(def index1 :(0..lentrTags-1))
				{
					String removetTag = rtTagsMulti[index1].trim()
					KeywordUtil.logInfo("Texttag to be removed..."+removetTag)
					//make object for remove icon
					TestObject rtTagsicon = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//span[@data-ttag="'+removetTag+'" and @class="rmttag close-icon-sm-red"]')
					(new generalFunc.AllgenralFunc()).clickUsingJS(rtTagsicon,10)
					KeywordUtil.logInfo("Clicked on... "+rtTagsicon+"remove tag")
					//Click on 'OK' in confirmation dailogue box
					TestObject okBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="btn blueBtn pull-right dialogboot" and @data-bb-handler="confirm"]')
					(new generalFunc.AllgenralFunc()).clickUsingJS(okBtn,10)
					KeywordUtil.logInfo("Clicked on OK button in confirmation dailogue box... ")
					//Click on 'Ok' button success dailogue box
					TestObject okBtnSuccess = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm" ]')
					(new generalFunc.AllgenralFunc()).clickUsingJS(okBtn,10)
					KeywordUtil.logInfo("Clicked on OK button in success dailogue box... ")
				}

				//Close Item window
				TestObject clsitemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//i[@class="close close-icon-lg-blue"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(clsitemWin,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Clicked on close Item Window...")

			}
		}
		else
		{
			KeywordUtil.logInfo("text tags validation in the item window is blank")
		}

	}//removeTtags


	@Keyword
	//Updated on 26-08-20 with all bug fixes for multiple value reading
	//Created on 24-08-20
	//Compare show tags values
	//def compareShowData(String compareSD,String SDitemId)
	def compareShowData(String sdItemID,String sdVals,String nSep,String vSep)
	{
		if(sdItemID!="")
		{
			def multIDs = sdItemID.split(nSep)
			def multVals = sdVals.split(nSep)
			int lenIdsMul = multIDs.size()
			for(def index :(0..lenIdsMul-1))
			{
				String sdItemid = multIDs[index].trim()
				String sdValsmul = multVals[index].trim()
				KeywordUtil.logInfo(sdItemid+"...."+sdValsmul)
				//Create object for given item id and scroll to the element
				TestObject itemIdObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+sdItemid+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.scrollToElement(itemIdObj, 10)
				//Check for multiple SD values for the same item
				def sameIdmulVals = sdValsmul.split(vSep)
				int lenSameIdmulVals = sameIdmulVals.size()
				for(def index1 :(0..lenSameIdmulVals-1))
				{
					String sameidMVals = sameIdmulVals[index1].trim()
					KeywordUtil.logInfo("same id"+sameidMVals)
					//TestObject sdLiObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+sdItemid+'"]//following-sibling::div[@class="showmh6div showthis"]//h6[contains(@class,"nomargin")]')
					TestObject sdLiObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+sdItemid+'"]//following-sibling::div[@class="showmh6div showthis"]/h6[@title="'+sameidMVals+'"]')
					if(WebUI.verifyElementPresent(sdLiObj,10,FailureHandling.CONTINUE_ON_FAILURE)==true)
					{

						KeywordUtil.logInfo("expected value presents in Show data")
					}else
					{
						KeywordUtil.logInfo("expected value not present in Show data")
					}

				}

			}
		}
		else
		{
			KeywordUtil.logInfo("Could not find Show data for the given Item ID")
		}



	}//compareShowData

	@Keyword
	//Created on 21-April-2020
	//Click on Search Icon
	def doSearch(String searchStatus, String searchKeyword){
		if(searchStatus != "")
		{

			if(searchStatus == "Global")
			{
				KeywordUtil.logInfo("Do Search keyword Global")
				//Make object for  Search Icon and then Click
				TestObject searchIcn= (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'lineRight', 'globalSearchEvt', '', '','')
				WebUI.click(searchIcn)
				//Make Object for SearchGlobal radio button and then click
				//TestObject searchGlbl= (new generalFunc.AllgenralFunc()).makeTestObject('label', 'Search Global', 'radio radioSrch', '', '', '','')
				TestObject searchGlbl= (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="radio radioSrch"]/input[@id="radioG"]')
				if(WebUI.verifyElementChecked(searchGlbl, 10))
				{

					//WebUI.click(searchGlbl)
					//Make object for Set Keyword Input and set keyword to search
					TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
					WebUI.click(searchInput)
					WebUI.setText(searchInput,searchKeyword)
					WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
					//Make Object for Search btn and click on Search
					TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
					WebUI.click(searchBtn)
					KeywordUtil.logInfo("Search for entered keywords")
					(new generalFunc.AllgenralFunc()).shortDelay()
					//Close Search Window
					TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
					WebUI.click(clsSearchBtn)

				}
				else
				{
					WebUI.click(searchGlbl)
					//Make object for Set Keyword Input and set keyword to search
					TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
					WebUI.click(searchInput)
					WebUI.setText(searchInput,searchKeyword)
					WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
					//Make Object for Search btn and click on Search
					TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
					WebUI.click(searchBtn)
					KeywordUtil.logInfo("Search for entered keywords")
					(new generalFunc.AllgenralFunc()).shortDelay()
					//Close Search Window
					TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
					WebUI.click(clsSearchBtn)

				}
			}
			else if(searchStatus == "Results")
			{
				KeywordUtil.logInfo("Do Search keyword  with in the results")
				//Make object for  Search Icon and then Click
				TestObject searchIcn= (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'lineRight', 'globalSearchEvt', '', '','')
				WebUI.click(searchIcn)
				//Make Object for Search with in the Results  radio button and then click
				//TestObject searchResults= (new generalFunc.AllgenralFunc()).makeTestObject('label', 'Search In Result', 'radio radioRclass', '', '', '','')
				TestObject searchResults= (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="radio radioRclass"]')
				/*if(WebUI.verifyElementChecked(searchResults, 10))
				 {
				 //WebUI.click(searchResults)
				 //Make object for Set Keyword Input and set keyword to search
				 TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
				 WebUI.click(searchInput)
				 WebUI.setText(searchInput,searchKeyword)
				 WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
				 //Make Object for Search btn and click on Search
				 TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
				 WebUI.click(searchBtn)
				 KeywordUtil.logInfo("Search for entered keywords")
				 (new generalFunc.AllgenralFunc()).shortDelay
				 //Close Search Window
				 TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
				 WebUI.click(clsSearchBtn)
				 }else*/
				// {
				KeywordUtil.logInfo("Click on Search with in the Results option")
				WebUI.click(searchResults)

				//Make object for Set Keyword Input and set keyword to search
				TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
				WebUI.click(searchInput)
				WebUI.setText(searchInput,searchKeyword)
				WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
				//Make Object for Search btn and click on Search
				TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
				WebUI.click(searchBtn)
				KeywordUtil.logInfo("Search for entered keywords")
				//Close Search Window
				TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
				WebUI.click(clsSearchBtn)
				//}
			}else
			{
				KeywordUtil.logInfo("Search Info is Invalid")
			}

		}
		else
		{
			KeywordUtil.logInfo("searchStatus Info is not available")
		}

	}//doSearch(String searchStatus, String searchKeyword)




	@Keyword
	//Main Apply button in my Selection
	def FilterApplyBtn(){
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject fltApplyBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'mainAplyBtn', '', '', '', '')
		if(WebUI.verifyElementVisible(fltApplyBtn,FailureHandling.OPTIONAL))
		{
			WebElement element = WebUiCommonHelper.findWebElement(fltApplyBtn,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).longDelay()
			KeywordUtil.logInfo("Clicked on main Apply button")

		}
		else
		{
			KeywordUtil.logInfo("Apply button is visible....But not able to clickable")
		}
	}//FilterApplyBtn

	@Keyword
	//Function to select attribute values
	//Requires Value/s with coma separated
	//This function called by 'selAttrNdVals'
	static clickAttrVals(String vals, String vSep)
	{
		def mulVals = vals.split(vSep)

		int lenMul = mulVals.size()

		for (def index : (0..lenMul-1))
		{
			TestObject attrV = (new generalFunc.AllgenralFunc()).makeTestObject('li', mulVals[index].trim(), 'txt-attr-val', '', '', '', '')
			if(WebUI.waitForElementClickable(attrV, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrV,10)
				KeywordUtil.logInfo("Selected attribute values")
			}else
			{
				KeywordUtil.logInfo("Could not click on attribute value")
			}
		}

	}//clickAttrVals
	@Keyword
	//Updated on 13-08-20  with the attribute name with xpath accessor
	//Click on Attribute names and calls function to select values
	//Requires attribute name/s with semicolon separated , attribute value/s with coma separated

	static selAttrNdVals(String attrNames,String attrVals, String nSep, String vSep)
	{

		if(attrNames != ""){

			def mulattrN = attrNames.split(nSep)
			def mulVals =  attrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()

				KeywordUtil.logInfo(attr)

				//TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', attr, 'wrap-attr-name', '', '', '','')
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls" and @data-attrname="'+attr+'"]')
				TestObject attrNCollpse = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+attr+'"]')
				//WebUI.click(attrN)
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrN,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
				clickAttrVals(mulVals[index],vSep)
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrNCollpse,10)
			}
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}

	}//selAttrNdVals

	@Keyword
	//Updated on 14-08-20 by attribute name xpath with original name
	//Created on 03-08-20
	//Click on Attribute names and click on select all values
	//Requires attribute name/s with semicolon separated , attribute value/s with coma separated

	static clickOnSelAllVals(String attrNameSelAllVals,String nSep)
	{

		if(attrNameSelAllVals != ""){

			def mulattrN = attrNameSelAllVals.split(nSep)
			int mulattrLen = mulattrN.size()

			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()
				KeywordUtil.logInfo("Attribute name"+attr)
				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				WebUI.click(Sinput)
				if(WebUI.waitForElementClickable(Sinput, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
				{
					//Set Search Keyword
					WebUI.setText(Sinput,attr)
					WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))

				}
				else
				{
					KeywordUtil.logInfo("Could not find search Input")
					KeywordUtil.markFailed("Could not find search Input")
				}

				//Create an object for attribute name to click on
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls" and @data-attrname="'+attr+'"]')

				//TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', attr, 'wrap-attr-name', '', '', '','')
				//Click on attribute name to expand
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrN,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Created object for select all check box and click
				TestObject selAllVals = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[contains(@class,"attr-name") and @data-forsrch="'+attr+'"]//div[contains(@class,"chk-div")]/label[@class="lbl_cls chkSelAttr_lbl"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(selAllVals,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}

	}//clickOnSelAllVals

	@Keyword
	//Updated on 2-9-20
	//Created on 28-08-20
	//Click on hierarchy name and click on select all values
	//Requires Hattribute name/s with semicolon separated

	static hclickOnSelAllVals(String hattrNameSelAllVals,String nSep)
	{

		if(hattrNameSelAllVals != ""){

			//Click on Hierarchy Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject hIcon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'hierarchy-icon-lg-grey', 'hierarchyBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(hIcon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Seperate multiple hNames with nSep and expand the attribute
			def mulhattrN = hattrNameSelAllVals.split(nSep)
			int mulattrLen = mulhattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String hattr = mulhattrN[index].trim()
				KeywordUtil.logInfo("hAttribute name"+hattr)

				//Create an object for hattribute name to click on
				TestObject hattrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[contains(@class,"attr-name") and @data-attrname="'+hattr+'"]')
				//(new generalFunc.AllgenralFunc()).clickUsingJS(hattrN,10)
				WebUI.click(hattrN)
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Expanded hAttribute "+hattr)

				//Click on select all check box
				TestObject hselAllVals = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[contains(@class,"attr-name") and @data-forsrch="'+hattr+'"]//div[contains(@class,"chk-div")]/label[@class="lbl_cls chkSelAttr_lbl"]')

				if(WebUI.verifyElementClickable(hselAllVals, FailureHandling.CONTINUE_ON_FAILURE)==true)
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(hselAllVals,10)
					(new generalFunc.AllgenralFunc()).shortDelay()
					KeywordUtil.logInfo("Selected all values for the hierarchy...."+hattr)
				}
				else
				{
					KeywordUtil.logInfo("Could not click on Select all values for the hierarchy...."+hattr)
				}


			}
			//Click on Save hierarchy button
			//Click on Hsave button
			TestObject hSave = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'blueBtn', 'apply-hierar', '', '','')
			WebUI.click(hSave)
			KeywordUtil.logInfo("Clicked on hierarchy save button....")


		}else
		{
			KeywordUtil.logInfo("Hierarchy attribute name is blank")
		}

	}//hclickOnSelAllVals





	@Keyword
	//Function Created on 04-05-2020
	//Reset Attribute Name

	static resetAttributeNM(String attrNMReset,String nSep)
	{

		if(attrNMReset != ""){

			def mulattrN = attrNMReset.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String ResetattrNM = mulattrN[index].trim()
				TestObject resetAttribute = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//span[@class="close-icon-sm-red close-attr" and @data-resetattr="'+ResetattrNM+'"]')
				WebUI.click(resetAttribute)

			}
		}else
		{
			KeywordUtil.logInfo("Reset attribute name info  is blank")
		}

	}//resetAttributeNM




	@Keyword
	//Function updated on 13-08-20 with the attribute name accessor with the xpath
	//Function Created on 30-04-2020
	//Requires Value/s with semicolon and  coma separated

	static searchAttrNdFlt(String sAttrNM,String attrVals, String nSep, String vSep)
	{

		if(sAttrNM != "")
		{

			def mulSrch = sAttrNM.split(nSep)
			def mulVals =  attrVals.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()

				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				WebUI.click(Sinput)

				//Set Search Keyword
				WebUI.setText(Sinput,Sname)
				WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))

				//Expand Filtered Attribute and select values
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls" and @data-attrname="'+Sname+'"]')
				TestObject attrNCollpse = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]')
				//(new generalFunc.AllgenralFunc()).clickUsingJS(attrN,10)
				WebUI.click(attrN)
				(new generalFunc.AllgenralFunc()).shortDelay()
				clickAttrVals(mulVals[index],vSep)
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrNCollpse,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Remove searched keyword
				TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close-icon-md-red resetAttributeSrch"]')
				WebUI.click(resetSearch)

			}

		}else
		{
			KeywordUtil.logInfo("Search attribute name infor is not available")
		}

	}//searchAttrNdFlt

	@Keyword
	//Function Created on 13-05-2020
	//Requires Value/s with semicolon and  coma separated

	static searchMsrNdFlt(String sMsrNM,String minVal,String maxVal, String nSep, String vSep)
	{

		if(minVal != "" & maxVal != "")
		{

			def mulSrch = sMsrNM.split(nSep)
			def mulMinVals = minVal.split(nSep)
			def mulMaxVals = maxVal.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()

				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				//WebUI.click(Sinput)
				(new generalFunc.AllgenralFunc()).clickUsingJS(Sinput,10)
				//Set Search Keyword
				WebUI.setText(Sinput,Sname)
				WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Create object for msrname and click
				WebDriver driver = DriverFactory.getWebDriver()
				//TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				TestObject msrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[contains(@class, "attr-name iamnumber searchAttrCls")  and @data-attrname="'+Sname+'"]//div[@class="wrap-attr-name"]')
				WebElement element = WebUiCommonHelper.findWebElement(msrN,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				/*TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				 WebUI.scrollToElement(msrN, 10)
				 WebUI.click(msrN)*/
				KeywordUtil.logInfo("msr"+Sname+"clicked" )
				WebUI.delay(10)
				String xpathmsrMin = '//li[@class="num-attr-val" and @data-valof="'+Sname+'"]//input[@type="text" and @name="minInput"]'
				KeywordUtil.logInfo(xpathmsrMin)
				//TestObject minValI = makeTestObject('input', '', 'minInput', '', '', '', '')
				TestObject minValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMin)
				if(WebUI.verifyElementClickable(minValI)==true)
				{
					KeywordUtil.logInfo("Input box present")
					WebUI.scrollToElement(minValI,10)

					//KeywordUtil.logInfo('get minvalue'+ minValI)
					WebUI.click(minValI)
					(new generalFunc.AllgenralFunc()).shortDelay()
					String valMin = mulMinVals[index].trim()
					KeywordUtil.logInfo(valMin)
					WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(minValI, valMin)
					WebUI.sendKeys(minValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					String xpathmsrMax = '//li[@class="num-attr-val" and @data-valof="'+Sname+'"]//input[@type="text" and @name="maxInput"]'
					KeywordUtil.logInfo(xpathmsrMax)
					//TestObject maxValI = makeTestObject('input', '', 'maxInput', '', '', '', '')
					TestObject maxValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMax)
					WebUI.scrollToElement(maxValI,10)
					String valMax = mulMaxVals[index].trim()
					KeywordUtil.logInfo('Max value'+valMax)
					WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(maxValI, valMax)
					WebUI.sendKeys(maxValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(msrN)
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
	//Function Created on 30-04-2020
	//Requires Value/s with semicolon and  coma separated

	static searchAttrVals(String srchAttrNM,String srchattrVals,String srchvalsStatus, String nSep, String vSep)
	{

		if(srchAttrNM != "")
		{

			def mulSrch = srchAttrNM.split(nSep)
			def mulVals =  srchattrVals.split(nSep)
			def mulStatus =  srchvalsStatus.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()
				String Svals = mulVals[index].trim()
				String Sstatus = mulStatus[index].trim()

				//Click on Attribute name to expand
				TestObject attrli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]')
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', Sname, 'wrap-attr-name', '', '', '','')
				if(WebUI.verifyElementPresent(attrli,10,FailureHandling.OPTIONAL))
				{


					//Select or deselect values with in the attribute
					//verify value checked status
					def mulValsSel =  Svals.split(vSep)
					int mulattrLenS = mulValsSel.size()
					//KeywordLogger log = new KeywordLogger()
					for(def index1 :(0..mulattrLenS-1))
					{
						String valsSrchSel = mulValsSel[index].trim()

						//Click on Search Input box with in the attribute
						TestObject SinputwithInAttr = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]//ul[@class="attr-val-lst"]//li[@class="searchLi"]//div/input')
						WebUI.click(SinputwithInAttr)

						//Set Search Keyword
						WebUI.setText(SinputwithInAttr,valsSrchSel)
						WebUI.sendKeys(SinputwithInAttr,Keys.chord(Keys.ENTER))


						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]')


						if(Sstatus == "Select")
						{
							if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.OPTIONAL))
							{
								KeywordUtil.logInfo("Attribut value already Selected")
							}else
							{
								//Click on value to select
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute value Selected as per given Input")
							}
						}
						else
						{
							if(WebUI.verifyElementPresent(attrValSpan,10))
							{
								KeywordUtil.logInfo("Attribut value is Selected...click on deselect")
								//Click on value to deselect
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute Value deselected now")
							}else
							{

								KeywordUtil.logInfo("Attribute value deSelected already")
							}
						}
					}//for(def index1 :(0..mulattrLen-1))
				}
				else
				{
					//Click on attribute name to expand
					WebUI.click(attrN)
					WebUI.delay(5)
					KeywordUtil.logInfo("Clicked on attribute name to expand")

					//Select or deselect values with in the attribute
					//verify value checked status
					def mulValsSel =  Svals.split(vSep)
					int mulattrLenS = mulValsSel.size()
					//KeywordLogger log = new KeywordLogger()
					for(def indexS :(0..mulattrLenS-1))
					{
						String valsSrchSel = mulValsSel[indexS].trim()

						//Click on Search Input box with in the attribute
						TestObject SinputwithInAttr = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]//ul[@class="attr-val-lst"]//li[@class="searchLi"]//div/input')
						WebUI.click(SinputwithInAttr)

						//Set Search Keyword
						WebUI.setText(SinputwithInAttr,valsSrchSel)
						WebUI.sendKeys(SinputwithInAttr,Keys.chord(Keys.ENTER))


						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]')


						if(Sstatus == "Select")
						{
							if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.OPTIONAL))
							{
								KeywordUtil.logInfo("Attribut value already Selected")
							}else
							{
								//Click on value to select
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute value Selected as per given Input")
							}
						}
						else
						{
							if(WebUI.verifyElementPresent(attrValSpan,10))
							{
								KeywordUtil.logInfo("Attribut value is Selected...click on deselect")
								//Click on value to deselect
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute Value deselected now")
							}else
							{

								KeywordUtil.logInfo("Attribute value deSelected already")
							}
						}
					}//for(def index1 :(0..mulattrLen-1))
				}

			}

		}

		else
		{
			KeywordUtil.logInfo("Search attribute name infor is not available")
		}

	}//searchAttrVals



	@Keyword
	//Function Created on 30-04-2020
	//Requires Value/s with semicolon and  coma separated

	static deselAttrVals(String SresetAttrNM,String SresetVals, String nSep, String vSep)
	{

		if(SresetAttrNM != "")
		{

			def mulRname = SresetAttrNM.split(nSep)
			def mulRVals =  SresetVals.split(nSep)
			int mulattrLen = mulRname.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String RsetAttrNM = mulRname[index].trim()
				String RsetValNM = mulRVals[index].trim()


				//Expand attribute name

				TestObject attrli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+RsetAttrNM+'"]')
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', RsetAttrNM, 'wrap-attr-name', '', '', '','')
				if(WebUI.verifyElementPresent(attrli,10,FailureHandling.CONTINUE_ON_FAILURE))
				{

					//Verify Value is Checked if it is, then uncheck the values
					//Make an object for values span element and verify checked or no
					def Rvals = RsetValNM.split(vSep)
					int RvalsLen = Rvals.size()
					for(def index1 :(0..RvalsLen-1))
					{
						String attrValsR = Rvals[index].trim()

						//verify value checked status
						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]')
						if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.CONTINUE_ON_FAILURE))
						{
							//Deselects values
							WebUI.click(attrValli)
							(new generalFunc.AllgenralFunc()).shortDelay()

						}
						else
						{
							KeywordUtil.logInfo("Attribute values is unchecked / not listed in the attribute")
						}
					}
				}else
				{
					WebUI.click(attrN)
					(new generalFunc.AllgenralFunc()).shortDelay()

					//Verify Value is Checked if it is, then uncheck the values
					//Make an object for values span element and verify checked or no
					def Rvals = RsetValNM.split(vSep)
					int RvalsLen = Rvals.size()
					for(def index1 :(0..RvalsLen-1))
					{
						String attrValsR = Rvals[index].trim()

						//verify value checked status
						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]')
						if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.CONTINUE_ON_FAILURE))
						{
							WebUI.click(attrValli)
							(new generalFunc.AllgenralFunc()).shortDelay()

						}
						else
						{
							KeywordUtil.logInfo("Attribute values is unchecked / not listed in the attribute")
						}

					}//for(def index1 :(0..RvalsLen-1))
				}

			}// for(def index :(0..mulattrLen-1))

			TestObject fltApplyBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'mainAplyBtn', '', '', '', '')
			WebUI.click(fltApplyBtn)
			WebUI.delay(5)

		}else
		{
			KeywordUtil.logInfo("Search attribute name info is not available")
		}

	}//searchAttrNdFlt


	@Keyword
	//Function is to select hiwerarchy attributes and values
	//Requires Hattrnames/s with semicolon separated and values with coma seperated
	//It calls values selection function from here
	static selHAttrNdVals(String hAttrNames,String hAttrVals,  String nSep, String vSep)
	{

		if(hAttrNames != "" ){
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject hIcon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'hierarchy-icon-lg-grey', 'hierarchyBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(hIcon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Select hierarchy icon
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
			(new generalFunc.AllgenralFunc()).shortDelay()

			def mulattrN = hAttrNames.split(nSep)
			def mulVals =  hAttrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()

				KeywordUtil.logInfo(attr)
				String xpathHattrColl ='//li[@data-attrname="'+attr+'"]//div[@class="wrap-hattr-name"]'
				//String xpathHattrExpnd = '//li[@class="attr-name" and @data-attrname="'+attr+'"]//div[@class="wrap-hattr-name"]'
				TestObject attrNC = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathHattrColl)
				//TestObject attrNExp = makeTestObject('', '', '', '', '', '',xpathHattrExpnd)
				//TestObject attrNE = makeTestObject('', '', '', '', '', '',xpathHattrExpnd)
				WebUI.click(attrNC)
				//shortDelay()
				clickAttrVals(mulVals[index],vSep)
				WebUI.click(attrNC)

				//TestObject attrN = makeTestObject('div', attr, 'wrap-hattr-name', '', '', '','')
				//WebUI.click(attrN)
				//shortDelay()
				//clickAttrVals(mulVals[index],vSep)
				//WebUI.click(attrN)
			}
			//Click on Hsave button
			TestObject hSave = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'blueBtn', 'apply-hierar', '', '','')
			WebUI.click(hSave)
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/hierarchysave'))
			(new generalFunc.AllgenralFunc()).shortDelay()

		}else
		{
			KeywordUtil.logInfo("Hattribute name is blank")
		}

	}//selHAttrNdVals





	@Keyword
	//Function to select measure filter values
	//Requires Measure name and values with semicolon separator
	def SetMsrValbyFunc(String msrNames, String minVal, String maxVal,  String nSep ){

		if(msrNames != "")
		{
			KeywordUtil.logInfo("msrname available")
			def mulmsrN = msrNames.split(nSep)
			def mulMinVals = minVal.split(nSep)
			def mulMaxVals = maxVal.split(nSep)
			int mulmsrLen = mulmsrN.size()
			for(def index :(0..mulmsrLen-1))
			{
				String msr = mulmsrN[index].trim()
				KeywordUtil.logInfo(msr)
				WebDriver driver = DriverFactory.getWebDriver()
				//TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				TestObject msrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@class="attr-name iamnumber searchAttrCls" and @data-attrname="'+msr+'"]//div[@class="wrap-attr-name"]')
				WebElement element = WebUiCommonHelper.findWebElement(msrN,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				/*TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				 WebUI.scrollToElement(msrN, 10)
				 WebUI.click(msrN)*/
				KeywordUtil.logInfo("msr"+msr+"clicked" )
				WebUI.delay(20)
				String xpathmsrMin = '//li[@class="num-attr-val" and @data-valof="'+msr+'"]//input[@type="text" and @name="minInput"]'
				KeywordUtil.logInfo(xpathmsrMin)
				//TestObject minValI = makeTestObject('input', '', 'minInput', '', '', '', '')
				TestObject minValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMin)
				if(WebUI.verifyElementClickable(minValI)==true)
				{
					KeywordUtil.logInfo("Input box present")
					WebUI.scrollToElement(minValI,10)

					//KeywordUtil.logInfo('get minvalue'+ minValI)
					WebUI.click(minValI)
					(new generalFunc.AllgenralFunc()).shortDelay()
					String valMin = mulMinVals[index].trim()
					KeywordUtil.logInfo(valMin)
					WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(minValI, valMin)
					WebUI.sendKeys(minValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					String xpathmsrMax = '//li[@class="num-attr-val" and @data-valof="'+msr+'"]//input[@type="text" and @name="maxInput"]'
					KeywordUtil.logInfo(xpathmsrMax)
					//TestObject maxValI = makeTestObject('input', '', 'maxInput', '', '', '', '')
					TestObject maxValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMax)
					WebUI.scrollToElement(maxValI,10)
					String valMax = mulMaxVals[index].trim()
					KeywordUtil.logInfo('Max value'+valMax)
					WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(maxValI, valMax)
					WebUI.sendKeys(maxValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(msrN)
				}
				else
				{
					KeywordUtil.logInfo("Measure Filter details are Invalid")
				}
			}//for(def index :(0..mulmsrLen-1))
		}
		else
		{
			KeywordUtil.logInfo("Measure name is not available")
		}
	}//SetMsrValbyFunc()

	/* *******************Filter by Tags *****************************/


	@Keyword
	//Function updated on 06-10-20 to chk error message for oops went wrong!!
	//Function to click on Tags tab then sets text
	//Requires text tag with semicolon separator
	//Updated on Show Tags behaviour on 10-09-20

	def setTextTag(String TextTag, String nSep)
	{
		if(TextTag != "")
		{

			//Click on Tags Tab
			TestObject tagsTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'tag-tab-anch', 'href', '#tagsTab','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(tagsTab, 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Switch to other text tags-Make object for chk other show tags
			TestObject showTagsinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//input')
			TestObject showTagslbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//label[@class="lbl_cls chkTag_lbl"]')
			if(WebUI.verifyElementNotChecked(showTagsinput,10,FailureHandling.OPTIONAL)==true)
			{
				//Click on show Tags checkbox
				(new generalFunc.AllgenralFunc()).clickUsingJS(showTagslbl, 10)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else
			{
				KeywordUtil.logInfo("Show other tags checkbox already checked")
			}


			def mulTags = TextTag.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				TestObject tagsInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("s2id_autogen1")')
				WebUI.setText(tagsInput,mulTags[index])
				KeywordUtil.logInfo('TextTag' +mulTags[index])
				(new generalFunc.AllgenralFunc()).shortDelay()
				WebUI.sendKeys(tagsInput,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Selected Given Text Tag/s"+tagsInput)
				//Verify Error msg check
				//String errMsg= "Oops! Something went wrong. Please try later"
				if(WebUI.verifyTextPresent("Oops! Something went wrong. Please try later", false, FailureHandling.OPTIONAL)==true)
				{
					TestObject errOKBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class ="confirm"]')
					WebUI.click(errOKBtn)
					KeywordUtil.logInfo("Clicked on OK button in error message")
				}
				else
				{
					KeywordUtil.logInfo("Error message not displayed...")
				}

			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchAttrs, 10)

		}else{
			KeywordUtil.logInfo("Text Tag/s info is not available ")
		}

	}//setTextTag

	@Keyword
	//Function updated on 07-10-20 with the shoe tags input accessor and updated the color gro accessor class
	//Function updated on 06-10-20 to chk error message for oops went wrong!!
	//Function updated on 17-08-20 for verifying show other tags enabled / disabled then proceed
	//Function Created on 29-04-2020
	//Function to click on Tags tab then sets text
	//Requires text tag with semicolon separator

	def setColorTag(String clrGrpNM,String ColorTag, String nSep, String vSep)
	{
		if(clrGrpNM != "" | ColorTag != "")
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on Tags Tab
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/Tags/tagstab'), 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulTags = ColorTag.split(nSep)
			def mulGrps = clrGrpNM.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				String mGrps = mulGrps[index].trim()
				String mTags = mulTags[index].trim()


				//Switch to other text tags-Make object for chk other show tags
				TestObject showTagsinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//input')
				//TestObject showTagslbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("mCSB_11_container")/label[@class="labelCls hideInSmap"]')
				TestObject showTagslbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//label[@class="lbl_cls chkTag_lbl"]')
				if(WebUI.verifyElementNotChecked(showTagsinput,10,FailureHandling.CONTINUE_ON_FAILURE)==true)
				{
					//Click on show Tags checkbox
					(new generalFunc.AllgenralFunc()).clickUsingJS(showTagslbl, 10)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("Show other tags checkbox already checked")
				}


				//Click on Color group to expand
				TestObject colorgrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[contains(@class,"colour-grp-name") and @data-clrgrpname="'+mGrps+'"]/div')
				(new generalFunc.AllgenralFunc()).clickUsingJS(colorgrpName, 10)
				def mulclrtags = mTags.split(vSep)
				for (def index1 : (0..lenMul-1))
				{
					String ClrTagsM = mulclrtags[index].trim()

					//Click on Color as per the given input
					TestObject CLRgrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="colour-tag-li" and @data-clrnnm="'+ClrTagsM+'"]/span')
					(new generalFunc.AllgenralFunc()).clickUsingJS(CLRgrpName, 10)
					//Verify Error msg check
					//String errMsg= "Oops! Something went wrong. Please try later"
					if(WebUI.verifyTextPresent("Oops! Something went wrong. Please try later", false, FailureHandling.OPTIONAL)==true)
					{
						TestObject errOKBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class ="confirm"]')
						WebUI.click(errOKBtn)
						KeywordUtil.logInfo("Clicked on OK button in error message")
					}
					else
					{
						KeywordUtil.logInfo("Error message not displayed...")
					}

				}

			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchAttrs, 10)

		}
		else{
			KeywordUtil.logInfo("Text Tag/s info is not available ")
		}

	}//setColorTag

	@Keyword
	//Function Created on 30-04-2020
	//Function to click on Tags tab then sets Icon
	//Requires text tag with semicolon separator

	def setIconTag(String iconGrpNM,String IconTag, String nSep, String vSep)
	{
		if(iconGrpNM != "" | IconTag != "")
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on Tags Tab
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/Tags/tagstab'), 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulTags = IconTag.split(nSep)
			def mulGrps = iconGrpNM.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				String mGrps = mulGrps[index].trim()
				String mTags = mulTags[index].trim()

				//Switch to other Icon tags-Make object for chk other show tags
				TestObject showTags = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("mCSB_11_container")/label[@class="labelCls hideInSmap"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(showTags, 10)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on Icon group to expand
				TestObject icongrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="icon-grp-name attrNamBotmBrdr" and @data-icngrpname="'+mGrps+'"]/div')
				(new generalFunc.AllgenralFunc()).clickUsingJS(icongrpName, 10)
				def mulicntags = mTags.split(vSep)
				for (def index1 : (0..lenMul-1))
				{
					String IconTagsM = mulicntags[index].trim()

					//Click on icon as per the given input
					TestObject iconTag = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="icon-tag-li" and @data-icnnm="'+IconTagsM+'"]/span')
					(new generalFunc.AllgenralFunc()).clickUsingJS(iconTag, 10)
				}

			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchAttrs, 10)

		}
		else{
			KeywordUtil.logInfo("Icon Tag/s info is not available ")
		}

	}//setIconTag





	/* *******************End of  Filter by Tags *****************************/

	/*   *****************Group By attributes and Tags ***************        */

	@Keyword
	//Function applies grouping on the attributes
	//Requires attribute name with semi colon separated

	static clickGrpAttrs(String groupByAttrName,  String nSep)
	{
		if(groupByAttrName != "" )
		{
			KeywordUtil.logInfo("groupByAttrName is available")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulAttrs = groupByAttrName.split(nSep)
			int lenMul = mulAttrs.size()
			for (def index : (0..lenMul-1))
			{
				String grpName = 'chkAttr'+mulAttrs[index]
				KeywordUtil.logInfo(grpName)
				TestObject GattrS = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'lbl_cls', '', 'for',grpName ,'')
				(new generalFunc.AllgenralFunc()).shortDelay()
				if(WebUI.verifyElementClickable(GattrS,FailureHandling.CONTINUE_ON_FAILURE))
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(GattrS,10)
				}
				else
				{
					KeywordUtil.logInfo("Could not click on the object")
				}
			}
			//WebUI.click(resCtrlMenu)
			executor.executeScript('arguments[0].click()', element)
		}else{
			KeywordUtil.logInfo("Attribute is not available to group")
		}

	}//clickGrpAttrs
	@Keyword
	//Function to group by Text Tags
	//Modified Function name from clickGrpTags to clickGrpTextTags on 22-07-2020
	//Also modified argument to  grpByTextTag on 22-07-2020
	static clickGrpTextTags(String grpByTextTag)
	{
		if(grpByTextTag == "Yes")
		{
			//Click on Result Control Menu
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Click on Grp Text Tag
			String grpTextTag = 'chkAttrText'
			TestObject GtextTag = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'lbl_cls chkTag_lbl', '', 'for',grpTextTag ,'')
			if(WebUI.verifyElementClickable(GtextTag,FailureHandling.CONTINUE_ON_FAILURE))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(GtextTag,10)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("Clicked on group by text tag")

			}
			else
			{
				KeywordUtil.logInfo("Could not click on the grpTextTag object")
			}

		}else{
			KeywordUtil.logInfo("Text tag Group info is not available")
		}
	}//clickGrpTextTags
	@Keyword
	//Group by color tags
	//Created  on 22-07-2020
	static clickGrpClrTags(String grpByClrTag)
	{
		if(grpByClrTag == "Yes")
		{
			//Click on Result Control Menu
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Click on Grp Color Tag
			String grpColourTag = 'chkAttrColour'
			TestObject GclrTag = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'lbl_cls chkTag_lbl', '', 'for',grpColourTag ,'')
			if(WebUI.verifyElementClickable(GclrTag,FailureHandling.CONTINUE_ON_FAILURE))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(GclrTag,10)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("Clicked on group by color")

			}
			else
			{
				KeywordUtil.logInfo("Could not click on the grpColourTag object")
			}

		}else{
			KeywordUtil.logInfo("Colour tag group info is not available")
		}

	}//clickGrpClrTags



	/* ******************End of Group By attributes and Tags ****************** */

	/*  **************************** Sort and Stats Functions**************************  */
	@Keyword
	//Done button in sort and stats window
	//Function Requires sortAttrname,Stats Attrname
	//Function calls only when arguments are not null
	def sortDone(String sortAttrName, String statsAttrName)
	{
		if(sortAttrName != "" | statsAttrName != ""){
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)
		}else{
			KeywordUtil.logInfo("Sort or Stats attribute name info is not available")
		}


	}
	//Created on 6-April-2020
	//To call directly in sort and stats functions
	@Keyword
	def doneSortBtn()
	{

		(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),10)
	}//doneSortBtn


	@Keyword
	//Main function to call in the test case
	//Function requires attribute name with semi colon separated ans status with coma sseparated
	//Also calls sort order function inside this method
	static doSort(String sortAttrName, String attrStatus, String nSep, String vSep)
	{
		if(sortAttrName != "")
		{
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulMsrSort = sortAttrName.split(nSep)
			def mulmsrStatus = attrStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				mDoSort(mulMsrSort[index], mulmsrStatus[index], vSep)
			}
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}else{
			KeywordUtil.logInfo("Sort Attribute is not available")
		}


	}//doSort
	@Keyword
	//Main function to call in test cases to do stats
	//Requires Attributename/s with semicolon separator
	static doStats(String statsAttrName, String nSep)
	{
		if(statsAttrName != "")
		{
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)


			def mulMsrStat = statsAttrName.split(nSep)
			int mulMsrLen = mulMsrStat.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrStat[index].trim()
				log.logInfo(msr)
				String woChkd = '//input[@data-stat='+msr+']/following-sibling::label'
				String whChkd = '//input[@data-stat='+msr+']'
				TestObject msrST = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', whChkd)

				if((WebUI.verifyElementChecked(msrST,10,FailureHandling.OPTIONAL))== true){
					log.logInfo("Stats is already set for attribute")
				}
				else
				{
					TestObject msrSTL = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',woChkd)
					WebUI.click(msrSTL)
					(new generalFunc.AllgenralFunc()).shortDelay()
					KeywordUtil.logInfo("Stats Checked for the attribute now")
				}
			}
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}else{
			KeywordUtil.logInfo("Stats Attribute info  is not available")
		}


	}//doStats

	@Keyword
	//Sub function for sort which called in tDosort function to set the sort order
	//Requires Attribute name with semicolon separator, status with coma separator
	static mDoSort(String sortAttrName, String attrStatus,String vSep)
	{

		def mulmsrStatus = attrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			String attrClass
			if(attrStatus == ' Ascend ' )
			{
				attrClass = 'sort-icon-sm-blue'
			}
			else if(attrStatus == ' descend ')
			{
				attrClass = 'descend'
			}
			else
			{
				attrClass = 'sort-icon-sm-darkgrey'
			}
			//Create Object
			TestObject sortAttr = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', attrClass, '', 'data-sort', sortAttrName,'')
			TestObject stDisable = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-darkgrey', '', 'data-sort', sortAttrName,'')
			TestObject stAscend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-blue', '', 'data-sort', sortAttrName,'')
			TestObject stDescend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'descend', '', 'data-sort', sortAttrName,'')
			//to do Ascend
			if(attrStatus == 'Ascend')
			{
				//SortOrderAscend(stDisable,stAscend,stDescend)
				//To do Ascend
				if ((WebUI.verifyElementPresent(stAscend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						//		10)
						KeywordUtil.logInfo("It's already in Ascend Order")
						(new generalFunc.AllgenralFunc()).shortDelay()
					})
				} else if ((WebUI.verifyElementPresent(stDescend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						WebUI.click(stDescend)
						WebUI.click(stDisable)
						//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
					})
				} else {
					WebUI.click(stDisable)
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

			}

			if(attrStatus == 'Descend')
			{
				//SortOrderDescend(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(stDescend, 10, FailureHandling.OPTIONAL)) == true)
				{
					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//	10)
					KeywordUtil.logInfo("It's already in Descend Status")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else if ((WebUI.verifyElementPresent(stDisable, 10, FailureHandling.OPTIONAL)) == true){
					WebUI.click(stDisable)
					WebUI.click(stAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

				else
				{
					WebUI.click(stAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				}


			}
			if(attrStatus == 'Disable')
			{
				//SortOrderDisable(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(stDisable, 10, FailureHandling.OPTIONAL)) == true) {

					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//		10)
					KeywordUtil.logInfo("It's already in Disable Status")
					(new generalFunc.AllgenralFunc()).shortDelay()

				} else if ((WebUI.verifyElementPresent(stAscend, 10, FailureHandling.OPTIONAL)) == true) {

					WebUI.click(stAscend)
					WebUI.click(stDescend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				} else {
					WebUI.click(stDescend)
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}
			}



		}



	}//mDoSort


	@Keyword
	//Group sort function Created on 6-April-2020
	//Click on groupsortTab and set aggregation call another sub function
	//Requires attrnames and values with semicolon and status with coma separated
	def DogroupSort(String grpsortAttrName, String grpattrStatus, String aggrVal,String vSep,String nSep)
	{
		if(grpsortAttrName!="")
		{
			//Call Stats Function
			String statAttr = "'"+grpsortAttrName+"'"
			doStats(statAttr,nSep)
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Create and click on groupsort object
			TestObject grpSortTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'groupsorttab', '', '','')
			WebUI.click(grpSortTab)

			//set sort order in groupsort
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulMsrSort = grpsortAttrName.split(nSep)
			def mulMsrAggr = aggrVal.split(nSep)
			def mulmsrStatus = grpattrStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				groupmDoSort(mulMsrSort[index], mulmsrStatus[index], vSep)
				grpSortAggr(mulMsrSort[index],mulMsrAggr[index])
				//KeywordUtil.logInfo(mulMsrAggr[index]))

			}

			//Click on sort done button
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}
		else
		{
			KeywordUtil.logInfo("Group sort info is blank")
		}

	}//DogroupSort()
	@Keyword
	//Requires attrnames with semicolon and status with coma separated
	static groupmDoSort(String grpsortAttrName, String grpattrStatus,String vSep)
	{

		def mulmsrStatus = grpattrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			String attrClass
			if(grpattrStatus == ' Ascend ' )
			{
				attrClass = 'sort-icon-sm-blue'
			}
			else if(grpattrStatus == ' descend ')
			{
				attrClass = 'descend'
			}
			else
			{
				attrClass = 'sort-icon-sm-darkgrey'
			}
			//Create Object
			TestObject grpsortAttr = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', attrClass, '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstDisable = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-darkgrey', '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstAscend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-blue', '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstDescend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'descend', '', 'data-grpsort', grpsortAttrName,'')
			//to do Ascend
			if(grpattrStatus == 'Ascend')
			{
				//SortOrderAscend(stDisable,stAscend,stDescend)
				//To do Ascend
				if ((WebUI.verifyElementPresent(grpstAscend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						//		10)
						KeywordUtil.logInfo("It's in Ascend Status")
						(new generalFunc.AllgenralFunc()).shortDelay()
					})
				} else if ((WebUI.verifyElementPresent(grpstDescend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						WebUI.click(grpstDescend)
						WebUI.click(grpstDisable)
						//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
					})
				} else {
					WebUI.click(grpstDisable)
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

			}

			if(grpstDisable == 'Descend')
			{
				//SortOrderDescend(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(grpstDescend, 10, FailureHandling.OPTIONAL)) == true)
				{
					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//	10)
					KeywordUtil.logInfo("It's already in Descend Status")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else if ((WebUI.verifyElementPresent(grpstDisable, 10, FailureHandling.OPTIONAL)) == true){
					WebUI.click(grpstDisable)
					WebUI.click(grpstAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

				else
				{
					WebUI.click(grpstAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				}


			}
			if(grpattrStatus == 'Disable')
			{
				//SortOrderDisable(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(grpstDisable, 10, FailureHandling.OPTIONAL)) == true) {

					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//		10)
					KeywordUtil.logInfo("It's already in Disable Status Only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				} else if ((WebUI.verifyElementPresent(grpstAscend, 10, FailureHandling.OPTIONAL)) == true) {

					WebUI.click(grpstAscend)
					WebUI.click(grpstDescend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				} else {
					WebUI.click(grpstDescend)
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}
			}



		}



	}//groupmDoSort

	@Keyword
	//Requires aggrname and val
	def grpSortAggr(String grpsortAttrName,String aggrVal)
	{
		KeywordUtil.logInfo("Aggregation value"+aggrVal)
		//Click on aggregation dropdown
		TestObject clickOnAggrDropDown = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@data-gsattrname="'+grpsortAttrName+'" and @class="gsortli gsortLiVisible oddBackGrnd_li"]//span[@class="select2-chosen"]')
		WebUI.click(clickOnAggrDropDown)
		(new generalFunc.AllgenralFunc()).shortDelay()
		//select aggregation
		KeywordUtil.logInfo(aggrVal)

		TestObject aggregationdd = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//ul[@class="select2-results"]//div[contains(text(),"'+aggrVal+'")]')
		//WebUI.selectOptionByValue(aggregationdd,aggrVal,false)
		//WebUI.selectOptionByValue(clickOnAggrDropDown, aggrVal, false)
		WebUI.click(aggregationdd)


	}//grpSortAggr()



	/*  *******************************  End of Sort and Stats  Functions************************  */

	/*  *******************************    ItemLimit Functions *******************************   */
	@Keyword
	//Clicks on result control menu and set limit
	//Requires number argument to set the limit
	def setItemLimit(def limit)
	{
		if(limit > 0)
		{

			KeywordUtil.logInfo("Item limit is available")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)


			//TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			//WebUI.click(resCtrlMenu)
			//WebUI.delay(5)
			//Click on Item Limit text box
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/MainButns/i_set-limit'),10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Set Limit as 10
			TestObject itemLimitInput = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', '', 'first-n', '', '','')
			WebUI.setText(itemLimitInput, limit)
			WebUI.sendKeys(itemLimitInput,Keys.chord(Keys.ENTER) )
			KeywordUtil.logInfo("Item limit applied now")


		}else{
			KeywordUtil.logInfo("Item limit is not available"+limit)
		}
	}//setItemLimit()

	/*  ****************************** End of ItemLimit Functions ******************************* */

	/*  *******************************     Show Data Functions   *******************************  */
	@Keyword
	//Click on Show button in show data window
	def enableShowData()
	{
		//Enable Show data
		TestObject enableSD = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
		WebUI.click(enableSD)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//enableShowData()


	@Keyword
	//Click on 'Hide' button in show data window
	def enableHideData()
	{

		//Enable Hide data



	}//enableHideData()

	@Keyword
	//Function sets the show data as per the inputs
	//Requires attrname with semicolon separator
	static doShowData(String showDataAttrName, String nSep)
	{
		if(showDataAttrName != "")
		{
			//WebUI.delay(5)
			//Showdata
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject showData = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'showmeasure-icon-xl-grey', 'showMeasrBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(showData,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//clickUsingJS(showData, 10)
			//WebUI.scrollToElement(showData, 10)
			//WebUI.click(showData)
			//Enable showdata
			TestObject enableSD = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
			(new generalFunc.AllgenralFunc()).clickUsingJS(enableSD,10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulMsrSD = showDataAttrName.split(nSep)
			int mulMsrLen = mulMsrSD.size()
			KeywordLogger log = new KeywordLogger()
			def done = false
			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSD[index].trim()
				log.logInfo(msr)
				String valwochk = '//input[@data-quickg='+msr+']/following-sibling::label'
				String valwchk = '//input[@data-quickg='+msr+']'
				TestObject msrSD = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',valwchk)

				log.logInfo(valwochk)
				log.logInfo(valwchk)
				if((WebUI.verifyElementChecked(msrSD,10,FailureHandling.OPTIONAL))== true){
					KeywordUtil.logInfo("Attribute already enabled for ShowData")

					//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Page_SLICeR Kanvas - My Selection/button_Done_close close-icon-lg-blue'))
					//WebUI.delay(5)
				}else
				{
					TestObject msrSDL = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', valwochk)
					KeywordUtil.logInfo("Click on Check box to Enable Show Data")
					(new generalFunc.AllgenralFunc()).clickUsingJS(msrSDL,10)
					//WebUI.scrollToElement(msrSDL, 10)
					//WebUI.click(msrSDL)
					(new generalFunc.AllgenralFunc()).shortDelay()
					done = true
					//saveShowData()
					//WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
				}
			}

			if(done==true)
			{
				//saveShowData()
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("done is true")
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
			}else
			{
				KeywordUtil.logInfo("done is not true")


				//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Page_SLICeR Kanvas - My Selection/button_Done_close close-icon-lg-blue'))
				//TestObject clsShowData = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'close close-icon-lg-blue', '', 'aria-label', 'Close', '')
				TestObject clsShowData = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//*[@id="bodyContent"]/div[7]/div/div/div[1]/button')
				if(WebUI.verifyElementClickable(clsShowData))
				{
					KeywordUtil.logInfo("Close button is clickable")
					(new generalFunc.AllgenralFunc()).clickUsingJS(clsShowData,10)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("Showdata close button not clickable")
				}
			}


		}else{
			KeywordUtil.logInfo("Showdata Attribute Info is not available ")
		}

	}//doShowData
	@Keyword
	static saveShowData()
	{

		WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//saveShowData

	@Keyword
	//Search for measure/attribute names in show data window
	def searchSDV(String showDataAttrName,String nSep)
	{
		if(showDataAttrName!="")
		{
			//Click on Show data Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject showData = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'showmeasure-icon-xl-grey', 'showMeasrBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(showData,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Read the attributes/measures
			def msrchVal = showDataAttrName.split(nSep)
			int lenmsrchVal = msrchVal.size()
			def done = false
			for(def index:(0..lenmsrchVal-1))
			{

				//Enable showdata
				TestObject enableSD = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
				(new generalFunc.AllgenralFunc()).clickUsingJS(enableSD,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on search show data Input
				String msr = msrchVal[index].trim()
				KeywordUtil.logInfo("Show data search keyword to enter..."+msr)

				//Click on search input box and set keyword to set show data
				TestObject srchInputSD = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@id="searchShowMeasure"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(srchInputSD,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Enter attribute/measure name in search Input
				WebUI.setText(srchInputSD,msr)
				WebUI.sendKeys(srchInputSD,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Validate if attribute/measure already selected for show data or no
				//msrU = ''
				String valwochk = '//input[@data-quickg="'+msr+'"]/following-sibling::label'
				String valwchk = '//input[@data-quickg="'+msr+'"]'
				KeywordUtil.logInfo(valwochk+valwchk)
				TestObject msrSD = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',valwchk)
				if((WebUI.verifyElementChecked(msrSD,10,FailureHandling.OPTIONAL))== true){
					KeywordUtil.logInfo("Attribute already enabled for ShowData")
					//Reset Search show data criteria
					TestObject resetSrch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//i[@class="close-icon-md-red resetShowMeasureSrch"]')
					WebUI.scrollToElement(resetSrch,10)
					(new generalFunc.AllgenralFunc()).clickUsingJS(resetSrch,10)

				}else
				{
					TestObject msrSDL = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', valwochk)
					KeywordUtil.logInfo("Click on Check box to Enable Show Data")
					(new generalFunc.AllgenralFunc()).clickUsingJS(msrSDL,10)
					(new generalFunc.AllgenralFunc()).shortDelay()
					done = true
					//Reset Search show data criteria
					TestObject resetSrch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//i[@class="close-icon-md-red resetShowMeasureSrch"]')
					WebUI.scrollToElement(resetSrch,10)
					(new generalFunc.AllgenralFunc()).clickUsingJS(resetSrch,10)
				}
			}

			if(done==true)
			{
				//saveShowData()
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("done is true")
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
			}else
			{
				KeywordUtil.logInfo("done is not true")
				TestObject clsShowData = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//*[@id="bodyContent"]/div[7]/div/div/div[1]/button')
				if(WebUI.verifyElementClickable(clsShowData))
				{
					KeywordUtil.logInfo("Close button is clickable")
					(new generalFunc.AllgenralFunc()).clickUsingJS(clsShowData,10)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("Showdata close button not clickable")
				}
			}

		}
		else
		{
			KeywordUtil.logInfo("Show data information is not available")
		}

	}//searchSDV



	/*  *******************************  End of Show Data Functions *******************************  */

	/* *******************************     Hierarchy Functions       ****************************** */

	@Keyword
	//Hierarchy Button in My selection
	def HierarchyBtn(){
		//Click on hierarchy
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//HierarchyBtn
	@Keyword
	//Save button in hierarchy window
	def HierarchySave(){
		//Click on Save button in hierarchy window
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Save'))
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//HierarchySave

	/* *****************************************  End of Hierarchy Functions  **************************************** */

	/* ***************************************  Validation Functions  ********************************** */
	@Keyword
	//Validate is expected and actual item count is matched / not matched
	def CompareItemCnt(String expected){
		//if(expected > "0" & groupByAttrName != ""){
		if(expected .contains(",") & expected > "0"){

			actualItemCntFlt(expected)

		}else{

			actualItemCntGrp(expected)

		}

	}//CompareItemCnt

	@Keyword
	//make Actual Count according to filters or groups
	def actualItemCntFlt(String expected){
		if(expected != ""){

			//Create object to get actual item count
			TestObject actualItemcntobj = (new generalFunc.AllgenralFunc()).makeTestObject('h4', '', '', 'img-ld-status', '', '', '')
			def actualItemcnt = WebUI.getText(actualItemcntobj)
			KeywordUtil.logInfo(actualItemcnt)
			//def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
			def actCnt = actualItemcnt.split(/\s/)
			String actCntStr = actCnt[0] + ',' + actCnt[2]

			if(expected == actCntStr)
			{

				(new generalFunc.AllgenralFunc()).shortDelay()
				//log.logInfo('Both actual and expected items are matched  '+actual)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actCntStr+'  '+ expected)

			}
			else
			{
				(new generalFunc.AllgenralFunc()).shortDelay()
				//log.logInfo('Items not matched with the expected items and the actual count is: '+actual)
				KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual and expected item  count is: '+actCntStr+' ' +expected)
			}

		}else
		{

			KeywordUtil.logInfo("Expected item count is not available")
		}

	}//actualItemCntFlt()

	@Keyword
	//make Actual Count according to filters or groups
	def actualItemCntGrp(String expected){
		if(expected != ""){
			def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
			def actCnt = actualItemcnt.split(/\s/)
			String actual = actCnt[0]
			if(expected == actual)
			{

				(new generalFunc.AllgenralFunc()).shortDelay()
				//log.logInfo('Both actual and expected items are matched  '+actual)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actual+'  '+ expected)

			}
			else
			{
				(new generalFunc.AllgenralFunc()).shortDelay()
				//log.logInfo('Items not matched with the expected items and the actual count is: '+actual)
				KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual and expected item  count is: '+actual+' ' +expected)
			}

		}else
		{

			KeywordUtil.logInfo("Expected item count is not available")
		}

	}//actualItemCntGrp()






	@Keyword
	//Validate is expected and actual tooltip info is matched / not matched
	def CompareStatsSummary(def tiexpected, def tiactual){
		//expec = WebUI.verifyElementPresent(findTestObject(expected),10,FailureHandling.CONTINUE_ON_FAILURE)
		//actu  = WebUI.getText(findTestObject('Object Repository/Final Objects/Validation Objects/Actual/ActualItemCnt'))
		if(tiexpected == true)
		{
			//log.logInfo('Both actual and expected info is same' + tiactual)
			KeywordUtil.markPassed('SUCCESS: Both actual and expected StatsSummary is same' + tiactual)
			//log.logInfo(actu)
		}
		else
		{
			//log.logInfo('tooltip info is not same as expected and the actual filtered info is: '+tiactual)
			KeywordUtil.markFailed('ERROR: StatsSummary is not same as expected and the actual filtered info is: '+tiactual)

		}
	}//CompareStatsSummary


	@Keyword
	def Stndardcnt() //gets actual standard item count
	{

		//Switch to standard
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/a_Standard'))
		(new generalFunc.AllgenralFunc()).longDelay()
		//Get Item count from Standard
		//JString is an another method to convert data types
		//JString stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		def stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		(new generalFunc.AllgenralFunc()).longDelay()

		//Set to string variable
		//JString partName = stndardcnt.split(/\s/)[0]
		def partName = stndardcnt.split(/\s/)[0]
		//Convert string to integer
		int intPartName = Integer.parseInt(partName)
		//WebUI.comment('Total no.of item count in Standard Mode'+intPartName)
		(new generalFunc.AllgenralFunc()).shortDelay()
		return intPartName
	}//stndardcnt()

	@Keyword
	def tabVsStdcnt() //Compares tabular and standard item count
	{
		//Assign tabular count to variable
		int tabcnt = (new tabularFunc.AlltabularFunc()).TabularItemCount()
		//Assign standard count to variable
		int stdcnt = Stndardcnt()
		if(tabcnt == stdcnt)
		{
			(new generalFunc.AllgenralFunc()).longDelay()
			KeywordUtil.markPassed('SUCCESS: Both Tabular and Standard items are matched '+stdcnt)
		}
		else
		{
			(new generalFunc.AllgenralFunc()).longDelay()
			KeywordUtil.markFailed('ERROR : Both Tabular and Standard items are not matched, the Stndard count is' +stdcnt+ 'and the actual count is'+tabcnt)
		}

	}//tabVsStdcnt()

	@Keyword
	def getActualItemCntG()
	{
		//Get actual item count
		def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		def actCnt = actualItemcnt.split(/\s/)
		//println (actICnt)
		//String actCntStr = actCnt[0] + ',' + actCnt[2]
		String actCntStr = actCnt[0]
		KeywordUtil.logInfo (actCntStr)
		return actCntStr
	}//getActualItemCntG()

	/* ************************************** End of Validation Functions  ********************************** */

	/* ***************************************  Workspace Functions   ********************************** */
	@Keyword
	//Save workspace and check if name already exists proceed
	//Requires 1 argument with the workspace name
	def saveWS(def workspaceNameS){
		//WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
		//Click on user dropdown menu
		if(workspaceNameS != '')
		{
			(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()

			WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/a_Save Workspace'))
			WebUI.delay(10)
			WebUI.setText(findTestObject('Final Objects/MySelection/MainButns/input_Name your workspace_wrkS'), workspaceNameS)
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/Workspace_button_save'))
			WebUI.delay(10)
			//if(WebUI.verifyElementPresent(findTestObject('Final Objects/MySelection/MainButns/button_OK'),10 ,FailureHandling.OPTIONAL)){
			if(WebUI.verifyTextNotPresent("Do you want to overwrite it", false, FailureHandling.OPTIONAL)){
				KeywordUtil.logInfo("No workspace named the same....If block")
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))
			}
			else
			{
				WebUI.delay(2)
				KeywordUtil.logInfo("workspace named as the same name....else block")
				//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Yes'))
				TestObject btnYes = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'dialogboot', '', 'data-bb-handler', 'confirm', '')
				KeywordUtil.logInfo("yes button check")

				WebUI.click(btnYes)
				(new generalFunc.AllgenralFunc()).shortDelay
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))

			}

		}
		else{

			KeywordUtil.markPassed("Workspace Save info is blank")
		}
	}//saveWS

	@Keyword
	//Created on 10-08-20
	//Updated on 13-08-20
	def restoreWorkspace(String restoreWS,String nSep)
	{
		if(restoreWS!="")
		{
			def mulRSWS = restoreWS.split(nSep)
			int lenWS = mulRSWS.size()

			//Call function to Click on user context menu
			(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Make Object for Restore workspace and then click for it's context menu
			TestObject restreWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@id="vi-restoreworkspace"]')
			if(WebUI.verifyElementPresent(restreWS, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(restreWS,10)
				KeywordUtil.logInfo("Clicked on Restore Workspace")
				//Create object to check workspace name available or no in the menu then click on it
				for (def index : (0..lenWS-1))
				{
					String RwsNMStr = mulRSWS[index].trim()
					//Object for ws name
					TestObject nameWSobj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@title="'+RwsNMStr+'" and @data-toggle="tooltip"]')
					//Click on WS name if it is present else print message for ws is not available
					if(WebUI.verifyElementPresent(nameWSobj,10,FailureHandling.CONTINUE_ON_FAILURE)==true)
					{
						(new generalFunc.AllgenralFunc()).clickUsingJS(nameWSobj,10)
						(new generalFunc.AllgenralFunc()).shortDelay()
						KeywordUtil.logInfo("Clicked on worksspace......" + RwsNMStr)
					}
					else
					{
						KeywordUtil.logInfo("Could not Click on worksspace......" + RwsNMStr)
					}

				}//for (def index : (0..lenWS-1))
			}
			else
			{
				KeywordUtil.logInfo("Restore Worksapce option is not available")
			}

		}
		else
		{
			KeywordUtil.logInfo("Restore Worksapce info is blank")
		}

	}//restoreWorkspace


	@Keyword
	//Created on 07-08-20
	//Updated on 10-08-20
	//Updated on 13-08-20 keepsharesetting 'Yes' or 'No' options
	//Updated on  09-09-20  with share options
	//Updated on 15-09-20 with save workspace name warning msg already exists
	//Save workspace and check if name already exists proceed
	def SaveWorkspace(String wsName,String keepShareStngs,String shareUserSave,String shareUserStatus,String nSep){
		if(wsName != '')
		{
			//WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
			//Call function to Click on user context menu
			(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Create object for save workspace option then click on it
			TestObject saveWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@id="svwrkmodal"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(saveWS,10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo("Clicked on save workspace option")
			//Create object for setTextbox and setName
			TestObject wsInputNM = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//input[@id="wrkSpcNm" and @placeholder="Workspace name"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(wsInputNM,10)
			WebUI.setText(wsInputNM, wsName)
			KeywordUtil.logInfo("Entered Workspace Name")
			//Create object for check box then click on it
			TestObject keepSSinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//div[@id="sharesetting"]//input[@id="inheritChk"]')
			TestObject keepSSlbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//div[@id="sharesetting"]//label[@class="lbl_cls chkTag_lbl"]')
			//Check for share settings then click on else continue
			if(keepShareStngs=="Yes")
			{

				//Verify input not checked then click it
				if(WebUI.verifyElementNotChecked(keepSSinput, 10, FailureHandling.OPTIONAL))
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(keepSSlbl,10)
					KeywordUtil.logInfo("Clicked on share settings option")
				}
				else
				{
					KeywordUtil.logInfo("Could not click on  Share settings option ")
				}
			}
			else if(keepShareStngs=="No")
			{
				//Verify input  checked then click it
				if(WebUI.verifyElementChecked(keepSSinput, 10, FailureHandling.OPTIONAL))
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(keepSSlbl,10)
					KeywordUtil.logInfo("Unchecked Keep share settings")
				}
				else
				{
					KeywordUtil.logInfo("Keep Share settings is already uncheked ")
				}
			}
			else
			{
				KeywordUtil.logInfo("Keep share settings info is blank")
			}

			//Share workspace with users based on the given inputs
			if(shareUserSave!="")
			{
				def MshareUserSave = shareUserSave.split(nSep)
				def MshareUserStatus = shareUserStatus.split(nSep)
				int lenShareUserSave = MshareUserSave.size()
				for(def index: (0..lenShareUserSave-1))
				{
					String SSUserNM = MshareUserSave[index].trim()
					String SSUserSts = MshareUserStatus[index].trim()
					KeywordUtil.logInfo("Share Workspace to User.."+SSUserNM+"... with.."+SSUserSts+"....option")
					//Verify the status and click on share status
					if(SSUserSts=="Edit")
					{
						//Make an object for Edit button and click on it
						TestObject editRadio = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//label[@class="radio shrusrRadio"]//input[@id="radioR" and @class="vi-usereditcheck" and @data-value="'+SSUserNM+'"]')
						WebUI.scrollToElement(editRadio, 10)
						(new generalFunc.AllgenralFunc()).clickUsingJS(editRadio,10)
						KeywordUtil.logInfo("Clicked on Edit Save workspace to the user.."+SSUserNM)
					}
					else if(SSUserSts=="View")
					{
						//Make an object for View button and click on it
						TestObject viewRadio = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//label[@class="radio shrusrRadio"]//input[@id="radioR" and @class="vi-userviewcheck" and @data-value="'+SSUserNM+'"]')
						WebUI.scrollToElement(viewRadio, 10)
						(new generalFunc.AllgenralFunc()).clickUsingJS(viewRadio,10)
						KeywordUtil.logInfo("Clicked on View Save workspace to the user.."+SSUserNM)
					}
					else
					{
						//Make an object for Deny button and click on it
						TestObject denyRadio = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//label[@class="radio shrusrRadio"]//input[@id="radioR" and @class="vi-userviewcheck" and @data-value="'+SSUserNM+'"]')
						WebUI.scrollToElement(denyRadio, 10)
						(new generalFunc.AllgenralFunc()).clickUsingJS(denyRadio,10)
						KeywordUtil.logInfo("Clicked on Deny Save workspace to the user.."+SSUserNM)

					}
				}
			}else
			{
				KeywordUtil.logInfo("Share information is blank while save workspace..")
			}

			//Create object for Save button and then click
			TestObject saveWSbtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@id="savework" and @class="btn blueBtn"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(saveWSbtn,10)
			KeywordUtil.logInfo("Clicked on Save Workspace")
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Validate for warning message/s
			String warnMsg = "You do not have Edit Permission on"+ wsName+". Please enter a new name."
			if(WebUI.verifyTextPresent(warnMsg, false, FailureHandling.OPTIONAL)==true)
			{
				KeywordUtil.logInfo("Workspace Name.. "+wsName+"...Already exists..Click on Cancel Workspace")
				TestObject cancelSave = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@id="savework" ]/following-sibling::button[@class="btn grayBtn"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(cancelSave,10)
				KeywordUtil.logInfo("Clicked on Cancel Save workspace")
			}
			else
			{
				KeywordUtil.logInfo("Workspace name error message not displayed")
			}



			//Create object for 'OK' button and then click
			//button[@class="confirm"]
			TestObject okBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@class="confirm"]')
			if(WebUI.verifyElementPresent(okBtn,10 ,FailureHandling.OPTIONAL)){
				(new generalFunc.AllgenralFunc()).clickUsingJS(okBtn,10)
				KeywordUtil.logInfo("Clicked on OK button after save workspace")
			}
			else
			{
				//Create object for 'Yes' btn in overwrite msg window
				TestObject yesBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@data-bb-handler="confirm"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(yesBtn,10)
				KeywordUtil.logInfo("Clicked on Yes on overwrite msg")
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Click on OK button
				(new generalFunc.AllgenralFunc()).clickUsingJS(okBtn,10)
				KeywordUtil.logInfo("Clicked on OK button after save workspace")
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}else
		{
			KeywordUtil.logInfo("Save workspace info is blank")
		}

	}//SaveWorkspace(def wsName,String keepShareStngs)



	@Keyword
	//LoadWorkspace from workspace module
	def loadWorkspace(String workspaceName, String nSep ){
		if(workspaceName != ''){
			def mulWSNames = workspaceName.split(nSep)
			int lenWS = mulWSNames.size()
			// KeywordUtil.logInfo(lenWS)
			for (def index : (0..lenWS-1))
			{
				//TestObject wsName = makeTestObject('h5', mulWSNames[index], 'workspaceTitleHead', '', '', '', '')
				TestObject wsName = (new generalFunc.AllgenralFunc()).makeTestObject('h5', '', 'workspaceTitleHead', '', 'data-workspace', mulWSNames[index], '')

				//Click on sideMenu bar
				(new generalFunc.AllgenralFunc()).sideMenuBar()
				//Click on Workspace Tab
				(new generalFunc.AllgenralFunc()).SwitchtoWorkspace()
				WebUI.click(wsName)
				(new generalFunc.AllgenralFunc()).shortDelay()

				/*check workspace in shared workspace tab
				 shortDelay()
				 if(WebUI.verifyElementPresent(wsName, 10, FailureHandling.STOP_ON_FAILURE)==true)
				 {
				 //KeywordUtil.logInfo(wsName)
				 WebUI.click(wsName)
				 WebUI.delay(20)
				 }else{
				 sharedWSTab()
				 shortDelay()
				 WebUI.click(wsName)
				 WebUI.delay(20)
				 }
				 */
			}

		}else
		{
			KeywordUtil.logInfo("Workspace Name is not avaialble")
		}



	}//loadWorkspace

	@Keyword
	//Share Workspace
	def shareWS( String shareStatus,String shareUser, String vSep){


		/*//Click on sideMenu bar
		 sideMenuBar()
		 //Click on Workspace Tab
		 SwitchtoWorkspace()
		 shortDelay()
		 //make shareWS object then click
		 TestObject shareWSIcon = makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',wsNameShare ,'')
		 WebUI.click(shareWSIcon)*/
		def mulUsers = shareUser.split(vSep)
		int lenMul = mulUsers.size()
		for (def index : (0..lenMul-1))
		{
			if(shareStatus == 'Edit')
			{

				//makeobject for shareWS with Edit permissions then click
				TestObject Edit = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', '', '', '','' ,'(.//*[normalize-space(text()) and normalize-space(.)='+mulUsers[index]+'])[2]/following::span[1]')
				WebUI.check(Edit)
				//Share workspaces
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/WSshareBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Ok btn on confirmMsg
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/shareconfirmBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else
			{
				//makeobject for shareWS with View permissions then click
				TestObject View = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', '', '', '','' ,'(.//*[normalize-space(text()) and normalize-space(.)='+mulUsers[index]+'])[2]/following::span[3]')
				WebUI.check(View)
				//Share workspaces
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/WSshareBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Ok btn on confirmMsg
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/shareconfirmBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}



	}//shareWS()

	@Keyword
	//Click on share Icon of mentioned workspace name
	def clickOnShareIcon(String wsNameShare, String nSep)
	{
		//Click on sideMenu bar
		(new generalFunc.AllgenralFunc()).sideMenuBar()
		//Click on Workspace Tab
		(new generalFunc.AllgenralFunc()).SwitchtoWorkspace()
		(new generalFunc.AllgenralFunc()).shortDelay()
		def mulShareWS = wsNameShare.split(nSep)
		int lenMul = mulShareWS.size()
		for (def index : (0..lenMul-1))
		{

			//make shareWS object then click
			TestObject shareWSIcon = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',mulShareWS[index] ,'')
			WebUI.click(shareWSIcon)

		}

	}//clickOnShareIcon()

	@Keyword
	//share workspace to multiple users with permissions
	def doShareWS(String wsNameShare, String shareStatus, String shareUser,  String vSep, String nSep){
		if(wsNameShare != "")
		{
			/*//Click on sideMenu bar
			 sideMenuBar()
			 //Click on Workspace Tab
			 SwitchtoWorkspace()
			 shortDelay()
			 //make shareWS object then click
			 TestObject shareWSIcon = makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',wsNameShare ,'')
			 WebUI.click(shareWSIcon)*/
			clickOnShareIcon(wsNameShare,nSep)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulshareStatus = shareStatus.split(nSep)
			def mulshareUser = shareUser.split(nSep)
			int mulStatusLen = mulshareStatus.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulStatusLen-1))
			{
				String wsStatus = mulshareStatus[index].trim()
				log.logInfo(wsStatus)
				shareWS(mulshareStatus[index], mulshareUser[index], vSep)

			}
		}else{
			KeywordUtil.logInfo("Share Workspace name is not available")
		}

	}//doShareWS()

	@Keyword
	//MyWorkspace Tab
	def myWSTab(){

		TestObject myWSTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'mywrk_tab', 'aria-controls', 'myWrkTab', '')
		(new generalFunc.AllgenralFunc()).shortDelay()
		WebUI.click(myWSTab)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//myWSTab()

	@Keyword
	//Shared Workspaces Tab
	def sharedWSTab(){

		TestObject sharedWSTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'shdwrk_tab', 'aria-controls', 'acl', '')
		WebUI.click(sharedWSTab)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//sharedWSTab()

	@Keyword
	//Created on 04-09-20
	//Updated on 07-09-20
	//CLicks on Default Workspace
	def makeDefaultWS(String dWSname,String nSep){
		if(dWSname!="")
		{
			//(new generalFunc.AllgenralFunc()).sideMenuBar()
			(new generalFunc.AllgenralFunc()).SwitchtoWorkspace()
			def muldwsNme = dWSname.split(nSep)
			int lenName = muldwsNme.size()

			for(def index :(0..lenName-1))
			{
				String DWSname = muldwsNme[index].trim()
				KeywordUtil.logInfo("Workspace.."+DWSname+"..set to default" )
				//Search for workspace
				TestObject searchWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchWorkspace"]')
				WebUI.click(searchWS)
				WebUI.setText(searchWS,DWSname)
				WebUI.sendKeys(searchWS,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Make object for default workspace then click on default
				TestObject defaultWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="setFaviourite workspaceFavouriteClass" and @data-workspace="'+DWSname+'"]')
				TestObject AdefaultWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="setFaviourite workspaceFavouriteGoldClass" and @data-workspace="'+DWSname+'"]')
				if((WebUI.verifyElementVisible(defaultWS, FailureHandling.OPTIONAL)==true))
				{

					WebUI.scrollToElement(defaultWS, 10)
					WebUI.click(defaultWS)
					//Click on success message
					(new generalFunc.AllgenralFunc()).shortDelay()
					TestObject okBtnSuccMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
					WebUI.click(okBtnSuccMsg)
					KeywordUtil.logInfo(DWSname+".....Set as a default")

					//Reset Search criteria
					TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@id="resetSearchWorkspace"]')
					WebUI.click(resetSearch)

				}
				else if((WebUI.verifyElementVisible(AdefaultWS, FailureHandling.OPTIONAL)==true))
				{

					KeywordUtil.logInfo(DWSname+".......Already set as default")
				}
				else
				{

					sharedWSTab()
					if((WebUI.verifyElementVisible(defaultWS, FailureHandling.OPTIONAL)==true))
					{

						WebUI.scrollToElement(defaultWS, 10)
						WebUI.click(defaultWS)
						//Click on success message
						(new generalFunc.AllgenralFunc()).shortDelay()
						TestObject okBtnSuccMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
						WebUI.click(okBtnSuccMsg)
						KeywordUtil.logInfo(DWSname+".....Set as a default")

						//Reset Search criteria
						TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@id="resetSearchWorkspace"]')
						WebUI.click(resetSearch)

					}
					else
					{

						KeywordUtil.logInfo(DWSname+".......Already set as default")
					}

				}

			}

		}
		else
		{
			KeywordUtil.logInfo("Set default Workspace info is blank")
		}

	}// makeDefaultWS()


	@Keyword
	//Created on 07-09-20
	//Updated on 08-09-20
	//CLicks on Default Workspace
	def delteWS(String delWSNM,String nSep){
		if(delWSNM!="")
		{
			//Navigate to Workspace Tab
			(new generalFunc.AllgenralFunc()).SwitchtoWorkspace()
			def muldwsNme = delWSNM.split(nSep)
			int lenName = muldwsNme.size()

			for(def index :(0..lenName-1))
			{
				String DWSname = muldwsNme[index].trim()
				KeywordUtil.logInfo("Workspace.."+DWSname+"..to be deleted" )
				//Search for workspace
				TestObject searchWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchWorkspace"]')
				WebUI.click(searchWS)
				WebUI.setText(searchWS,DWSname)
				WebUI.sendKeys(searchWS,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Make object for delete workspace then click on delete
				TestObject deltWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="workspaceTrashClass " and @data-workspace="'+DWSname+'"]')

				if((WebUI.verifyElementVisible(deltWS, FailureHandling.OPTIONAL)==true))
				{

					WebUI.scrollToElement(deltWS, 10)
					WebUI.click(deltWS)
					//Click on delete confirmation message
					(new generalFunc.AllgenralFunc()).shortDelay()
					TestObject delBtnOKMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[ @class="btn blueBtn pull-right dialogboot" and @data-bb-handler="confirm"]')
					WebUI.click(delBtnOKMsg)
					KeywordUtil.logInfo("Cliked on delete workspace..."+DWSname)
					//Click on success message of deleted workspace
					(new generalFunc.AllgenralFunc()).shortDelay()
					TestObject okBtnSuccMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
					WebUI.click(okBtnSuccMsg)
					KeywordUtil.logInfo(DWSname + "...Deleted Successfully...")

					//Reset Search criteria
					TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@id="resetSearchWorkspace"]')
					WebUI.click(resetSearch)

				}

				else
				{

					sharedWSTab()
					if((WebUI.verifyElementVisible(deltWS, FailureHandling.OPTIONAL)==true))
					{
						WebUI.scrollToElement(deltWS, 10)
						WebUI.click(deltWS)
						//Click on delete confirmation message
						(new generalFunc.AllgenralFunc()).shortDelay()
						TestObject delBtnOKMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[ @class="btn blueBtn pull-right dialogboot" and @data-bb-handler="confirm"]')
						WebUI.click(delBtnOKMsg)
						KeywordUtil.logInfo("Cliked on delete workspace..."+DWSname)
						//Click on success message of deleted workspace
						(new generalFunc.AllgenralFunc()).shortDelay()
						TestObject okBtnSuccMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
						WebUI.click(okBtnSuccMsg)
						KeywordUtil.logInfo(DWSname + "...Deleted Successfully...")

						//Reset Search criteria
						TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@id="resetSearchWorkspace"]')
						WebUI.click(resetSearch)
					}
					else
					{

						KeywordUtil.logInfo(DWSname+".......Already deleted")
					}

				}

			}
		}
		else
		{
			KeywordUtil.logInfo("Delete Workspace info is blank")
		}

	}// delteWS()









	/* ***************************** End of Workspace Functions ****************************** */	


	@Keyword
	def switchToTabular()
	{

		//Click on Tabular Tab and make object for the same
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject switchToTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', 'tab-cls-anch', '', 'aria-controls','pivotTab' ,'')
		WebElement element = WebUiCommonHelper.findWebElement(switchToTab,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		//Validation for warning message if it presents
		TestObject fltORgrpErr = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', 'sa-confirm-button-container', '', '','' ,'')
		if(WebUI.verifyElementPresent(fltORgrpErr,10,FailureHandling.OPTIONAL)==true)
		{
			WebUI.click(fltORgrpErr)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("No warnig messages displayed")
		}
		(new generalFunc.AllgenralFunc()).longDelay()


	}//switchToTabular()

	@Keyword
	//Function Created on 28-04-2020
	//Export from standard mode
	def exportFrmSM(String ESMfileNM,String ESMcmnts){

		if(ESMfileNM != "")
		{


			//Click on export Icon in standard mode
			TestObject exportIcon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'exportMySel', 'oldtitle', 'Export My Selection', '')
			WebUI.click(exportIcon)

			//Set name for Export from Standard Mode
			TestObject exportFNM = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control', 'myselexportnm', '', '', '')
			WebUI.click(exportFNM)
			WebUI.sendKeys(exportFNM, ESMfileNM)
			WebUI.sendKeys(exportFNM,Keys.chord(Keys.ENTER))

			//Set Comments on Export from Standard Mode
			if(ESMcmnts != "")
			{
				TestObject exportcmnts = (new generalFunc.AllgenralFunc()).makeTestObject('textarea', '', 'form-control scrollbarAll', 'myselexportcmnts', '', '', '')
				WebUI.click(exportcmnts)
				WebUI.sendKeys(exportcmnts, ESMcmnts)
				WebUI.sendKeys(exportcmnts,Keys.chord(Keys.ENTER))
				//Click on Export button
				TestObject exportBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn', 'exportmyselbtn', '', '', '')
				WebUI.click(exportBtn)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on 'OK' button of queued message
				TestObject OKbtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '', '', '')
				WebUI.click(OKbtn)

			}
			else
			{
				//Click on Export button
				TestObject exportBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn', 'exportmyselbtn', '', '', '')
				WebUI.click(exportBtn)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on 'OK' button of queued message
				TestObject OKbtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '', '', '')
				WebUI.click(OKbtn)
			}



		}else
		{
			KeywordUtil.logInfo("Export from Standard mode info details are not provided")
		}


	}//exportFrmSM()

	@Keyword
	//click on download link
	def downloadIcon(){

		TestObject downloadIcn = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', '', 'icon', '', '', '')
		//(new generalFunc.AllgenralFunc()).shortDelay()
		WebUI.click(downloadIcn)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//downloadIcon()



	@Keyword
	//Function created on 06-05-2020
	//click on loadmore button
	def loadmoreBtn(){

		TestObject loadmoreBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//*[@id="mCSB_6_container"]/center/button')
		(new generalFunc.AllgenralFunc()).clickUsingJS(loadmoreBtn,10)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//loadmoreBtn()


	@Keyword
	//Function created on 06-05-2020
	//click on restore last workspace
	def restoreLastWS(){

		//Create object to click on restore workspace icon
		TestObject restoreWSIcon = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@id="wrkSpace-restore"]')

		WebUI.click(restoreWSIcon)
		(new generalFunc.AllgenralFunc()).shortDelay()

		//check for the workspace name visible or no and then click on it
		TestObject restoreWSNM = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="wrkSpaceNmCls" and @id="wrkspcnmSpan"]')
		if(WebUI.verifyElementVisible(restoreWSNM))
		{
			String titleWS = WebUI.getAttribute(restoreWSNM,'title')
			KeywordUtil.logInfo("Restored Last workspace named..."+ titleWS)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Error:Could not click on Restore last workspace Name")
			(new generalFunc.AllgenralFunc()).shortDelay()
		}


	}//restoreLastWS()
	@Keyword
	//Function created on 28-09-2020
	//Edit attribute
	def doEditAttrVal(String EdititemID,String EAttrMsrNM,String newEditVal,String nSep,String vSep ){
		if(EdititemID!="")
		{
			def mulIds = EdititemID.split(nSep)
			def mulNMmsr = EAttrMsrNM.split(nSep)
			def mulNewVal = newEditVal.split(nSep)
			int lenmulIds = mulIds.size()
			for(def index:(0..lenmulIds-1))
			{
				String prodId = mulIds[index].trim()
				String attrMsrNM = mulNMmsr[index].trim()
				String attrMsrVal = mulNewVal[index].trim()
				//Click on Item window
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+prodId+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.scrollToElement(itemIDObj, 10)
				WebUI.click(itemIDObj)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on Edit Icon
				TestObject editIcon = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="edit-icon-lg-white" and @id="attrEdit"]')
				WebUI.click(editIcon)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Check for multiple attribute and measures
				def mulMNMvals = attrMsrNM.split(vSep)
				def mulAMVals = attrMsrVal.split(vSep)
				int lenmul = mulMNMvals.size()
				for(def index1:(0..lenmul-1))
				{
					String NMattrmsr = mulMNMvals[index1].trim()
					String VALattrmsr = mulAMVals[index1].trim()

					//Click on attribute/measure value drop down
					TestObject clickOnValDropdowndiv = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="pull-left attrnm-div" and @data-attrnm="'+NMattrmsr+'"]/following-sibling::div')
					WebUI.scrollToElement(clickOnValDropdowndiv, 10)
					WebUI.click(clickOnValDropdowndiv)
					(new generalFunc.AllgenralFunc()).shortDelay()
					KeywordUtil.logInfo("Clicked on Attribute/Measure.."+NMattrmsr+ "..value dropdown")

					//Click the attribute value in div
					TestObject attrMsrDiv = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//div[.="'+VALattrmsr+'"]')
					WebUI.scrollToElement(attrMsrDiv,10)
					WebUI.click(attrMsrDiv)
					(new generalFunc.AllgenralFunc()).shortDelay()
					KeywordUtil.logInfo("Clicked on Attribute/Measure Value .."+VALattrmsr+ "..in the dropdown list")
				}

				//Click on Save Edit Attribute Changes button
				TestObject saveEditChngs = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@id="saveEditAttr"]')
				WebUI.scrollToElement(saveEditChngs,10)
				WebUI.click(saveEditChngs)
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Clicked on Save button on Edit attribute window")
				//Click on 'Ok' button in save success message
				TestObject saveSucsMsg = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
				WebUI.click(saveSucsMsg)
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Attribute changes saved successfully..")
				//Close item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Item window closed..")


			}



		}


		else
		{
			KeywordUtil.logInfo("Edit attribute info is not available")
		}


	}//doEditAttrVal()


	@Keyword
	//Function created on 11-05-2020
	//set zoom level
	def setZoomLvl(String setZoomLvl){

		//Object for Zoom Minus
		TestObject zoomMinus = (new generalFunc.AllgenralFunc()).makeTestObject('', '', 'minus_icon', 'zoomminus', '', '', '')
		//Object for Zoom Plus
		TestObject zoomPlus = (new generalFunc.AllgenralFunc()).makeTestObject('', '', 'plus_icon', 'zoomplus', '', '', '')

		//Object for Zoom Plus
		TestObject zoomZero = (new generalFunc.AllgenralFunc()).makeTestObject('', '', 'slider-selection', '', 'style', 'left: 0%; width: 0%;', '')

		//zoom slicer object
		TestObject zSlider = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//div[@class="slider-track"]/div[@class="slider-handle round"]')

		String getSlider = WebUI.getText(zSlider)
		Double actualZoom = Double.parseDouble(getSlider)
		KeywordUtil.logInfo("int value"+actualZoom+"String value:"+getSlider)

		//Difference of zoom
		Double inputZoom = Double.parseDouble(setZoomLvl)
		Double getDiff = (inputZoom - actualZoom).round(2)
		KeywordUtil.logInfo("difference def value"+ getDiff)

		//loop the clicks
		if(inputZoom>=actualZoom)
		{
			KeywordUtil.logInfo("Inputzoom is greater than actualzoom ...call zoom plus")

		}
		else
		{
			KeywordUtil.logInfo("Inputzoom is greater than actualzoom ...call zoom minus")
		}







		/*
		 if(setZoomLvl == "0.3")
		 {
		 if(getSlider== setZoomLvl)
		 {
		 KeywordUtil.logInfo("Zoom Level is 0.3")
		 }
		 else if(getSlider=="0.4")
		 {
		 WebUI.click(zoomMinus)
		 }
		 else if(getSlider=="0.5")
		 {
		 WebUI.
		 }
		 }*/



	}//setZoomLvl()

	@Keyword
	//Created on 11-09-20
	//Histogram filters
	def histFlts(String grpName)
	{
		//Click on group histogram Icon
		String xPath = '//span[@id="grpHistBtnId" and @class="grpHistBtn grphist-icon-md-blue statUnClickClass"]'
		//TestObject grpHist = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', 'row ui-selectable', '', '', '', '')
		TestObject grpHist = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', 'main-grp lasso-grp-item ui-selectable', '', '', '','')
		String grpNM = WebUI.getAttribute(grpHist,"data-grpval")
		KeywordUtil.logInfo("group name...."+grpNM)


	}//histFlts()

	@Keyword
	//Created on 11-09-20
	//Error Message Validation for 5k items on groups
	def grpdataErr5K()
	{
		//Sorry! Group operation result contains more than 5000 items. Please try reducing the scope of the filter conditions.
		//Verify Text Present
		if(WebUI.verifyTextPresent("Sorry! Group operation result contains more than 5000 items. Please try reducing the scope of the filter conditions.", false, FailureHandling.CONTINUE_ON_FAILURE)==true)
		{
			KeywordUtil.logInfo("Error message displayed for more than 5k items on grouped data...")
			TestObject OKBtnErr = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
			WebUI.click(OKBtnErr)
			KeywordUtil.logInfo("Clicked on OK button in error message...")
		}
		else
		{
			KeywordUtil.logInfo("5k error meesage not displayed...")
		}

	}









	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
