CREATE TABLE EN_RMV_RPLC_DOC_T (
	DOC_HDR_ID			NUMBER(14) NOT NULL,
	OPRN				CHAR(1) NOT NULL,
	PRSN_EN_ID			VARCHAR2(30) NOT NULL,
	RPLC_PRSN_EN_ID     VARCHAR2(30) NULL,
	DB_LOCK_VER_NBR		NUMBER(8) DEFAULT 0,
	CONSTRAINT EN_RMV_RPLC_DOC_T_PK PRIMARY KEY (DOC_HDR_ID)
)
/