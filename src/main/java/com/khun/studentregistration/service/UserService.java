package com.khun.studentregistration.service;

import com.khun.studentregistration.entity.Role;
import com.khun.studentregistration.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUserByUsername(String username);
    User getUserById(Long id);
    DataTablesOutput<User> getUsers(DataTablesInput input);
    List<User> getAllUsers();
    List<User> getUsersByRole(String roleName);

    Long getTeacherCount();

}
