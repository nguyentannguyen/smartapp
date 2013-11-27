package com.smartapp.util;

import com.smartapp.data.domain.T_Account;
import com.smartapp.data.domain.T_User;
import com.smartapp.model.Creator;
import com.smartapp.model.Event;
import com.smartapp.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Model <==> Domain Converter
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class DomainConverter {
    public T_Account toT_Account(Event order){
        T_Account account = new T_Account();
        account.setIdentifier(UUID.randomUUID().toString());
        account.setUuid(order.getPayload().getCompany().getUuid());
        account.setName(order.getPayload().getCompany().getName());

        return account;
    }

    public T_User toT_User(User user){
        T_User t_user = new T_User();
        t_user.setEmail(user.getEmail());
        t_user.setFirstname(user.getFirstName());
        t_user.setLastname(user.getLastName());
        t_user.setOpenid(user.getOpenId());

        return t_user;
    }

    public T_User toT_User(Creator user){
        T_User t_user = new T_User();
        t_user.setEmail(user.getEmail());
        t_user.setFirstname(user.getFirstName());
        t_user.setLastname(user.getLastName());
        t_user.setOpenid(user.getOpenId());

        return t_user;
    }
}
