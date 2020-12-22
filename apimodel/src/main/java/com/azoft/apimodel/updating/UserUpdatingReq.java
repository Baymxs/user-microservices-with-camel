package com.azoft.apimodel.updating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatingReq {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
}
