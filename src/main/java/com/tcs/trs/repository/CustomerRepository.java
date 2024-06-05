package com.tcs.trs.repository;

import org.springframework.data.repository.CrudRepository;

import com.tcs.trs.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
}