package com.example.uniclub06.Repository;

import com.example.uniclub06.Entity.UserEntity;
import com.example.uniclub06.Request.AuthenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByEmail(String email);
}
