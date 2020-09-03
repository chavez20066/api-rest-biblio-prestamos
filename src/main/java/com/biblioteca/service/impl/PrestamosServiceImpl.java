package com.biblioteca.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.model.PrestamoModel;
import com.biblioteca.service.PrestamosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class PrestamosServiceImpl implements PrestamosService {

	private static final Log LOG = LogFactory.getLog(PrestamosServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional(readOnly=true)
	public String findAllPrestamosByCodigoAlumno(String codigoAlumno) throws JsonProcessingException {
		// TODO Auto-generated method stub
		Map<String, String> body = new HashMap<String, String>();
		try {		
			//String varSql="select Z36_MATERIAL,Z36_ID FROM CSM50.Z36 " + 
			//		"where CSM50.Z36.Z36_ID='"+codigoAlumno+"'";
			String varSql="select i.issue_id,i.itemnumber from issues i  " + 
					"inner join borrowers b " + 
					"on i.borrowernumber=b.borrowernumber " + 
					"where b.cardnumber='"+codigoAlumno+"'";
		
			LOG.info("Returning  findAllPrestamosByCodigoAlumno SQL:" + varSql);
			Query query = em.createNativeQuery(varSql);
			List<Object[]> results= (List<Object[]>) query.getResultList();			
				
			
			if(results.size()==0) {
				body.put("result", "ok"); // no hay prestamo
				body.put("mensaje", "Usted no adeuda Material Bibliográfico");
			}
			else {
				body.put("result", "advertencia");					
				body.put("mensaje", "Usted adeuda Material Bibliográfico, acérquese a Biblioteca Central de la UCSM");              
			}
			
		}catch (Exception e) {
			body.put("result", "error");
			body.put("mensaje", e.getMessage());
            
		}
		return new ObjectMapper().writeValueAsString(body);		
	}


	@Override
	@Transactional(readOnly=true)
	public String findAllPrestamosActivosDetalleByCodigoAlumno(String codigoAlumno) throws JsonProcessingException {
		Map<String, String> body = new HashMap<String, String>();
		
		try {		
			
			String varSql="select it.itemcallnumber,bi.title,bi.author,i.issuedate from issues i  " + 
					" inner join borrowers b" + 
					" on i.borrowernumber=b.borrowernumber " + 
					" inner join items it" + 
					" on i.itemnumber=it.itemnumber" + 
					" inner join biblio bi" + 
					" on it.biblionumber=bi.biblionumber" + 
					" where b.cardnumber='"+codigoAlumno+"'";
		
			LOG.info("Returning  findAllPrestamosActivosDetalleByCodigoAlumno SQL:" + varSql);
			Query query = em.createNativeQuery(varSql);
			List<Object[]> results= (List<Object[]>) query.getResultList();			
				
			
			if(results.size()==0) {
				body.put("result", "1"); // no hay prestamo
				body.put("mensaje", "Usted no adeuda Material Bibliográfico");
			}
			else {
				body.put("result", "2");					
				body.put("mensaje", "Usted adeuda Material Bibliográfico, acérquese a Biblioteca Central de la UCSM"); 
				//body.put("detalle", new ObjectMapper().writeValueAsString(results));
				List<PrestamoModel> prestamos= new ArrayList<PrestamoModel>();
				PrestamoModel prestamoModel;
				
				for (Object[] objects : results) {
					prestamoModel = new PrestamoModel();
					prestamoModel.setCodigo( objects[0].toString());
					prestamoModel.setTitulo( objects[1].toString());
					prestamoModel.setAutor( objects[2].toString());
					prestamoModel.setFecha( objects[3].toString());
					prestamos.add(prestamoModel);				
				}	             
				body.put("detalle", new ObjectMapper().writeValueAsString(prestamos));
			}
			
		}catch (Exception e) {
			body.put("result", "0");
			body.put("mensaje", e.getMessage());
            
		}
		return new ObjectMapper().writeValueAsString(body);	
	}


	@Override
	@Transactional(readOnly=true)
	public String findAllPrestamosDetalle(String codigoAlumno) throws JsonProcessingException {
		
		Map<String, String> body = new HashMap<String, String>();
		
		try {		
			
			String varSql="select it.itemcallnumber,bi.title,bi.author,i.issuedate,i.returndate from issues i   " + 
					"inner join borrowers b " + 
					"on i.borrowernumber=b.borrowernumber " + 
					"inner join items it " + 
					"on i.itemnumber=it.itemnumber " + 
					"inner join biblio bi " + 
					"on it.biblionumber=bi.biblionumber " + 
					" where b.cardnumber='"+codigoAlumno+"'"+
					" union all " + 
					"select it.itemcallnumber,bi.title,bi.author,i.issuedate,i.returndate from old_issues i   " + 
					"inner join borrowers b " + 
					"on i.borrowernumber=b.borrowernumber " + 
					"inner join items it " + 
					"on i.itemnumber=it.itemnumber " + 
					"inner join biblio bi " + 
					"on it.biblionumber=bi.biblionumber " + 
					"where b.cardnumber='"+codigoAlumno+"'";
		
			LOG.info("Returning  findAllPrestamosDetalle SQL:" + varSql);
			Query query = em.createNativeQuery(varSql);
			List<Object[]> results= (List<Object[]>) query.getResultList();			
				
			
			if(results.size()==0) {
				body.put("result", "1"); // no hay prestamo
			}
			else {
				body.put("result", "2");					
				//body.put("detalle", new ObjectMapper().writeValueAsString(results));
				List<PrestamoModel> prestamos= new ArrayList<PrestamoModel>();
				PrestamoModel prestamoModel;
				
				for (Object[] objects : results) {
					prestamoModel = new PrestamoModel();
					prestamoModel.setCodigo( objects[0].toString());
					prestamoModel.setTitulo( objects[1].toString());
					prestamoModel.setAutor( objects[2].toString());
					prestamoModel.setFecha( objects[3].toString());
					String fechaDevo=null;
					if(objects[4]!=null) fechaDevo=objects[4].toString();
					prestamoModel.setFechaDevolucion(fechaDevo);
					prestamos.add(prestamoModel);				
				}	             
				body.put("detalle", new ObjectMapper().writeValueAsString(prestamos));
			}
			
		}catch (Exception e) {
			body.put("result", "0");
			body.put("mensaje", e.getMessage());
            
		}
		return new ObjectMapper().writeValueAsString(body);	
	
	}
}
