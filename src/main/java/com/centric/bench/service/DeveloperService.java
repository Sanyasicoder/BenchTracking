package com.centric.bench.service;

import java.util.List;

import com.centric.bench.dto.DeveloperDTO;
import com.centric.bench.model.Developer;

public interface DeveloperService {
	
	public  Developer addDeveloper(DeveloperDTO developer);
	
	public List<Developer> getALLDevelopers();
	
	public Developer getDeveloperById(Long devId);	
	
	
	public void deleteDevById(Long devId);
	

}
