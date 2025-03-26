package com.room.authentication.config;

import com.room.authentication.constants.GeneralConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
//@Component
public class AppConfig {
    @Value("${env}")
    public String Env;


    public boolean IsProduction(){
        return Env.equals(GeneralConstants.Prod);
    }

}




