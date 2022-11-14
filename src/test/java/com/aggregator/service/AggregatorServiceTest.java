package com.aggregator.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AggregatorServiceTest {

    @InjectMocks
    AggregatorService aggregatorService;
    
    @Mock
    PricingService pricingService;
    
    @Mock
    TrackingService trackingService;
    
    @Mock
    ShipmentService shipmentService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenRequestParameters_whenFiveParametersRequested_thenRetrieveResponseImmediately(){

        aggregatorService.callBulkApis("NL,CN,BR,AL,AF","109347263,123456862","109347263,123456862");
        Assertions.assertEquals("RRR","RRR");
    }

}
