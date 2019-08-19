package pass.salt.modules.server.webparse

import pass.salt.SaltApplication
import pass.salt.modules.server.security.SaltSecurity

class Webparse {
    companion object {

        fun parse(lines: MutableList<String>, model: Model): String {
            val comment = ParserHelp(false, false, "")
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
        data class WebParseLoop(var loopFlag: Boolean, var tagStop: String, var listName: String, var list: List<Any>, var cache: MutableList<String>)
        data class WebConf(var ipAddress: String, var method: String, var port: String, var preUrl: String, val security: SaltSecurity?)
        data class ParserHelp(var comment: Boolean, var notFinished: Boolean, var cache: String)

        fun webParse(site: MutableList<String>, model: Model): String {
            val webConf = readWebConf()
            val textConf = WebParseText(false, "", "")
            val loopConf = WebParseLoop(false, "", "", mutableListOf(), mutableListOf())
            var fullSite = ""
            for (tag in site) {
                if ((tag.startsWith("<") && tag.contains("th:")) || loopConf.loopFlag ) {
                    if (loopConf.loopFlag) {
                       // if (tag.contains(loopConf.tagStop)) {
                        // TODO check <div th:each ><div> </div></div>
                        if(testLoopTagStop(tag, loopConf)) {
                            loopConf.loopFlag = false
                            for (el in loopConf.list) {
                                model.addAttribute(loopConf.listName, el)
                                for (looptag in loopConf.cache) {
                                    if (textConf.textFlag) {
                                        if (looptag.contains(textConf.tagStop)) {
                                            textConf.textFlag = false
                                            fullSite += textConf.text + looptag
                                        }
                                    }
                                    else {
                                        val test = looptag.replace(" ", "")
                                        fullSite += if (looptag.contains(" ") && test != "") {
                                            val looptagName = looptag.substring(1, looptag.indexOf(" "))
                                            val looptagParam = looptag.substring(looptagName.length + 2, looptag.length-1)
                                            textConf.tagStop = looptagName
                                            "<" + looptagName + webParseHelp(looptagParam, model, webConf, textConf) + ">"
                                        } else looptag
                                    }
                                }
                            }
                        }
                        else {
                            loopConf.cache.add(tag)
                        }

                    }
                    else {
                        val tRaw = tag.replace("</", "").replace("<", "").replace("/>", "").replace(">", "")
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
                            } else {
                                fullSite += "<$tName"
                                textConf.tagStop = tName
                                /** */
                                fullSite += webParseHelp(tRawParam, model, webConf, textConf)
                                fullSite += ">"
                            }
                        } else {
                            if(tRaw.startsWith("th:login")) {
                                if (webConf.security != null) {
                                    fullSite += script + "\"" + webConf.preUrl + webConf.security.login + "\""
                                    fullSite += script2 + "console.log(\"Wrong credentials\");"
                                    fullSite += script3 + "window.location.href = \"" + webConf.preUrl + webConf.security.success + "\"" + script4
                                }
                            }
                            else fullSite += tag
                        }
                    }
                }
                else {
                    if (textConf.textFlag) {
                        if (tag.contains(textConf.tagStop)) {
                            textConf.textFlag = false
                            fullSite += textConf.text + tag
                        }
                    }
                    else fullSite += tag
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

        private fun webParseHelp(param: String, model: Model, webConf: WebConf, textConf: WebParseText): String {
            var tRawParam = param
            var tAttrParams = ""
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
                    when (tAttrName) {
                        "th:text" -> {
                            val attrBegin = tAttrVal.indexOf("\${")
                            val attrEnd = tAttrVal.indexOf("}", attrBegin)
                            val tAttrValBegin = tAttrVal.substring(1, attrBegin)
                            val tAttrValEnd = tAttrVal.substring(attrEnd+2)
                            val modelSearch = tAttrVal.substring(attrBegin+2, attrEnd)
                            val modelResult = if (modelSearch.contains(".")) {
                                val tmp = modelSearch.split(".")
                                model.getAttribute(tmp[0], tmp[1])
                            } else {
                                model.getAttribute(modelSearch)
                            }
                            textConf.textFlag = true
                            textConf.text = tAttrValBegin + modelResult + tAttrValEnd
                        }
                        "th:href" -> {
                            tAttrParams += " " + parseHref(tAttrVal, model, webConf)
                        }
                        else -> tAttrParams += " $tAttrName=$tAttrVal"
                    }
                }
            } while (tRawParam != "")
            return tAttrParams
        }

        fun parseHref(attrVal: String, model: Model, webConf: WebConf): String {
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
            return "href=\"" + webConf.preUrl + newVal
        }



        fun parseLine(line: String, help: ParserHelp): MutableList<String> {
            var tmp = "" + line
            var length = tmp.length
            tmp.trim()
            // Comment check
            if (help.comment) {
                if (line.contains("-->")) {
                    help.comment = false
                    tmp = tmp.substring(tmp.indexOf("-->"))
                }
                else return mutableListOf()
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
            val list = mutableListOf<String>()
            var more = true
            while (more) {
                var begin = tmp.indexOf("<")
                var end = tmp.indexOf(">")
                var test = tmp.indexOf("<", begin)
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

        val script =
        """
            <script>
            ${'$'}('#submitButton').on('click', function() {auth() });
            ${'$'}('#username').keypress(function (e) {
                if (e.which == 13) {
                    auth()
                }
            });
            ${'$'}('#password').keypress(function (e) {
                if (e.which == 13) {
                    auth()
                }
            });
            function auth() {
                ${'$'}.ajax({
                    url: 
        """
        val script2 =
        """
                    ,
                    type: 'POST',
                    headers: {
                    "Authorization": "Basic " + btoa(${'$'}('#username').val() + ":" + ${'$'}('#password').val())
                    },
                    async: true,
                    statusCode: {
                        403: function(xhr) {
                            $("#alert").text("Wrong credentials");
                            $("#alert").removeAttr("hidden");
        """
        val script3 =
        """
                        }
                    },
                    success: function() {
        """
        val script4 =
        """
                    }
                })
            }
            </script>
        """



        /**
        <script>
            $('#submitButton').on('click', function() {auth() });

            function auth() {
                $.ajax({
                    url: "https://localhost:8080/login",
                    type: 'POST',
                    headers: {
                    "Authorization": "Basic " + btoa($('#username').val() + ":" + $('#password').val())
                    },
                    async: true,
                    statusCode: {
                        403: function(xhr) {
                            console.log("Wrong credentials");
                        }
                    },
                    success: function() {
                        window.location.href = "https://localhost:8080"
                    }
                })
            }

        </script>
        */
    }
}