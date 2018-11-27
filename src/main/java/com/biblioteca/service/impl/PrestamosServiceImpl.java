package com.biblioteca.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			String varSql="select Z36_MATERIAL,Z36_ID FROM CSM50.Z36 " + 
					"where CSM50.Z36.Z36_ID='"+codigoAlumno+"'";
		
			LOG.info("Returning  findAllPrestamosByCodigoAlumno SQL:" + varSql);
			Query query = em.createNativeQuery(varSql);
			List<Object[]> results= (List<Object[]>) query.getResultList();			
				
			
			if(results.size()==0) {
				body.put("result", "ok");
			}
			else {
				body.put("result", "advertencia");					
				body.put("mensaje", "USTED ADEUDA MATERIAL BIBLIOGRAFÍCO, ACÉRQUESE A BIBLIOTECA CENTRAL DE LA UCSM");              
			}
			
		}catch (Exception e) {
			body.put("result", "error");
			body.put("mensaje", e.getMessage());
            
		}
		return new ObjectMapper().writeValueAsString(body);
		
	}

}
