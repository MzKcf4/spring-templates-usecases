CREATE TABLE EMPLOYEE_DETAIL
(
	EMPLOYEE_DETAIL_OID		number(10) NOT NULL,
	EMPLOYEE_OID			number(10) NOT NULL,
	ADDRESS					varchar2(50) NOT NULL,
	-- audit fields --
	VERSION			number(10),
	CREATE_DATE		timestamp(6) DEFAULT SYSDATE,
	CREATE_USER		number(10) DEFAULT 1,
	UPDATE_DATE		timestamp(6) DEFAULT SYSDATE,
	UPDATE_USER		number(10) DEFAULT 1,
	------------------
	CONSTRAINT	PK_EMPLOYEE_DETAIL PRIMARY KEY (EMPLOYEE_DETAIL_OID),
	CONSTRAINT	FK1_EMPLOYEE_DETAIL	FOREIGN KEY	(EMPLOYEE_OID) REFERENCES EMPLOYEE(EMPLOYEE_OID)
	
);