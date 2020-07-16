package org.example.dropwizard.database;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface DemoUserDAO {

	@SqlQuery("SELECT * FROM DEMO_USER WHERE username = :username")
	@RegisterBeanMapper(TupleDemoUser.class)
	TupleDemoUser findNameById(@Bind("username") String username);

	@SqlQuery("SELECT * FROM DEMO_USER")
	@RegisterBeanMapper(TupleDemoUser.class)
	List<TupleDemoUser> list();

	@SqlUpdate("insert into DEMO_USER (username) values (:username)")
	void insert(@Bind("username") String username);

	@SqlScript("create table if not exists DEMO_USER (username CHAR(10) primary key)")
	void createTable();

}
