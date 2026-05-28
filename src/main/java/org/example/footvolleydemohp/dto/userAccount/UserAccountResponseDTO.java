package org.example.footvolleydemohp.dto.userAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAccountResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String addressLine;
    private String city;
    private String postalCode;
    private String country;

    private boolean isMember;
    private String role;
}
