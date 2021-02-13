package com.jwtauthencation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthencation.helper.JwtUtil;
import com.jwtauthencation.model.JwtResponse;
import com.jwtauthencation.model.jwtRequest;
import com.jwtauthencation.services.customUserDetailsService;

@RestController
public class JwtController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private customUserDetailsService customUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try 
		{
		
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		}catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Bed Credentials");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bed Credentials");
		}
		 //  fine area
			 UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
			 String token = this.jwtUtil.generateToken(userDetails)	;
			System.out.println("jwt " + token);
	
			// {"token":"value"}
			return ResponseEntity.ok(new JwtResponse(token));
			
	}
}
