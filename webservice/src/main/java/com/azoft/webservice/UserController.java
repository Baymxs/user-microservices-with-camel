package com.azoft.webservice;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.apimodel.creation.UserCreationRes;
import com.azoft.apimodel.getting.UserGettingRes;
import com.azoft.apimodel.updating.UserUpdatingReq;
import com.azoft.webservice.service.ServiceRes;
import com.azoft.webservice.service.ServiceStatus;
import com.azoft.webservice.service.UserService;
import com.azoft.webservice.webcomon.HttpRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserCreationReq userCreationReq) {
        ServiceRes<UserCreationRes> result = userService.createUser(userCreationReq);

        if (result.getStatus() == ServiceStatus.USER_ALREADY_CREATED) {
            return HttpRes.conflict(HttpStatus.BAD_REQUEST, result.getStatus().name());
        }

        return HttpRes.ok(result.getBody());
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserUpdatingReq userUpdatingReq) {
        ServiceStatus serviceStatus = userService.updateUser(userUpdatingReq);

        if (serviceStatus == ServiceStatus.USER_NOT_FOUND) {
            return HttpRes.conflict(HttpStatus.NOT_FOUND, serviceStatus.name());
        }

        return HttpRes.ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        ServiceRes<UserGettingRes> result = userService.getUser(id);

        if (result.getStatus() == ServiceStatus.USER_NOT_FOUND) {
            return HttpRes.conflict(HttpStatus.NOT_FOUND, result.getStatus().name());
        }

        return HttpRes.ok(result.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        ServiceStatus serviceStatus = userService.deleteUser(id);

        if (serviceStatus == ServiceStatus.USER_NOT_FOUND) {
            return HttpRes.conflict(HttpStatus.NOT_FOUND, serviceStatus.name());
        }

        return HttpRes.ok();
    }
}
