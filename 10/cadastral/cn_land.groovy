// собрать кадастровые номера из примера

import groovy.json.JsonSlurper

def inputFile = new File("cn_land.json")
def application = new JsonSlurper().parseText(inputFile.text)

// begin import to bpmn

static def normalize(numbers) {
    if (!numbers) {
        return []
    }

    if (!(numbers instanceof java.util.List)) {
        numbers = [numbers]
    }

    numbers.removeAll([null])
    return numbers
};

static def takeData(data, results) {
    results.cadastral += normalize(data?.landPlotCadastralNumber)
};

def base = application?.statement?.landPlot

def results = [cadastral: []]

takeData(base?.divideLandPlot, results)
takeData(base?.unitedLandPlot?.unitedLandPlotData, results)
takeData(base?.selectedLandPlotInformation, results)
takeData(base?.infoRepartitionLandPlot?.infoRepartitionLandPlotTypeData, results)
takeData(base?.lackOfAddress, results)
takeData(base?.bringingAddressAccordanceLayoutTerritory, results)

// return results.cadastral;

// end import to bpmn
println results.cadastral
