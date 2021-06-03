// извлечение данных по "земельным участкам" для CustomFields

import groovy.json.JsonSlurper

def inputFile = new File("test_1.json")
def response = new JsonSlurper().parseText(inputFile.text)

def application = response
//def interDepReqs = response.interDepartmentReq

// begin import to bpmn

def normalize(numbers) {
    if (!numbers) {
        return []
    }

    if (!(numbers instanceof java.util.List)) {
        numbers = [numbers]
    }

    numbers.removeAll([null])
    return numbers
};

def takeData(data, results) {
    results.cadastral += normalize(data?.landPlotCadastralNumber)
};

def base = application?.statement?.landPlot

def results = [
        cadastral  : [],
        address    : [],
        description: []
]

takeData(base?.divideLandPlot, results)
takeData(base?.unitedLandPlot?.unitedLandPlotData, results)
takeData(base?.selectedLandPlotInformation, results)
takeData(base?.infoRepartitionLandPlot?.infoRepartitionLandPlotTypeData, results)
takeData(base?.lackOfAddress, results)
takeData(base?.bringingAddressAccordanceLayoutTerritory, results)


//return [
//        number : results.cadastral,
//        address: results.address + results.description
//]
// end import to bpmn

 println results.cadastral
