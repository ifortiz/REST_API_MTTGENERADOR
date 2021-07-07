package com.example.demo.modelDAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Actividad;
import com.example.demo.model.Equipo;
import com.example.demo.model.OrdenTrabajo;

public interface IOrdenTrabajoDAO extends CrudRepository<OrdenTrabajo, Long>{

	public OrdenTrabajo findByOt(Long ot);
	
/*	@Query("SELECT e FROM Equipo e WHERE e.equipogeneral = ?1")
	public Equipo getEquipoById(Long idEquipo);*/
	
    @Modifying
    @Query(value = "insert into orden_trabajo (fecha,ot,unidad,usuario,id_equipo) VALUES (:fecha,:ot,:unidad,:usuario,:idEquipo)", nativeQuery = true)
    @Transactional
    public int insertWithOutEntity(
    		@Param("fecha")String fecha,
    		@Param("ot")long ot,
    		@Param("unidad")long unidad,
    		@Param("usuario")long usuario,
    		@Param("idEquipo")Long idequipo);
    

}
