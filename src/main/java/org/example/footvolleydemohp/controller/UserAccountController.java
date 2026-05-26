package org.example.footvolleydemohp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final UserAccountService userAccountService;

    //***CRUD***--------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @PostMapping
    public ResponseEntity<UserAccount> createUserAccount(
            @Valid @RequestBody UserAccount user) {

        UserAccount createdUser = userAccountService.createUser(user);

        return ResponseEntity
                .created(URI.create("/api/users/" + createdUser.getId()))
                .body(createdUser);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUserAccounts() {

        return ResponseEntity.ok(userAccountService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccountById(@PathVariable Long id) {

        return ResponseEntity.ok(userAccountService.getUserById(id));
    }

    //-----------------------------------------------------------------------------------------------------------------U
    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable Long id, @Valid @RequestBody UserAccount user) {
        return ResponseEntity.ok(userAccountService.updateUser(id, user));
    }

    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}