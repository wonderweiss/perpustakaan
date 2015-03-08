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
import org.hitzy.perpustakaan.dao.BukuDao;
import org.hitzy.perpustakaan.model.Buku;

import com.google.gson.Gson;

@Path("BukuService")
public class BukuService {
//	private Logger log = new AppLogger(BukuService.class).getLogger();
	private Logger log = Logger.getLogger(BukuService.class);
	public BukuService() {
//		initDashboardService();
	}
	
	BukuDao dao = new BukuDao();

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
	@Path("/GetBuku/{bukuId}")
	@Produces("application/json")
	public String getBuku(@PathParam("bukuId")String bukuId){
		log.info("getBuku invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", "0");
		params.put("limit", "1");
		params.put("bukuId", bukuId);
		
		List<Buku> res = dao.getBuku(params);
		log.info("res size: "+res.size());
		Gson gson = new Gson();
		return gson.toJson(res);
	}

	@PUT
	@Path("/AddBuku")
	@Consumes("application/json")
	public String addBuku(String data){
		log.info("addBuku invoked...");
		Gson gson = new Gson();
		Buku buku = gson.fromJson(data, Buku.class);
		
		List<Buku> listBuku = new ArrayList<>();
		listBuku.add(buku);
		int res = dao.addBuku(listBuku);
		
		return gson.toJson(res);
	}
	
	@POST
	@Path("/UpdateBuku")
	@Consumes("application/json")
	public String updateBuku(String data){
		log.info("updateBuku invoked...");
		Gson gson = new Gson();
		Buku buku = gson.fromJson(data, Buku.class);
		
		int res = dao.upateBuku(buku);
		
		return gson.toJson(res);
	}
	
	@DELETE
	@Path("/DeleteBuku/{bukuIds}")
	@Consumes("application/json")
	public String deleteBuku(@PathParam("bukuIds")String bukuIds){
		log.info("deleteBuku invoked...");
		
		String[] arrayIds = bukuIds.split("-");
		List<Integer> ids = new ArrayList<>();
		for (String string : arrayIds) {
			ids.add(Integer.valueOf(string));
		}
		
		int res = dao.deleteBuku(ids);
		
		Gson gson = new Gson();
		return gson.toJson(res);
	}
	
	@GET
	@Path("/GetAllBuku")
	@Produces("application/json")
	public String getAllBuku(@QueryParam("start")String start,@QueryParam("limit")String limit){
		log.info("getAllBuku invoked...");
		Map<String, String> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("bukuId", "0");
		
		List<Buku> listBuku = dao.getBuku(params);
		
		Gson gson = new Gson();
		return gson.toJson(listBuku);
	}
	
}
