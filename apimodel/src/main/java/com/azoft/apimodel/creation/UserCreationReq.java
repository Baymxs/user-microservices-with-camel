package com.azoft.apimodel.creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationReq implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
}


