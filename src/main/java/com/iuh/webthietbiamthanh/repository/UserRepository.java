package com.iuh.webthietbiamthanh.repository;


import com.iuh.webthietbiamthanh.models.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDtls, String> {
    public UserDtls findByEmail(String email);
    public List<UserDtls> findByRole(String role);
    public UserDtls findByResetToken(String token);
}
