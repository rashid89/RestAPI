package com.number.generator;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NumberGeneratorController {
	
	@Autowired
	NumberGeneratorService numGenService;
	
	
	@PostMapping("/generate")
	public Task generateTask(@RequestBody InputJson inputJson) {
		System.out.println("Calling Accept Input");
		
		UUID uuid = numGenService.generateNumberAndUUID(inputJson);
		
		Task task = new Task();
		task.setTask(uuid);
		return task;
	}

	@GetMapping("/tasks/{uuid}/status")
	public OutputJson getStatus(@PathVariable(value = "uuid") UUID uuid) {
		
		System.out.println("Calling get...");
		System.out.println(uuid);
		
		String status =  numGenService.getTaskStatusByUUID(uuid); 
		OutputJson response = new OutputJson();
		response.setResult(status);
		return response;
	}
	
	
	@GetMapping("/tasks/{uuid}")
	public BulkResponse getNumberList(@PathVariable(value = "uuid") UUID uuid, @RequestParam(required = true) String action) {
		
		System.out.println("Getting Number List...");
		List<String> nums = null;
		BulkResponse response = new BulkResponse();
		if(action.equals("get_numlist")){
			nums = numGenService.getGeneratedNumberByUUID(uuid);
		}
		response.setResult(nums);
		return response;
		
	}

	@PostMapping("/bulkGenerate")
	public Task generateBulkTask(@RequestBody List<InputJson> requestJson) {
		System.out.println("Accept Input Bulk");
		
		UUID uuid = numGenService.generateNumberAndUUID(requestJson);
		
		Task response = new Task();
		response.setTask(uuid);
		return response;
	}

}