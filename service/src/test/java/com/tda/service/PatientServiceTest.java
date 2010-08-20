package com.tda.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;
import com.tda.model.patient.Sex;
import com.tda.service.api.PatientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/test-datasource.xml", "classpath:/spring-service.xml",
		"classpath:/spring-security.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PatientServiceTest {

	@Autowired
	private PatientService patientService;

	@Test
	public void findByName() {
		Patient patient = PatientBuilder.createPatient()
				.withFirstName("myname").withLastName("last")
				.withBirthdate(new Date()).withDni("00000000")
				.withSex(Sex.male).build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByFirstName("myname").get(0));

	}

	@Test
	public void findByExample() {
		Patient patient = PatientBuilder.createPatient()
				.withFirstName("myname").withLastName("last")
				.withBirthdate(new Date()).withDni("00000000")
				.withSex(Sex.male).build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByExample(patient).get(0));
	}

	@Test
	public void findByFirstNameContaining() {
		Patient patient = PatientBuilder.createPatient()
				.withFirstName("myname").withLastName("last")
				.withBirthdate(new Date()).withDni("00000000")
				.withSex(Sex.male).build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByFirstName("name").get(0));
	}

	@Test
	public void findByDni() {
		Patient patient = PatientBuilder.createPatient()
				.withFirstName("myname").withLastName("last")
				.withBirthdate(new Date()).withDni("32018393")
				.withSex(Sex.male).build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByDni("32018393").get(0));
	}

	@Test
	public void findBySex() {
		Patient patient = PatientBuilder.createPatient()
				.withFirstName("myname").withLastName("last")
				.withBirthdate(new Date()).withDni("32018393")
				.withSex(Sex.male).build();
		patientService.save(patient);

		assertEquals(patient, patientService.findBySex(Sex.male).get(0));
	}

}
