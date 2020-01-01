package pass.salt.code.modules.server.webparse

import pass.salt.code.loader.config.Config

/**
 * Utility functions for advanced HTML-template functionality.
 */
class WebTools {
    companion object {

        /**
         * Returns js login script when `<th:login/>` is found in a HTML-site.
         */
        fun buildLogin(preUrl: String, login: String, success: String): String {
            var lg = script + "\"" + preUrl + login + "\""
            lg += script2 + "console.log(\"Wrong credentials\");"
            lg += script3 + "window.location.href = \"" + preUrl + success + "\"" + script4
            return lg
        }

        /**
         * Returns js logout script when `<th:logout/>` is found in a HTML-site.
         */
        fun buildLogout(preUrl: String, logout: String, login: String): String {
            var lgo = lo + "\"" + preUrl + logout + "\""
            lgo += lo2 +  "window.location.href = \"" + preUrl + login + "\"" + lo3
            return lgo
        }

        /**
         * Returns the ip-address of the Salt application.
         */
        fun getIPAddress(conf: Config): String {
            val ipAddress = conf.findObjectAttribute("server", "ip_address") as String
            val redirect = conf.findObjectAttribute("server", "redirect") as Boolean
            if (redirect) {
                val method = (conf.findObjectAttribute("server", "redirect_protocol") as String)
                        .toLowerCase()
                val port = if (method == "https") {
                    conf.findObjectAttribute<String>("server", "https_port")
                } else conf.findObjectAttribute<String>("server", "http_port")
                return "$method://$ipAddress:$port"
            }
            else {
                val method = if (conf.findObjectAttribute("server", "https") as Boolean) {
                    "https"
                } else "http"
                val port = if (method == "https") {
                    conf.findObjectAttribute<String>("server", "https_port")
                } else conf.findObjectAttribute<String>("server", "http_port")
                return "$method://$ipAddress:$port"
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
                            window.setTimeout(function() {${'$'}("#alert").attr("hidden", true);}, 5000);
                        },
                        423: function(xhr) {
                            $("#alert").text("Account temporally locked");
                            $("#alert").removeAttr("hidden");
                            console.log("Account temporally locked");
                            window.setTimeout(function() {${'$'}("#alert").attr("hidden", true);}, 5000);
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

        val lo =
                """
            <script>
            ${'$'}('#logoutButton').on('click', function() {logout() });
            function logout() {
                ${'$'}.ajax({
                    url: 
        """
        val lo2 =
                """
                    ,
                    type: 'POST',
                    async: true,
                    success: function() {
        """
        val lo3 =
                """
                    }
                })
            }
            </script>
        """
    }
}