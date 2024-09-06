package com.example.uniclub06.Controller;

import com.example.uniclub06.Reponse.BaseReponse;
import com.example.uniclub06.Request.AuthenRequest;
import com.example.uniclub06.Service.AuthenService;
import com.example.uniclub06.Utils.JwtHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("/authen")
@CrossOrigin
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<?> authen (@RequestBody AuthenRequest authenRequest) throws JsonProcessingException {
//            SecretKey secretKey = Jwts.SIG.HS256.key().build();
//            String key = Encoders.BASE64.encode(secretKey.getEncoded());
//              cach thong thuong
//            boolean isSuccess= authenService.checkLogin(authenRequest);
             UsernamePasswordAuthenticationToken authentoken = new UsernamePasswordAuthenticationToken(authenRequest.email(),authenRequest.password());
             Authentication authentication= authenticationManager.authenticate(authentoken);

             List<GrantedAuthority> listrole =(List<GrantedAuthority>) authentication.getAuthorities();
             String data = objectMapper.writeValueAsString(listrole);
             System.out.println(data);


            String token =  jwtHelper.generateToken(data);
            BaseReponse reponse= new BaseReponse();
            reponse.setData(token);
            return new  ResponseEntity<>(reponse,HttpStatus.OK);
    }
}
