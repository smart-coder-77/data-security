package com.user.data.repository;

import com.user.data.entity.AppUser;
import com.user.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Choudhury Subham on 13-05-2022
 */
@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
