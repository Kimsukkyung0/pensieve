package com.example.pensieve.common.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class MyUserDetails implements UserDetails {
    private Long userId;
    private String email;
    private String pw;
    private String nickNm;


    @Override
    public String getPassword(){
        return this.pw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
        //Todo : 여기가몬데 ?? Collection 그리고 ? extends GrantedAuthority 는 뭔디?

    }

    @Override
    public String getUsername(){
        return getNickNm();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

