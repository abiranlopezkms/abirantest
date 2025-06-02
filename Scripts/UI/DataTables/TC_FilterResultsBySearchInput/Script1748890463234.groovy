import pages.DataTablesPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.setText(DataTablesPage.getSearchInput(), 'London')
WebUI.delay(2)
WebUI.verifyElementPresent(DataTablesPage.getOfficeCellByText('London'), 5)
