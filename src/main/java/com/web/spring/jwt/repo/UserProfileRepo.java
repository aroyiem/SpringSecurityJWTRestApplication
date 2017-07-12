/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.repo;

import com.web.spring.jwt.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author subho
 */
public interface UserProfileRepo extends JpaRepository<UserProfile, Integer>{
    
    public UserProfile findById(int id);
    
    public UserProfile findByType(String type);
}
