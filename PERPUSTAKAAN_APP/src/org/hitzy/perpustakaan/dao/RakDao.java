package org.hitzy.perpustakaan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hitzy.perpustakaan.model.Rak;
import org.hitzy.perpustakaan.util.ConnectionManager;

public class RakDao {
//	private Logger log = new AppLogger(RakDao.class).getLogger();
	private Logger log = Logger.getLogger(RakDao.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public int addRak(List<Rak> listRak){
		log.info("addRak invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (Rak rak : listRak) {
	    		String query = "INSERT INTO perpustakaan.rak (nama_rak, segmen) "
	    				+ "VALUES (?,?)";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setString(1, rak.getNamaRak());
	    		ps.setString(2, rak.getSegmen());
	    		
	    		result = ps.executeUpdate();
    		}
    		con.commit();

    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.error(e);
    		return 0;
    	} finally{
    		if (con != null){
//    			ConnectionManager.closeResultSet(rs);
    			ConnectionManager.closeStatement(ps);
    			ConnectionManager.closeConnection(con);
    		}
    	}
    	
		return result;
	}
	
	public int upateRak( Rak rak){
		log.info("updateRak invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "UPDATE perpustakaan.rak SET nama_rak = ?, segmen = ?"
    				+ "WHERE rak_id = ?";
//	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setString(1, rak.getNamaRak());
    		ps.setString(2, rak.getSegmen());
    		ps.setInt(3, rak.getRakId());
    		
    		result = ps.executeUpdate();

    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.error(e);
    		return 0;
    	} finally{
    		if (con != null){
//    			ConnectionManager.closeResultSet(rs);
    			ConnectionManager.closeStatement(ps);
    			ConnectionManager.closeConnection(con);
    		}
    	}
    	
		return result;
	}
	
	public int deleteRak(List<Integer> rakIds){
		log.info("deleteRak invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (int rakId : rakIds) {
	    		String query = "DELETE FROM perpustakaan.rak WHERE rak_id = ?";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setInt(1, rakId);
	    		
	    		result = ps.executeUpdate();
    		}
    		con.commit();

    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.error(e);
    		return 0;
    	} finally{
    		if (con != null){
//    			ConnectionManager.closeResultSet(rs);
    			ConnectionManager.closeStatement(ps);
    			ConnectionManager.closeConnection(con);
    		}
    	}
    	
		return result;
	}
	
	public List<Rak> getRak(Map<String, String> params){
		log.info("getAnggota invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<Rak> result = new ArrayList<Rak>();
    	int start = Integer.valueOf(params.get("start"));
    	int limit = Integer.valueOf(params.get("limit"));
    	int rakId = Integer.valueOf(params.get("rakId"));
    	
    	String whereClause = "";
    	if(rakId>0){
    		whereClause+="WHERE rak_id="+rakId+" ";
    	}
    	
    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "SELECT 	rak_id, nama_rak, segmen "
    				+ "FROM perpustakaan.rak "
    				+ whereClause
    				+ "LIMIT ?, ?";
//	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setInt(1, start);
    		ps.setInt(2, limit);
    		
    		rs = ps.executeQuery();
    		
    		while(rs.next()){
    			Rak rak = new Rak();
    			rak.setRakId(rs.getInt(1));
    			rak.setNamaRak(rs.getString(2));
    			rak.setSegmen(rs.getString(3));
    			
    			result.add(rak);
    		}

    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.error(e);
    		return null;
    	} finally{
    		if (con != null){
    			ConnectionManager.closeResultSet(rs);
    			ConnectionManager.closeStatement(ps);
    			ConnectionManager.closeConnection(con);
    		}
    	}
    	
		return result;
	}
}
