package com.azoft.service.processor;

import com.azoft.apimodel.updating.UserUpdatingReq;
import com.azoft.service.entity.User;
import com.azoft.service.exeption.ResourceNotFoundException;
import com.azoft.service.repository.UserRepository;
import com.azoft.service.webcommon.Http;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateReqProcessor implements Processor {
    private final UserRepository userRepository;

    @Override
    public void process(Exchange exchange) {
        UserUpdatingReq userUpdatingReq = (UserUpdatingReq) exchange.getIn().getBody();

        int id = Integer.parseInt(exchange.getIn().getHeader("id").toString());

        User user = userRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User with id [" + id + "] not found"));

        user.setName(userUpdatingReq.getName());
        user.setSurname(userUpdatingReq.getSurname());
        user.setPatronymic(userUpdatingReq.getPatronymic());
        user.setEmail(userUpdatingReq.getEmail());

        Http.success(exchange, userRepository.save(user));
        log.info("User with id [{}] was updated", id);
    }
}
