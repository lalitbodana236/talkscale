package com.projectone.common.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    
    private String firstname;
    
    private String lastname;
    
    private String username;
    
    private String email;
    
    private String mobile;
}
