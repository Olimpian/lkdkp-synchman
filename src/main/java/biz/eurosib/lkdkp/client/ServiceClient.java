package biz.eurosib.lkdkp.client;

import biz.eurosib.lkdkp.client.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import java.util.UUID;

public class ServiceClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(ServiceClient.class);

    public ResponseResponse getValue(String value) {
        log.info("client test");

        Response request = new Response();

//        request.setGUID(UUID.randomUUID().toString());
//        request.setRequest(value);

        ObjectFactory factory = new ObjectFactory();
        request.setJson(factory.createResponseJson(value));
        request.setTaskId(factory.createResponseTaskId(UUID.randomUUID().toString()));

        log.info("Requesting location for " + value);


//        ResponseResponse response = (ResponseResponse) getWebServiceTemplate()
//                .marshalSendAndReceive(request,
//                        new SoapActionCallback("http://www.Eurosib.biz/DataCoreWS#DataCoreWS_response:response"));

        ResponseResponse response = (ResponseResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request,
                        new SoapActionCallback("http://tempuri.org/IService1/response"));

        return response;
    }
}
