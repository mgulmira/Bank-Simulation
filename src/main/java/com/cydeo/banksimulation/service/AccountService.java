package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {


    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccount();

    List<AccountDTO> listAllActiveAccount();
    void deleteAccount(Long account);

    AccountDTO retrieveById(Long account);

}
