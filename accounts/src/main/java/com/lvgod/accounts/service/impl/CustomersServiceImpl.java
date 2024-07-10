package com.lvgod.accounts.service.impl;

import com.lvgod.accounts.dto.AccountsDto;
import com.lvgod.accounts.dto.CardsDto;
import com.lvgod.accounts.dto.CustomerDetailsDto;
import com.lvgod.accounts.dto.LoansDto;
import com.lvgod.accounts.entity.Accounts;
import com.lvgod.accounts.entity.Customer;
import com.lvgod.accounts.exception.ResourceNotFoundException;
import com.lvgod.accounts.mapper.AccountsMapper;
import com.lvgod.accounts.mapper.CustomerMapper;
import com.lvgod.accounts.repository.AccountsRepository;
import com.lvgod.accounts.repository.CustomerRepository;
import com.lvgod.accounts.service.ICustomersService;
import com.lvgod.accounts.service.client.CardsFeignClient;
import com.lvgod.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
