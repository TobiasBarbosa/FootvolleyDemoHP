package org.example.footvolleydemohp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.dto.userAccount.CreateUserAccountRequestDTO;
import org.example.footvolleydemohp.dto.userAccount.UserAccountResponseDTO;
import org.example.footvolleydemohp.mapper.UserAccountMapper;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    //***DEPENDENCIES***------------------------------------------------------------------------------------------------
    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;

    //***CRUD***--------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @PostMapping
    public ResponseEntity<UserAccountResponseDTO> createUserAccount(@Valid @RequestBody CreateUserAccountRequestDTO dto) {
        UserAccount user = userAccountMapper.toEntity(dto);
        UserAccount saved = userAccountService.createUser(user);

        return ResponseEntity
                            .created(URI.create("/api/users/" + saved.getId()))
                            .body(userAccountMapper.toResponseDTO(saved));
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponseDTO> getUserAccountById(@PathVariable Long id) {

        UserAccount user = userAccountService.getUserById(id);

        return ResponseEntity.ok(userAccountMapper.toResponseDTO(user));
    }

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAllUserAccounts() {

        List<UserAccountResponseDTO> users = userAccountService.getAllUsers()
                                                               .stream()
                                                               .map(userAccountMapper::toResponseDTO)
                                                               .toList();

        return ResponseEntity.ok(users);
    }

    //-----------------------------------------------------------------------------------------------------------------U
    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponseDTO> updateUserAccount(@PathVariable Long id, @Valid
                                                                    @RequestBody CreateUserAccountRequestDTO dto) {
        UserAccount updated = userAccountMapper.toEntity(dto);
        UserAccount saved = userAccountService.updateUser(id, updated);

        return ResponseEntity.ok(userAccountMapper.toResponseDTO(saved));
    }

    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }


}