package com.azoft.apimodel.creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationReq implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
}


