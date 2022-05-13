package com.user.data.repository;

import com.user.data.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Choudhury Subham on 13-05-2022
 */

@Repository
public interface UserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String userName);
}
