package com.example.evaluation.controller;

import com.example.evaluation.DTO.API;
import com.example.evaluation.DTO.LoginForm;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.User;
import com.example.evaluation.service.AuthenticationService;
import com.example.evaluation.service.MyUserDetailsService;
import com.example.evaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
   // private final MyUserDetailsService detailsService;

    private final AuthenticationService authenticationService;
    Logger logger= LoggerFactory.getLogger(UserController.class);


    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        logger.info("get all user");
        List<User> user=userService.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<API> adduser(@RequestBody  @Valid User user){
         logger.info("add user");
        userService.adduser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("New User added !",201));
    }
    @PostMapping("/login")
    public ResponseEntity<API> login(@RequestBody @Valid LoginForm info){
        logger.info("login user");
        Authentication auth = new UsernamePasswordAuthenticationToken(info.getUsername(),info.getPassword());
        var validAuth = this.authenticationService.authenticate(auth);
        if(validAuth!=null){
            SecurityContext context = new SecurityContextImpl();
            context.setAuthentication(validAuth);
            SecurityContextHolder.setContext(context);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Login success!",200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Login failed!",400));
    }


    @PutMapping("/update-user/{id}")
    public ResponseEntity<API> UpdateUser(@RequestBody @Valid User user,@PathVariable Integer id) throws RecordNotFoundException {
         logger.info("update user");
        userService.updateUser(user,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("User Update",201));
    }


    @DeleteMapping("/remove-user/{id}")
    public ResponseEntity<API> removeUser(@PathVariable Integer id){
        logger.info("remove user");
        var removed = userService.removeUser(id);
        if(removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Record deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }


}
