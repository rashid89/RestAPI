package com.number.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberGeneratorService {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String INPROGRESS = "INPROGRESS";
	public static final String ERROR = "ERROR";
	
	@Autowired
	NumberGeneratorRepository generatorRepo;

	public UUID generateNumberAndUUID(InputJson inputJson) {
		
		Integer goal = inputJson.getGoal();
		Integer step = inputJson.getStep();
		StringBuilder sb = new StringBuilder();
		List<String> numList = new ArrayList<>();
		
		int i = goal;
		while (i >= 0) {
			sb.append(i);
			sb.append(",");
			i= i-step;
		}
		UUID uuid = UUID.randomUUID();
		sb.deleteCharAt(sb.length()-1); 
		numList.add(sb.toString());
		addGeneratedNumber(uuid, numList);
		return uuid;
	}

	public UUID generateNumberAndUUID(List<InputJson> inputJsons) {
		
		List<String> numList = new ArrayList<>();
		
		for (int i = 0; i < inputJsons.size(); i++) {
			
			StringBuilder sb = new StringBuilder();
			Integer goal = inputJsons.get(i).getGoal();
			Integer step = inputJsons.get(i).getStep();
			
			int j = goal;
			
			while (j >= 0) {
				sb.append(j);
				sb.append(",");
				j= j-step;
			}
			sb.deleteCharAt(sb.length()-1); 
			numList.add(sb.toString());
		}
		
		UUID uuid = UUID.randomUUID();
		addGeneratedNumber(uuid,numList);
		return uuid;
	}

	private void addGeneratedNumber(UUID uuid, List<String> nums) {
		generatorRepo.addTask(uuid,nums);
	}
	
	public String getTaskStatusByUUID(UUID uuid) {
		List<String> nums =  generatorRepo.getTaskByUUID(uuid);
		if(nums != null) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}
	
	public List<String> getGeneratedNumberByUUID(UUID uuid) {
		List<String> nums =  generatorRepo.getTaskByUUID(uuid);
		
		if(nums == null) {
			return Collections.emptyList();
		}else{
			return nums;
		}
	}
}
