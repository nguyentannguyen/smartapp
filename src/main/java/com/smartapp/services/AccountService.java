package com.smartapp.services;

import com.smartapp.data.domain.T_Account;
import com.smartapp.data.repository.AccountRepository;
import com.smartapp.model.Event;
import com.smartapp.util.DomainConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Account services
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Service
@Transactional
public class AccountService {

    @Autowired private AccountRepository accountRepository;
    @Autowired private DomainConverter domainConverter;

    /**
     * Create an account base on 'Company' object
     * retrieved from fireEventUrl
     *
     * @param order from AppDirect
     * @return account identifier
     */
    public String create(Event order) throws IllegalAccessException {
        T_Account account = domainConverter.toT_Account(order);
        account = accountRepository.create(account);
        return account.getIdentifier();
    }

    /**
     * Check if an account existed against account identifier
     *
     * @param identifier account identifier
     * @return true if existed, otherwise false
     */
    @Transactional(readOnly = true)
    public boolean checkIfExist(String identifier){
        return accountRepository.checkIfAccountExisted(identifier);
    }

    /**
     * Find an account from given uuid
     *
     * @param uuid of account
     * @return identifier if existed, otherwise null
     */
    @Transactional(readOnly = true)
    public String findByUid(String uuid){
        T_Account account = accountRepository.loadByUuid(uuid);
        return account!=null ? account.getIdentifier() : null;
    }
}
