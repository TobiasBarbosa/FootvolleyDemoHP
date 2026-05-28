package org.example.footvolleydemohp.mapper;

import org.example.footvolleydemohp.dto.userAccount.CreateUserAccountRequestDTO;
import org.example.footvolleydemohp.dto.userAccount.UserAccountResponseDTO;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    //***DTO → ENTITY***-----------------------------------------------------------------------------------------------
    public UserAccount toEntity(CreateUserAccountRequestDTO dto) {

        UserAccount user = new UserAccount();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setAddressLine(dto.getAddressLine());
        user.setCity(dto.getCity());
        user.setPostalCode(dto.getPostalCode());
        user.setCountry(dto.getCountry());
        user.setPassword(dto.getPassword());
        user.setMember(dto.isMember());

        // STRING → ENUM conversion
        user.setRole(Role.valueOf(dto.getRole()));

        return user;
    }

    //***ENTITY → RESPONSE DTO***--------------------------------------------------------------------------------------
    public UserAccountResponseDTO toResponseDTO(UserAccount user) {

        return new UserAccountResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddressLine(),
                user.getCity(),
                user.getPostalCode(),
                user.getCountry(),
                user.isMember(),
                user.getRole().name()
        );
    }
}