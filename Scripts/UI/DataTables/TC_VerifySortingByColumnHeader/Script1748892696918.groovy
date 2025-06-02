import pages.DataTablesPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String beforeSort = WebUI.getText(DataTablesPage.getFirstCellByColumn(6))
WebUI.comment("Before sort: " + beforeSort)

WebUI.click(DataTablesPage.getColumnHeaderByName('Extn'))
WebUI.delay(2)
String afterSort = WebUI.getText(DataTablesPage.getFirstCellByColumn(6))
WebUI.comment("After sort: " + afterSort)

assert beforeSort != afterSort : "Sorting by 'Extn' did not change row order."
