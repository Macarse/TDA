package com.tda.service;

import static org.junit.Assert.assertEquals;

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
		Patient patient = PatientBuilder.createPatient().withName("myname")
				.build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByName("myname").get(0));

	}

	@Test
	public void findByExample() {
		Patient patient = PatientBuilder.createPatient().withName("myname")
				.build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByExample(patient).get(0));
	}

	@Test
	public void findByNameContaining() {
		Patient patient = PatientBuilder.createPatient().withName("myname")
				.build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByNameContaining("name")
				.get(0));
	}

	@Test
	public void findByDni() {
		Patient patient = PatientBuilder.createPatient().withDni("32018393")
				.build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByDni("32018393").get(0));
	}

	@Test
	public void findBySex() {
		Patient patient = PatientBuilder.createPatient().withSex(Sex.male)
				.build();
		patientService.save(patient);

		assertEquals(patient, patientService.findBySex(Sex.male).get(0));
	}

	@Test
	public void findByMotherNameContaining() {
		Patient patient = PatientBuilder.createPatient()
				.withMotherName("myname").build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByMotherNameContaining("name")
				.get(0));
	}

	@Test
	public void findByFatherNameContaining() {
		Patient patient = PatientBuilder.createPatient()
				.withFatherName("myname").build();
		patientService.save(patient);

		assertEquals(patient, patientService.findByFatherNameContaining("name")
				.get(0));
	}
}
