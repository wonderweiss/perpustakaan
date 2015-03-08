package org.hitzy.perpustakaan.model;

import java.util.Date;

public class Anggota {

	private int anggotaId;
	private String namaAnggota;
	private String jenisKelamin;
	private String pekerjaan;
	private String tempatLahir;
	private Date tanggalLahir;
	private String telepon;
	private String email;

	public int getAnggotaId() {
		return anggotaId;
	}
	public void setAnggotaId(int anggotaId) {
		this.anggotaId = anggotaId;
	}
	public String getNamaAnggota() {
		return namaAnggota;
	}
	public void setNamaAnggota(String namaAnggota) {
		this.namaAnggota = namaAnggota;
	}
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public String getPekerjaan() {
		return pekerjaan;
	}
	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
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
	
	
}
