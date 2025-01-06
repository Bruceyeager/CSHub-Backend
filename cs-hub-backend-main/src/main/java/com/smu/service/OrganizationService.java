package com.smu.service;

import com.smu.model.Organization;
import com.smu.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationService {

	private final OrganizationRepository organizationRepository;

	public Organization add(Organization organization){
		log.info("inside OrganizationService add");
		Organization result = organizationRepository.save(organization);
		log.info("exit OrganizationService add");
		return result;
	}

	public void deleteById(Long id){
		log.info("inside OrganizationService deleteById");
		organizationRepository.deleteById(id);
		log.info("exit OrganizationService deleteById");
	}

	public List<Organization> findAll(){
		log.info("inside OrganizationService findAll");
		List<Organization> organizations= organizationRepository.findAll();
		log.info("exit OrganizationService findAll");
		return organizations;
	}

	public Organization findOneById(Long id){
		log.info("inside OrganizationService findOneById");
		Optional<Organization> organization= organizationRepository.findById(id);
		log.info("exit OrganizationService findOneById");
		Organization result =null;
		if(organization.isPresent()){
			result = organization.get();
		}
		return result;
	}
}
