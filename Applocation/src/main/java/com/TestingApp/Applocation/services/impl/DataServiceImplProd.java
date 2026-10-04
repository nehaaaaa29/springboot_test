package com.TestingApp.Applocation.services.impl;

import com.TestingApp.Applocation.services.DataService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class DataServiceImplProd implements DataService {

    @Override
    public String getData() {
        return "prod Data";
    }
}
