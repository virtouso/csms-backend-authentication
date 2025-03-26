package com.room.authentication.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record MetaResultTyped<T> (

        @JsonProperty("result") T result,
        @JsonProperty("errors") List<String> errors,
        @JsonProperty("developer_message") String developerMessage,
        @JsonProperty("message_code") String messageCode) {


//    public static <T> MetaResultTyped<T> create(T result, String developerMessage, String messageCode, boolean isProd, List<String> errors) {
//        String devMessage = isProd ? "" : developerMessage;
//        return new MetaResultTyped<>(result,errors, devMessage, messageCode);
//    }

}
