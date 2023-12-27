package com.MyBlogApplication.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginDto {
    private String usernameOEmail;
    private String password;
}
