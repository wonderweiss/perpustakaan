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
import org.hitzy.perpustakaan.model.Buku;
import org.hitzy.perpustakaan.util.ConnectionManager;

public class BukuDao {

//	private Logger log = new AppLogger(BukuDao.class).getLogger();
	private Logger log = Logger.getLogger(BukuDao.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public int addBuku(List<Buku> listBuku){
		log.info("addBuku invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (Buku buku : listBuku) {
	    		String query = "INSERT INTO perpustakaan.buku (judul, penulis, "
	    				+ "penerbit, tahun_terbit, isbn, rak_id) "
	    				+ "VALUES (?,?,?,?,?,?)";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setString(1, buku.getJudul());
	    		ps.setString(2, buku.getPenulis());
	    		ps.setString(3, buku.getPenerbit());
	    		ps.setInt(4, buku.getTahunTerbit());
	    		ps.setString(5, buku.getIsbn());
	    		ps.setInt(6, buku.getRakId());
	    		
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
	
	public int upateBuku( Buku buku){
		log.info("updateBuku invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;
    	
    	System.out.println(buku.toString());
    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "UPDATE perpustakaan.buku SET judul = ?, "
    				+ "penulis = ?, penerbit= ?, tahun_terbit = ?, isbn = ?, rak_id = ? "
    				+ "WHERE buku_id = ?";
//	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setString(1, buku.getJudul());
    		ps.setString(2, buku.getPenulis());
    		ps.setString(3, buku.getPenerbit());
    		ps.setInt(4, buku.getTahunTerbit());
    		ps.setString(5, buku.getIsbn());
    		ps.setInt(6, buku.getRakId());
    		ps.setInt(7, buku.getBukuId());
    		
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
	
	public int deleteBuku(List<Integer> bukuIds){
		log.info("deleteBuku invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (int bukuId : bukuIds) {
	    		String query = "DELETE FROM perpustakaan.buku WHERE buku_id = ?";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setInt(1, bukuId);
	    		
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
	
	public List<Buku> getBuku(Map<String, String> params){
		log.info("getBuku invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<Buku> result = new ArrayList<Buku>();
    	int start = Integer.valueOf(params.get("start"));
    	int limit = Integer.valueOf(params.get("limit"));
    	int bukuId = Integer.valueOf(params.get("bukuId"));
    	
    	String whereClause = "";
    	if(bukuId>0){
    		whereClause+="WHERE buku_id="+bukuId+" ";
    	}
    	
    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "SELECT 	buku_id, judul, penulis, penerbit, tahun_terbit, isbn, rak_id "
    				+ "FROM perpustakaan.buku "
    				+ whereClause
    				+ "LIMIT ?, ?";
	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setInt(1, start);
    		ps.setInt(2, limit);
    		
    		rs = ps.executeQuery();
    		
    		while(rs.next()){
    			Buku buku = new Buku();
    			buku.setBukuId(rs.getInt(1));
    			buku.setJudul(rs.getString(2));
    			buku.setPenulis(rs.getString(3));
    			buku.setPenerbit(rs.getString(4));
    			buku.setTahunTerbit(rs.getInt(5));
    			buku.setIsbn(rs.getString(6));
    			buku.setRakId(rs.getInt(7));
    			
    			result.add(buku);
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
