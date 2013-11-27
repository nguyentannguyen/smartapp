package com.smartapp.util;

import com.smartapp.model.Event;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class JaxbUtilTest {

    public static void main(String[] args){
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><event><creator><email>test-email+creator@appdirect.com</email><firstName>DummyCreatorFirst</firstName><language>fr</language><lastName>DummyCreatorLast</lastName><openId>https://www.appdirect.com/openid/id/ec5d8eda-5cec-444d-9e30-125b6e4b67e2</openId><uuid>ec5d8eda-5cec-444d-9e30-125b6e4b67e2</uuid></creator><flag>STATELESS</flag><marketplace><baseUrl>https://acme.appdirect.com</baseUrl><partner>ACME</partner></marketplace><payload><company><country>CA</country><email>company-email@example.com</email><name>Example Company Name</name><phoneNumber>415-555-1212</phoneNumber><uuid>d15bb36e-5fb5-11e0-8c3c-00262d2cda03</uuid><website>http://www.example.com</website></company><configuration><entry><key>domain</key><value>mydomain</value></entry></configuration><order><editionCode>BASIC</editionCode><item><quantity>10</quantity><unit>USER</unit></item><item><quantity>15</quantity><unit>MEGABYTE</unit></item><pricingDuration>MONTHLY</pricingDuration></order></payload><returnUrl>https://www.appdirect.com/finishprocure?token=dummyOrder</returnUrl><type>SUBSCRIPTION_ORDER</type></event>";
        JaxbUtil jb = new JaxbUtil();
        try {
            jb.unmarshalResponse(str.getBytes(),Event.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
