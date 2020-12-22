package com.azoft.webservice.service;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.apimodel.creation.UserCreationRes;
import com.azoft.apimodel.getting.UserGettingRes;
import com.azoft.apimodel.updating.UserUpdatingReq;
import lombok.Getter;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Getter
    private static String messageType;

    @Produce(value = "direct:startRabbitMQPoint")
    private ProducerTemplate producer;

    public ServiceResult<UserCreationRes> createUser(UserCreationReq userCreationReq) {
        messageType = RequestType.CREATE.name();
        sendMessage(userCreationReq);
        return null;
    }

    public ServiceStatus updateUser(UserUpdatingReq userUpdatingReq) {
        messageType = RequestType.UPDATE.name();
        sendMessage(userUpdatingReq);
        return null;
    }

    public ServiceResult<UserGettingRes> getUser(Long id) {
        messageType = RequestType.GET.name();
        sendMessage(id);
        return null;
    }

    public ServiceStatus deleteUser(Long id) {
        messageType = RequestType.DELETE.name();
        sendMessage(id);
        return null;
    }

    private <T> void sendMessage(T body) {
        producer.asyncSendBody(producer.getDefaultEndpoint(), body);
    }
}


