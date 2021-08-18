// собрать кадастровые номера из примера

import groovy.json.JsonSlurper

def inputFile = new File("cn_land.json")
def application = new JsonSlurper().parseText(inputFile.text)

// begin import to bpmn

static def normalize(numbers) {
    if (!numbers) return [];
    if (!(numbers instanceof List)) numbers = [numbers];
    numbers.removeAll([null]);
    return numbers;
};

//def results = [cadastral: []]

//static def takeData(data, CNKey, results) {
//    results.cadastral += normalize(data?[CNKey])
//};

def cadastral = []
// извлекаем КН Зем.Уч. из раздела ЗУ
def base = application?.statement?.landPlot
//takeData(base?.divideLandPlot, "landPlotCadastralNumber", results)
//takeData(base?.unitedLandPlot?.unitedLandPlotData, "landPlotCadastralNumber", results)
//takeData(base?.selectedLandPlotInformation, "landPlotCadastralNumber", results)
//takeData(base?.infoRepartitionLandPlot?.infoRepartitionLandPlotTypeData, "landPlotCadastralNumber", results)
//takeData(base?.lackOfAddress, "landPlotCadastralNumber", results)
//takeData(base?.bringingAddressAccordanceLayoutTerritory, "landPlotCadastralNumber", results)

def take = { data, CNKey ->
    cadastral += normalize(data?[CNKey])
};


take(base?.lackOfAddress, "landPlotCadastralNumber")


//def results = [cadastral: []]

// return results.cadastral;

// end import to bpmn
println cadastral
