package com.azoft.service.processor;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.service.entity.User;
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
public class CreateReqProcessor implements Processor {
    private final UserRepository userRepository;

    @Override
    public void process(Exchange exchange) {
        UserCreationReq userCreationReq = (UserCreationReq) exchange.getIn().getBody();

        if (userRepository.findByEmail(userCreationReq.getEmail()) == null) {
            User user = new User(null, userCreationReq.getName(), userCreationReq.getSurname(), userCreationReq.getPatronymic(), userCreationReq.getEmail());

            Http.success(exchange, userRepository.save(user));
            log.info("User with email [{}] was created", user.getEmail());
        } else {
            Http.badRequest(exchange, "User with email [" + userCreationReq.getEmail() + "] has already been created");
            log.info("User with email [{}] has already been created", userCreationReq.getEmail());
        }
    }
}
