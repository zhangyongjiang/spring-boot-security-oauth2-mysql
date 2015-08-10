package org.magnum.mobilecloud.video.repository;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiService {
	@Autowired UserDetailsService userDetailsService;
	@Autowired PasswordEncoder passwordEncoder;

    @RequestMapping("/pub/{productId}")
    public ResponseEntity<String> getProductComposite(
        @PathVariable int productId) {

    	return new ResponseEntity<String>(productId+"", HttpStatus.OK);
    }

    @RequestMapping("/hi")
    public ResponseEntity<String> sayHi() {
    	return new ResponseEntity<String>("hello world", HttpStatus.OK);
	}

    @RequestMapping("/admin/add-user/{username}/{password}")
    public ResponseEntity<String> adminResource(Principal currentUser, @PathVariable("username") String username, @PathVariable("password") String password) {
    	JdbcUserDetailsManager userDetailsManager = (JdbcUserDetailsManager) userDetailsService;
    	userDetailsManager.createUser(new User(username, passwordEncoder.encode(password), new ArrayList<GrantedAuthority>()));
      	return new ResponseEntity<String>(currentUser.getName(), HttpStatus.OK);
	}

}
