package com.smartapp.services.events;

import com.smartapp.model.ErrorCode;
import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import org.springframework.stereotype.Component;

/**
 * ASSIGNMENT service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class AssignmentService extends EventService {

    @Override
    public Result execute(String eventUrl) throws Exception {
        Event assigned = fireEventUrl(eventUrl);
        validateEvent(assigned, EventType.USER_ASSIGNMENT);

        if (userService.checkIfUserExist(assigned.getPayload().getUser().getEmail())){
            result.setErrorCode(ErrorCode.USER_ALREADY_EXISTS);
            throw new IllegalAccessException("User already existed!");
        }

        String identifier = assigned.getPayload().getAccount().getAccountIdentifier();
        userService.create(assigned.getPayload().getUser(),identifier);

        result.setSuccess(true);
        result.setMessage("User is assigned to the account!");
        result.setAccountIdentifier(identifier);

        return result;
    }
}
