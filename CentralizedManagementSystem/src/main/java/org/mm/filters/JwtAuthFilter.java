package org.mm.filters;

import java.io.IOException;

import org.mm.entity.UserProfileDetailsEntity;
import org.mm.service.JwtService;
import org.mm.service.SecondUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter
{

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private SecondUserService secondUserService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String requestTokenHeader = request.getHeader("Authorization");
		
		if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer"))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = requestTokenHeader.split("Bearer ")[1];
		
		Long userIdFromToken = jwtService.getUserIdFromToken(token);
		
		if(userIdFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserProfileDetailsEntity user = secondUserService.getUserByUserId(userIdFromToken);
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request)
			);
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		
		filterChain.doFilter(request, response);
	}

}
