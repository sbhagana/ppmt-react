package com.ppmtoolfullstack.ppmt.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ppmtoolfullstack.ppmt.Domain.User;
import com.ppmtoolfullstack.ppmt.services.CustomUserDetailsSevice;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private CustomUserDetailsSevice customUserDetailsService;
	
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String jwt = getJwtFromRequest(request);
			if(StringUtils.hasText(jwt)&&tokenProvider.validateToken(jwt)) {
				Long userId = tokenProvider.getUserIdFromJWT(jwt);
				User userDetails = customUserDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new 
						UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);	
			}
		} catch (Exception e) {
			logger.error("could not setuser authentication in security context", e);
		}
		filterChain.doFilter(request, response);
	}




	private String getJwtFromRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String bearerToken = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
