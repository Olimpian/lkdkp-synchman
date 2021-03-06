package biz.eurosib.lkdkp.controller;

import biz.eurosib.lkdkp.cachedb.CacheAnswer;
import biz.eurosib.lkdkp.cachedb.CacheAnswerRepository;
import biz.eurosib.lkdkp.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CacheController {
    @Autowired
    private CacheAnswerRepository repository;
    @Autowired
    private CacheService service;

    @GetMapping("/cached")
    public boolean isCached(@RequestParam(name = "request") String request) {
        List<CacheAnswer> answers = repository.findByRequest(request);
        return !answers.isEmpty();
    }

    @GetMapping("/cache")
    public String cache() {
        service.cache();
        return "cached";
    }

    @GetMapping("/cache")
    public String cache(@RequestParam(name = "request") String request) {
        service.cache(request);
        return "cached " + request;
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello, i'm sych-manager!";
    }

    @GetMapping("/version")
    public String version() {
        //todo release version
        return "1.0";
    }
}
