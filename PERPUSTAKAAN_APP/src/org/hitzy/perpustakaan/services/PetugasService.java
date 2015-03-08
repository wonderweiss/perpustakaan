package org.hitzy.perpustakaan.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.hitzy.perpustakaan.dao.PetugasDao;
import org.hitzy.perpustakaan.model.Petugas;

import com.google.gson.Gson;

@Path("PetugasService")
public class PetugasService {
//	private Logger log = new AppLogger(PetugasService.class).getLogger();
	private Logger log = Logger.getLogger(PetugasService.class);
	public PetugasService() {
//		initDashboardService();
	}
	
	PetugasDao dao = new PetugasDao();

	/*private void initDashboardService() {
		ConnectionService service = new ConnectionService();
		InitialContext initialContext = service.getInitialContext();
		try {
			remote = (DashboardBeanRemote)initialContext.lookup("Core/Dashboard");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}*/
	

	
	@GET
	@Path("/GetPetugas/{petugasId}")
	@Produces("application/json")
	public String getPetugas(@PathParam("petugasId")String petugasId){
		log.info("getPetugas invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", "0");
		params.put("limit", "1");
		params.put("petugasId", petugasId);
		
		List<Petugas> res = dao.getPetugas(params);
		log.info("res size: "+res.size());
		Gson gson = new Gson();
		return gson.toJson(res);
	}

	@PUT
	@Path("/AddPetugas")
	@Consumes("application/json")
	public String addPetugas(String data){
		log.info("addPetugas invoked...");
		Gson gson = new Gson();
		Petugas petugas = gson.fromJson(data, Petugas.class);
		
		List<Petugas> listPetugas = new ArrayList<>();
		listPetugas.add(petugas);
		int res = dao.addPetugas(listPetugas);
		
		return gson.toJson(res);
	}
	
	@POST
	@Path("/UpdatePetugas")
	@Consumes("application/json")
	public String updatePetugas(String data){
		log.info("updatePetugas invoked...");
		Gson gson = new Gson();
		Petugas petugas = gson.fromJson(data, Petugas.class);
		
		int res = dao.upatePetugas(petugas);
		
		return gson.toJson(res);
	}
	
	@DELETE
	@Path("/DeletePetugas/{petugasIds}")
	@Consumes("application/json")
	public String deletePetugas(@PathParam("petugasIds")String petugasIds){
		log.info("deletePetugas invoked...");
		
		String[] arrayIds = petugasIds.split("-");
		List<Integer> ids = new ArrayList<>();
		for (String string : arrayIds) {
			ids.add(Integer.valueOf(string));
		}
		
		int res = dao.deletePetugas(ids);
		
		Gson gson = new Gson();
		return gson.toJson(res);
	}
	
	@GET
	@Path("/GetAllPetugas")
	@Produces("application/json")
	public String getAllPetugas(@QueryParam("start")String start,@QueryParam("limit")String limit){
		log.info("getAllPetugas invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("petugasId", "0");
		
		List<Petugas> listPetugas = dao.getPetugas(params);
		
		Gson gson = new Gson();
		return gson.toJson(listPetugas);
	}
	
}
