package com.tda.model.dentist;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import com.tda.model.patient.Patient;

@Entity
public class DentistForm {
	private Long id;

	private Patient patient;

	private Date fillingDate;

	// HISTORY
	private boolean receivedAttentionInTrain;

	private boolean receivedAttentionOutsideTrain;

	private boolean receivedAnesthesia;

	private boolean haemorrhage;

	private boolean medicineAllergic;

	private boolean yodoAllergic;

	private String comment;

	// ODONTOGRAM
	private Double cpod;

	private Double cpos;

	private Double ceod;

	private Double ceos;

	private Collection<Tooth> tooths;

	// INSPECTION

	private String stains;

	private boolean cleftPalate;

	private boolean cleftLip;

	private String cleftComments;

	@Enumerated
	private SeverityLevel gumDisease;

	@Enumerated
	private SeverityLevel periodontalDisease;

	@Enumerated
	private SeverityLevel toothMobility;

	private String severityLevelComments;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isReceivedAttentionInTrain() {
		return receivedAttentionInTrain;
	}

	public void setReceivedAttentionInTrain(boolean receivedAttentionInTrain) {
		this.receivedAttentionInTrain = receivedAttentionInTrain;
	}

	public boolean isReceivedAttentionOutsideTrain() {
		return receivedAttentionOutsideTrain;
	}

	public void setReceivedAttentionOutsideTrain(
			boolean receivedAttentionOutsideTrain) {
		this.receivedAttentionOutsideTrain = receivedAttentionOutsideTrain;
	}

	public boolean isReceivedAnesthesia() {
		return receivedAnesthesia;
	}

	public void setReceivedAnesthesia(boolean receivedAnesthesia) {
		this.receivedAnesthesia = receivedAnesthesia;
	}

	public boolean isHaemorrhage() {
		return haemorrhage;
	}

	public void setHaemorrhage(boolean haemorrhage) {
		this.haemorrhage = haemorrhage;
	}

	public boolean isMedicineAllergic() {
		return medicineAllergic;
	}

	public void setMedicineAllergic(boolean medicineAllergic) {
		this.medicineAllergic = medicineAllergic;
	}

	public boolean isYodoAllergic() {
		return yodoAllergic;
	}

	public void setYodoAllergic(boolean yodoAllergic) {
		this.yodoAllergic = yodoAllergic;
	}

	public Double getCpod() {
		return cpod;
	}

	public void setCpod(Double cpod) {
		this.cpod = cpod;
	}

	public Double getCpos() {
		return cpos;
	}

	public void setCpos(Double cpos) {
		this.cpos = cpos;
	}

	public Double getCeod() {
		return ceod;
	}

	public void setCeod(Double ceod) {
		this.ceod = ceod;
	}

	public Double getCeos() {
		return ceos;
	}

	public void setCeos(Double ceos) {
		this.ceos = ceos;
	}

	public String getStains() {
		return stains;
	}

	public void setStains(String stains) {
		this.stains = stains;
	}

	public boolean isCleftPalate() {
		return cleftPalate;
	}

	public void setCleftPalate(boolean cleftPalate) {
		this.cleftPalate = cleftPalate;
	}

	public boolean isCleftLip() {
		return cleftLip;
	}

	public void setCleftLip(boolean cleftLip) {
		this.cleftLip = cleftLip;
	}

	public String getCleftComments() {
		return cleftComments;
	}

	public void setCleftComments(String cleftComments) {
		this.cleftComments = cleftComments;
	}

	public SeverityLevel getGumDisease() {
		return gumDisease;
	}

	public void setGumDisease(SeverityLevel gumDisease) {
		this.gumDisease = gumDisease;
	}

	public SeverityLevel getPeriodontalDisease() {
		return periodontalDisease;
	}

	public void setPeriodontalDisease(SeverityLevel periodontalDisease) {
		this.periodontalDisease = periodontalDisease;
	}

	public SeverityLevel getToothMobility() {
		return toothMobility;
	}

	public void setToothMobility(SeverityLevel toothMobility) {
		this.toothMobility = toothMobility;
	}

	public String getSeverityLevelComments() {
		return severityLevelComments;
	}

	public void setSeverityLevelComments(String severityLevelComments) {
		this.severityLevelComments = severityLevelComments;
	}

	public void setTooths(Collection<Tooth> tooths) {
		this.tooths = tooths;
	}

	public String toothsToString() {
		if (tooths == null || tooths.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (Tooth tooth : tooths) {
			sb.append(tooth.toString());
		}

		return sb.toString();
	}

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Tooth.class, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@ForeignKey(name = "ID_USER", inverseName = "ID_TOOTH")
	public Collection<Tooth> getTooths() {
		return tooths;
	}
}
