package com.khun.studentregistration.handler;

import com.khun.studentregistration.entity.MyUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userDetails.hasRole("ROLE_USER")) {
            redirectURL = "/student";
        } else if (userDetails.hasRole("ROLE_ADMIN")) {
            redirectURL = "/admin/dashboard";
        }

        response.sendRedirect(redirectURL);

    }




}
