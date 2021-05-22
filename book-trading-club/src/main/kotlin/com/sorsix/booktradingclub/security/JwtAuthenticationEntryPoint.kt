package com.sorsix.booktradingclub.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val logger: Logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

    @Throws(IOException::class, ServletException::class)
    override fun commence(httpServletRequest: HttpServletRequest?,
                          httpServletResponse: HttpServletResponse,
                          e: AuthenticationException) {
        logger.error("Unauthorized. Message - [{}]", e.message)
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
    }

}
