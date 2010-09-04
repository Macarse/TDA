package com.tda.model.pediatrician;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.tda.model.patient.Patient;

@Entity
public class PediatricianForm {
	private Long id;

	private Patient patient;

	private Date fillingDate;

	// PERINATAL BACKGROUND
	private BirthPlace birthPlace;

	private boolean patologyDuringBirth;

	private BirthType birthType;

	private BirthTypePresentation birthTypePresentation;

	private BirthTypeTermination birthTypeTermination;

	private Integer gestationalAge;

	private Double birthWeight;

	private Double size;

	private Double headCircumference;

	private boolean apgarDepressed;

	private boolean apgarReanimated;

	private boolean malformation;

	private boolean jaundice;

	private boolean respiratoryDisease;

	private boolean congenitalInfection;

	private boolean neurologicalProblems;

	private boolean aids;

	private boolean acquiredInfection;

	private String otherPerinatalDiseases;

	private ExitStatus exitStatus;

	// PATIENT BACKGROUND

	private boolean consumedTobacco;

	private boolean consumedAlcohol;

	private boolean tattooed;

	private boolean infectiousDiseases;

	private boolean hemorrhagic;

	private boolean trauma;

	private boolean allergies;

	private boolean previousAdmissions;

	private String otherPatientDiseases;

	// FAMILY BACKGROUND

	private boolean cardiovascular;

	private boolean dbt;

	private boolean hta;

	private boolean asthma;

	private boolean mentalDisorder;

	private boolean familyAllergies;

	private boolean addictions;

	private boolean familyInfectiousDiseases;

	private boolean familyHemorrhagic;

	private String otherFamilyDiaseases;

	// MATURATION AND DEVELOPMENT

	private String maturationAndDevelopment;

	// PHYSICAL EXAM

	// TODO Some things are filled in Nursing and have to be shown up here.

	private String symptoms;

	private String pathologyFound;

	// TODO LABORATORY

	// RADIOLOGY

	private String chest;

	private String bones;

	private String radiologyOther;

	private String radiologyComments;

	// TODO DIAGNOSIS

	// INTERNMENT

	private boolean interconsultation;

	private boolean internment;

	private String treatment;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@Transient
	public boolean isNew() {
		return id == null;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}

	@Enumerated
	public BirthPlace getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(BirthPlace birthPlace) {
		this.birthPlace = birthPlace;
	}

	public boolean isPatologyDuringBirth() {
		return patologyDuringBirth;
	}

	public void setPatologyDuringBirth(boolean patologyDuringBirth) {
		this.patologyDuringBirth = patologyDuringBirth;
	}

	@Enumerated
	public BirthType getBirthType() {
		return birthType;
	}

	public void setBirthType(BirthType birthType) {
		this.birthType = birthType;
	}

	@Enumerated
	public BirthTypePresentation getBirthTypePresentation() {
		return birthTypePresentation;
	}

	public void setBirthTypePresentation(
			BirthTypePresentation birthTypePresentation) {
		this.birthTypePresentation = birthTypePresentation;
	}

	@Enumerated
	public BirthTypeTermination getBirthTypeTermination() {
		return birthTypeTermination;
	}

	public void setBirthTypeTermination(
			BirthTypeTermination birthTypeTermination) {
		this.birthTypeTermination = birthTypeTermination;
	}

	public Integer getGestationalAge() {
		return gestationalAge;
	}

	public void setGestationalAge(Integer gestationalAge) {
		this.gestationalAge = gestationalAge;
	}

	public Double getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(Double birthWeight) {
		this.birthWeight = birthWeight;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getHeadCircumference() {
		return headCircumference;
	}

	public void setHeadCircumference(Double headCircumference) {
		this.headCircumference = headCircumference;
	}

	public boolean isApgarDepressed() {
		return apgarDepressed;
	}

	public void setApgarDepressed(boolean apgarDepressed) {
		this.apgarDepressed = apgarDepressed;
	}

	public boolean isApgarReanimated() {
		return apgarReanimated;
	}

	public void setApgarReanimated(boolean apgarReanimated) {
		this.apgarReanimated = apgarReanimated;
	}

	public boolean isMalformation() {
		return malformation;
	}

	public void setMalformation(boolean malformation) {
		this.malformation = malformation;
	}

	public boolean isJaundice() {
		return jaundice;
	}

	public void setJaundice(boolean jaundice) {
		this.jaundice = jaundice;
	}

	public boolean isRespiratoryDisease() {
		return respiratoryDisease;
	}

	public void setRespiratoryDisease(boolean respiratoryDisease) {
		this.respiratoryDisease = respiratoryDisease;
	}

	public boolean isCongenitalInfection() {
		return congenitalInfection;
	}

	public void setCongenitalInfection(boolean congenitalInfection) {
		this.congenitalInfection = congenitalInfection;
	}

	public boolean isNeurologicalProblems() {
		return neurologicalProblems;
	}

	public void setNeurologicalProblems(boolean neurologicalProblems) {
		this.neurologicalProblems = neurologicalProblems;
	}

	public boolean isAids() {
		return aids;
	}

	public void setAids(boolean aids) {
		this.aids = aids;
	}

	public boolean isAcquiredInfection() {
		return acquiredInfection;
	}

	public void setAcquiredInfection(boolean acquiredInfection) {
		this.acquiredInfection = acquiredInfection;
	}

	public String getOtherPerinatalDiseases() {
		return otherPerinatalDiseases;
	}

	public void setOtherPerinatalDiseases(String otherPerinatalDiseases) {
		this.otherPerinatalDiseases = otherPerinatalDiseases;
	}

	@Enumerated
	public ExitStatus getExitStatus() {
		return exitStatus;
	}

	public void setExitStatus(ExitStatus exitStatus) {
		this.exitStatus = exitStatus;
	}

	public boolean isConsumedTobacco() {
		return consumedTobacco;
	}

	public void setConsumedTobacco(boolean consumedTobacco) {
		this.consumedTobacco = consumedTobacco;
	}

	public boolean isConsumedAlcohol() {
		return consumedAlcohol;
	}

	public void setConsumedAlcohol(boolean consumedAlcohol) {
		this.consumedAlcohol = consumedAlcohol;
	}

	public boolean isTattooed() {
		return tattooed;
	}

	public void setTattooed(boolean tattooed) {
		this.tattooed = tattooed;
	}

	public boolean isInfectiousDiseases() {
		return infectiousDiseases;
	}

	public void setInfectiousDiseases(boolean infectiousDiseases) {
		this.infectiousDiseases = infectiousDiseases;
	}

	public boolean isHemorrhagic() {
		return hemorrhagic;
	}

	public void setHemorrhagic(boolean hemorrhagic) {
		this.hemorrhagic = hemorrhagic;
	}

	public boolean isTrauma() {
		return trauma;
	}

	public void setTrauma(boolean trauma) {
		this.trauma = trauma;
	}

	public boolean isAllergies() {
		return allergies;
	}

	public void setAllergies(boolean allergies) {
		this.allergies = allergies;
	}

	public boolean isPreviousAdmissions() {
		return previousAdmissions;
	}

	public void setPreviousAdmissions(boolean previousAdmissions) {
		this.previousAdmissions = previousAdmissions;
	}

	public String getOtherPatientDiseases() {
		return otherPatientDiseases;
	}

	public void setOtherPatientDiseases(String otherPatientDiseases) {
		this.otherPatientDiseases = otherPatientDiseases;
	}

	public boolean isCardiovascular() {
		return cardiovascular;
	}

	public void setCardiovascular(boolean cardiovascular) {
		this.cardiovascular = cardiovascular;
	}

	public boolean isDbt() {
		return dbt;
	}

	public void setDbt(boolean dbt) {
		this.dbt = dbt;
	}

	public boolean isHta() {
		return hta;
	}

	public void setHta(boolean hta) {
		this.hta = hta;
	}

	public boolean isAsthma() {
		return asthma;
	}

	public void setAsthma(boolean asthma) {
		this.asthma = asthma;
	}

	public boolean isMentalDisorder() {
		return mentalDisorder;
	}

	public void setMentalDisorder(boolean mentalDisorder) {
		this.mentalDisorder = mentalDisorder;
	}

	public boolean isFamilyAllergies() {
		return familyAllergies;
	}

	public void setFamilyAllergies(boolean familyAllergies) {
		this.familyAllergies = familyAllergies;
	}

	public boolean isAddictions() {
		return addictions;
	}

	public void setAddictions(boolean addictions) {
		this.addictions = addictions;
	}

	public boolean isFamilyInfectiousDiseases() {
		return familyInfectiousDiseases;
	}

	public void setFamilyInfectiousDiseases(boolean familyInfectiousDiseases) {
		this.familyInfectiousDiseases = familyInfectiousDiseases;
	}

	public boolean isFamilyHemorrhagic() {
		return familyHemorrhagic;
	}

	public void setFamilyHemorrhagic(boolean familyHemorrhagic) {
		this.familyHemorrhagic = familyHemorrhagic;
	}

	public String getOtherFamilyDiaseases() {
		return otherFamilyDiaseases;
	}

	public void setOtherFamilyDiaseases(String otherFamilyDiaseases) {
		this.otherFamilyDiaseases = otherFamilyDiaseases;
	}

	public String getMaturationAndDevelopment() {
		return maturationAndDevelopment;
	}

	public void setMaturationAndDevelopment(String maturationAndDevelopment) {
		this.maturationAndDevelopment = maturationAndDevelopment;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setPathologyFound(String pathologyFound) {
		this.pathologyFound = pathologyFound;
	}

	public String getPathologyFound() {
		return pathologyFound;
	}

	public void setChest(String chest) {
		this.chest = chest;
	}

	public String getChest() {
		return chest;
	}

	public void setBones(String bones) {
		this.bones = bones;
	}

	public String getBones() {
		return bones;
	}

	public void setRadiologyOther(String radiologyOther) {
		this.radiologyOther = radiologyOther;
	}

	public String getRadiologyOther() {
		return radiologyOther;
	}

	public void setRadiologyComments(String radiologyComments) {
		this.radiologyComments = radiologyComments;
	}

	public String getRadiologyComments() {
		return radiologyComments;
	}

	public void setInterconsultation(boolean interconsultation) {
		this.interconsultation = interconsultation;
	}

	public boolean isInterconsultation() {
		return interconsultation;
	}

	public void setInternment(boolean internment) {
		this.internment = internment;
	}

	public boolean isInternment() {
		return internment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getTreatment() {
		return treatment;
	}

}
