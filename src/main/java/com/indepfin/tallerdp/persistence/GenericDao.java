package com.indepfin.tallerdp.persistence;

import java.sql.SQLException;

public class GenericDao {
	
	private DatabaseManager dbManager;
	
	public GenericDao() throws SQLException {
		setDbManager(new DatabaseManager());
	}

	public DatabaseManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

}
