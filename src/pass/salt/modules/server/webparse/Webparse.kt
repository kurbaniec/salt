package pass.salt.modules.server.webparse

import java.util.regex.Pattern

class Webparse {
    companion object {
        fun parse(lines: MutableList<String>, model: Model): String {
            //val tagRx = Regex.fromLiteral("<((?=!\\-\\-)!\\-\\-[\\s\\S]*\\-\\-|((?=\\?)\\?[\\s\\S]*\\?|((?=\\/)\\/[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*|[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*(?:\\s[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*(?:=(?:\"[^\"]*\"|'[^']*'|[^'\"<\\s]*))?)*)\\s?\\/?))>")
            val full = mutableListOf<String>()
            for (line in lines) {
                val tmp = parseLine(line)
                //webParse(tmp)
                full.addAll(tmp)
                println("baum")
            }
            println("baum2")
            return webParse(full, model)
        }

        data class WebConf(var textFlag: Boolean, var tagStop: String, var text: String)
        fun webParse(site: MutableList<String>, model: Model): String {
            val conf = WebConf(false, "", "")
            var fullSite = ""
            for (tag in site) {
                if (tag.startsWith("<") && tag.contains("th:")) {
                    val tRaw = tag.replace("<", "").replace("</", "").replace(">", "")
                    var tName = ""
                    if (tRaw.contains(" ")) {
                        tName = tRaw.substring(0, tRaw.indexOf(" "))
                        fullSite += "<$tName"
                        conf.tagStop = tName
                        var tRawParam = tRaw.substring(tName.length+1)
                        do {
                            var begin = tRawParam.indexOf("\"")
                            var end = tRawParam.indexOf("\"", begin + 1)
                            var test = tRawParam.indexOf("\\\"")
                            if (begin != -1) {
                                if (end == test) {
                                    var toAdd = test
                                    do {
                                        end = tRawParam.indexOf("\"", test + 1)
                                        test = tRawParam.indexOf("\\\"", test + 1)
                                    } while (end == test)
                                }
                                val attrRaw = tRawParam.substring(0, end+1)
                                tRawParam = tRawParam.substring(end+1)
                                tRawParam = tRawParam.trim()
                                val attr = attrRaw.split("=")
                                // check for webparse
                                val tAttrName = attr[0]
                                val tAttrVal = attr[1]
                                if (tAttrName == "th:text") {
                                    val attrBegin = tAttrVal.indexOf("\${")
                                    val attrEnd = tAttrVal.indexOf("}", attrBegin)
                                    val tAttrValBegin = tAttrVal.substring(1, attrBegin)
                                    val tAttrValEnd = tAttrVal.substring(attrEnd+2)
                                    val modelSearch = tAttrVal.substring(attrBegin+2, attrEnd)
                                    val modelResult = if (modelSearch.contains(".")) {
                                        val tmp = modelSearch.split(modelSearch)
                                        model.getAttribute(tmp[0], tmp[1])
                                    }
                                    else {
                                        model.getAttribute(modelSearch)
                                    }
                                    conf.textFlag = true
                                    conf.text = tAttrValBegin + modelResult + tAttrValEnd
                                }
                                else {
                                    fullSite += " $tRawParam"
                                }
                            }
                        } while (tRawParam != "")
                        fullSite += ">"
                    }
                    else {
                        fullSite += tag
                    }
                }
                else {
                    if (conf.textFlag) {
                        if (tag.contains(conf.tagStop)) {
                            fullSite += conf.text + tag
                        }
                    }
                    else fullSite += tag
                }
            }
            return fullSite
        }



        fun parseLine(line: String): MutableList<String> {
            var tmp = "" + line
            var length = tmp.length
            tmp.trim()
            length = length - tmp.length
            val list = mutableListOf<String>()
            var more = true
            while (more) {
                var begin = tmp.indexOf("<")
                var end = tmp.indexOf(">")
                var test = tmp.indexOf("<", begin)
                if (end < begin) {
                    list.add(tmp.substring(0, end+1))
                    tmp = tmp.substring(end+1)
                }
                else if (test in (begin + 1) until end) {
                    list.add(tmp.substring(0, test))
                    tmp = tmp.substring(test)
                }
                begin = tmp.indexOf("<")
                end = tmp.indexOf(">")
                if (begin == -1 && end == -1) {
                    break
                }
                if (begin != 0) list.add(tmp.substring(0, begin))
                val tag = tmp.substring(begin, end+1)
                list.add(tag) //.replace("<", "").replace(">", "").replace("</", ""))
                tmp = tmp.substring(end+1)
                begin = tmp.indexOf("<")
                end = tmp.indexOf(">")
                test = tmp.indexOf("<", begin+1)
                if (begin == -1) {
                    more = false
                }
                else {
                    do {
                        if (end < begin) {
                            list.add(tmp.substring(0, end+1))
                            tmp = tmp.substring(end+1)
                        }
                        else if (test in (begin + 1) until end) {
                            list.add(tmp.substring(0, test))
                            tmp = tmp.substring(test)
                        }
                        begin = tmp.indexOf("<")
                        end = tmp.indexOf(">")
                        if (begin == -1) {
                            more = false
                        }
                    } while (end < begin)
                }
            }
            list.add("\r\n")
            return list
        }
    }

    /**
    data class webConf(var textFlag: Boolean, var text: String)
    fun webParse(line: MutableList<String>) {
    val conf = webConf(false, "")
    for (tag in line) {
    if (tag.startsWith("<")) {
    var tmp = tag.replace("<", "").replace("</", "").replace(">", "")
    val hasAttrib = tag.indexOf(" ") != -1
    if (hasAttrib) {
    val name = tmp.substring(0, tag.indexOf(" "))
    tmp = tmp.substring(name.length)
    val tmp2 = tmp.split("\" ")
    val size = tmp2.size
    val attributes = mutableMapOf<String, String>()
    for (i in 1..size) {
    if (tmp2[i].contains("=")) {
    val tmp3 = tmp2[i].split("=")
    if (tmp3.size == 2) {
    attributes[tmp3[0]] = tmp3[1]
    }
    }
    }
    }
    else {
    val name = tmp
    }
    }
    }
    }*/
}