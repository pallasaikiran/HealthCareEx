package com.sai.app1.specialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.sai.app1.entity.Specialization;
import com.sai.app1.repository.SpecializationRepository;

@DataJpaTest(showSql=true)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class SpecializationRepositoryTest {
	
	@Autowired
	private SpecializationRepository repo;
	

	
	
	/**
	 * Test save operation
	 */
	
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec=new Specialization(null,"CRDLS","Cardiology","Interventional Cardiology is that branch of cardiology that specifically deals with diagnosing and treating cardiovascular diseases ");
		spec=repo.save(spec);
		assertNotNull(spec.getId(),"spec is not created");
	}
	/**
	 * Displacy All Opearations
	 */
	 
	@Test
	@Order(2)
	public void testSpscDisplayAll() {
		 List<Specialization> list=repo.findAll();
		 assertNotNull(list);
		 assertThat(list.size()>0);
	 }
}
