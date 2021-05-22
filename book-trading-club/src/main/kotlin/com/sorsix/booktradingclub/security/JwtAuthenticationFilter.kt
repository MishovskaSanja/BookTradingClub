package com.sorsix.booktradingclub.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import java.io.IOException
import javax.servlet.ServletException

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var jwtToken: JwtToken

    @Autowired
    private lateinit var userDetailsServiceImpl: UserDetailsServiceImpl


    private fun getJwtToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = getJwtToken(request)
            if (StringUtils.hasText(jwt) && jwtToken.validateJwtToken(jwt)) {
                val username = jwtToken.getUsernameFromJwtToken(jwt)
                val userDetails = userDetailsServiceImpl.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            Companion.logger.error("Cannot set user authentication - [{}]", ex)
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    }

}
