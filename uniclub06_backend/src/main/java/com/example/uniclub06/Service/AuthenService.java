package com.example.uniclub06.Service;

import com.example.uniclub06.Entity.RoleEntity;
import com.example.uniclub06.Request.AuthenRequest;
import com.example.uniclub06.dto.RoleDTO;

import java.util.List;

public interface AuthenService {
  List<RoleDTO> checkLogin(AuthenRequest authenRequest);
}
