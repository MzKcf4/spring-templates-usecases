package com.springtemplate.jpa.service;

public interface DbService {
	
	void insertReadonlyRecords(int count);
	
	void loadAllReadonlyRecord_ReadonlyMode();
	void loadAllReadonlyRecord_Not_ReadonlyMode();
}
