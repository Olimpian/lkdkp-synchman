package biz.eurosib.lkdkp.service;

import biz.eurosib.lkdkp.client.ServiceClient;
import biz.eurosib.lkdkp.client.wsdl.ResponseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WfcService {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ServiceClient wfcClient;

    public String get(String request) {

        ResponseResponse response = wfcClient.getValue(request);
//        System.err.println(response.getReturn());
        System.err.println(response.getResponseResult().getValue());

//        return response.getReturn();
        return response.getResponseResult().getValue();
    }
}
