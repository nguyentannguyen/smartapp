package com.smartapp.services.events;

import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import org.springframework.stereotype.Component;

/**
 * CANCEL service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class CancelService extends EventService {

    @Override
    public Result execute(String eventUrl) throws Exception{
        Event assigned = fireEventUrl(eventUrl);
        validateEvent(assigned, EventType.SUBSCRIPTION_CANCEL);

        //TODO : Cancel Order

        result.setSuccess(true);
        result.setMessage("Order is cancelled");

        return result;
    }
}
