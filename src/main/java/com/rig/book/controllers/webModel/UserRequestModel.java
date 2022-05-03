package com.rig.book.controllers.webModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequestModel {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "password is mandatory")
    private String password;

}
