package com.contactura.contactura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactura.contactura.model.Contactura;

@Repository //Para informar que é um repositório
public interface ContacturaRepository extends JpaRepository<Contactura, Long> {

}
