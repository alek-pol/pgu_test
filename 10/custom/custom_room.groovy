// извлечение данных по "помещениям" для CustomFields

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
    results.cadastral += normalize(data?.premiseCadastralNumber)
    results.address += normalize(data?.fiasPremise)
    results.description += normalize(data?.descriptionLocationPremise)
};

def takeDataB(data, results) {
    results.cadastral += normalize(data?.buildingCadastralNumber)
    results.address += normalize(data?.fiasPremise)
    results.description += normalize(data?.descriptionLocationPremise)
};

def takeDataL(data, results) {
    results.cadastral += normalize(data?.landPlotCadastralNumber)
    results.address += normalize(data?.fiasLandPlot)
//    results.description += normalize(data?.descriptionLocationPremise)
};

def base = application?.statement?.premise

def results = [
        cadastral  : [],
        address    : [],
        description: []
]

takeData(base?.premiseInformation, results)
takeDataB(base?.buildingSection, results)
takeData(base?.premiseSectionInformation?.premiseInformationOwn, results)
takeData(base?.unitedPremiseInfo?.unitedPremiseInfoBlock, results)
takeData(base?.reorganizationSpaceInformation, results)
takeData(base?.lackOfAddress, results)
takeDataL(base?.bringingAddressAccordance, results)

//return  [
//        number : results.cadastral,
//        address: results.address + results.description
//]
// begin import to bpmn

println results
