package com.number.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class NumberGeneratorRepository {

	static Map<UUID,List<String>> taskData = new HashMap<>();

	public void addTask(UUID uuid, List<String> nums) {
		
		System.out.println("Calling Repository..");
		
		System.out.println(uuid);
		taskData.put(uuid, nums);
		
		System.out.println("Task Data: " +taskData);
	}

	public List<String> getTaskByUUID(UUID uuid) {
		return taskData.get(uuid);
	}
}
