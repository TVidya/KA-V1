package layoutFunc

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

public class AlllayoutFunc {

	@Keyword
	def loadTemplate(String tempName) {
		if(tempName != "")
		{
			(new tabularFunc.AlltabularFunc()).switchToLayoutFromTabular()
			(new generalFunc.AllgenralFunc()).shortDelay()
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject tempN = (new generalFunc.AllgenralFunc()).makeTestObject('img', '', 'templateDP', '', 'data-dptemplate',tempName ,'')
			WebElement element = WebUiCommonHelper.findWebElement(tempN,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).longDelay()
		}else
		{
			KeywordUtil.logInfo("tempName is blank")
		}


	}//loadTemplate(String tempName)

	@Keyword
	//Function to click on ' Current Template ' Tab
	def currentTemplate() {


		TestObject currentTemp = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Current Template', '', 'vi-expcurrenttmp', '','' ,'')
		WebUI.click(currentTemp)
		(new generalFunc.AllgenralFunc()).longDelay()
	}//currentTemplate

	@Keyword
	def clickOnExportBtnL()
	{
		//Click on Export button in Layout
		TestObject exportInLayout = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', 'export layoutmenu-link', '', 'href','#export' ,'')
		WebUI.click(exportInLayout)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//clickOnExportBtnL()


	@Keyword
	//Function exports template from tempalte
	//Requires 'Exportfilename' and 'Exportformat'
	def exportTemplateL(String tplExportFnm, String tplExportFormat) {

		if(tplExportFnm != "" & tplExportFormat != "" )
		{

			//Click on Export Tab
			clickOnExportBtnL()
			//Click on Current Template
			currentTemplate()

			//Set Export template filename
			TestObject tplExportFile = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control', 'tpl-export-exportfilename', '','' ,'')
			(new generalFunc.AllgenralFunc()).shortDelay()
			WebUI.click(tplExportFile)
			WebUI.setText(tplExportFile, tplExportFnm)
			WebUI.sendKeys(tplExportFile, Keys.chord(Keys.ENTER))
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Click on exportFormat
			//validate the format then make object inorder export the ssame
			if(tplExportFormat == "png")
			{
				TestObject formatPNG = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn btn-sm fmt vi-export', 'png', '','' ,'')
				WebUI.delay(5)
				WebUI.click(formatPNG)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object for export success button
				TestObject exportedSucc = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportedSucc)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else if(tplExportFormat == "jpg")
			{
				TestObject formatPNG = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn btn-sm fmt vi-export', 'jpg', '','' ,'')

				WebUI.click(formatPNG)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object for export success button
				TestObject exportedSucc = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportedSucc)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else if(tplExportFormat == "pdf")
			{
				TestObject formatPNG = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn btn-sm fmt vi-export', 'pdf', '','' ,'')

				WebUI.click(formatPNG)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object for export success button
				TestObject exportedSucc = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportedSucc)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else
			{
				
				KeywordUtil.markFailed("tplExportFormat is blank/invalid")
			}

		}else
		{
			KeywordUtil.markFailed("tplExportFormat and filename is blank")
		}

	}//exportTemplateL

	@Keyword
	//Switch to Schedule Module
	def switchToScheduleL()
	{
		//Click on Export button in Layout
		TestObject schdTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'vi-schedule', 'href','#' ,'')
		WebUI.click(schdTab)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//switchToScheduleL()

	@Keyword
	//Select Export mode
	def selectExportMode()
	{
		//Click on ExportMode dropdown
		TestObject clickOnExportModeDD = (new generalFunc.AllgenralFunc()).makeTestObject('span', 'Select Export Mode', '', 'select2-chosen-8', '','' ,'')
		WebUI.click(clickOnExportModeDD)
		(new generalFunc.AllgenralFunc()).shortDelay()
		//Click on Search Input inside dropdown
		TestObject clickOnExportModesearch = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', '', 's2id_autogen8_search', '','' ,'')
		WebUI.click(clickOnExportModesearch)
		(new generalFunc.AllgenralFunc()).shortDelay()
		WebUI.setText(clickOnExportModesearch,'Only Tabular (Pivot Table)')
		WebUI.sendKeys(clickOnExportModesearch,Keys.chord(Keys.ENTER))
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//selectExportMode()

	@Keyword
	//set Export format
	def exportFormat(String formatType)
	{
		if(formatType != "")
		{
			//Click on export Tab
			WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/exportbtn'))

			//For PNG
			//click on current template
			WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/a_Current Template'))

			//Set export file name
			WebUI.setText(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/Exportformats/outputfilename'), 'PNG')

			TestObject formatN = (new generalFunc.AllgenralFunc()).makeTestObject('button', formatType, '', 'png', '', '','')
			(new generalFunc.AllgenralFunc()).shortDelay()
			WebUI.click(formatN)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on output file export request popup OK button
			WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/Exportformats/OutptreqconfirmButtonOK'))
			(new generalFunc.AllgenralFunc()).shortDelay()
		}else{
			KeywordUtil.logInfo("Format type is not available ")
		}
	}//static exportFormat(String formatType)








}
