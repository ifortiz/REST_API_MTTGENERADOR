package com.example.demo.modelDAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Detalleactividad;

public interface IDetalleactividadDAO extends CrudRepository<Detalleactividad, Long>{
	   @Modifying
	    @Query(value = 
	    "insert into detalleactividad (nomprueba,valorprueba,actividad_id) VALUES (:nomprueba,:valorprueba,:actividad_id)", nativeQuery = true)
	    @Transactional
	    public int saveWithOutActividad
	    (@Param("nomprueba")String nomprueba,
	    		@Param("valorprueba")String valorprueba,
	    		@Param("actividad_id")long actividad_id);
	   
}
