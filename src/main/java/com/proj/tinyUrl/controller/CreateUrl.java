package com.proj.tinyUrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proj.tinyUrl.entites.Url;
import com.proj.tinyUrl.models.Response;
import com.proj.tinyUrl.services.CreateUrlService;

@RestController
@RequestMapping("/shortenUrl")
public class CreateUrl {
	
	@Autowired
	CreateUrlService createurlService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Response createUrl(@RequestBody Url url) {
		return createurlService.createUrl(url);
	}
	
	@RequestMapping(value = "/getUrl", method = RequestMethod.GET)
	public Response getUrl() {
		return createurlService.getUrl();
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Response getUrl(@PathVariable Integer id) {
		return createurlService.getById(id);
	}
}
