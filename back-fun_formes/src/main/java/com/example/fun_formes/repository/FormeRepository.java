package com.example.fun_formes.repository;

import com.example.fun_formes.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormeRepository extends JpaRepository<Form, Long> {
}
