package com.neotao.testing.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public Map<String, Object> getSomeData(String queryString) {
        Map<String,Object> data = new HashMap<>();
        data.put(queryString,"this is testData");
        return data;
    }
}
