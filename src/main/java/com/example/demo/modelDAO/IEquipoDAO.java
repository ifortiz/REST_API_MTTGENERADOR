package com.example.demo.modelDAO;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Equipo;

public interface IEquipoDAO extends CrudRepository<Equipo, Long>{
public Equipo findOneByidcod(long id);
}
