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

import com.tda.model.item.Category;
import com.tda.model.item.Item;
import com.tda.model.item.MeasureUnit;
import com.tda.service.api.ItemService;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

	private ItemService itemService;

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		setupForm(model, new Item());

		return "item/createForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Model model, @Valid Item anItem, BindingResult result) {
		if (result.hasErrors()) {
			setupForm(model, anItem);
			return "item/createForm";
		}
		itemService.save(anItem);

		return "redirect:/item";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		setupForm(model, itemService.findById(id));

		return "item/createForm";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteItem(@PathVariable Long id) {
		itemService.delete(itemService.findById(id));

		return "redirect:/item";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getList() {
		ModelAndView modelAndView = new ModelAndView("item/list");
		modelAndView.addObject("itemList", itemService.findAll());

		return modelAndView;
	}

	private void setupForm(Model model, Item item) {
		model.addAttribute(item);
		model.addAttribute("categories", Category.values());
		model.addAttribute("measureUnits", MeasureUnit.values());
	}
}
