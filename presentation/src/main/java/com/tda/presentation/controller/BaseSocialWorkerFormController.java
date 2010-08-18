package com.tda.presentation.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

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
import com.tda.model.socialworker.WaterSource;
import com.tda.model.socialworker.WaterSourceType;

public class BaseSocialWorkerFormController {
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
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

}
