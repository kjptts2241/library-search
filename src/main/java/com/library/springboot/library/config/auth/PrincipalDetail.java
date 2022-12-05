package com.library.springboot.library.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.library.springboot.library.dao.User;

public class PrincipalDetail implements UserDetails {
    
    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUser_id();
    }

    /*
     * 계정이 만료되지 않았는지 리턴 (true : 만료안됨)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * 계정이 잠겨있지 않았는지 리턴 (true : 잠기지 않음)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /*
     * 비밀번호가 만료되지 않았는지 리턴 (true : 만료안됨)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * 계정이 활성화(사용 가능)인지 리턴 (true : 활성화)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    /*
     * 계정이 갖고 있는 권한 목록을 리턴 (권한이 여러개면 루프(for문)을 해야 하지만 우리는 한개만 사용)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        
        collectors.add(()->{ return "ROLE_"+user.getRole();}); // 람다식 (밑의 것에서 람다식으로)
        
        // collectors.add(new GrantedAuthority() {
            
        //     @Override
        //     public String getAuthority() {
        //         return "ROLE_"+user.getRole();
        //     }
        // });

        return collectors;
    }
    
    
}
