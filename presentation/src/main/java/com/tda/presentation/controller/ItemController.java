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

	private static final String ITEM_FORM_DELETE_ERROR = "item.form.deleteError";
	private static final String ITEM_FORM_NOT_FOUND = "item.form.notFound";
	private static final String ITEM_LIST = "item/list";
	private static final String ITEM_FORM_MESSAGE = "message";
	private static final String ITEM_FORM_ADD_SUCCESSFUL = "item.form.addSuccessful";
	private static final String REDIRECT_TO_ITEM_LIST = "redirect:/item";
	private static final String ITEM_CREATE_FORM = "item/createForm";
	private ItemService itemService;

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		setupForm(model, new Item());

		return ITEM_CREATE_FORM;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(Model model, @Valid Item anItem,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();

		if (result.hasErrors()) {
			setupForm(model, anItem);
			modelAndView.setViewName(ITEM_CREATE_FORM);
		} else {
			modelAndView.setViewName(REDIRECT_TO_ITEM_LIST);
			modelAndView.addObject(ITEM_FORM_MESSAGE, ITEM_FORM_ADD_SUCCESSFUL);
			itemService.save(anItem);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		setupForm(model, itemService.findById(id));

		return ITEM_CREATE_FORM;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ModelAndView deleteItem(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView(REDIRECT_TO_ITEM_LIST);
		Item anItem = itemService.findById(id);

		if (anItem == null) {
			modelAndView.addObject(ITEM_FORM_MESSAGE, ITEM_FORM_NOT_FOUND);
		} else {

			try {
				itemService.delete(anItem);
			} catch (Exception e) {
				modelAndView.addObject(ITEM_FORM_MESSAGE,
						ITEM_FORM_DELETE_ERROR);
			}
		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getList() {
		ModelAndView modelAndView = new ModelAndView(ITEM_LIST);
		modelAndView.addObject("itemList", itemService.findAll());

		return modelAndView;
	}

	private void setupForm(Model model, Item item) {
		model.addAttribute(item);
		model.addAttribute("categories", Category.values());
		model.addAttribute("measureUnits", MeasureUnit.values());
	}
}
