// собрать кадастровые номера из примера

import groovy.json.JsonSlurper

def inputFile = new File("cn_land.json")
def application = new JsonSlurper().parseText(inputFile.text)

// begin import to bpmn
def cadastral = []
static def normalize(numbers) {
    if (!numbers) return [];
    if (!(numbers instanceof List)) numbers = [numbers];
    numbers.removeAll([null]);
    return numbers;
};

def takeData = { data, CNKey ->
    cadastral += normalize(data?[CNKey])
};

// извлекаем КН Зем.Уч. из раздела ЗУ
def base = application?.statement?.landPlot

takeData(base?.divideLandPlot, "landPlotCadastralNumber")
takeData(base?.unitedLandPlot?.unitedLandPlotData, "landPlotCadastralNumber")
takeData(base?.selectedLandPlotInformation, "landPlotCadastralNumber")
takeData(base?.infoRepartitionLandPlot?.infoRepartitionLandPlotTypeData, "landPlotCadastralNumber")
takeData(base?.lackOfAddress, "landPlotCadastralNumber")
takeData(base?.bringingAddressAccordanceLayoutTerritory, "landPlotCadastralNumber")



println cadastral
