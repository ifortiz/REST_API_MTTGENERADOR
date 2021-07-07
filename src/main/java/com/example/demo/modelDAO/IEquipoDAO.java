package com.example.demo.modelDAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Equipo;

public interface IEquipoDAO extends CrudRepository<Equipo, Long>{
public Equipo findOneByidcod(Long id);
public List<Equipo> findByDescripcionContaining(String descripcion);

}
