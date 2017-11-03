package info.xiaomo.security.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * @author : xiaomo
 */
@Controller
class HelloController {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    @RequestMapping(value = "/login", method = arrayOf(RequestMethod.GET))
    fun login(): String {
        return "login"
    }

}