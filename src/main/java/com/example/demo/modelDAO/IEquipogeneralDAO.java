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
import com.example.demo.model.Equipogeneral;

public interface IEquipogeneralDAO extends CrudRepository<Equipogeneral, Long>{

	public Equipogeneral findByOt(Long ot);
	
	@Query("SELECT e FROM Equipo e WHERE e.equipogeneral = ?1")
	public Equipo getEquipoById(Long idEquipo);
	
    @Modifying
    @Query(value = "insert into equipogeneral (fecha,ot,unidad,usuario) VALUES (:fecha,:ot,:unidad,:usuario)", nativeQuery = true)
    @Transactional
    public int insertWithOutEntity(@Param("fecha")Date fecha,@Param("ot")long ot,@Param("unidad")long unidad,@Param("usuario")long usuario);
}
