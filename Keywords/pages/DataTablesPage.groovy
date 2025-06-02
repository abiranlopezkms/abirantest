package pages

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

class DataTablesPage {

    static TestObject getSearchInput() {
        TestObject input = new TestObject('search_input')
        input.addProperty('xpath', ConditionType.EQUALS, '//div[@class="demo-html"]//input[@type="search"]')
        return input
    }

    static TestObject getOfficeCellByText(String city) {
        TestObject cell = new TestObject("office_cell_${city}")
        cell.addProperty('xpath', ConditionType.EQUALS, "//table[@id='example']//tbody//tr/td[3][text()='${city}']")
        return cell
    }

    static TestObject getVisibleRows() {
        TestObject rows = new TestObject('all_visible_rows')
        rows.addProperty('xpath', ConditionType.EQUALS, '//table[@id="example"]//tbody//tr')
        return rows
    }

    static TestObject getFirstCellByColumn(int columnIndex) {
        TestObject cell = new TestObject("first_cell_col_$columnIndex")
        cell.addProperty('css', ConditionType.EQUALS, "#example tbody tr:nth-child(1) td:nth-child(${columnIndex})")
        return cell
    }

    static TestObject getColumnHeaderByName(String name) {
        TestObject header = new TestObject("header_${name}")
        header.addProperty('xpath', ConditionType.EQUALS, "//th[contains(.,'${name}')]")
        return header
    }

    static TestObject getNextButton() {
        TestObject next = new TestObject('next_button')
        next.addProperty('css', ConditionType.EQUALS, 'button.dt-paging-button.next')
        return next
    }
}