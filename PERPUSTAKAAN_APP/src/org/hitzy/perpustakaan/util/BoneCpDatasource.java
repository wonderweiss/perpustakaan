package org.hitzy.perpustakaan.util;


public class BoneCpDatasource {
	
	protected BoneCpDatasource(){		
	}
	
	public void init(){
		ConnectionManager.detectEnvirontment();
		ConnectionManager.configurePerpustakaanConnPool();
	}
	
	
	public void down(){
		ConnectionManager.shutdownConnPool(ConnectionManager.PERPUSTAKAAN);
	}
		
}
