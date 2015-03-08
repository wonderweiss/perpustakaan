package org.hitzy.perpustakaan.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Startup implements ServletContextListener{

	BoneCpDatasource boneCpDatasource = new BoneCpDatasource();
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("startup...............!!!");
		
		boneCpDatasource.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		boneCpDatasource.down();
	}
}
