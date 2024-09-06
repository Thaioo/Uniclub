package com.example.uniclub06.Service.imp;

import com.example.uniclub06.Entity.RoleEntity;
import com.example.uniclub06.Entity.UserEntity;
import com.example.uniclub06.Repository.UserRepository;
import com.example.uniclub06.Request.AuthenRequest;
import com.example.uniclub06.Service.AuthenService;
import com.example.uniclub06.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenServiceImp implements AuthenService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<RoleDTO> checkLogin(AuthenRequest authenRequest) {
        List<RoleDTO> roles = new ArrayList<>();
        UserEntity user = userRepository.findUserEntityByEmail(authenRequest.email());
        if (user != null && passwordEncoder.matches(authenRequest.password(), user.getPassword())) {
            RoleEntity roleEntity= user.getRole();
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(roleEntity.getId());
            roleDTO.setName(roleEntity.getName());
            roles.add(roleDTO);
        }
        return roles;
    }
}
