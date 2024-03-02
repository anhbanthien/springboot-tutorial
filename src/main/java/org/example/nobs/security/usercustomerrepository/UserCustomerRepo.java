package org.example.nobs.security.usercustomerrepository;

import org.example.nobs.security.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCustomerRepo extends JpaRepository<CustomerUser,String> {
}
