package com.room.authentication.util;

import com.room.authentication.config.AppConfig;
import com.room.authentication.dto.MetaResult;
import com.room.authentication.dto.MetaResultTyped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MetaResultFactory {

    final AppConfig appConfig;

    public MetaResultFactory(AppConfig appConfig) {
        this.appConfig = appConfig;
    }


    public  MetaResult create(String developerMessage, String messageCode, List<String> errors) {
        String devMessage = appConfig.IsProduction() ? "" : developerMessage;
        return new MetaResult(errors,devMessage, messageCode);
    }

    public  <T> MetaResultTyped<T> create(T result, String developerMessage, String messageCode,  List<String> errors) {
        String devMessage = appConfig.IsProduction() ? "" : developerMessage;
        return new MetaResultTyped<>(result,errors, devMessage, messageCode);
    }


}
