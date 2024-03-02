package org.example.nobs.security;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.nobs.repo.CustomerRepo;
import org.example.nobs.security.usercustomerrepository.UserCustomerRepo;
import org.example.nobs.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserCustomerRepo userCustomerRepo;
    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody LoginRequest request){
        try{
            // đóng gói thông tin người dùng gửi đến api
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
           //kiểm tra hợp lệ hay không và trả về đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(token);
            //lưu trữ thông tin xác thực của người dùng , cho phép ứng dụng tiếp tục sử dụng thông tin này trong quá trình xử lý yêu cầu tiếp theo
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //tạo token
            String jwtToken = JWTUtils.generateToken(request.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwtToken));
        }catch (Exception E){
          return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");}
    }

    @PostMapping("/create-new")
    public ResponseEntity createUser (@RequestBody LoginRequest loginRequest){
        Optional<CustomerUser> customerRepoOptional = userCustomerRepo.findById(loginRequest.getUsername());
            if (customerRepoOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exists");
            }
            CustomerUser customerUser = new CustomerUser();
            customerUser.setUsername(loginRequest.getUsername());
            customerUser.setPassword(encoder.encode(loginRequest.getPassword()));
            userCustomerRepo.save(customerUser);
            return ResponseEntity.ok("success");
        }

}
