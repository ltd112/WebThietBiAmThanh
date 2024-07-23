package com.iuh.webthietbiamthanh.service.impl;

import com.iuh.webthietbiamthanh.models.User;
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
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsers(String role) {
        return null;
    }

    @Override
    public Boolean updateAccountStatus(Integer id, Boolean status) {
        return null;
    }

    @Override
    public void increaseFailedAttempt(User user) {

    }

    @Override
    public void userAccountLock(User user) {

    }

    @Override
    public boolean unlockAccountTimeExpired(User user) {
        return false;
    }

    @Override
    public void resetAttempt(int userId) {

    }

    @Override
    public void updateUserResetToken(String email, String resetToken) {

    }

    @Override
    public User getUserByToken(String token) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
