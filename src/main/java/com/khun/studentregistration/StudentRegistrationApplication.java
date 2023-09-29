package com.khun.studentregistration;

import com.khun.studentregistration.entity.Role;
import com.khun.studentregistration.entity.User;
import com.khun.studentregistration.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class StudentRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentRegistrationApplication.class, args);
    }



//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null, "ADMIN"));
//            userService.saveRole(new Role(null, "USER"));
//            userService.saveUser(new User(null,"USR0009","Khun Aung Paing","khun","khun@gmail.com","$2a$12$L0BhxelOE9gX6etT8zT2AuvZWASgfBa7gDaTGo6uvmKunqC1jKuMq",true,new HashSet<>(),new HashSet<>()));
//            userService.saveUser(new User(null,"USR0010","Mg Mg","mgmg","mg@gmail.com","$2a$12$L0BhxelOE9gX6etT8zT2AuvZWASgfBa7gDaTGo6uvmKunqC1jKuMq",true,new HashSet<>(),new HashSet<>()));
//
//            userService.addRoleToUser("khun","ADMIN");
//            userService.addRoleToUser("khun","USER");
//            userService.addRoleToUser("mgmg","USER");
//
//        };
//    }
}
