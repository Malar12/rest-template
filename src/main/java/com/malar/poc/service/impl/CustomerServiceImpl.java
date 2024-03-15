package com.malar.poc.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.malar.poc.mapper.CustomerDetailsDTO;
import com.malar.poc.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Value("{$order.service.url}")
	private String orderSrvcUrl;
	
	@Autowired
	RestTemplate template;
	
	@Override
	public CustomerDetailsDTO fetchCustomerDetails(long custId, String custName) {
		CustomerDetailsDTO cdto = null;
		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("customerId", String.valueOf(custId));
		headers.set("customerName", custName);
		headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON));
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		String orderSrvcEndpoint = orderSrvcUrl + "/fetch-details";
		ResponseEntity<CustomerDetailsDTO> fetchResponse = template.exchange(orderSrvcEndpoint, HttpMethod.GET, entity,CustomerDetailsDTO.class);
		cdto = fetchResponse.getBody();
		return cdto;
	}

	
}
