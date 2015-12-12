package com.bounswe2015group5.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConversionServiceConfiguration implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired private Set<Converter<?, ?>> converters;
    @Autowired private ConversionService defaultConversionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        GenericConversionService gcs = (GenericConversionService) defaultConversionService;
        for(Converter<?, ?> converter : converters) {
            gcs.addConverter(converter);
        }
    }
}


