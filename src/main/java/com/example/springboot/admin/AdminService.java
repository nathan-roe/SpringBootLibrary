package com.example.springboot.admin;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole(@troles.ADMIN)")
    public boolean ensureAdmin() {
        return true;
    }
}
