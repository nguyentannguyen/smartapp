package com.smartapp.services;

import com.smartapp.model.ErrorCode;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import com.smartapp.services.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Events services
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Service
@Transactional
public class AppEventService {

    private static final Logger log = Logger.getLogger(AppEventService.class.getName());

    @Autowired
    ApplicationContext context;

    /**
     * Retrieve an service against eventtype
     * and excute the sevice with eventUrl
     *
     * @param eventType
     * @param eventUrl
     * @return Result
     */
    public Result processEvent(EventType eventType, String eventUrl){

        EventService event = null;
        switch (eventType) {
            case SUBSCRIPTION_ORDER:
                event = context.getBean(OrderService.class);
                break;
            case SUBSCRIPTION_CHANGE:
                event = context.getBean(ChangeService.class);
                break;
            case SUBSCRIPTION_CANCEL:
                event = context.getBean(CancelService.class);
                break;
            case SUBSCRIPTION_STATUS:
                event = context.getBean(StatusService.class);
                break;
            case SUBSCRIPTION_NOTICE:
                event = context.getBean(NoticeService.class);
                break;
            case USER_ASSIGNMENT:
                event = context.getBean(AssignmentService.class);
                break;
            case USER_UNASSIGNMENT:
                event = context.getBean(UnassignmentService.class);
                break;
        }

        return excute(event, eventType, eventUrl);
    }

    /**
     * Run the service requested
     *
     * @param service
     * @param eventType
     * @param eventUrl
     * @return
     */
    private Result excute(EventService service,EventType eventType, String eventUrl){
        try{
            if (null== service){
                service.getResult().setErrorCode(ErrorCode.CONFIGURATION_ERROR);
                throw new IllegalArgumentException("Invalid Command");
            }
            service.execute(eventUrl);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            service.getResult().setSuccess(false);
            service.getResult().setErrorCode(service.getResult().getErrorCode() != null ? service.getResult().getErrorCode() : ErrorCode.UNKNOWN_ERROR);
            service.getResult().setMessage("EVENT:" + eventType + " - " + e.getMessage());
        }

        return service.getResult();
    }
}
