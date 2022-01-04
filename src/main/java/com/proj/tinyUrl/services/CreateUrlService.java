package com.proj.tinyUrl.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proj.tinyUrl.constant.MessageConstants;
import com.proj.tinyUrl.entites.Url;
import com.proj.tinyUrl.models.Response;
import com.proj.tinyUrl.repository.UrlRepository;

@Service
public class CreateUrlService {
	
	@Autowired
	UrlRepository urlRepository;
	
	@Autowired
	Response response;
	
	@Transactional
	public Response createUrl(Url url) {
		HashMap<String, Object> additionalDetails = new HashMap<String, Object>();
		if(this.urlRepository.save(url) != null) {
			url.setTinyUrl("https://localhost:3000/?id=" + url.getId());
			response.setStatusCode(HttpStatus.OK);
			response.setMessage(MessageConstants.TINY_URL_CREATED_SUCCESS);
		} else {
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(MessageConstants.TINY_URL_CREATED_FAILED);
		}
		additionalDetails.put("UrlData", url);
		response.setAdditionalData(additionalDetails);
		return response;
	}
	
	public Response getUrl() {
		List<Url> allUrls = this.urlRepository.findAll();
		HashMap<String, Object> additionalDetails = new HashMap<String, Object>();
		additionalDetails.put("UrlData", allUrls);
		response.setStatusCode(HttpStatus.OK);
		response.setMessage("");
		response.setAdditionalData(additionalDetails);
		return response;
	}
	
	public Response getById(Integer id) {
		Optional<Url> url = this.urlRepository.findById(id);
		if(url.isPresent()) {
			response.setMessage(MessageConstants.TINY_URL_FOUND);
		} else {
			response.setMessage(MessageConstants.TINY_URL_NOT_FOUND);
		}
		HashMap<String, Object> additionalDetails = new HashMap<String, Object>();
		additionalDetails.put("UrlData", url);
		response.setStatusCode(HttpStatus.OK);
		response.setAdditionalData(additionalDetails);
		return response;
	}

}
