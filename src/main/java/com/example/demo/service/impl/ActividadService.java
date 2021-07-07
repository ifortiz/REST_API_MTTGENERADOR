package com.example.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Actividad;
import com.example.demo.model.EmailActividades;
import com.example.demo.model.OrdenTrabajo;
import com.example.demo.modelDAO.IActividadDAO;
import com.example.demo.service.CorreoService;
import com.example.demo.service.IActividadService;

@Service
public class ActividadService implements IActividadService{
	private static final String subject = "no-reply";
	private static final String ACTIVIDAD_NO_CUMPLIO= "NO CUMPLIO";
	@Autowired
	private IActividadDAO dao;
	@Autowired
	private CorreoService serviceCorreo;
	@Override
	public List<Actividad> findAll() {		
		return (List<Actividad>) dao.findAll();
	}

	@Override
	public Actividad findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public int saveWithOutDetalleActividad(Actividad actividad) {		
		return dao.insertWithOutDetalleActividad(actividad.getCumplimiento(),actividad.getDescripcion()
				,actividad.getNumeroAct(),actividad.getObservacion(),
				actividad.getPermitivo(),actividad.getUsuarioact(),actividad.getUsuariomod(),actividad.getEquipo().getIdcod(),actividad.getFcreacion(),actividad.getEquipogeneral().getId());
	}


	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public  List<Actividad> findByEquipo(Long id) {
		return dao.findByEquipo(id);
		
	}

	@Override
	public Actividad findLast() {
		// TODO Auto-generated method stub
		return dao.findTopByOrderByIdDesc();
	}

	@Override
	public List<Actividad> findByOt(Long id) {
		return dao.findByOt(id);
	}

	@Override
	public List<Long> findByIdEquipoGeneral(Long id) {
		// TODO Auto-generated method stub
		return dao.findByIdEquipogeneral(id);
	}

	@Override
	public Actividad saveWithDetalleActividad(Actividad actividad) {
		// TODO Auto-generated method stub
		return dao.save(actividad);
	}

	@Override
	public int updateIdEquipogeneral(Long idactividad,Long idEquipogeneral) {
		// TODO Auto-generated method stub
		return  dao.updateIdEquipogeneral(idactividad,idEquipogeneral);
	}

	@Override
	public int updateIdEquipogeneralPrueba(Long idActividad, Long idEquipogeneral, Long idEquipoPrueba) {
		// TODO Auto-generated method stub
		return  dao.updateIdEquipogeneralPrueba(idActividad,idEquipogeneral,idEquipoPrueba);
	}

	@Override
	public boolean enviarEmail(EmailActividades email) {
			try {
				
				List<Actividad > actividades = dao.findByOt(email.getOt());
				List <Actividad> noCumplio= new ArrayList<>();
				List <Actividad> siCumplio= new ArrayList<>();
				for (Actividad actividad : actividades) {
					if ( actividad.getCumplimiento().contains( ACTIVIDAD_NO_CUMPLIO))
						noCumplio.add(actividad);
					else
						siCumplio.add(actividad);
				}
				
				Date date = new Date();
				 DateFormat dateF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				 String fechaReporte = dateF.format(date);
				 
			    String html="<html><head>"
                        + "<title> TITULO</title>"
                        + "</head>"+"<LINK REL='stylesheet' HREF='stylesheet/fac_css.css' TYPE='text/css'>"
                        + "<body>"
                        +"<h3>Reporte de mantenimiento 8000Hr  POR OT </h3>"
                        +"<img src='http://www.lopaestra.com/wp-content/uploads/2018/01/Lopaestra_0.png'>"
                        +"<h2>Fecha del reporte: "+fechaReporte+" </h2>"
                        +"<h2>Responsable: "+email.getNombreCompletoResponsable()+" </h2>"
                        +"<h2>ot: "+email.getOt()+" </h2>"
                        +"<h2>Codigo equipo: "+email.getNombreCompletoEquipo()+" </h2>"
                        +"<h3>1. ACTIVIDADES</h3>";

                if(siCumplio.size()>0){
                    html+="<h4>Actividades Realizadas</h4>";
                    for(Actividad actividadCumplio: siCumplio){
                    	if ( actividadCumplio.getDescripcion() != null && !actividadCumplio.getDescripcion().trim().isEmpty())
                        html+= "<h5>"+ actividadCumplio.getDescripcion()+"</h5>";
                    }
                }
                if(noCumplio.size()>0){
                    html+="<h4>Actividades NO  Realizadas</h4>";
                    for(Actividad actividadNoCumplio: noCumplio){
                    	if ( actividadNoCumplio.getDescripcion() != null  && !actividadNoCumplio.getDescripcion().trim().isEmpty() )
                        html+= "<h5>"+ actividadNoCumplio.getDescripcion()+" ("+actividadNoCumplio.getPermitivo()+")</h5>";
                    }
                }
                html+="<h3>2. Modificaciones o partes cambiadas</h3>";
                html+="<p>"+email.getModificaciones()+"</p></br>";
                html+="<h3>3. Condiciones del objeto de mantenimiento</h3>";
                html+="<p>"+email.getCondiciones()+"</p></br>";
                html+="<h3>4. Pendientes</h3>";
                html+="<p>"+email.getPendientes()+"</p></br>";
                html+="<p>Si crees que hay un error en el reporte, contactanos al correo mecanic@reportes.com </p>";
                html+="</body></html>";
				
				
				
				
				
				if ( email.getCorreoResponsable().equals(email.getCorreoCurrent())) 
					serviceCorreo.mandarCorreo(email.getCorreoSupervisor(),email.getCorreoCurrent(),"",subject, html);
				else
					serviceCorreo.mandarCorreo(email.getCorreoSupervisor(),email.getCorreoCurrent(),email.getCorreoResponsable(),subject, html);
				
				return true;
			} catch (MessagingException e) {
				return false;
	
			}

	}

}
