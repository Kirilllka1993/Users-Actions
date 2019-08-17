package com.example.demo.dto;

import com.example.demo.model.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserAccountDto {

    private long id;
    @Size(min = 3, max = 16)
    private String username;
    @Size(min = 3, max = 16)
    private String password;
    @Size(min = 1, max = 16)
    private String firstName;
    @Size(min = 1, max = 16)
    private String lastName;
    private String role;
    private String status;
    private LocalDate createdAt;

    public UserAccountDto (UserAccount userAccount){
        this.id= userAccount.getId();
        this.username=userAccount.getUsername();
        this.password=userAccount.getPassword();
        this.firstName=userAccount.getFirstName();
        this.lastName=userAccount.getLastName();
        this.role=userAccount.getRole().name();
        this.status=userAccount.getStatus().name();
        this.createdAt=userAccount.getCreatedAt();
    }
}
