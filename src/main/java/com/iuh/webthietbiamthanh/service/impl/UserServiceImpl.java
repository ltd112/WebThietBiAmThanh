package com.iuh.webthietbiamthanh.service.impl;

import com.iuh.webthietbiamthanh.models.UserDtls;
import com.iuh.webthietbiamthanh.repository.UserRepository;
import com.iuh.webthietbiamthanh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDtls saveUser(UserDtls user) {
        return null;
    }

    @Override
    public UserDtls getUserByEmail(String email) {
        return null;
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
