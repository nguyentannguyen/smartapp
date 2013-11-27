package com.smartapp.resources.event;

import com.smartapp.model.EventType;
import com.smartapp.model.Result;
import com.smartapp.services.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Events resources
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
@Path("/events")
@Produces(MediaType.APPLICATION_XML)
public class AppEventResource {

    @Autowired
    private AppEventService appEventService;

    @GET
    @Path("/order")
    public Result order(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.SUBSCRIPTION_ORDER,eventUrl);
    }

    @GET
    @Path("/change")
    public Result change(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.SUBSCRIPTION_CHANGE,eventUrl);
    }

    @GET
    @Path("/cancel")
    public Result cancel(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.SUBSCRIPTION_CANCEL,eventUrl);
    }

    @GET
    @Path("/status")
    public Result status(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.SUBSCRIPTION_STATUS,eventUrl);
    }

    @GET
    @Path("/notice")
    public Result notice(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.SUBSCRIPTION_NOTICE,eventUrl);
    }

    @GET
    @Path("/assigned")
    public Result assigned(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.USER_ASSIGNMENT,eventUrl);
    }

    @GET
    @Path("/unassigned")
    public Result unassigned(@QueryParam("eventUrl") String eventUrl){
        return appEventService.processEvent(EventType.USER_UNASSIGNMENT,eventUrl);
    }
}
