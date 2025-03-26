package com.room.authentication.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.authentication.dto.DriverInStation.DriverInStationAuthRequest;
import com.room.authentication.dto.DriverInStation.DriverStationAuthResponse;
import com.room.authentication.repository.DriverRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class StationService {


    private static final  String REQUEST_TOPIC = "request";
    private static final String RESPONSE_TOPIC = "response";
    private static final String GROUP_ID = "auth";

    ObjectMapper objectMapper = new ObjectMapper();

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DriverRepository driverRepo;

    public StationService(KafkaTemplate<String, String> kafkaTemplate, DriverRepository driverRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.driverRepo = driverRepository;
    }

    @KafkaListener(topics = REQUEST_TOPIC, groupId = GROUP_ID)
    public void AuthenticateDriverInStation(ConsumerRecord<String, String> receivedRecord) throws JsonProcessingException {
        var correlationId = GetCorrelationId(receivedRecord);

        var data = ValidateData(receivedRecord.value());
        if (data == null) {
            SendResponse(DriverStationAuthResponse.AuthorizationStatus.INVALID, correlationId);
            return;
        }

        var driverData = driverRepo.findByDriverId(data.driverIdentifier().id());

        if (driverData.isEmpty()) {
            SendResponse(DriverStationAuthResponse.AuthorizationStatus.UNKNOWN, correlationId);
            return;
        }

        if (!driverData.get().chargeAllowed) {
            SendResponse(DriverStationAuthResponse.AuthorizationStatus.REJECTED, correlationId);
            return;
        }
        SendResponse(DriverStationAuthResponse.AuthorizationStatus.ACCEPTED, correlationId);

    }


    private void SendResponse(DriverStationAuthResponse.AuthorizationStatus status, byte[] correlationId) throws JsonProcessingException {
        var responseModel = new DriverStationAuthResponse(status);
        var text = objectMapper.writeValueAsString(responseModel);
        ProducerRecord<String, String> response = new ProducerRecord<String, String>(RESPONSE_TOPIC, text);

        response.headers().add(new RecordHeader(KafkaHeaders.CORRELATION_ID, correlationId));

        kafkaTemplate.send(response);
    }

    private   byte[] GetCorrelationId(ConsumerRecord<String, String> record) {
     //   var correlationId = "".getBytes();
        for (Header header : record.headers()) {
            System.out.println("Header Key: " + header.key());
            if (Objects.equals(header.key(), KafkaHeaders.CORRELATION_ID)) {
             return header.value();
            }
        }
        return new byte[0];
    }


    private DriverInStationAuthRequest ValidateData(String input) {


        DriverInStationAuthRequest result = null;
        try {
            result = objectMapper.readValue(input, DriverInStationAuthRequest.class);
        } catch (JsonProcessingException e) {
            return null;
        }
        return result;
    }


}
