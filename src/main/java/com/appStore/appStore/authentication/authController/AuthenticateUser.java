package com.appStore.appStore.authentication.authController;


import com.appStore.appStore.authentication.DO.DBClient;
import com.appStore.appStore.authentication.VO.ClientDB;
import com.appStore.appStore.authentication.authDomain.AuthenticationUser;
import com.appStore.appStore.authentication.authService.AuthenticationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
@RestController
public class AuthenticateUser {

    private AuthenticationUserService authUserService;

    @Autowired
    public AuthenticateUser(AuthenticationUserService authUserService) {
        this.authUserService = authUserService;
    }

    @RequestMapping(value ="authenticate/{username}" ,method = RequestMethod.GET)
    DBClient authenticate(@PathVariable("username") String username){
        return this.authUserService.returnUser(username);
    }

    @RequestMapping(value ="authenticate/register" ,method = RequestMethod.POST)
    AuthenticationUser registerUser(@RequestBody ClientDB clientDB){
       return this.authUserService.registerUser(clientDB);
    }


}
