/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.repo;

import com.web.spring.jwt.entities.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author subho
 */
public interface PersistentLoginsRepo extends JpaRepository<PersistentLogins, String>{
    
    public PersistentLogins findBySeries(String seriesId);
    
    public PersistentLogins findByUsername(String username);
}
