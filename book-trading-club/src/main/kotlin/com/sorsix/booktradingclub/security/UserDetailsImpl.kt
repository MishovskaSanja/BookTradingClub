package com.sorsix.booktradingclub.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sorsix.booktradingclub.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
        private val username: String,
        @JsonIgnore
        private val password: String,
        private val authorities: Collection<GrantedAuthority>
) : UserDetails {
    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun create(user: User): UserDetailsImpl {
            return UserDetailsImpl(
                    user.username,
                    user.password,
                    listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
        }
    }


}

