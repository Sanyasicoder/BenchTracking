package com.centric.bench.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.centric.bench.custom.exception.EmptyInputException;
import com.centric.bench.dto.DeveloperDTO;
import com.centric.bench.dto.ResponseDTO;
import com.centric.bench.model.Developer;
import com.centric.bench.service.DeveloperService;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@GraphQLApi
@Component
@Slf4j
@Validated
@RequiredArgsConstructor
public class BenchResolver {
	
	@Autowired
	private final DeveloperService devService;
	
	private static final  Logger log = LoggerFactory.getLogger(BenchResolver.class);
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GraphQLMutation(name="createDeveloper", description="Query")
	public ResponseDTO<Developer> createDeveloper( @GraphQLNonNull @GraphQLArgument(name="developer") DeveloperDTO value) {
				
		   Developer dev = devService.addDeveloper(value);
		return ResponseDTO.<Developer>builder().data(dev).responseCode(HttpStatus.OK.value())
											.responseMessage("Success").build();
	}
	
	@Secured("ROLE_USER")
	@GraphQLQuery(name="getAllDeveloper", description="Query to fetch all developer")
	public ResponseDTO<List<Developer>> getAllDeveloper() {
		
		   List<Developer> dev = devService.getALLDevelopers();
		return ResponseDTO.<List<Developer>>builder().data(dev).responseCode(HttpStatus.OK.value())
											.responseMessage("Success").build();
	}
	
	@Secured("ROLE_USER")
	@GraphQLQuery(name="getDeveloper", description="Query")
	public ResponseDTO<Developer> getDeveloper( @GraphQLNonNull @GraphQLArgument(name="developerId") Long devId) {
		
		   Developer dev = devService.getDeveloperById(devId);
		return ResponseDTO.<Developer>builder().data(dev).responseCode(HttpStatus.OK.value())
											.responseMessage("Success").build();
	}
	@Secured("ROLE_ADMIN")
	@GraphQLMutation(name="updateDeveloper", description="Query")
	public ResponseDTO<Developer> updateDeveloper( @GraphQLNonNull @GraphQLArgument(name="developer") DeveloperDTO value) {
		
		Developer dev = devService.addDeveloper(value);
		return ResponseDTO.<Developer>builder().data(dev).responseCode(HttpStatus.OK.value())
											.responseMessage("Success").build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GraphQLMutation(name="deleteDeveloper", description="Query")
	public ResponseDTO<String> deleteDeveloper( @GraphQLNonNull @GraphQLArgument(name="developerId") Long devId) {
		
		  devService.deleteDevById(devId);
		return ResponseDTO.<String>builder().data("Deleted Successfully").responseCode(HttpStatus.OK.value())
											.responseMessage("Success").build();
	}

}
