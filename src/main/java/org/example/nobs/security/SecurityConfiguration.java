package org.example.nobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("123"))
                .roles("BASIC","SPECIAL")
                .build();
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("123"))
                .roles("BASIC")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
            return  httpSecurity
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
//                    .requestMatchers(HttpMethod.GET,"special").hasRole("SPECIAL")
//                    .requestMatchers(HttpMethod.GET,"basic").hasRole("SPECIAL,BASIC")
                    .and()
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults())
                    .build();
    }

    //note: trong đoạn mã này tôi đã sử dụng @EnableGlobalMethodSecurity(prePostEnabled = true)
    // để kích hoạt việc sử dụng các Annotation bảo mật trên phương thức như @PreAuthorize.
    //luồng hoạt động của nó sẽ là :
    //Khi một yêu cầu đến endpoint /special, nó sẽ đi qua phương thức special() được đánh dấu bằng @PreAuthorize("hasRole('ADMIN')").
    // Điều này có nghĩa là chỉ người dùng có vai trò ADMIN mới có thể truy cập vào endpoint này. Vì vậy, nếu người dùng không có vai trò ADMIN, yêu cầu sẽ bị từ chối và trả về mã trạng thái 403 Forbidden.
    //Khi một yêu cầu đến endpoint /basic, nó sẽ đi qua phương thức basic() được đánh dấu bằng @PreAuthorize("hasRole('ADMIN') or hasRole('USER')").
    // Điều này có nghĩa là người dùng cần có vai trò ADMIN hoặc USER mới có thể truy cập vào endpoint này. Vì vậy, nếu người dùng không có vai trò ADMIN hoặc USER, yêu cầu sẽ bị từ chối và trả về mã trạng thái 403 Forbidden.
    //Ngoài ra, tất cả các yêu cầu khác đều sẽ yêu cầu người dùng phải xác thực (authenticated()) thông qua cơ chế đăng nhập (form login hoặc HTTP Basic). Điều này đảm bảo rằng các phần của ứng dụng không được bảo vệ bởi các quy tắc ủy quyền cụ thể vẫn yêu cầu người dùng phải đăng nhập để truy cập.
}
