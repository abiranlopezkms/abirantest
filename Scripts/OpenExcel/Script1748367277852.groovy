import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.*
import groovy.json.JsonOutput

import java.text.SimpleDateFormat
import java.util.Locale

// read excel file
String excelPath = 'C:\\Users\\AbiranLopez\\Documents\\katalontest\\Data.xlsx'
FileInputStream fis = new FileInputStream(excelPath)
Workbook workbook = new XSSFWorkbook(fis)

List<Map> employeeList = []
Sheet employeeListheet = workbook.getSheet('employee')
def header = employeeListheet.getRow(0).collect { it.stringCellValue }

employeeListheet.eachWithIndex { row, idx ->
    if (idx == 0) return
    def employeeData = [:]
    row.eachWithIndex { cell, i ->
        def col = header[i]
        def value
		switch (cell.getCellType()) {
			case CellType.STRING:
			value = cell.getStringCellValue()
			break
			case CellType.NUMERIC:
			value = DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() : cell.getNumericCellValue()
			break
			default:
			value = cell.toString()
		}
		employeeData[col] = value
    }
    employeeList << employeeData
}

// get exchange rate
Sheet exchangeSheet = workbook.getSheet('exchange rate')
double usdToVnd = exchangeSheet.getRow(1).getCell(1).numericCellValue

workbook.close()
fis.close()


// setup date format
def sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
def thresholdDate = sdf.parse("01-Jan-2011")

// apply filters and print
def filters = [
    salaryOfEmployeesNamedBradley: employeeList.findAll { it.Name?.toLowerCase()?.contains('bradley') },
    salaryOfEmployeesGreaterThan400: employeeList.findAll { 
		def salary = it.Salary
		salary != null && salary.toString().isNumber() && salary.toDouble() > 400
		},
    firstThreeEmployeesFromTokyo: employeeList.findAll { it.Office == 'Tokyo' }.take(3),
    employeesWithAgeLessThanOrEqualTo40: employeeList.findAll { 
		def ageVal = it.Age
		ageVal != null && ageVal.toString().isNumber() && ageVal.toDouble() <= 40
		},
    employeesWithAgeContainingDigit3: employeeList.findAll { it.Age?.toString()?.contains("3") },
    employeesWithStartDateAfter2011: employeeList.findAll {
       def rawStartDate = it['Start date']
	   if (rawStartDate instanceof Date) {
		   return rawStartDate >= thresholdDate
	   }
	   if (rawStartDate instanceof String) {
		   try {
			   return sdf.parse(rawStartDate) >= thresholdDate
		   } catch (e) {
			   return false
		   }
	   }
    },
    accountsOrEngineersWithSalaryBelow5MVnd: employeeList.findAll {
        def positionValue = it.Position?.toLowerCase()
        (positionValue?.contains("accountant") || positionValue?.contains("software engineer")) &&
        it.Salary instanceof Number && (it.Salary * usdToVnd < 5_000_000)
    }
]

filters.each { key, list ->
    println "\n=== ${key} (${list.size()} results) ==="
    list.each { println it }
}


// export all employees and exchange rate to JSON files
def employeeJsonPath = 'C:\\Users\\AbiranLopez\\Documents\\katalontest\\employees.json'
def exchangeJsonPath = 'C:\\Users\\AbiranLopez\\Documents\\katalontest\\exchange_rate.json'

new File(employeeJsonPath).text = JsonOutput.prettyPrint(JsonOutput.toJson(employeeList))
new File(exchangeJsonPath).text = JsonOutput.prettyPrint(JsonOutput.toJson([USD: 1, VND: usdToVnd]))