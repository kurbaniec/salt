package pass.salt.modules.server.webparse

import java.util.regex.Pattern

class Webparse {
    companion object {
        fun parse(lines: MutableList<String>, model: Model) {
            //val tagRx = Regex.fromLiteral("<((?=!\\-\\-)!\\-\\-[\\s\\S]*\\-\\-|((?=\\?)\\?[\\s\\S]*\\?|((?=\\/)\\/[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*|[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*(?:\\s[^.\\-\\d][^\\/\\]'\"[!#\$%&()*+,;<=>?@^`{|}~ ]*(?:=(?:\"[^\"]*\"|'[^']*'|[^'\"<\\s]*))?)*)\\s?\\/?))>")
            for (line in lines) {
                val tmp = parseLine(line)
                /**val str = line + "hahah"
                val tmp = str.split(tagRx)
                println(tmp)*/
                /**
                var tmp = "" + line
                var length = tmp.length
                tmp.trim()
                length = length - tmp.length
                val begin = tmp.indexOf("<")
                val end = tmp.indexOf(">")
                tmp = tmp.substring(begin, end)
                val tag = tmp.replace("<", "").replace(">", "").replace("</", "")
                 */
                println("baum")
            }
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
            return list
        }
    }
}