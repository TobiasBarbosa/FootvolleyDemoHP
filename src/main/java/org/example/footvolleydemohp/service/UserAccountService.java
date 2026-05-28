package org.example.footvolleydemohp.service;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.exceptions.customexceptions.userexceptions.UserAlreadyExistsException;
import org.example.footvolleydemohp.exceptions.customexceptions.userexceptions.UserNotFoundException;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    //ACCESS ATTRIBUTES***----------------------------------------------------------------------------------------------
    private final UserAccountRepository userAccountRepository;

    //***BUSINESS LOGIC & CRUD***---------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    public UserAccount createUser(UserAccount user) {
        // email must be unique
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        return userAccountRepository.save(user);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    public UserAccount getUserById(Long id) {
        return userAccountRepository.findById(id)
                                    .orElseThrow(()-> new UserNotFoundException(id));
    }

    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    //-----------------------------------------------------------------------------------------------------------------U
    public UserAccount updateUser(Long id, UserAccount updatedUser) {

        UserAccount existingUser = getUserById(id);

        // Business rule: email uniqueness check (only if changed)
        if (!existingUser.getEmail().equals(updatedUser.getEmail())
                && userAccountRepository.existsByEmail(updatedUser.getEmail())) {

            throw new UserAlreadyExistsException(updatedUser.getEmail());
        }

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAddressLine(updatedUser.getAddressLine());
        existingUser.setCity(updatedUser.getCity());
        existingUser.setPostalCode(updatedUser.getPostalCode());
        existingUser.setCountry(updatedUser.getCountry());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setMember(updatedUser.isMember());
        existingUser.setRole(updatedUser.getRole());

        return userAccountRepository.save(existingUser);
    }

    //-----------------------------------------------------------------------------------------------------------------D
    public void deleteUser(Long id) {
        UserAccount user = getUserById(id);
        userAccountRepository.delete(user);
    }
}
