package com.tcs.trs.repository;

import org.springframework.data.repository.CrudRepository;

import com.tcs.trs.entity.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {
	
}

