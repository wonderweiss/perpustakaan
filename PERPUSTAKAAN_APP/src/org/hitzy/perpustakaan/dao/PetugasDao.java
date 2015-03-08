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
import org.hitzy.perpustakaan.model.Petugas;
import org.hitzy.perpustakaan.util.ConnectionManager;

public class PetugasDao {

//	private Logger log = new AppLogger(PetugasDao.class).getLogger();
	private Logger log = Logger.getLogger(PetugasDao.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public int addPetugas(List<Petugas> listPetugas){
		log.info("addPetugas invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (Petugas petugas : listPetugas) {
	    		String query = "INSERT INTO perpustakaan.petugas (nama_petugas, "
	    				+ "jenis_kelamin, jabatan, alamat, tempat_lahir, tanggal_lahir, telepon, email, jam_tugas) "
	    				+ "VALUES (?,?,?,?,?,?,?,?,?)";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setString(1, petugas.getNamaPetugas());
	    		ps.setString(2, petugas.getJenisKelamin());
	    		ps.setString(3, petugas.getJabatan());
	    		ps.setString(4, petugas.getAlamat());
	    		ps.setString(5, petugas.getTempatLahir());
	    		ps.setString(6, sdf.format(petugas.getTanggalLahir()));
	    		ps.setString(7, petugas.getTelepon());
	    		ps.setString(8, petugas.getEmail());
	    		ps.setString(9, petugas.getJamTugas());
	    		
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
	
	public int upatePetugas(Petugas petugas){
		log.info("updatePetugas invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "UPDATE perpustakaan.petugas SET nama_petugas = ?, "
    				+ "jenis_kelamin = ?, jabatan = ?, alamat = ?, tempat_lahir = ?, tanggal_lahir = ?, telepon = ?, email = ?, jam_tugas = ? "
    				+ "WHERE petugas_id = ?";
//	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setString(1, petugas.getNamaPetugas());
    		ps.setString(2, petugas.getJenisKelamin());
    		ps.setString(3, petugas.getJabatan());
    		ps.setString(4, petugas.getAlamat());
    		ps.setString(5, petugas.getTempatLahir());
    		ps.setString(6, sdf.format(petugas.getTanggalLahir()));
    		ps.setString(7, petugas.getTelepon());
    		ps.setString(8, petugas.getEmail());
    		ps.setString(9, petugas.getJamTugas());
    		ps.setInt(10, petugas.getPetugasId());
    		
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
	
	public int deletePetugas(List<Integer> petugasIds){
		log.info("deletePetugas invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int result = 0;

    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		con.setAutoCommit(false);
    		for (int petugasId : petugasIds) {
	    		String query = "DELETE FROM perpustakaan.petugas WHERE petugas_id = ?";
//	    		log.info(query);
	    		
	    		ps = con.prepareStatement(query);
	    		ps.setInt(1, petugasId);
	    		
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
	
	public List<Petugas> getPetugas(Map<String, String> params){
		log.info("getPetugas invoked.");

		Connection con = null;		
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<Petugas> result = new ArrayList<Petugas>();
    	int start = Integer.valueOf(params.get("start"));
    	int limit = Integer.valueOf(params.get("limit"));
    	int petugasId = Integer.valueOf(params.get("petugasId"));
    	
    	String whereClause = "";
    	if(petugasId>0){
    		whereClause+="WHERE petugas_id="+petugasId+" ";
    	}

    	
    	try {
    		con = ConnectionManager.getConnection(ConnectionManager.PERPUSTAKAAN);
    		String query = "SELECT 	petugas_id, nama_petugas, jenis_kelamin, jabatan, tempat_lahir, tanggal_lahir, telepon, email, jam_tugas "
    				+ "FROM perpustakaan.petugas "
    				+ whereClause
    				+ "LIMIT ?, ?";
	    		log.info(query);
    		
    		ps = con.prepareStatement(query);
    		ps.setInt(1, start);
    		ps.setInt(2, limit);
    		
    		rs = ps.executeQuery();
    		
    		while(rs.next()){
    			Petugas petugas = new Petugas();
    			petugas.setPetugasId(rs.getInt(1));
    			petugas.setNamaPetugas(rs.getString(2));
    			petugas.setJenisKelamin(rs.getString(3));
    			petugas.setJabatan(rs.getString(4));
    			petugas.setTempatLahir(rs.getString(5));
    			petugas.setTanggalLahir(rs.getDate(6));
    			petugas.setTelepon(rs.getString(7));
    			petugas.setEmail(rs.getString(8));
    			petugas.setJamTugas(rs.getString(9));
    			
    			result.add(petugas);
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
