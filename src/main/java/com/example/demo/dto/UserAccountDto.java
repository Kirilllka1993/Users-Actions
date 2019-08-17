package com.example.demo.dto;

import com.example.demo.model.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserAccountDto {

    private long id;
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^[A-Za-z]*$", message = "Username состоит только из латинских букв")
    private String username;
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^(?=.*[0-9])[A-Za-z0-9].{2,}$", message = "Пароль должен иметь минимум одну цифру и должен состоять из латинских букв и цифр")
    private String password;
    @Size(min = 1, max = 16)
    @Pattern(regexp = "^[A-Za-z]*$", message = "First name состоит только из латинских букв")
    private String firstName;
    @Size(min = 1, max = 16)
    @Pattern(regexp = "^[A-Za-z]*$", message = "Last name состоит только из латинских букв")
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
