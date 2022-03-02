package com.thiethaa.advproject.repository;

import com.thiethaa.advproject.entity.Advproject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvprojectRepository extends JpaRepository<Advproject,String> {
}
