package com.smartapp.util;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class to sign the request with OAuth and result as bytes
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class FireEventUtil {

    @Value( "${consume_key}" )
    private String consume_key;

    @Value( "${consume_secret}" )
    private String consume_secret;

    public byte[] getDataFromEvent(String eventUrl) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        HttpURLConnection request = null;
        InputStream is = null;
        try{
            OAuthConsumer consumer = new DefaultOAuthConsumer(consume_key, consume_secret);
            URL url = new URL(eventUrl);
            request = (HttpURLConnection) url.openConnection();
            consumer.sign(request);
            request.connect();

            if (request.getResponseCode() == 200) {
                is = request.getInputStream();
                byte[] bytes = IOUtils.toByteArray(is);
                is.close();
                return bytes;
            } else {
                throw new RuntimeException("Response code: "+ request.getResponseCode() + " - Message: " + request.getResponseMessage());
            }
        }finally {
            if (request!=null){
                request.disconnect();
            }
            if (is!=null){
                is.close();
            }
        }
    }
}
