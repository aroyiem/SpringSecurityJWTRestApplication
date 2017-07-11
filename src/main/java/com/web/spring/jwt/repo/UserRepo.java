/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.repo;


import com.web.spring.jwt.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 330085
 */
public interface UserRepo extends JpaRepository<AppUser, Long>{
    
    public AppUser findById(Long id);
    
    public AppUser findBySsoId(String ssoId);
    
    public void removeByssoId(String ssoId);
    
    public void removeByFirstName(String firstName);   
    
}
