package biz.eurosib.lkdkp.client;


import biz.eurosib.lkdkp.client.wsdl.datacore.Response;
import biz.eurosib.lkdkp.client.wsdl.datacore.ResponseResponse;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.UUID;

public class DataCoreClient extends WfcClient {
    private ServiceConfig config;

    public DataCoreClient(ServiceConfig config) {
        this.config = config;
    }

    public ResponseResponse getValue(String value) {
        log.info("client test");

        Response request = new Response();

        request.setGUID(UUID.randomUUID().toString());
        request.setRequest(value);

        log.info("Requesting location for " + value);


        ResponseResponse response = (ResponseResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request,
                        new SoapActionCallback(config.getSoapAction()));

        return response;
    }

    public String convert (String request) {
        ResponseResponse response = this.getValue(request);
        System.err.println(response.getReturn());

        return response.getReturn();
    }
}
