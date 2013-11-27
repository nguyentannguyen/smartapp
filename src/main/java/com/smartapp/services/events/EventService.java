package com.smartapp.services.events;

import com.smartapp.model.ErrorCode;
import com.smartapp.model.Event;
import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import com.smartapp.services.AccountService;
import com.smartapp.services.UserService;
import com.smartapp.util.FireEventUtil;
import com.smartapp.util.JaxbUtil;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Abstract Event service
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public abstract class EventService {
    @Autowired private FireEventUtil fireEventUtil;
    @Autowired private JaxbUtil jaxbUtil;
    @Autowired protected AccountService accountService;
    @Autowired protected UserService userService;

    Result result = new Result();

    /**
     * Main method to process the event
     *
     * @return result of the event
     */
    public abstract Result execute(String eventUrl) throws Exception;

    public Result getResult(){
        return result;
    }

    /**
     * Fire an event from AppDirect's event url
     *
     * @param eventUrl
     * @return Event object obtained frorm AppDirect
     */
    protected Event fireEventUrl(String eventUrl) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException, JAXBException {
        byte[] bytes =  fireEventUtil.getDataFromEvent(eventUrl);
        return jaxbUtil.unmarshalResponse(bytes, Event.class);
    }

    /**
     * Validate an Event.
     *
     * @param event
     * @param eventType
     * @return true if validated, otherwise false
     */
    protected void validateEvent(Event event, EventType eventType) throws IllegalAccessException {
        if (event.getType()!=eventType){
            result.setErrorCode(ErrorCode.CONFIGURATION_ERROR);
            throw new IllegalAccessException("The event is not matched");
        }

        if (event.getPayload()==null){
            result.setErrorCode(ErrorCode.INVALID_RESPONSE);
            throw new IllegalAccessException("Could not find the payload");
        }

        if (EventType.SUBSCRIPTION_NOTICE!=eventType && event.getCreator()==null){
            result.setErrorCode(ErrorCode.INVALID_RESPONSE);
            throw new IllegalAccessException("Could not find requester");
        }

        if (EventType.SUBSCRIPTION_ORDER!=eventType){

            if (EventType.SUBSCRIPTION_NOTICE!=eventType){
                if (!userService.checkIfUserExist(event.getCreator().getEmail())){
                    result.setErrorCode(ErrorCode.UNAUTHORIZED);
                    throw new IllegalAccessException("The requester is not authorized to perform the action");
                }
            }

            if (!accountService.checkIfExist(event.getPayload().getAccount().getAccountIdentifier())){
                result.setErrorCode(ErrorCode.ACCOUNT_NOT_FOUND);
                throw new IllegalAccessException("Account does not existed!");
            }
        }
    }
}
