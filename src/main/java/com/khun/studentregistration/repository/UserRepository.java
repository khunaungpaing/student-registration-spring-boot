package com.khun.studentregistration.repository;

import com.khun.studentregistration.entity.Role;
import com.khun.studentregistration.entity.Student;
import com.khun.studentregistration.entity.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends DataTablesRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
    User findByCode(String code);
    long countByRolesContains(Role role);
    List<User> findUsersByRolesContains(Role role);

}
