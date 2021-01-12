package com.azoft.service.processor;

import com.azoft.service.exeption.ResourceNotFoundException;
import com.azoft.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReqProcessor implements Processor {
    private final UserRepository userRepository;

    @Override
    public void process(Exchange exchange) {
        int id = Integer.parseInt(exchange.getIn().getHeader("id").toString());

        userRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User with id [" + id + "] not found"));


        userRepository.deleteById(id);
    }
}
