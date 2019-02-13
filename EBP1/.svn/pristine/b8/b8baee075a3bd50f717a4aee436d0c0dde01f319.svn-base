package com.ibm.p1.tools;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

public class BlobMySQLDialect extends MySQLDialect {
	public BlobMySQLDialect() {
		super();
		registerHibernateType(Types.LONGNVARCHAR, Hibernate.TEXT.getName());

		registerHibernateType(-1, Hibernate.STRING.getName());

		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
	}
}