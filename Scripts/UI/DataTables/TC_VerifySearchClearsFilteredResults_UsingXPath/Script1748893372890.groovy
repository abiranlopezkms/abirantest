import pages.DataTablesPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.setText(DataTablesPage.getSearchInput(), 'Tokyo')
WebUI.delay(2)

def tokyoCount = WebUI.findWebElements(DataTablesPage.getOfficeCellByText('Tokyo'), 5).size()
WebUI.comment("Rows with 'Tokyo': $tokyoCount")
assert tokyoCount > 0 : "No rows with 'Tokyo' found after filtering"

WebUI.setText(DataTablesPage.getSearchInput(), '')
WebUI.delay(3)

def allRows = WebUI.findWebElements(DataTablesPage.getVisibleRows(), 5).size()
WebUI.comment("Rows after clearing filter: $allRows")
assert allRows > tokyoCount : "Table did not reset properly after clearing filter"  // this step fails because loading all the rows are not working properly
