package com.azoft.service.processor;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.service.entity.User;
import com.azoft.service.repository.UserRepository;
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
    public void process(Exchange exchange) throws Exception {
        UserCreationReq userCreationReq = (UserCreationReq) exchange.getIn().getBody();
//            if (userRepository.findByEmail(userCreationReq.getEmail()) == null) {
//                User user = new User(null, userCreationReq.getName(), userCreationReq.getSurname(), userCreationReq.getPatronymic(), userCreationReq.getEmail());
//
//                userRepository.save(user);
//                log.info("User with email [{}] was created", user.getEmail());
//
//                return new UserServiceResponseDto<>(null, 200, "Success");
//            } else {
//                log.info("User with email [{}] has already been created", userCreationReq.getEmail());
//                return new UserServiceResponseDto<>(null, 400, "User with email [" + userDto.getEmail() + "] has already been created");
//            }
//        log.info("Unable to process a request of the [{}] type for payload [UserDto] type", requestType);
//        return new UserServiceResponseDto<>(null, 405, "Unable to process a request of the [" + requestType + "] type for payload [UserDto] type");
    }
}
