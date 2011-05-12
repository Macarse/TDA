package com.tda.presentation.controller;

import java.beans.PropertyEditorSupport;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tda.model.nurse.NurseAction;
import com.tda.model.nurse.Vaxine;
import com.tda.service.api.VaxineService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public class BaseNurseFormController {
	private VaxineService vaxineService;

	@ModelAttribute("allVaxines")
	public Collection<Vaxine> populateVaxines() {
		return vaxineService.findAll();
	}

	@ModelAttribute("allNurseActions")
	public NurseAction[] populateNurseActions() {
		return NurseAction.values();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Vaxine.class, new VaxineEditor());
	}

	private class VaxineEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			// En text me llega el name de la VAXINE en cuestion
			try {
				setValue(vaxineService.findByName(text));
			} catch (SingleResultExpectedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoDataFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public String getAsText() {
			return ((Vaxine) getValue()).getName();
		}
	}

	@Autowired
	public void setVaxineService(VaxineService vaxineService) {
		this.vaxineService = vaxineService;
	}
}
