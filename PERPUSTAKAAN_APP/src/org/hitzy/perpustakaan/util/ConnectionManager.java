package org.hitzy.perpustakaan.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class ConnectionManager {
	//private static Logger log = new AppLogger(ConnectionManager.class).getLogger();
	private static Logger log = Logger.getLogger(ConnectionManager.class);
	
	private static BoneCP connectionPool = null;

	public static final int PERPUSTAKAAN = 1;
	private static BoneCP perpustakaanConnectionPool = null;
	
	static String osName = System.getProperty("os.name").toLowerCase();
	static String url = "", userName = "", password = "", perpustakaan = "";
	static int minConnectionsPerPartition, maxConnectionsPerPartition, idleMaxAgeInSeconds, partitionCount, closeConnectionWatchTimeout;
	
//	private String configFileForWindows = this.getClass().getClassLoader().getResource("/META-INF/bonecpConfig.properties").getFile().substring(1).toString();
//	private String configFileForLinux = this.getClass().getClassLoader().getResource("/META-INF/bonecpConfig.properties").getFile().toString();
	
	
	public static void detectEnvirontment(){
		log.info("DETECTING OS ENVIRONTMENT...");
		ConnectionManager cm = new ConnectionManager();
//		log.info("configFileForWindows: "+cm.configFileForWindows);
//		log.info("configFileForLinux: "+cm.configFileForLinux);
//		Properties prop = new Properties();
//		InputStream input = null;

		/*try {
			if(osName.contains("windows"))
				input = new FileInputStream(cm.configFileForWindows);
			else
				input = new FileInputStream(cm.configFileForLinux);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
		
		//general config
		minConnectionsPerPartition = 1;
		maxConnectionsPerPartition = 30;
		idleMaxAgeInSeconds = 30;
		partitionCount = 2;
		closeConnectionWatchTimeout = 60;
		
		if(osName.contains("windows")){
			log.info("CONFIGURING SYSTEM ON "+osName+" ENVIRONTMENT (local server / development)");
			
			url = "jdbc:mysql://localhost:3306/";
			userName = "root";
			password = "root";
			perpustakaan = "perpustakaan";
			
			log.info("url: "+url);
			log.info("userName: "+userName);
			log.info("password: "+password);
			log.info("perpustakaan: "+perpustakaan);
		}
		else{
			log.info("CONFIGURING SYSTEM ON "+osName+" ENVIRONTMENT");
			
			url = "jdbc:mysql://localhost:3306/";
			userName = "root";
			password = "root";
			perpustakaan = "perpustakaan";
		}
	}
	
	public static void configurePerpustakaanConnPool() {
//		log.info("lalalalala");
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			BoneCPConfig config = new BoneCPConfig();

			config.setJdbcUrl(url+perpustakaan);
			config.setUsername(userName);
			config.setPassword(password);
			config.setMinConnectionsPerPartition(minConnectionsPerPartition);
			config.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
			config.setIdleMaxAgeInSeconds(idleMaxAgeInSeconds);
			config.setPartitionCount(partitionCount);
			config.setCloseConnectionWatchTimeout(closeConnectionWatchTimeout, TimeUnit.SECONDS);
			
			connectionPool = new BoneCP(config); 
			log.info("contextInitialized.....Connection Pooling perpustakaan is configured");
			log.info("Total connections ==> " + connectionPool.getTotalCreatedConnections());
			ConnectionManager.setConnectionPool(PERPUSTAKAAN, connectionPool);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	public static void shutdownConnPool(int CURRENT_CHOICE) {

		try {
			BoneCP connectionPool = ConnectionManager.getConnectionPool(CURRENT_CHOICE);
			log.info("contextDestroyed....");
			if (connectionPool != null) {
				connectionPool.shutdown();
				log.info("contextDestroyed.....Connection Pooling shut downed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(int CURRENT_CHOICE) {

		Connection conn = null;
		try {
			conn = getConnectionPool(CURRENT_CHOICE).getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeResultSet(ResultSet rSet) {
		try {
			if (rSet != null)
				rSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static BoneCP getConnectionPool(int CURRENT_CHOICE) {
		if(CURRENT_CHOICE==PERPUSTAKAAN)
			return perpustakaanConnectionPool;
		else 
			return null;
	}

	public static void setConnectionPool(int CURRENT_CHOICE, BoneCP connectionPool) {
		if(CURRENT_CHOICE==PERPUSTAKAAN)
			ConnectionManager.perpustakaanConnectionPool = connectionPool;
	}

}
