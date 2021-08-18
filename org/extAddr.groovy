import groovy.json.JsonSlurper


def inputFile = new File("org_1.json")
def organization = [new JsonSlurper().parseText(inputFile.text)]

// ------

// "extIndex"     - Индекс
// "extRegion"    - Регион / субъект
// "extDistrict"  - Район
// "extCity"      - Город
// "extCommunity" - Населенный пункт
// "extStreet"    - улица
// "extHouse"     - дом
// "extBuilding"  - расширение дома
// "extOffice"    - офис / квартира (корпус, строение, подъезд, этаж, квартира, офис,..)
//
// "extComment" - Комментарий


// "extIndex": "298650",
// "extRegion": "РЕСПУБЛИКА КРЫМ",
// "extDistrict": null,
// "extCity": "ГОРОД ЯЛТА",
// "extCommunity": "ПОСЕЛОК ГОРОДСКОГО ТИПА МАССАНДРА",
// "extStreet": "УЛИЦА ВИНОДЕЛА ЕГОРОВА",
// "extHouse": "ДОМ 16",
// "extBuilding": "312312 УВ ЦУВЦ", строение
// "extOffice": "КВАРТИРА 18",

// "extComment": null,
// "extCountry": { "caption": "Российская Федерация", "shortName": "РОССИЯ",
// "extAddress": null,

if (!organization[0]) {
    println "xxx"
};

def keys = ["extIndex", "extRegion", "extDistrict", "extCity", "extCommunity",
            "extStreet", "extHouse", "extBuilding", "extOffice"];

def addressLegal = organization?.extAddresses?.inject([], { result, item ->
    if (item.extAddressType == "legal") {
        keys.each {result << (item[it] ?: '')};
    }
    result;
})

println UUID.randomUUID().toString()
println new Date().format( 'yyyy-MM-dd' )
return  addressLegal.join(', ')
//def z = addrresLegal.join(', ')



