package com.example.demo.modelDAO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Actividad;
import com.example.demo.model.OrdenTrabajo;

public interface IActividadDAO extends JpaRepository<Actividad, Long>{
//@Query("select p from Product p inner join ProductCategory  pc on pc.id_procat = p.categoryProduct.id_procat where pc.id_procat = :id")
	@Query("SELECT a FROM Actividad a WHERE a.equipo.idcod = ?1 ORDER BY a.equipogeneral.ot DESC ")
	public List<Actividad> findByEquipo(Long equipo);
	
	@Query("SELECT a FROM Actividad a WHERE a.equipogeneral.ot = ?1 ORDER BY a.numeroAct DESC ")
	public List<Actividad> findByOt(Long ot);

	   @Modifying
	    @Query(value = 
	    "insert into actividad (cumplimiento,descripcion,numero_act,observacion,permitivo,usuarioact,usuariomod,equipo,fcreacion,equipogeneral) VALUES (:cumplimiento,:desc,:num_act,:observacion,:permitivo,:usuarioact,:usuariomod,:equipo,:fcreacion,:idEquipogeneral)", nativeQuery = true)
	    @Transactional
	    public int insertWithOutDetalleActividad
	    (@Param("cumplimiento")String cumplimiento,
	    		@Param("desc")String desc,
	    		@Param("num_act")long num_act,@Param("observacion")String observacion,
	    		@Param("permitivo")String permitivo,@Param("usuarioact")String usuarioact,@Param("usuariomod")String usuariomod,
	    		@Param("equipo")Long idEquipo,@Param("fcreacion")Calendar fcreacion,@Param("idEquipogeneral")long idEquipogeneral);
	   
	   public Actividad findTopByOrderByIdDesc();
	   
	    @Query(value = 
	    "SELECT equipogeneral FROM actividad WHERE equipo = :idequipo", nativeQuery = true)
	   public List<Long> findByIdEquipogeneral(@Param("idequipo")long idequipo);
	    
	    @Transactional
	    @Modifying
	    @Query("update Actividad a set a.equipogeneral.id = :equipogeneral where a.id = :actividad")
	    public int updateIdEquipogeneral(@Param("actividad")Long actividad,@Param("equipogeneral")Long equipogeneral);
	    
	    
	    
	    @Transactional
	    @Modifying
	    @Query("update Actividad a set a.equipogeneral.id = :equipogeneral, a.equipoprueba.id = :equipoprueba where a.id = :actividad")
	    public int updateIdEquipogeneralPrueba(@Param("actividad")Long actividad,@Param("equipogeneral")Long equipogeneral,@Param("equipoprueba")Long equipoprueba);

}
