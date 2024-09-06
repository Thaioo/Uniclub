package com.example.uniclub06.filter;

import com.example.uniclub06.Utils.JwtHelper;
import com.example.uniclub06.dto.AuthorityDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorHeader = request.getHeader("Authorization");
        if (authorHeader != null && authorHeader.startsWith("Bearer")) {
            String token = authorHeader.substring(7);
            String data= jwtHelper.decodeToken(token);
            if (data != null) {
                System.out.println("kiemtra" + data);
                List<AuthorityDTO> authorityDTOS = objectMapper.readValue(data, new TypeReference<List<AuthorityDTO>>(){});
                List <GrantedAuthority> grantedAuthorities = new ArrayList<>();
                authorityDTOS.forEach(dataDTO ->{
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(dataDTO.getAuthority());
                    grantedAuthorities.add(simpleGrantedAuthority);
                });
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("","",grantedAuthorities);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
            System.out.println("kiemtra " + data);
        }
        filterChain.doFilter(request, response);
    }
}
