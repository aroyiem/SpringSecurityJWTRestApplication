/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.service;

import com.web.spring.jwt.entities.AppUser;
import java.util.List;

/**
 *
 * @author subho
 */
public interface UserService {
    
    AppUser findById(Long id);
     
    AppUser findBySSO(String sso);
     
    void saveUser(AppUser user);
     
    void updateUser(AppUser user);
     
    void deleteUserBySSO(String sso);
 
    List<AppUser> findAllUsers(); 
     
    boolean isUserSSOUnique(Long id, String sso);
}
