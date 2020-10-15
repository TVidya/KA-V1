import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.stringtemplate.v4.compiler.STParser.ifstat_return as ifstat_return
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.keyword.builtin.VerifyMatchKeyword as VerifyMatchKeyword
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.Keys as Keys
//import com.kms.katalon.core.webui.key
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
//import org.eclipse.jetty.client.ContinueProtocolHandler.ContinueListener as ContinueListener
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.keyword.excel.ExcelKeywords


KeywordLogger log = new KeywordLogger()

//Start fresh or continue
CustomKeywords.'com.BB.genralFunctions.startFresh'(startFresh)

//set zoom
CustomKeywords.'standardFunc.AllstandardFunc.setZoomLvl'(setZoomLvl)

/*
 searchMsrNdFlt
//Filter with attributes
//Select multiple attributes and values
CustomKeywords.'com.BB.genralFunctions.selAttrNdVals'( attrNames, attrVals,nSep,vSep)
//Select hierarchy attribute and values
CustomKeywords.'com.BB.genralFunctions.selHAttrNdVals'( hAttrNames, hAttrVals,nSep,vSep)
WebUI.delay(5)
//SetText tag
CustomKeywords.'com.BB.genralFunctions.setTextTag'(TextTag,nSep)
WebUI.delay(5)
//Click on Measure Name
CustomKeywords.'com.BB.genralFunctions.SetMsrValbyFunc'(msrNames,minVal ,maxVal, nSep)
WebUI.delay(5)

//Select attributes/measure for show data
CustomKeywords.'com.BB.genralFunctions.doShowData'(showDataAttrName, nSep)
WebUI.delay(5)
//Apply Sort and stats on measures
//Click on Sort control Icon
//Select measures and set  sort order
CustomKeywords.'com.BB.genralFunctions.doSort'(sortAttrName,  sortStatus, nSep,vSep )
WebUI.delay(10)
//Select measure for enable stats
CustomKeywords.'com.BB.genralFunctions.doStats'(statsAttrName, nSep)
//Click on done in Sort and Stats control
CustomKeywords.'com.BB.genralFunctions.sortDone'(sortAttrName,statsAttrName)
//Group by attributes and tags
CustomKeywords.'com.BB.genralFunctions.clickGrpAttrs'(groupByAttrName,  nSep)
CustomKeywords.'com.BB.genralFunctions.clickGrpTags'(groupByTag)
//Set Item limit
CustomKeywords.'com.BB.genralFunctions.setItemLimit'(limit)

//Apply Filters
//Click on apply
CustomKeywords.'com.BB.genralFunctions.FilterApplyBtn'()
CustomKeywords.'com.BB.genralFunctions.longDelay'()
//compare item count
CustomKeywords.'com.BB.genralFunctions.CompareItemCnt'(expectedItemCnt)
CustomKeywords.'com.BB.genralFunctions.longDelay'()

//Create New Tabular
CustomKeywords.'com.BB.genralFunctions.newTabular'(newTbl)

//config tabular
CustomKeywords.'com.BB.genralFunctions.configTabular'(rowFields, colFields, dataFields, nSep, to,msrNameT,aggrType,rowSubTotal,preview,ImageTabularEnable,colSubTotal,grandTotal,itemCount,tableName)

//Apply Tabular
CustomKeywords.'com.BB.genralFunctions.TabularApply'()

//Filters with in the tabular
CustomKeywords.'com.BB.genralFunctions.tblselAttrNdVals'(tattrNames, tattrVals, nSep, vSep)

//Reset Attributes or measures
CustomKeywords.'com.BB.genralFunctions.resetAttrOrMsr'(resetAttrOrMsrnm, nSep)

//chk sort order
CustomKeywords.'com.BB.genralFunctions.tdoSort'(tsortAttrName,tattrStatus,nSep,vSep)

//chk sort order for row fields
//chk sort order
CustomKeywords.'com.BB.genralFunctions.trowdoSort'(tsortRAttrName,tattrRStatus,nSep,vSep)

//Apply Tabular
CustomKeywords.'com.BB.genralFunctions.TabularApply'()

//Copy Pivot
CustomKeywords.'com.BB.genralFunctions.copyPivotTbl'(copyPivot)

//RemoveAll Tabels
CustomKeywords.'com.BB.genralFunctions.resetAllTbls'(removeAlltbls)


//Save Tabular
CustomKeywords.'com.BB.genralFunctions.saveWS'(workspaceNameS)

//Save Tabular
CustomKeywords.'com.BB.genralFunctions.exportAllTabularR15'(exportFileName)

*/







