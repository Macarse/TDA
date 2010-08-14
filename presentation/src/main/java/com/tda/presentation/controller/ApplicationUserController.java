package com.tda.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.applicationuser.ApplicationUser;
import com.tda.service.api.ApplicationUserService;
import com.tda.service.api.AuthorityService;

@Controller
@RequestMapping(value = "/applicationuser")
public class ApplicationUserController {

	private ApplicationUserService applicationUserService;
	private AuthorityService authorityService;

	@Autowired
	public void setApplicationUserService(
			ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

	@Autowired
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new ApplicationUser());

		model.addAttribute("allAuthorities", authorityService.findAll());

		return "applicationuser/createForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid ApplicationUser aUser, BindingResult result) {
		if (result.hasErrors()) {
			return "applicationuser/createForm";
		}
		applicationUserService.save(aUser);

		return "redirect:/presentation/applicationuser/list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		// TODO refactor... repeated code from getCreateForm
		model.addAttribute(applicationUserService.findById(id));

		model.addAttribute("allAuthorities", authorityService.findAll());

		return "applicationuser/createForm";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteApplicationUser(@PathVariable Long id) {
		applicationUserService.delete(applicationUserService.findById(id));

		return "redirect:/presentation/applicationuser/list";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getList() {
		ModelAndView modelAndView = new ModelAndView("applicationuser/list");
		modelAndView.addObject("applicationUserList",
				applicationUserService.findAll());

		return modelAndView;
	}

}
