package com.example.uniclub06.Repository;

import com.example.uniclub06.Entity.SizeEntity;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository  extends JpaRepository<SizeEntity,Integer> {
}
