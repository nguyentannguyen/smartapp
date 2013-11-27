package com.smartapp.services;

import com.smartapp.data.domain.T_Account;
import com.smartapp.data.domain.T_User;
import com.smartapp.data.repository.AccountRepository;
import com.smartapp.data.repository.UserRepository;
import com.smartapp.model.Creator;
import com.smartapp.model.User;
import com.smartapp.util.DomainConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User services
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Service
@Transactional
public class UserService {

    @Autowired private AccountRepository accountRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private DomainConverter domainConverter;

    /**
     * Create and store a new user
     *
     * @param user from AppDirect
     * @param identifier account identifier that user associate with
     */

    public void create(User user,String identifier) throws IllegalAccessException {
        create(domainConverter.toT_User(user),identifier);
    }

    public void create(Creator user,String identifier) throws IllegalAccessException {
        create(domainConverter.toT_User(user),identifier);
    }

    private void create(T_User t_user,String identifier) throws IllegalAccessException {
        T_Account account = accountRepository.loadByIdentifier(identifier);
        t_user.setAccount(account);
        userRepository.create(t_user);
    }

    public void delete(User user, String identifier) throws IllegalAccessException {
        T_User t_user = loadByEmail(user.getEmail(), identifier);

        if (t_user==null){
            throw new IllegalAccessException("The user does not existed");
        }
        userRepository.delete(t_user);
    }

    @Transactional(readOnly = true)
    public T_User loadByEmail(String email, String identifier){
        return userRepository.loadByEmail(email, identifier);
    }

    @Transactional(readOnly = true)
    public boolean checkIfUserExist(String email){
        return userRepository.checkIfUserExisted(email);
    }
}
