package com.hitrac.SpringProject.repsitory;


import com.hitrac.SpringProject.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDtls,Long> {

    Optional<UserDtls> findByUsername(String username);
    public UserDtls findByEmail(String email);
    public boolean existsByEmail(String email);





}
