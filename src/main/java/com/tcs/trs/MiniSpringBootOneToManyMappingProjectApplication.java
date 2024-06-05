package com.tcs.trs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.tcs.trs.dto.CardDTO;
import com.tcs.trs.dto.CustomerDTO;
import com.tcs.trs.service.CardCustomerService;

@SpringBootApplication
public class MiniSpringBootOneToManyMappingProjectApplication implements CommandLineRunner {

	@Autowired
	CardCustomerService cardCustomerService;
	
	@Autowired
	Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(MiniSpringBootOneToManyMappingProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//addCustomer();
		//getCustomerWithCardDetails();
		//addNewCardToExistingCustomer();
		  deleteCardOfExistingCustomer();
	}
	
	public void deleteCardOfExistingCustomer() {
		try {
			Integer customerId = 1007;
			List<Integer> cardIdsToDelete = new ArrayList<>();
			cardIdsToDelete.add(111111);
			cardIdsToDelete.add(222222);
			cardCustomerService.deleteCardOfExistingCustomer(customerId, cardIdsToDelete);
			System.out.println("\n" + environment.getProperty("UserInterface.CARD_DEACTIVATED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			System.out.println(message);
		}
	}
	
	public void addNewCardToExistingCustomer() throws Exception {
		Integer customerId = 1007;
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCardId(3333333);
		cardDTO.setCardNumber("33333333333333");
		cardDTO.setExpiryDate(LocalDate.of(2030, 03, 07));
		try {
			cardCustomerService.issueCardToExistingCustomer(customerId, cardDTO);
			System.out.println("\n" + environment.getProperty("UserInterface.CARD_ADDED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			System.out.println(message);
		}
	}
	
	
	public void getCustomerWithCardDetails() {
		try {
			Integer customerId = 1007;
			CustomerDTO customerDTO = cardCustomerService.getCustomerDetails(customerId);
			System.out.println(customerDTO);
			if (customerDTO.getCards().isEmpty()) {
				System.out.println(environment.getProperty("UserInterface.NO_CARDS"));
			}
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			System.out.println(message);
		}
	}
	
	public void addCustomer() {
		try {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setName("Mini Sachdeva");
			customerDTO.setEmailId("mini@tcs.com");
			customerDTO.setDateOfBirth(LocalDate.of(1992, 1, 10));
			CardDTO cardDTO = new CardDTO();
			cardDTO.setCardId(111111);
			cardDTO.setCardNumber("11111111111111111111");
			cardDTO.setExpiryDate(LocalDate.of(2024, 02, 27));
			CardDTO cardDTO2 = new CardDTO();
			cardDTO2.setCardId(222222);
			cardDTO2.setCardNumber("2222222222222222222");
			cardDTO2.setExpiryDate(LocalDate.of(2022, 10, 15));
			List<CardDTO> cardDTOs = new LinkedList<>();
			cardDTOs.add(cardDTO);
			cardDTOs.add(cardDTO2);
			
			customerDTO.setCards(cardDTOs);
			
			cardCustomerService.addCustomer(customerDTO);
			System.out.println("\n" + environment.getProperty("UserInterface.CARD_AND_CUSTOMER_ADDED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			System.out.println(message);
		}
	}

}
