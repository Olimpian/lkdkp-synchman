package biz.eurosib.lkdkp.service;

import biz.eurosib.lkdkp.cachedb.CacheAnswer;
import biz.eurosib.lkdkp.cachedb.CacheAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private WfcService wfcService;
    private CacheAnswerRepository repository;

    @Autowired
    public CacheService(WfcService wfcService,
                        CacheAnswerRepository repository) {
        this.wfcService = wfcService;
        this.repository = repository;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 600000)
    public void cache(){
        String request = "{\"request\":\"getLocationList\",\"data\":\"\"}";

        String answer = wfcService.get(request);

        CacheAnswer cacheAnswer = new CacheAnswer();
        cacheAnswer.setRequest("getLocationList");
        cacheAnswer.setData(answer);
        repository.save(cacheAnswer);
    }
}
