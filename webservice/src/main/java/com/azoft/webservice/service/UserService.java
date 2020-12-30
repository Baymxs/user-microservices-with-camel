package com.azoft.webservice.service;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.apimodel.creation.UserCreationRes;
import com.azoft.apimodel.getting.UserGettingRes;
import com.azoft.apimodel.updating.UserUpdatingReq;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Produce(value = "direct:rabbitMQPoint")
    private ProducerTemplate producer;

    public ServiceRes<UserCreationRes> createUser(UserCreationReq req) {
        sendMessage(new Req(ReqType.CREATE, req));
        return null;
    }

    public ServiceStatus updateUser(UserUpdatingReq req) {
        sendMessage(new Req(ReqType.UPDATE, req));
        return null;
    }

    public ServiceRes<UserGettingRes> getUser(Long id) {
        sendMessage(new Req(ReqType.GET, id));
        return null;
    }

    public ServiceStatus deleteUser(Long id) {
        sendMessage(new Req(ReqType.DELETE, id));
        return null;
    }

    private <T> void sendMessage(T body) {
        producer.asyncSendBody(producer.getDefaultEndpoint(), body);
    }
}


