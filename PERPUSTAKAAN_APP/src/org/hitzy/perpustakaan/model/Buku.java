package org.hitzy.perpustakaan.model;

public class Buku {

	private int bukuId;
	private String judul;
	private String penulis;
	private String penerbit;
	private int tahunTerbit;
	private String isbn;
	private int rakId;

	public int getBukuId() {
		return bukuId;
	}
	public void setBukuId(int bukuId) {
		this.bukuId = bukuId;
	}
	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getPenulis() {
		return penulis;
	}
	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}
	public String getPenerbit() {
		return penerbit;
	}
	public void setPenerbit(String penerbit) {
		this.penerbit = penerbit;
	}
	public int getTahunTerbit() {
		return tahunTerbit;
	}
	public void setTahunTerbit(int tahunTerbit) {
		this.tahunTerbit = tahunTerbit;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getRakId() {
		return rakId;
	}
	public void setRakId(int rakId) {
		this.rakId = rakId;
	}
	@Override
	public String toString() {
		return "Buku [bukuId=" + bukuId + ", judul=" + judul + ", penulis="
				+ penulis + ", penerbit=" + penerbit + ", tahunTerbit="
				+ tahunTerbit + ", isbn=" + isbn + ", rakId=" + rakId + "]";
	}

	
}
