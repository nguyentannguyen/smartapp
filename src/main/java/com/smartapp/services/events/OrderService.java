package com.smartapp.services.events;

import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import org.springframework.stereotype.Component;

/**
 * ORDER service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class OrderService extends EventService {

    @Override
    public Result execute(String eventUrl) throws Exception{

        Event order = fireEventUrl(eventUrl);
        validateEvent(order, EventType.SUBSCRIPTION_ORDER);

        // Create account if not existed
        String identifier = accountService.findByUid(order.getPayload().getCompany().getUuid());
        identifier = identifier==null ? accountService.create(order) : identifier;

        // Create user if not existed
        if (!userService.checkIfUserExist(order.getCreator().getEmail())){
            userService.create(order.getCreator(),identifier);
        }

        //@TODO: Store other data of "Order" event as needed

        result.setSuccess(true);
        result.setMessage("order successfully!");
        result.setAccountIdentifier(identifier);

        return result;
    }
}
