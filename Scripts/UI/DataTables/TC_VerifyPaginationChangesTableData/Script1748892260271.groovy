import pages.DataTablesPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String page1Text = WebUI.getText(DataTablesPage.getFirstCellByColumn(1))
WebUI.comment("Page 1 - First cell: " + page1Text)

WebUI.click(DataTablesPage.getNextButton())
WebUI.delay(2)
String page2Text = WebUI.getText(DataTablesPage.getFirstCellByColumn(1))
WebUI.comment("Page 2 - First cell: " + page2Text)

assert page1Text != page2Text : "Pagination did not load different data."
