//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.service;

import ku.cs.Wabi.Cafe.entity.Member;
import ku.cs.Wabi.Cafe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImp implements UserDetailsService { //service ทีใช้จัดการพวก log in log out


    @Autowired
    private MemberRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Member user = userRepository.findByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }


        List<SimpleGrantedAuthority> authorities = new ArrayList<>(); //จัดการสิทธิการใช้งาน
        authorities.add(new SimpleGrantedAuthority(user.getRole()));


        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities); //ส่งข้อมูลไปให้ spring security
    }
}
