package com.iuh.webthietbiamthanh.service.impl;

import com.iuh.webthietbiamthanh.models.UserDtls;
import com.iuh.webthietbiamthanh.repository.UserRepository;
import com.iuh.webthietbiamthanh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDtls saveUser(UserDtls user) {
        user.setRole("ROLE_USER");
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        UserDtls saveUser = userRepository.save(user);
        return saveUser;
    }

    @Override
    public UserDtls getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDtls> getUsers(String role) {
        return null;
    }

    @Override
    public Boolean updateAccountStatus(Integer id, Boolean status) {
        return null;
    }

    @Override
    public void increaseFailedAttempt(UserDtls user) {

    }

    @Override
    public void userAccountLock(UserDtls user) {

    }

    @Override
    public boolean unlockAccountTimeExpired(UserDtls user) {
        return false;
    }

    @Override
    public void resetAttempt(int userId) {

    }

    @Override
    public void updateUserResetToken(String email, String resetToken) {

    }

    @Override
    public UserDtls getUserByToken(String token) {
        return null;
    }

    @Override
    public UserDtls updateUser(UserDtls user) {
        return null;
    }
}
