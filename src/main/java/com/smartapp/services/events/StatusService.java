package com.smartapp.services.events;

import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import org.springframework.stereotype.Component;

/**
 * STATUS service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class StatusService extends EventService {

    @Override
    public Result execute(String eventUrl) throws Exception{
        Event assigned = fireEventUrl(eventUrl);
        validateEvent(assigned, EventType.SUBSCRIPTION_STATUS);

        //TODO : Process status event

        result.setSuccess(true);
        result.setMessage("success");

        return result;
    }
}
