package com.tcs.trs.service;

import java.util.List;

import com.tcs.trs.dto.CardDTO;
import com.tcs.trs.dto.CustomerDTO;
import com.tcs.trs.exception.InfyBankException;
public interface CardCustomerService {
	
	public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException;
	public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException;
	public void issueCardToExistingCustomer(Integer customerId, CardDTO cardDTO) throws InfyBankException;
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws InfyBankException;
	
}
