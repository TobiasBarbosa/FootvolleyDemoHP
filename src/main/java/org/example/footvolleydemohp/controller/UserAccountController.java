package org.example.footvolleydemohp.controller;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody UserAccount user) {
        return ResponseEntity.status(201)
                .body(userAccountService.createUser(user));
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
    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
