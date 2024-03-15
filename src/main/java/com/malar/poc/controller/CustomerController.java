package com.malar.poc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malar.poc.mapper.CustomerDetailsDTO;
import com.malar.poc.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer-service")
@Slf4j
public class CustomerController {

	private  CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping(name="/fetch-customer-details")
	public ResponseEntity<CustomerDetailsDTO> fetchDetails(
			@RequestHeader(name="custId", required = true)
			long customerId,
			@RequestHeader(name="custName", required = true)
			String customerName){
		CustomerDetailsDTO response = customerService.fetchCustomerDetails(customerId, customerName);
		return ResponseEntity.status(200).body(response);
	}
	
}
