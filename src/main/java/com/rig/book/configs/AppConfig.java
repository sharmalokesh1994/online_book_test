package com.rig.book.configs;

import com.rig.book.model.UserModel;
import com.rig.book.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    public void createAdminUser() {
        UserModel userModel = UserModel.builder().username("admin@admin.com").password("admin").role("ROLE_ADMIN").build();
        userDetailsService.save(userModel);
    }

}
