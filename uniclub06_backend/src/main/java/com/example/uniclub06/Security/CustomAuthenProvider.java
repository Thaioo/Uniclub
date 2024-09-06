package com.example.uniclub06.Security;

import com.example.uniclub06.Request.AuthenRequest;
import com.example.uniclub06.Service.AuthenService;
import com.example.uniclub06.dto.RoleDTO;
import com.example.uniclub06.exception.AuthenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    AuthenService authenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        AuthenRequest authenRequest = new AuthenRequest(username, password);
        List<RoleDTO> roleDTOS=    authenService.checkLogin(authenRequest);
        if(roleDTOS.size()>0){
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            roleDTOS.forEach(roleDTO -> {
//                SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(roleDTO.getName());
//                grantedAuthorities.add(simpleGrantedAuthority);
//            });
            List<SimpleGrantedAuthority> grantedAuthorities= roleDTOS.stream()
                    .map(item-> new SimpleGrantedAuthority(item.getName())).toList();
            return new UsernamePasswordAuthenticationToken("","",grantedAuthorities);
        }else {
            throw new AuthenException("đăng nhập thất bại");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
