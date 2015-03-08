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
import org.hitzy.perpustakaan.dao.RakDao;
import org.hitzy.perpustakaan.model.Rak;

import com.google.gson.Gson;

@Path("RakService")
public class RakService {
//	private Logger log = new AppLogger(RakService.class).getLogger();
	private Logger log = Logger.getLogger(RakService.class);
	public RakService() {
//		initDashboardService();
	}
	
	RakDao dao = new RakDao();

	@GET
	@Path("/GetRak/{rakId}")
	@Produces("application/json")
	public String getRak(@PathParam("rakId")String rakId){
		log.info("getRak invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", "0");
		params.put("limit", "1");
		params.put("rakId", rakId);
		
		List<Rak> res = dao.getRak(params);
		log.info("res size: "+res.size());
		Gson gson = new Gson();
		return gson.toJson(res);
	}

	@PUT
	@Path("/AddRak")
	@Consumes("application/json")
	public String addRak(String data){
		log.info("addRak invoked...");
		Gson gson = new Gson();
		Rak rak = gson.fromJson(data, Rak.class);
		
		List<Rak> listRak = new ArrayList<>();
		listRak.add(rak);
		int res = dao.addRak(listRak);
		
		return gson.toJson(res);
	}
	
	@POST
	@Path("/UpdateRak")
	@Consumes("application/json")
	public String updateRak(String data){
		log.info("updateRak invoked...");
		Gson gson = new Gson();
		Rak rak = gson.fromJson(data, Rak.class);
		
		int res = dao.upateRak(rak);
		
		return gson.toJson(res);
	}
	
	@DELETE
	@Path("/DeleteRak/{rakIds}")
	@Consumes("application/json")
	public String deleteRak(@PathParam("rakIds")String rakIds){
		log.info("deleteRak invoked...");
		
		String[] arrayIds = rakIds.split("-");
		List<Integer> ids = new ArrayList<>();
		for (String string : arrayIds) {
			ids.add(Integer.valueOf(string));
		}
		
		int res = dao.deleteRak(ids);
		
		Gson gson = new Gson();
		return gson.toJson(res);
	}
	
	@GET
	@Path("/GetAllRak")
	@Produces("application/json")
	public String getAllRak(@QueryParam("start")String start,@QueryParam("limit")String limit){
		log.info("getAllRak invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("rakId", "0");
		
		List<Rak> listRak = dao.getRak(params);
		
		Gson gson = new Gson();
		return gson.toJson(listRak);
	}
	
}
