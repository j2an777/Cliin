package com.cliin.cliinbn2.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private final HttpServletRequest request;

    public SessionManager(HttpServletRequest request) {
        this.request = request;
    }

    public String getUserIdFromSession() {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("userId");
    }
}
