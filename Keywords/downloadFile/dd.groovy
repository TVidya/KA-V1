package downloadFile

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable


public class DownloadManager {

	private static final String downloadPath = "C:/katalon_test_downloads";

	private DownloadManager() {}

	public static File getLastDownloadedFile() {
		File downloadDirectory = new File(downloadPath);
		File[] downloadedFiles = downloadDirectory.listFiles();
		if(downloadedFiles == null || downloadedFiles.length == 0) {
			return null;
		}

		File lastModifiedFile = downloadedFiles[0];
		for(int i = 1; i < downloadedFiles.length; i++) {
			if(lastModifiedFile.lastModified() < downloadedFiles[i].lastModified()) {
				lastModifiedFile = downloadedFiles[i];
			}
		}
		return lastModifiedFile;
	}

	public static File getDownloadedFile(final String fileName) {
		boolean downloaded = isFileDownloaded(fileName);
		if(downloaded) {
			return new File(downloadPath + File.separator + fileName);
		}
		else {
			return null;
		}
	}

	public static boolean isFileDownloaded(final String fileName) {
		File downloadDirectory = new File(downloadPath);
		File[] downloadedFiles = downloadDirectory.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.equals(fileName);
					}
				});
	}

	public static void waitForDownload() {
		File downloadDirectory = new File(downloadPath);
		int fileCount = downloadDirectory.listFiles().length;
		int expectedFileCount = fileCount + 1;
		long startTime = System.currentTimeMillis();
		while(fileCount < expectedFileCount && (System.currentTimeMillis() - startTime) < 300000) {
			fileCount = downloadDirectory.listFiles().length;
			Thread.sleep(1000);
		}
	}
}

