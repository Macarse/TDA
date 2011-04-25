package com.tda.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.patientqueue.PatientQueue;
import com.tda.persistence.dao.PatientQueueDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/test-datasource.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PatientQueueDAOTest {
	@Autowired
	PatientQueueDAO patientQueueDAO;

	@Test
	public void findByUser() {
		List<PatientQueue> list = patientQueueDAO
				.findPatientsByApplicationUserId(1L);
		assertEquals(list, null);
	}
}
