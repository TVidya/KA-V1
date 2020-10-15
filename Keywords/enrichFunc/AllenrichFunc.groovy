package enrichFunc

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
import java.util.concurrent.TimeUnit

public class AllenrichFunc {

	@Keyword
	// Function created on 12-May
	// Switch to Enrich from standard mode
	def switchToEnrichFrmStd()
	{
		//Click on Enrich Icon in standard and make object for the same
		TestObject switchToEnrich =(new generalFunc.AllgenralFunc()). makeTestObject('li', '', '', 'goEnrich', 'data-url','/enrich/enrich' ,'')
		WebUI.click(switchToEnrich)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToEnrichFrmStd()

	@Keyword
	// Function created on 12-May
	// Switch to Enrich from side menu bar
	def switchToEnrichFrmSideMenu()
	{
		//Click on side Menu
		//Click on side menu and navigate to dashboard
		(new generalFunc.AllgenralFunc()).sideMenuBar()

		//Click on Enrich Icon in side menu and make object for the same
		TestObject switchToEnrich =(new generalFunc.AllgenralFunc()). makeTestObject('i', '', 'enrich_icon', '', '','' ,'')
		WebUI.click(switchToEnrich)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToEnrichFrmSideMenu()

	@Keyword
	// Function created on 12-May
	// Show ID
	def enrichShowID()
	{
		//Click on side Menu
		//Click on side menu and navigate to dashboard
		(new generalFunc.AllgenralFunc()).sideMenuBar()

		//Click on Enrich Icon in side menu and make object for the same
		TestObject switchToEnrich =(new generalFunc.AllgenralFunc()). makeTestObject('i', '', 'enrich_icon', '', '','' ,'')
		WebUI.click(switchToEnrich)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//enrichShowID()

	@Keyword
	//Created on 08-09-20
	//Click on Select All items check box
	def selAllitems()
	{
		(new generalFunc.AllgenralFunc()).shortDelay()
		//Make object for select all check box and click
		TestObject selectAllitemsInput =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//div[@id="assist-selectall"]//input')
		TestObject selectAllitemsLbl =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//div[@id="assist-selectall"]//label[@class="lbl_cls"]')
		//Verify select all checked or no
		if(WebUI.verifyElementNotChecked(selectAllitemsInput, 10, FailureHandling.CONTINUE_ON_FAILURE))
		{
			(new generalFunc.AllgenralFunc()).clickUsingJS(selectAllitemsLbl,10)
			KeywordUtil.logInfo("Clicked on Select all items")
		}
		else
		{
			KeywordUtil.logInfo("Selectall is already checked")

		}
	}//selAllitems()
	@Keyword
	//Created on 08-09-20
	//Updated on 09-09-20
	//Click on Color tag icon in Enrich Screen
	def doClrTag(String TclrGrp,String TclrName,String nSep,String vSep)
	{
		if(TclrGrp!="")
		{
			switchToEnrichFrmSideMenu()
			selAllitems()
			(new generalFunc.AllgenralFunc()).longDelay()
			def mTclrGrp = TclrGrp.split(nSep)
			def mTclrName = TclrName.split(nSep)
			int mGrplen = mTclrGrp.size()
			//Make object to color tag then click on it
			TestObject colorTagIcn =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//span[@id="multi-tag-btns"]//a[@id="assist-color-tags"]//span[@id="vi-glyphicon-tint"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(colorTagIcn,10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on show all tags check box
			TestObject showAllcolorTags =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//div[@class="chk-div"]//label[@class="lbl_cls chkTag_lbl" and @for="chkAttrPrivate1"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(showAllcolorTags,10)
			KeywordUtil.logInfo("Clicked on Show all color tags")
			(new generalFunc.AllgenralFunc()).shortDelay()

			for(def index :(0..mGrplen-1))
			{
				String cgrpNM = mTclrGrp[index].trim()
				String mcNM = mTclrName[index].trim()
				KeywordUtil.logInfo("Click on color group name..."+cgrpNM)
				//CLick on color group name
				TestObject clrGrpNM =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//li[@class="list-group-item clrGrpLi" and @data-clrgrp="'+cgrpNM+'"]')
				WebUI.click(clrGrpNM)
				KeywordUtil.logInfo("Clicked on group..."+cgrpNM)
				def colorsM = mcNM.split(vSep)
				int colorsMlen = colorsM.size()
				for(def index1 :(0..colorsMlen-1))
				{
					String clrName = colorsM[index1].trim()
					KeywordUtil.logInfo("Click on color name.."+clrName)
					//Click on Color name
					TestObject clrNM =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//div[@class="clr-tag-select" and @data-cname="'+clrName+'"]//div[@class="clr-tag-modal"]')
					(new generalFunc.AllgenralFunc()).shortDelay()
					(new generalFunc.AllgenralFunc()).clickUsingJS(clrNM,10)
					KeywordUtil.logInfo("Clicked on color name..."+clrName)
					(new generalFunc.AllgenralFunc()).longDelay()
				}
				//Click on Tag button
				TestObject clrNM =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//button[@id="save-colour-modal"]')
				WebUI.click(clrNM)
				KeywordUtil.logInfo("Clicked on tag button...")
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on OK button in success message
				TestObject OKbtn =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//button[@class="confirm"]')
				WebUI.click(OKbtn)
				KeywordUtil.logInfo("Clicked on OK button in success message...")

			}
		}
		else
		{
			KeywordUtil.logInfo("Color Tag info is not available")
		}
	}//doClrTag
	
	@Keyword
	//Created on 10-09-20
	//Click on Text tag icon in Enrich Screen and tag items with Text
	def doTxtTag(String addTxtTag,String nSep)
	{
		if(addTxtTag!="")
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			switchToEnrichFrmSideMenu()
			selAllitems()
			(new generalFunc.AllgenralFunc()).longDelay()
			def mATxtTags = addTxtTag.split(nSep)
			int mTagslen = mATxtTags.size()
			//Make object to click on text tag icon then click on it
			TestObject textTagIcn =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//span[@id="multi-tag-btns"]//a[@id="assist-text-tags"]//span[@id="vi-glyphicon-text"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(textTagIcn,10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			for(def index :(0..mTagslen-1))
			{
				String textTagsNM =  mATxtTags[index].trim()
				KeywordUtil.logInfo("Click on input and enter given text..."+textTagsNM)
			//Click on Input div to enter text tag
			TestObject divInput =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//input[@id="s2id_autogen4"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(divInput,10)
			WebUI.setText(divInput,textTagsNM )
			WebUI.sendKeys(divInput, Keys.chord(Keys.SPACE))
			(new generalFunc.AllgenralFunc()).shortDelay()
             //CLick on create button
			 TestObject TxtTagCrBtn =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//span[@id="addNew"]')
			 WebUI.click(TxtTagCrBtn)
			 KeywordUtil.logInfo("Clicked on Tag Create Button and Text tag..."+textTagsNM +"...added to the list ")
			//Click on OK button in Tag created confirmation message 
			TestObject okBtnTC =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//button[@class="confirm"]')
		    (new generalFunc.AllgenralFunc()).clickUsingJS(okBtnTC,10)
			KeywordUtil.logInfo("Clicked on Ok button in create Tag Success Message...")
		  }
		  //Click on Tag Text  button
		  TestObject tagTxt =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//button[@id="save-text-modal"]')
		  WebUI.click(tagTxt)
		  KeywordUtil.logInfo("Clicked on tag button...")
		  (new generalFunc.AllgenralFunc()).shortDelay()

		  //Click on OK button in  success message after tagging with text
		  TestObject OKbtnafterTag =(new generalFunc.AllgenralFunc()). makeTestObject('', '', '', '', '','' ,'//button[@class="confirm"]')
		  WebUI.click(OKbtnafterTag)
		  KeywordUtil.logInfo("Clicked on OK button in success message...after text tagging")

		}
		else
		{
			KeywordUtil.logInfo("Text Tag info is not available")
		}
	}//doTxtTag


}//public class AllenrichFunc