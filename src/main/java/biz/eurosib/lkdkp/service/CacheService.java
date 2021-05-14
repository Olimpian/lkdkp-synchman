package biz.eurosib.lkdkp.service;

import biz.eurosib.lkdkp.cachedb.CacheAnswer;
import biz.eurosib.lkdkp.cachedb.CacheAnswerRepository;
import biz.eurosib.lkdkp.client.WfcClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private WfcClient wfcClient;
    private CacheAnswerRepository repository;

    @Autowired
    public CacheService(WfcClient wfcClient,
                        CacheAnswerRepository repository) {
        this.wfcClient = wfcClient;
        this.repository = repository;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 600000)
    public void cache(){
        String request = "{\"request\":\"getLocationList\",\"data\":\"\"}";

        String answer = wfcClient.convert(request);

        CacheAnswer cacheAnswer = new CacheAnswer();
        cacheAnswer.setRequest("getLocationList");
        cacheAnswer.setData(answer);
        repository.save(cacheAnswer);
    }

    public void cache(String request) {
        String answer = wfcClient.convert(request);

        JSONObject jsonRequest = new JSONObject(request);
        CacheAnswer cacheAnswer = new CacheAnswer();
        cacheAnswer.setRequest(jsonRequest.getString("request"));
        cacheAnswer.setData(answer);
        repository.save(cacheAnswer);
    }
}
