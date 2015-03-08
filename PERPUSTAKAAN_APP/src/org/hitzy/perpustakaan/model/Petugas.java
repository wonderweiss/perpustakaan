package org.hitzy.perpustakaan.model;

import java.util.Date;

public class Petugas {

	private int petugasId;
	private String namaPetugas;
	private String jenisKelamin;
	private String jabatan;
	private String alamat;
	private String tempatLahir;
	private Date tanggalLahir;
	private String telepon;
	private String email;
	private String jamTugas;
	
	public int getPetugasId() {
		return petugasId;
	}
	public void setPetugasId(int petugasId) {
		this.petugasId = petugasId;
	}
	public String getNamaPetugas() {
		return namaPetugas;
	}
	public void setNamaPetugas(String namaPetugas) {
		this.namaPetugas = namaPetugas;
	}
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getTempatLahir() {
		return tempatLahir;
	}
	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}
	public Date getTanggalLahir() {
		return tanggalLahir;
	}
	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}
	public String getTelepon() {
		return telepon;
	}
	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJamTugas() {
		return jamTugas;
	}
	public void setJamTugas(String jamTugas) {
		this.jamTugas = jamTugas;
	}
	
	
}
