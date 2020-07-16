package org.example.dropwizard.database;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class DemoUserRepository {

	@Inject
	private DemoUserDAO demoUserDAO;
	

	private void initialize() {
		
		demoUserDAO.createTable();
		
		if(demoUserDAO.list().isEmpty()){
			Set<String> initNames = new HashSet<>();
			for(int i=0; i < 15; ++i) {
				String name = String.valueOf((char)('A'+RandomUtils.nextInt(0, 25)));
				name += String.valueOf((char)('a'+RandomUtils.nextInt(0, 25)));
				name += String.valueOf((char)('a'+RandomUtils.nextInt(0, 25)));
				name += String.valueOf(RandomUtils.nextInt(0, 9));
				name += String.valueOf(RandomUtils.nextInt(0, 9));
				initNames.add(name);
			}
			
			for (String name : initNames) {
				demoUserDAO.insert(name);
			}
		}
	}
	
	public TupleDemoUser findNameById(String username) {

		if(StringUtils.isAllBlank(username)) {
			return null;
		}
		
		initialize();
		return demoUserDAO.findNameById(username);
	}

	public List<TupleDemoUser> list() {
		
		initialize();
		return demoUserDAO.list();
	}
}
