/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.dao;

import com.web.spring.jwt.entities.AppUser;
import com.web.spring.jwt.repo.UserRepo;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 *
 * @author subho
 */
@Repository("userDao")
public class UserDao {
    
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    
    @Autowired
    private UserRepo userRepo;
    
    public AppUser findById(Long id) {
        AppUser user = userRepo.findById(id);
        if(user!=null){
            user.getUserProfileList().size();
        }
        return user;
    }
 
    public AppUser findBySSO(String sso) {
        LOGGER.info("SSO : {}"+ sso);       
        AppUser user = userRepo.findBySsoId(sso);
        if(user!=null){
            user.getUserProfileList().size();
        }
        return user;
    }
 
    public List<AppUser> findAllUsers() {        
        List<AppUser> users = userRepo.findAll(sortByFirstNameAsc());
        return users;
    }
 
    public void save(AppUser user) {
        userRepo.save(user);
    }
 
    public void deleteBySSO(String sso) {
        userRepo.removeByssoId(sso);
    }
    
    private Sort sortByFirstNameAsc(){
        return new Sort(Sort.Direction.ASC, "firstName");
    }
}
