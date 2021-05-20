package com.example.demo.modelDAO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Actividad;

public interface IActividadDAO extends JpaRepository<Actividad, Long>{
//@Query("select p from Product p inner join ProductCategory  pc on pc.id_procat = p.categoryProduct.id_procat where pc.id_procat = :id")
	@Query("SELECT a.numeroAct FROM Actividad a WHERE a.equipo.idcod = ?1 ORDER BY a.numeroAct DESC ")
	public List<Long> findByEquipo(Long equipo);

	   @Modifying
	    @Query(value = 
	    "insert into actividad (cumplimiento,descripcion,numero_act,observacion,permitivo,usuarioact,usuariomod,equipo,fcreacion) VALUES (:cumplimiento,:desc,:num_act,:observacion,:permitivo,:usuarioact,:usuariomod,:equipo,:fcreacion)", nativeQuery = true)
	    @Transactional
	    public int insertWithOutDetalleActividad
	    (@Param("cumplimiento")String cumplimiento,
	    		@Param("desc")String desc,
	    		@Param("num_act")long num_act,@Param("observacion")String observacion,
	    		@Param("permitivo")String permitivo,@Param("usuarioact")String usuarioact,@Param("usuariomod")String usuariomod,
	    		@Param("equipo")long idEquipo,@Param("fcreacion")Calendar fcreacion);
	   
	   public Actividad findTopByOrderByIdDesc();
}
