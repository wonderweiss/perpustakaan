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
import org.hitzy.perpustakaan.model.Anggota;
import org.hitzy.perpustakaan.util.ConnectionManager;

public class AnggotaDao {

	private Logger log = Logger.getLogger(AnggotaDao.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public int addAnggota(List<Anggota> listAnggota){
		log.info("addAnggota invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (Anggota anggota : listAnggota) {
	    		String query = "INSERT INTO perpustakaan.anggota (nama_anggota, "
	    				+ "jenis_kelamin, pekerjaan, tempat_lahir, tanggal_lahir, telepon, email) "
	    				+ "VALUES (?,?,?,?,?,?,?)";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setString(1, anggota.getNamaAnggota());
	    		ps.setString(2, anggota.getJenisKelamin());
	    		ps.setString(3, anggota.getPekerjaan());
	    		ps.setString(4, anggota.getTempatLahir());
	    		ps.setString(5, sdf.format(anggota.getTanggalLahir()));
	    		ps.setString(6, anggota.getTelepon());
	    		ps.setString(7, anggota.getEmail());
	    		
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
	
	public int upateAnggota(Anggota anggota){
		log.info("updateAnggota invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "UPDATE perpustakaan.anggota SET nama_anggota = ?, "
    				+ "jenis_kelamin = ?, pekerjaan = ?, tempat_lahir = ?, tanggal_lahir = ?, telepon = ?, email = ? "
    				+ "WHERE anggota_id = ?";
//	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setString(1, anggota.getNamaAnggota());
    		ps.setString(2, anggota.getJenisKelamin());
    		ps.setString(3, anggota.getPekerjaan());
    		ps.setString(4, anggota.getTempatLahir());
    		ps.setString(5, sdf.format(anggota.getTanggalLahir()));
    		ps.setString(6, anggota.getTelepon());
    		ps.setString(7, anggota.getEmail());
    		ps.setInt(8, anggota.getAnggotaId());
    		
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
	
	public int deleteAnggota(List<Integer> anggotaIds){
		log.info("deleteAnggota invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (int anggotaId : anggotaIds) {
	    		String query = "DELETE FROM perpustakaan.anggota WHERE anggota_id = ?";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setInt(1, anggotaId);
	    		
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
	
	public List<Anggota> getAnggota(Map<String, String> params){
		log.info("getAnggota invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<Anggota> result = new ArrayList<Anggota>();
    	int start = Integer.valueOf(params.get("start"));
    	int limit = Integer.valueOf(params.get("limit"));
    	int anggotaId = Integer.valueOf(params.get("anggotaId"));
    	
    	String whereClause = "";
    	if(anggotaId>0){
    		whereClause+="WHERE petugas_id="+anggotaId+" ";
    	}

    	
    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "SELECT 	anggota_id, nama_anggota, jenis_kelamin, pekerjaan, tempat_lahir, tanggal_lahir, telepon, email "
    				+ "FROM perpustakaan.anggota "
    				+ whereClause
    				+ "LIMIT ?, ?";
//	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setInt(1, start);
    		ps.setInt(2, limit);
    		
    		rs = ps.executeQuery();
    		
    		while(rs.next()){
    			Anggota anggota = new Anggota();
    			anggota.setAnggotaId(rs.getInt(1));
    			anggota.setNamaAnggota(rs.getString(2));
    			anggota.setJenisKelamin(rs.getString(3));
    			anggota.setPekerjaan(rs.getString(4));
    			anggota.setTempatLahir(rs.getString(5));
    			anggota.setTanggalLahir(rs.getDate(6));
    			anggota.setTelepon(rs.getString(7));
    			anggota.setEmail(rs.getString(8));
    			
    			result.add(anggota);
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
