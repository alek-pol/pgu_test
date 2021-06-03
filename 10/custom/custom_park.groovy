// извлечение данных по "парковочным местам" для CustomFields

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
    results.address += normalize(data?.fiasLandPlot)
    results.description += normalize(data?.descriptionLocationPremise)
};

def takeDataB(data, results) {
    results.cadastral += normalize(data?.landPlotCadastralNumber)
    results.address += normalize(data?.fiasLandPlot)
    results.description += normalize(data?.descriptionLocationLandPlot)
};

def base = application?.statement?.parkingPlace

def results = [
        cadastral  : [],
        address    : [],
        description: []
]

takeData(base?.buildingSection, results)
takeData(base?.roomSection, results)
takeData(base?.consolidationOfPremises?.consolidationOfPremisesBlock, results)
takeData(base?.redevelopmentOfCommonAreas, results)
takeDataB(base?.lackOfAddress, results)
takeData(base?.bringingAddressAccordanceProjectDocumentation, results)

//return [
//        number : results.cadastral,
//        address: results.address + results.description
//]
// end import to bpmn

println([
        number : results.cadastral,
        address: results.address + results.description
])


//
// landPlotCadastralNumber  fiasLandPlot            -
// landPlotCadastralNumber  fiasLandPlot    descriptionLocationPremise
// landPlotCadastralNumber  fiasLandPlot            -
// landPlotCadastralNumber  fiasLandPlot    descriptionLocationPremise
// landPlotCadastralNumber  fiasLandPlot    descriptionLocationLandPlot
// landPlotCadastralNumber       -          descriptionLocationPremise
//
