package com.smartapp.services.events;

import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import org.springframework.stereotype.Component;

/**
 * NOTICE service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class NoticeService extends EventService {

    @Override
    public Result execute(String eventUrl) throws Exception{
        Event assigned = fireEventUrl(eventUrl);
        validateEvent(assigned, EventType.SUBSCRIPTION_NOTICE);

        //TODO : Update against notices

        result.setSuccess(true);
        result.setMessage("Success");

        return  result;
    }
}
