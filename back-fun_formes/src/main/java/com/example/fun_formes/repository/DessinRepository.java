package com.example.fun_formes.repository;

import com.example.fun_formes.model.Dessin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DessinRepository extends JpaRepository<Dessin, Long> {
    
    List<Dessin> findByNiveau(String niveau);
    Dessin findByNiveauAndId(@Param("niveau") String niveau, @Param("id") Long id);

}
