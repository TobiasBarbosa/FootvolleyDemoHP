package org.example.footvolleydemohp.service;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.model.Role;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    //ACCESS ATTRIBUTES***----------------------------------------------------------------------------------------------
    private final UserAccountRepository userAccountRepository;

    //***BUSINESS LOGIC & CRUD***---------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    public UserAccount createUser(UserAccount user) {
        return userAccountRepository.save(user);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    public UserAccount getUserById(Long id) {
        return userAccountRepository.findById(id)
                                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    public List<UserAccount> getAllUsersAsAdmin(UserAccount requester) {

        if (requester.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Not authorized");
        }

        return userAccountRepository.findAll();
    }

    //-----------------------------------------------------------------------------------------------------------------U
    //-----------------------------------------------------------------------------------------------------------------D
    public void deleteUser(Long id) {
        UserAccount user = getUserById(id);
        userAccountRepository.delete(user);
    }
}
