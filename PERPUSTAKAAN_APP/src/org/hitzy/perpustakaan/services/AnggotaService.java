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
import org.hitzy.perpustakaan.dao.AnggotaDao;
import org.hitzy.perpustakaan.model.Anggota;

import com.google.gson.Gson;

@Path("AnggotaService")
public class AnggotaService {
//	private Logger log = new AppLogger(AnggotaService.class).getLogger();
	private Logger log = Logger.getLogger(AnggotaService.class);
	public AnggotaService() {
//		initDashboardService();
	}
	
	AnggotaDao dao = new AnggotaDao();

	@GET
	@Path("/GetAnggota/{anggotaId}")
	@Produces("application/json")
	public String getAnggota(@PathParam("anggotaId")String anggotaId){
		log.info("getAnggota invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", "0");
		params.put("limit", "1");
		params.put("anggotaId", anggotaId);
		
		List<Anggota> res = dao.getAnggota(params);
		log.info("res size: "+res.size());
		Gson gson = new Gson();
		return gson.toJson(res);
	}

	@PUT
	@Path("/AddAnggota")
	@Consumes("application/json")
	public String addAnggota(String data){
		log.info("addAnggota invoked...");
		Gson gson = new Gson();
		Anggota anggota = gson.fromJson(data, Anggota.class);
		
		List<Anggota> listAnggota = new ArrayList<>();
		listAnggota.add(anggota);
		int res = dao.addAnggota(listAnggota);
		
		return gson.toJson(res);
	}
	
	@POST
	@Path("/UpdateAnggota")
	@Consumes("application/json")
	public String updateAnggota(String data){
		log.info("updateAnggota invoked...");
		Gson gson = new Gson();
		Anggota anggota = gson.fromJson(data, Anggota.class);
		
		int res = dao.upateAnggota(anggota);
		
		return gson.toJson(res);
	}
	
	@DELETE
	@Path("/DeleteAnggota/{anggotaIds}")
	@Consumes("application/json")
	public String deleteAnggota(@PathParam("anggotaIds")String anggotaIds){
		log.info("deleteAnggota invoked... ");
		
		String[] arrayIds = anggotaIds.split("-");
		List<Integer> ids = new ArrayList<>();
		for (String string : arrayIds) {
			ids.add(Integer.valueOf(string));
		}
		
		int res = dao.deleteAnggota(ids);
		
		Gson gson = new Gson();
		return gson.toJson(res);
	}
	
	@GET
	@Path("/GetAllAnggota")
	@Produces("application/json")
	public String getAllAnggota(@QueryParam("start")String start,@QueryParam("limit")String limit){
		log.info("getAllAnggota invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("anggotaId", "0");
		
		List<Anggota> listAnggota = dao.getAnggota(params);
		
		Gson gson = new Gson();
		return gson.toJson(listAnggota);
	}
	
}
