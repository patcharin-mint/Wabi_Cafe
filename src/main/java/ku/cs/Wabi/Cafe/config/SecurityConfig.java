//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.config;

import ku.cs.Wabi.Cafe.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig { //ใช้กำหนดเงื่อนไขการเข้าถึงของแต่ละหน้าได้

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(new AntPathRequestMatcher("/")).permitAll()                     //ไม่ต้อง login ทุก role มีสิทธิเข้าถึง
                    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll()
                    .requestMatchers(
                            new AntPathRequestMatcher("/categories/add")).hasRole("ADMIN")
                    .requestMatchers(
                            new AntPathRequestMatcher("/menus/add")).hasRole("ADMIN")
                    .requestMatchers(
                            new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                    .anyRequest().authenticated()                                                           // หน้าอื่นต้อง login
            )
            .formLogin((form) -> form
                    .loginPage("/login") //login ที่ path นี้นะ
                    .defaultSuccessUrl("/", true) //ถ้า login สำเร็จจะไปที่ path นี้นะ
                    .permitAll()
            )
            .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12); //hash 12รอบ
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //ให้ security ignore h2-console
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")); //อนญาตให้เข้าถึง H2 database ได้ที่ url /h2-console
    }

}
