package com.khun.studentregistration.service.impl;

import com.khun.studentregistration.entity.MyUserDetails;
import com.khun.studentregistration.entity.Role;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.repository.RoleRepository;
import com.khun.studentregistration.repository.UserRepository;
import com.khun.studentregistration.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void saveUser(User user) {
        log.info("Saving new user{} to the database", user.getName());
        userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role{} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public DataTablesOutput<User> getUsers(DataTablesInput input) {
        log.info("Fetching all users");
        return userRepository.findAll(input);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        return userRepository.findUsersByRolesContains(role);
    }

    @Override
    public Long getTeacherCount() {
        Role role = roleRepository.findByName("ROLE_TEACHER");
        return userRepository.countByRolesContains(role);
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElse(userRepository.findByUsername(username));

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
