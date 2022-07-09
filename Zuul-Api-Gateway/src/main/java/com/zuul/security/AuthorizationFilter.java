package com.zuul.security;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private Environment environment;

	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorizationToken = request.getHeader("Authorization");

		if (authorizationToken == null
				|| !authorizationToken.startsWith(this.environment.getProperty("token.prefix"))) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthenticationToken(request, authorizationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request,
			String authorizationToken) {

		String token = authorizationToken.replaceAll(this.environment.getProperty("token.prefix"), "");
		if (token == null) {
			return null;
		}
		String userId = Jwts.parser().setSigningKey(this.environment.getProperty("token.zuul.secret")).parseClaimsJws(token)
				.getBody().getSubject();
         
		//token.zuul.secret must be same as users-ms token.secret
		
		if (userId == null) {
			return null;
		}

		return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());

	}

}
