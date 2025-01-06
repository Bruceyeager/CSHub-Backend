package com.smu.controller;

import com.smu.model.Organization;
import com.smu.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationController {

	private final OrganizationService service;

	@PostMapping("/add")
	public ResponseEntity<Organization> add(@RequestBody Organization organization) {
		Organization result = service.add(organization);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping ("/deleteById/{id}")
	public ResponseEntity<Long> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok(id);
	}

	@GetMapping ("/findAll")
	public ResponseEntity<List<Organization>> findAll() {
		List<Organization> organizations= service.findAll();
		return  ResponseEntity.ok(organizations);
	}

	@GetMapping ("/findOneById/{id}")
	public ResponseEntity<Organization> findOneByIdVIP(@PathVariable Long id) {
		Organization organization= service.findOneById(id);
		return ResponseEntity.ok(organization);
	}
}
