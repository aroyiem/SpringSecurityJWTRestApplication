/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.rest.controller;

import com.web.spring.jwt.entities.AppUser;
import com.web.spring.jwt.security.JwtTokenUtil;
import com.web.spring.jwt.security.JwtUser;
import com.web.spring.jwt.service.UserProfileService;
import com.web.spring.jwt.service.UserService;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 330085
 */
@RestController
public class UserRestController {
    
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserProfileService userProfileService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET, headers="Accept=application/json")
    public List<AppUser> listUsers(ModelMap modelMap){        
        List<AppUser> userList = userService.findAllUsers();
        return userList;
    }
    
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid AppUser user, BindingResult result, ModelMap modelMap){
        
        if(result.hasErrors()){
            return "registration";
        }
        
        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
         * and applying it on field [sso] of Model class [User].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         * 
         */
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }
        
        userService.saveUser(user);        
        return "registrationsuccess";
    }
    
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid AppUser user, BindingResult result, @PathVariable String ssoId){
        
        if(result.hasErrors()){
            return "registration";
        }
        //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }
 
 
        userService.updateUser(user);

        return "update user successfull";
    }
    
    @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId){
        userService.deleteUserBySSO(ssoId);
        return "user successfully deleted";
    }

}
