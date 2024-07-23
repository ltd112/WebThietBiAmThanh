package com.iuh.webthietbiamthanh.repository;


import com.iuh.webthietbiamthanh.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);
    public List<User> findByRole(String role);
    public User findByResetToken(String token);
}
