package org.example.nobs.security;

import org.example.nobs.security.usercustomerrepository.UserCustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserCustomerRepo userCustomerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         CustomerUser customerUser = userCustomerRepo.findById(username).get();

        return User
                .withUsername(customerUser.getUsername())
                .password(customerUser.getPassword())
                .build() ;
    }
}
