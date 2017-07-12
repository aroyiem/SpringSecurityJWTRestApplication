/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.service;

import com.web.spring.jwt.entities.UserProfile;
import com.web.spring.jwt.repo.UserProfileRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author subho
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    private UserProfileRepo userProfileRepo;
    
    @Override
    public UserProfile findById(int id) {
        return userProfileRepo.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return userProfileRepo.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileRepo.findAll();
    }
    
}
