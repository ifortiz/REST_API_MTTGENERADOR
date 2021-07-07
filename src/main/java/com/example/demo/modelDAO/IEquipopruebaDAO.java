package com.example.demo.modelDAO;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Actividad;
import com.example.demo.model.EquipoPruebar;
import com.example.demo.model.Equipoprueba;

public interface IEquipopruebaDAO extends CrudRepository<Equipoprueba, Long> {
	


	  /*  @Query(value = 
	    "SELECT *  FROM equipoprueba)", nativeQuery = true)  
	   public List<EquipoPruebar> findEquiposPrueba();*/
	   
	    @Query(value = 
	    	    "SELECT e.id, e.marca, e.modelo,e.tipo  FROM Equipoprueba e", nativeQuery = true)  
	    	   public List<Equipoprueba> findEquiposPrueba();

}
