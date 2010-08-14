package com.tda.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tda.model.patient.Patient;
import com.tda.model.socialworker.Addiction;
import com.tda.model.socialworker.AdultEducationalLevel;
import com.tda.model.socialworker.BathroomSewerType;
import com.tda.model.socialworker.Electricity;
import com.tda.model.socialworker.InteriorFloor;
import com.tda.model.socialworker.KitchenFuel;
import com.tda.model.socialworker.LivesWith;
import com.tda.model.socialworker.MainIncome;
import com.tda.model.socialworker.Mistreatment;
import com.tda.model.socialworker.NBI;
import com.tda.model.socialworker.RoofType;
import com.tda.model.socialworker.Scholarity;
import com.tda.model.socialworker.SchoolHours;
import com.tda.model.socialworker.SchoolService;
import com.tda.model.socialworker.SocialWorkerForm;
import com.tda.model.socialworker.WaterSource;
import com.tda.model.socialworker.WaterSourceType;
import com.tda.service.api.PatientService;
import com.tda.service.api.SocialWorkerFormService;

@Controller
@RequestMapping(value = "/patient/{patientId}/socialworker/new")
@SessionAttributes("socialWorkerForm")
public class SocialWorkerFormController {
	private static final String SOCIAL_WORKER_ADD_FORM = "socialworkerform/form";
	private SocialWorkerFormService socialWorkerFormService;
	private PatientService patientService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("patientId") long patientId,
			Model model) {
		Patient patient = patientService.findById(patientId);
		SocialWorkerForm socialWorkerForm = new SocialWorkerForm();
		socialWorkerForm.setPatient(patient);
		model.addAttribute("socialWorkerForm", socialWorkerForm);
		return SOCIAL_WORKER_ADD_FORM;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@Valid @ModelAttribute("socialWorkerForm") SocialWorkerForm socialWorkerForm,
			BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			return SOCIAL_WORKER_ADD_FORM;
		} else {
			socialWorkerFormService.save(socialWorkerForm);
			status.setComplete();
			return "redirect:/patient/" + socialWorkerForm.getPatient().getId();
		}
	}

	@ModelAttribute("livesWith")
	public LivesWith[] populateLivesWith() {
		return LivesWith.values();
	}

	@ModelAttribute("interiorFloor")
	public InteriorFloor[] populateInteriorFloor() {
		return InteriorFloor.values();
	}

	@ModelAttribute("roofType")
	public RoofType[] populateRoofType() {
		return RoofType.values();
	}

	@ModelAttribute("waterSource")
	public WaterSource[] populateWaterSource() {
		return WaterSource.values();
	}

	@ModelAttribute("waterSourceType")
	public WaterSourceType[] populateWaterSourceType() {
		return WaterSourceType.values();
	}

	@ModelAttribute("bathroomSewerType")
	public BathroomSewerType[] populateBathroomSewerType() {
		return BathroomSewerType.values();
	}

	@ModelAttribute("kitchenFuel")
	public KitchenFuel[] populateKitchenFuel() {
		return KitchenFuel.values();
	}

	@ModelAttribute("electricity")
	public Electricity[] populateElectricity() {
		return Electricity.values();
	}

	@ModelAttribute("scholarity")
	public Scholarity[] populateScholarity() {
		return Scholarity.values();
	}

	@ModelAttribute("schoolHours")
	public SchoolHours[] populateSchoolHours() {
		return SchoolHours.values();
	}

	@ModelAttribute("schoolService")
	public SchoolService[] populateSchoolService() {
		return SchoolService.values();
	}

	@ModelAttribute("adultEducationalLevel")
	public AdultEducationalLevel[] populateAdultEducationalLevel() {
		return AdultEducationalLevel.values();
	}

	@ModelAttribute("mainIncome")
	public MainIncome[] populateMainIncome() {
		return MainIncome.values();
	}

	@ModelAttribute("nbi")
	public NBI[] populateNBI() {
		return NBI.values();
	}

	@ModelAttribute("addiction")
	public Addiction[] populateAddiction() {
		return Addiction.values();
	}

	@ModelAttribute("mistreatment")
	public Mistreatment[] populateMistreatment() {
		return Mistreatment.values();
	}

	@Autowired
	public void setSocialWorkerFormService(
			SocialWorkerFormService socialWorkerFormService) {
		this.socialWorkerFormService = socialWorkerFormService;
	}

	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
}
