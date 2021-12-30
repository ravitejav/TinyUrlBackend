package com.proj.tinyUrl.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

@Service
public class Logger {
	
	public void log(String Message, String type) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		System.out.println(df + " " + type + " " + Message);
	}
	
	public void info(String Message) {
		this.log(Message, "INFO");
	}
	
	public void error(String Message) {
		this.log(Message, "ERROR");
	}

}
