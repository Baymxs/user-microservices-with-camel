package com.azoft.apimodel.updating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatingReq implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
}
