package pass.dev.server

import pass.salt.annotations.Autowired
import pass.salt.annotations.Scan

@Scan
class Test2 {

    @Autowired
    lateinit var test: Test

    fun test2(): String {
        return "test2"
    }
}