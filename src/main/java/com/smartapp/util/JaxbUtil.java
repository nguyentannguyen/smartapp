package com.smartapp.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Jaxb util class to unmarshal bytes to a POJO
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
public class JaxbUtil {

    public <T> T unmarshalResponse(byte[] bytes, Class<T> clazz) throws JAXBException, IOException {
        ByteArrayInputStream input = null;
        try{
            input = new ByteArrayInputStream(bytes);
            JAXBContext jc = JAXBContext.newInstance (clazz);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (T) unmarshaller.unmarshal(input);
        }finally {
            if (input!=null){
                input.close();
            }
        }
    }
}
