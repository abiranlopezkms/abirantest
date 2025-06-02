import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar

// load JSON files
String employeeJsonFilePath = 'C:\\Users\\AbiranLopez\\Documents\\katalontest\\employees.json'
String exchangeJsonFilePath = 'C:\\Users\\AbiranLopez\\Documents\\katalontest\\exchange_rate.json'

def employeesList = new JsonSlurper().parse(new File(employeeJsonFilePath))
def exchangeRateData = new JsonSlurper().parse(new File(exchangeJsonFilePath))
def usdToVnd = (exchangeRateData.VND ?: exchangeRateData[0]?.VND) as Double

// setup date format
def dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
def thresholdDate = dateFormat.parse("01-Jan-2011")

// apply filters
def employeeFilters = [
    salaryOfEmployeesNamedBradley: employeesList.findAll {
        it.Name?.toLowerCase()?.contains('bradley')
    },

    salaryOfEmployeesGreaterThan400: employeesList.findAll {
        def salaryValue = it.Salary
        salaryValue?.toString()?.isNumber() && salaryValue.toDouble() > 400
    },

    firstThreeEmployeesFromTokyo: employeesList.findAll {
        it.Office == 'Tokyo'
    }.take(3),

    employeesWithAgeLessThanOrEqualTo40: employeesList.findAll {
        def ageValue = it.Age
        ageValue?.toString()?.isNumber() && ageValue.toDouble() <= 40
    },

    employeesWithAgeContainingDigit3: employeesList.findAll {
        it.Age?.toString()?.contains("3")
    },

    employeesWithStartDateAfter2011: employeesList.findAll {
        def rawStartDate = it["Start date"]

        if (rawStartDate instanceof Date) {
            return rawStartDate >= thresholdDate
        }

        if (rawStartDate instanceof String) {
            try {
                return dateFormat.parse(rawStartDate) >= thresholdDate
            } catch (ignored) {
                return false
            }
        }

        return false
    },

    accountsOrEngineersWithSalaryBelow5MVnd: employeesList.findAll {
        def positionValue = it.Position?.toLowerCase()
        def salaryValue = it.Salary?.toString()?.isNumber() ? it.Salary.toDouble() : 0

        positionValue && (positionValue.contains("accountant") || positionValue.contains("software engineer")) &&
        (salaryValue * usdToVnd < 5_000_000)
    }
]

// print to console
employeeFilters.each { key, list ->
    println "\n=== ${key} (${list.size()} results) ==="
    list.each { println it }
}

// write employees.csv
def allHeaders = employeesList.collectMany { it.keySet() }.unique()
def employeeCsvFile = new File("C:\\Users\\AbiranLopez\\Documents\\katalontest\\employees.csv")
employeeCsvFile.withWriter("UTF-8") { writer ->
    writer.writeLine(allHeaders.join(','))
    employeesList.each { row ->
        writer.writeLine(allHeaders.collect { h -> row[h] ?: '' }.join(','))
    }
}

// write exchange_rate.csv
def exchangeCsvFile = new File("C:\\Users\\AbiranLopez\\Documents\\katalontest\\exchange_rate.csv")
def exchangeHeaders = exchangeRateData.keySet() ?: exchangeRateData[0]?.keySet()
def exchangeValues = exchangeRateData instanceof Map ? exchangeRateData.values() : exchangeRateData[0].values()

exchangeCsvFile.withWriter("UTF-8") { writer ->
    writer.writeLine(exchangeHeaders.join(','))
    writer.writeLine(exchangeValues.join(','))
}