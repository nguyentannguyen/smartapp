package com.smartapp.services.events;

import com.smartapp.model.ErrorCode;
import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import org.springframework.stereotype.Component;

/**
 * UNASSIGNMENT service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class UnassignmentService extends EventService {

    @Override
    public Result execute(String eventUrl) throws Exception{
        Event assigned = fireEventUrl(eventUrl);
        validateEvent(assigned, EventType.USER_UNASSIGNMENT);

        if (!userService.checkIfUserExist(assigned.getPayload().getUser().getEmail())){
            result.setErrorCode(ErrorCode.USER_NOT_FOUND);
            throw new IllegalAccessException("User not found!");
        }

        String identifier = assigned.getPayload().getAccount().getAccountIdentifier();
        userService.delete(assigned.getPayload().getUser(), identifier);

        result.setSuccess(true);
        result.setMessage("User is unassigned to the account!");
        result.setAccountIdentifier(identifier);

        return result;
    }
}
