package com.azoft.service.processor;

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
public class GetReqProcessor implements Processor {
    private final UserRepository userRepository;

    @Override
    public void process(Exchange exchange) {
        int id = Integer.parseInt(exchange.getIn().getHeader("id").toString());

        User user = userRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User with id [" + id + "] not found"));

        Http.success(exchange, user);
        log.info("User with id [{}] was received", id);
    }
}
