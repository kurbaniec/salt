package pass.salt.modules.server.webparse

import com.sun.org.apache.xpath.internal.operations.Bool
import pass.salt.SaltApplication
import pass.salt.modules.server.security.SaltSecurity

class Webparse {
    companion object {

        fun parse(lines: MutableList<String>, model: Model): String {
            val comment = ParserHelp(false, false, "", false)
            //val tagRx = Regex.fromLiteral("<((?=!\\-\\-)!\\-\\-[\\s\\S]*\\-\\-|((?=\\?)\\?[\\s\\S]*\\?|((?=\\/)\\/[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*|[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*(?:\\s[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*(?:=(?:\"[^\"]*\"|'[^']*'|[^'\"<\\s]*))?)*)\\s?\\/?))>")
            val full = mutableListOf<String>()
            for (line in lines) {
                val tmp = parseLine(line, comment)
                //webParse(tmp)
                full.addAll(tmp)
            }
            return webParse(full, model)
        }

        data class WebParseText(var textFlag: Boolean, var tagStop: String, var text: String)
        data class WebParseLoop(var loopFlag: Boolean, var tagStop: String, var listName: String, var list: List<Any>, var cache: MutableList<String>, var notFinishedScript: Boolean)
        data class WebConf(var ipAddress: String, var method: String, var port: String, var preUrl: String, val security: SaltSecurity?)
        data class ParserHelp(var comment: Boolean, var notFinished: Boolean, var cache: String, var notFinishedScript: Boolean)

        fun webParse(site: MutableList<String>, model: Model): String {
            val webConf = readWebConf()
            val textConf = WebParseText(false, "", "")
            val loopConf = WebParseLoop(false, "", "", mutableListOf(), mutableListOf(), false)
            var fullSite = ""
            for (tagRaw in site) {
                if ((tagRaw.trim().startsWith("<") && tagRaw.contains("th:")) || loopConf.loopFlag ) {
                    if (loopConf.loopFlag) {
                       // if (tag.contains(loopConf.tagStop)) {
                        // TODO check <div th:each ><div> </div></div>
                        if(testLoopTagStop(tagRaw, loopConf)) {
                            loopConf.loopFlag = false
                            for (el in loopConf.list) {
                                model.addAttribute(loopConf.listName, el)
                                for (looptagRaw in loopConf.cache) {
                                    val looptag = webParseShort(looptagRaw, model) // Short parse ${{_}}
                                    if (textConf.textFlag) {
                                        if (looptag.contains(textConf.tagStop)) {
                                            textConf.textFlag = false
                                            fullSite += textConf.text + looptag
                                        }
                                    }
                                    else {
                                        if (loopConf.notFinishedScript) {
                                            fullSite += looptag
                                        }
                                        else if (looptag.contains("<script") && !looptag.contains("</script")) {
                                            loopConf.notFinishedScript = true
                                            fullSite += looptag
                                        }
                                        else if (looptag.contains("</script")) {
                                            loopConf.notFinishedScript = false
                                            fullSite += looptag
                                        }
                                        else {
                                            val test = looptag.replace(" ", "")
                                            fullSite += if (looptag.trim().contains(" ") && test != "" &&
                                                    (!looptag.contains("<!--") && !looptag.contains("-->")) && // TODO check this webparse condition
                                                    (looptag.contains("<") || looptag.contains(">"))) {
                                                val looptagName = looptag.trim().substring(1, looptag.indexOf(" "))
                                                val looptagParam = looptag.trim().substring(looptagName.length + 2, looptag.length - 1)
                                                textConf.tagStop = looptagName
                                                "<" + looptagName + webParseHelp(looptagParam, model, webConf, textConf) + ">"
                                            } else looptag
                                        }
                                    }
                                }
                            }
                            loopConf.cache.clear()
                        }
                        else {
                            loopConf.cache.add(tagRaw)
                        }

                    }
                    else {
                        var tagParsed = webParseShort(tagRaw, model)
                        var space = "";
                        for (letter in tagParsed) {
                            if (Character.isWhitespace(letter)) space += " " else break
                        }
                        tagParsed = tagParsed.substring(space.length)
                        val tRaw = tagParsed.replace("</", "").replace("<", "").replace("/>", "").replace(">", "")
                        var tName = ""
                        if (tRaw.contains(" ")) {
                            tName = tRaw.substring(0, tRaw.indexOf(" "))
                            val tRawParam = tRaw.substring(tName.length + 1)
                            if (tName.startsWith("th:block") && tRawParam.contains("th:each")) {
                                loopConf.loopFlag = true
                                loopConf.tagStop = "/$tName"
                                val looptmp = tRawParam.split("=").toMutableList()
                                looptmp[1] = looptmp[1].replace("\"", "")
                                val listtmp = looptmp[1].split(":").toMutableList()
                                loopConf.listName = listtmp[0].replace(" ", "")
                                val listName = listtmp[1].replace("\${", "").replace("}", "").replace(" ", "")
                                loopConf.list = model.getAttributeList(listName)
                            } else { // operations like th:text...
                                fullSite += "$space<$tName"
                                textConf.tagStop = tName
                                fullSite += webParseHelp(tRawParam, model, webConf, textConf)
                                fullSite += ">"
                            }
                        } else {
                            if(tRaw.startsWith("th:login") && webConf.security != null) {
                                fullSite += WebTools.buildLogin(webConf.preUrl, webConf.security.login, webConf.security.success)
                            }
                            else if(tRaw.startsWith("th:logout") && webConf.security != null) {
                                fullSite += WebTools.buildLogout(webConf.preUrl, webConf.security.logout, webConf.security.login)
                            }
                            else fullSite += space + tagParsed
                        }
                    }
                }
                else {
                    val tagParsed = webParseShort(tagRaw, model)
                    if (textConf.textFlag) {
                        if (tagParsed.contains(textConf.tagStop)) {
                            textConf.textFlag = false
                            fullSite += textConf.text + tagParsed
                        }
                    }
                    else fullSite += tagParsed
                }
            }
            return fullSite
        }

        private fun testLoopTagStop(tag: String, conf: WebParseLoop): Boolean {
            val tName = if (tag.contains(" ")) {
                tag.substring(0, tag.indexOf(" ")).replace("<", "").replace("</", "").replace(">", "")
            }
            else {
                tag.replace("</", "").replace("<", "").replace(">", "")
            }
            var count = 0
            for (el in conf.cache) {
                if (el.startsWith(tName) || el.startsWith("<$tName") || el.startsWith("</$tName")) {
                    count++
                }
            }
            return count % 2 == 0 && tag.contains(conf.tagStop)
        }

        private fun webParseShort(element: String, model: Model): String {
            var line = ""+element
            // TODO short thymeleaf
            var finished = true
            do {
                val begin = line.indexOf("\${{")
                val end = line.indexOf("}}", begin + 1)
                if (begin != -1 && end != -1) {
                    val beginLine = line.substring(0, begin)
                    val attrRaw = line.substring(begin + 3, end)
                    val endLine = line.substring(end + 2)
                    val attr = if (attrRaw.contains(".")) {
                        val tmp = attrRaw.split(".")
                        if (tmp.size == 2) {
                            model.getAttribute(tmp[0], tmp[1])
                        } else "wrongSyntxError"
                    } else model.getAttribute(attrRaw)
                    line = beginLine + attr + endLine
                    finished = line.indexOf("\${{") == -1
                }
            } while (!finished)
            return line
        }

        private fun webParseHelp(param: String, model: Model, webConf: WebConf, textConf: WebParseText): String {
            var tRawParam = param
            var tAttrParams = ""
            do {
                val begin = tRawParam.indexOf("\"")
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
                    when (tAttrName) {
                        "th:text" -> {
                            val attrBegin = tAttrVal.indexOf("\${")
                            val attrEnd = tAttrVal.indexOf("}", attrBegin)
                            val tAttrValBegin = tAttrVal.substring(1, attrBegin)
                            val tAttrValEnd = tAttrVal.substring(attrEnd+2)
                            val modelSearch = tAttrVal.substring(attrBegin+2, attrEnd)
                            val modelResult = if (modelSearch.contains(".")) {
                                val tmp = modelSearch.split(".")
                                if (tmp.size == 2) {
                                    model.getAttribute(tmp[0], tmp[1])
                                } else "wrongSyntxError"
                            } else {
                                model.getAttribute(modelSearch)
                            }
                            textConf.textFlag = true
                            textConf.text = tAttrValBegin + modelResult + tAttrValEnd
                        }
                        "th:href" -> tAttrParams += " " + parsePath("href", tAttrVal, model, webConf)
                        "th:src" -> tAttrParams += " " + parsePath("src", tAttrVal, model, webConf)
                        else -> tAttrParams += " $tAttrName=$tAttrVal"
                    }
                }
                else if (begin == -1 && end == -1) { // for cases like "hidden" at the end of a tag
                    tAttrParams += " $tRawParam"
                    tRawParam = tRawParam.substring(tRawParam.length)
                }
            } while (tRawParam != "")
            return tAttrParams
        }

        fun parsePath(tag: String, attrVal: String, model: Model, webConf: WebConf): String {
            var newVal = attrVal.replace("\"@{", "")
            var begin = newVal.indexOf("\${")
            while (begin != -1) {
                val end = newVal.indexOf("}")
                if (begin != -1) {
                    val newBegin = newVal.substring(0, begin)
                    val newEnd = newVal.substring(end+1)
                    val modelSearch = newVal.substring(begin+2, end)
                    val modelResult = if (modelSearch.contains(".")) {
                        val tmp = modelSearch.split(".")
                        model.getAttribute(tmp[0], tmp[1])
                    } else {
                        model.getAttribute(modelSearch)
                    }
                    newVal = newBegin + modelResult + newEnd
                    begin = newVal.indexOf("\${")
                }
            }
            newVal = newVal.replace("}", "")
            return tag + "=\"" + webConf.preUrl + newVal
        }

        fun parseLine(line: String, help: ParserHelp): MutableList<String> {
            var tmp = "" + line
            var length = tmp.length
            val list = mutableListOf<String>()
            var more = true
            // Comment check
            if (help.comment) {
                if (line.contains("-->")) {
                    help.comment = false
                    tmp = tmp.substring(tmp.indexOf("-->")+3).trim()
                }
                else return list
            }
            if (tmp.contains("<!--")) {
                val begin = tmp.indexOf("<!--")
                if (tmp.contains("-->")) {
                    val end = line.indexOf("-->")
                    tmp = tmp.substring(0, begin) + tmp.substring(end+3)
                }
                else {
                    tmp = tmp.substring(0, begin)
                    help.comment = true
                }
            }
            // Multiline check
            if (help.notFinished) {
                help.notFinished = false
                tmp = help.cache + tmp
            }
            // script check
            if (help.notFinishedScript) {
                list.add(tmp)
                more = false
            }
            else if (tmp.contains("<script") && !tmp.contains("</script")) {
                help.notFinishedScript = true
                list.add(tmp)
                more = false
            }
            else if (tmp.contains("</script")) {
                help.notFinishedScript = false
                list.add(tmp)
                more = false
            }
            while (more) {
                var begin = tmp.indexOf("<")
                var end = tmp.indexOf(">")
                var test = tmp.indexOf("<", begin+1)
                if (begin != -1 && end == -1) { // multiline tag
                    help.notFinished = true
                    help.cache = tmp
                    return mutableListOf()
                }
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
                    list.add(line)
                    break
                }
                if (begin != 0 && begin != -1) list.add(tmp.substring(0, begin))
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

        private fun readWebConf(): WebConf {
            val conf = SaltApplication.config
            val security = SaltApplication.container.getElement("saltSecurity") as SaltSecurity?
            val ipAddress = conf.findObjectAttribute("server", "ip_address") as String
            val redirect = conf.findObjectAttribute("server", "redirect") as Boolean
            if (redirect) {
                val method = (conf.findObjectAttribute("server", "redirect_protocol") as String)
                        .toLowerCase()
                val port = if (method == "https") {
                     conf.findObjectAttribute<String>("server", "https_port")
                } else conf.findObjectAttribute<String>("server", "http_port")
                return WebConf(ipAddress, method, port, "$method://$ipAddress:$port", security)
            }
            else {
                val method = if (conf.findObjectAttribute("server", "https") as Boolean) {
                    "https"
                } else "http"
                val port = if (method == "https") {
                    conf.findObjectAttribute<String>("server", "https_port")
                } else conf.findObjectAttribute<String>("server", "http_port")
                return WebConf(ipAddress, method, port, "$method://$ipAddress:$port", security)
            }
        }
    }
}