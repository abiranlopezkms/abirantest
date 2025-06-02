import java.text.SimpleDateFormat
import java.util.Locale

// read CSVs
def readCsvFile(path) {
    def lines = new File(path).readLines()
    def headers = lines[0].split(',').collect { it.trim() }
    def rows = []

    lines.tail().each { line ->
        def values = line.split(',', -1)
        def rowMap = [:]
        headers.eachWithIndex { headerName, columnIndex -> rowMap[headerName] = values[columnIndex] }
        rows << rowMap
    }

    return rows
}

List<Map<String, String>> employeeList = readCsvFile("C:\\Users\\AbiranLopez\\Documents\\katalontest\\employees.csv")
Map<String, String> exchangeRateRow = readCsvFile("C:\\Users\\AbiranLopez\\Documents\\katalontest\\exchange_rate.csv")[0]
def usdToVnd = exchangeRateRow["VND"]?.toString()?.isNumber() ? exchangeRateRow["VND"].toDouble() : 0

// setup date parsing
def dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
def thresholdDate = dateFormat.parse("01-Jan-2011")

// apply filters
def employeeFilters = [
    salaryOfEmployeesNamedBradley: employeeList.findAll {
        it["Name"]?.toLowerCase()?.contains('bradley')
    },

    salaryOfEmployeesGreaterThan400: employeeList.findAll {
        it["Salary"]?.toString()?.isNumber() && it["Salary"].toDouble() > 400
    },

    firstThreeEmployeesFromTokyo: employeeList.findAll {
        it["Office"] == "Tokyo"
    }.take(3),

    employeesWithAgeLessThanOrEqualTo40: employeeList.findAll {
        it["Age"]?.toString()?.isNumber() && it["Age"].toDouble() <= 40
    },

    employeesWithAgeContainingDigit3: employeeList.findAll {
        it["Age"]?.toString()?.contains("3")
    },

    employeesWithStartDateAfter2011: employeeList.findAll {
        def rawStartDate = it["Start date"]
        try {
            rawStartDate && dateFormat.parse(rawStartDate) >= thresholdDate
        } catch (ignored) {
            false
        }
    },

    accountsOrEngineersWithSalaryBelow5MVnd: employeeList.findAll {
        def positionValue = it["Position"]?.toLowerCase()
        def salary = it["Salary"]?.toString()?.isNumber() ? it["Salary"].toDouble() : 0

        positionValue && (positionValue.contains("accountant") || positionValue.contains("software engineer")) &&
        (salary * usdToVnd < 5_000_000)
    }
]

// print results
employeeFilters.each { key, list ->
    println "\n=== ${key} (${list.size()} results) ==="
    list.each { println it }
}