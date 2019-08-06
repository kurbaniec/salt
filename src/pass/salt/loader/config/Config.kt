package pass.salt.loader.config

import pass.salt.loader.parser.TOMLParser

class Config() {
    val config: TOMLParser = TOMLParser("default.toml")

    init {
        config.overdrive(TOMLParser("config.toml"))
    }
}