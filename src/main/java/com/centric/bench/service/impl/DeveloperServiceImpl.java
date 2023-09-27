package com.centric.bench.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centric.bench.custom.exception.EmptyInputException;
import com.centric.bench.dto.DeveloperDTO;
import com.centric.bench.model.Developer;
import com.centric.bench.repositories.DeveloperRepository;
import com.centric.bench.service.DeveloperService;
@Service
public class DeveloperServiceImpl implements DeveloperService {
	
	@Autowired
	private DeveloperRepository empRepo;

	@Override
	public Developer addDeveloper(DeveloperDTO developer) {
		if(developer.getName().isEmpty() || developer.getLastProjectName().isEmpty()) {
			throw new EmptyInputException("601", "name or last project name is empty");
		}
		
		Developer dev = new Developer();
		dev.setId(developer.getId());
		dev.setName(developer.getName());
		dev.setOnBench(developer.isOnBench());
		Developer savedDeveloper = empRepo.save(dev);
		
		return savedDeveloper;
	}

	@Override
	public List<Developer> getALLDevelopers() {
		return empRepo.findAll();
	}

	@Override
	public Developer getDeveloperById(Long devId) {
		return empRepo.findById(devId).get();
	}

	@Override
	public void deleteDevById(Long devId) {
		empRepo.deleteById(devId);
		System.out.println("Id deleted ");
		
	}
	

}
