package com.example.ms_clima.repository;

import com.example.ms_clima.model.ClienteAlertaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteAlertaRepository extends JpaRepository<ClienteAlertaModel, Long> {

}

