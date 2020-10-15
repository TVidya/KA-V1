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

CustomKeywords.'generalFunc.AllgenralFunc.startFresh'(startFresh)
CustomKeywords.'standardFunc.AllstandardFunc.searchAttrNdFlt'(sAttrNM, attrVals, nSep, vSep)
//Click on apply
CustomKeywords.'standardFunc.AllstandardFunc.FilterApplyBtn'()
CustomKeywords.'generalFunc.AllgenralFunc.shortDelay'()
//Compare show data value
CustomKeywords.'standardFunc.AllstandardFunc.compareShowData'(sdItemID,sdVals,nSep,vSep)
CustomKeywords.'generalFunc.AllgenralFunc.shortDelay'()
/*
//SetText tag
CustomKeywords.'standardFunc.AllstandardFunc.setTextTag'(TextTag,nSep)
WebUI.delay(5)
//Click on apply
CustomKeywords.'standardFunc.AllstandardFunc.FilterApplyBtn'()
CustomKeywords.'generalFunc.AllgenralFunc.shortDelay'()
//Save as a workspace
CustomKeywords.'standardFunc.AllstandardFunc.SaveWorkspace'(wsName, keepShareStngs)
CustomKeywords.'generalFunc.AllgenralFunc.shortDelay'()
*/

/*
//Filter with attributes
//Select multiple attributes and values
CustomKeywords.'standardFunc.AllstandardFunc.selAttrNdVals'( attrNames, attrVals,nSep,vSep)
//Select hierarchy attribute and values
CustomKeywords.'standardFunc.AllstandardFunc.selHAttrNdVals'( hAttrNames, hAttrVals,nSep,vSep)
WebUI.delay(5)
//SetText tag
CustomKeywords.'standardFunc.AllstandardFunc.setTextTag'(TextTag,nSep)
WebUI.delay(5)
//Click on Measure Name
CustomKeywords.'standardFunc.AllstandardFunc.SetMsrValbyFunc'(msrNames,minVal ,maxVal, nSep)
WebUI.delay(5)

//Select attributes/measure for show data
CustomKeywords.'standardFunc.AllstandardFunc.doShowData'(showDataAttrName, nSep)
WebUI.delay(5)
//Apply Sort and stats on measures
//Click on Sort control Icon
//Select measures and set  sort order
CustomKeywords.'standardFunc.AllstandardFunc.doSort'(sortAttrName,  sortStatus, nSep,vSep )
WebUI.delay(10)
//Select measure for enable stats
CustomKeywords.'standardFunc.AllstandardFunc.doStats'(statsAttrName, nSep)
//Click on done in Sort and Stats control
CustomKeywords.'standardFunc.AllstandardFunc.sortDone'(sortAttrName,statsAttrName)
//Group by attributes and tags
CustomKeywords.'standardFunc.AllstandardFunc.clickGrpAttrs'(groupByAttrName,  nSep)
CustomKeywords.'standardFunc.AllstandardFunc.clickGrpTags'(groupByTag)
//Set Item limit
CustomKeywords.'standardFunc.AllstandardFunc.setItemLimit'(limit)

//Apply Filters
//Click on apply
CustomKeywords.'standardFunc.AllstandardFunc.FilterApplyBtn'()
CustomKeywords.'generalFunc.AllgenralFunc.shortDelay'()
//compare item count
CustomKeywords.'standardFunc.AllstandardFunc.CompareItemCnt'(expectedItemCnt)
CustomKeywords.'generalFunc.AllgenralFunc.longDelay'()

//Create New Tabular 
CustomKeywords.'tabularFunc.AlltabularFunc.newTabular'(newTbl)
//config tabular
CustomKeywords.'tabularFunc.AlltabularFunc.configTabularR15'(rowFields, colFields, dataFields, nSep,vSep, to,msrNameT,aggrType,rowSubTotal,rFieldnm,colSubTotal,cFieldnm,preview,ImageTabularEnable,itemCount,tableName)
//CustomKeywords.'tabularFunc.AlltabularFunc.configTabular'(rowFields,colFields,  dataFields,  nSep,vSep, to, msrNameT, aggrType, rowSubTotal, rFieldnm,colSubTotal, cFieldnm, preview, ImageTabularEnable,  itemCount,  tableName)
CustomKeywords.'generalFunc.AllgenralFunc.longDelay'()

//Apply Tabular Config
CustomKeywords.'tabularFunc.AlltabularFunc.TabularApply'()
CustomKeywords.'generalFunc.AllgenralFunc.shortDelay'()


//Filters with in the tabular
CustomKeywords.'tabularFunc.AlltabularFunc.tblFilters'(tattrNames, tattrVals, nSep, vSep)



//Copy Pivot
CustomKeywords.'tabularFunc.AlltabularFunc.copyPivotTbl'(copyPivot)

//chk sort order
CustomKeywords.'tabularFunc.AlltabularFunc.tdoSortR15'(tsortAttrName,tattrStatus,nSep,vSep)
WebUI.delay(10)


//chk sort order for row fields
//chk sort order
CustomKeywords.'tabularFunc.AlltabularFunc.trowdoSortR15'(tsortRAttrName,tattrRStatus,nSep,vSep)



//Reset Attributes or measures
CustomKeywords.'tabularFunc.AlltabularFunc.resetAttrOrMsr'(resetAttrOrMsrnm, nSep)

//Save Tabular
CustomKeywords.'standardFunc.AllstandardFunc.SaveWorkspace'(workspaceNameS)

//Export Tabularr
WebUI.delay(10)
CustomKeywords.'tabularFunc.AlltabularFunc.exportAllTabularR15'(exportFileName)


*/





