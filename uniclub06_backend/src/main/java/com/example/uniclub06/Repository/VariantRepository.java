package com.example.uniclub06.Repository;

import com.example.uniclub06.Entity.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity,Integer> {
}
