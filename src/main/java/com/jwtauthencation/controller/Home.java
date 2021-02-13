package com.jwtauthencation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	// api
	@RequestMapping("/welcome")
	public String welStringcome()
	{
		String text = "this is pricate test";
		System.out.println("print once twice");
		return text;
	}
	
	@RequestMapping("/getUser")
	public String getUser()
	{
		String text = "this get user";
		System.out.println("print once");
		return text;
	}
}
