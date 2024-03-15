package com.malar.poc.service;

import org.springframework.stereotype.Service;

import com.malar.poc.mapper.CustomerDetailsDTO;

@Service
public interface CustomerService {

	public CustomerDetailsDTO fetchCustomerDetails(long custId, String custName);
}
