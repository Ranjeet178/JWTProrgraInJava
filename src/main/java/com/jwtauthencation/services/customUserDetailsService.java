package com.jwtauthencation.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class customUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(userName.equals("rahul")){
			return new User("rahul", "Durgesh", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("user not found exception");
		}
		
	}
	
	

}
