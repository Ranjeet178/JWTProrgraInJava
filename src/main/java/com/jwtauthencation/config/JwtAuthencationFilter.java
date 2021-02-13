package com.jwtauthencation.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwtauthencation.helper.JwtUtil;
import com.jwtauthencation.services.customUserDetailsService;

@Component
public class JwtAuthencationFilter extends OncePerRequestFilter {
	@Autowired
	private customUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get jwt
		// Berer
		//validation
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		//null and format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username=this.jwtutil.extractUsername(jwtToken);
			}catch (Exception e) {
				// TODO: handle exception
			e.printStackTrace();
			}
			UserDetails userdetails= this.customUserDetailsService.loadUserByUsername(username);
			// security
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				// 
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken	=new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}else {
				System.out.println("token is not validated");
			}
		
		}
		filterChain.doFilter(request, response);
		
	}

}
