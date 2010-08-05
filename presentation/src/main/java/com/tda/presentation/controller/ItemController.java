package com.tda.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.item.Category;
import com.tda.model.item.Item;
import com.tda.model.item.MeasureUnit;
import com.tda.service.api.ItemService;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

	private ItemService itemService;
	private PagedListHolder<Item> pagedHolder;

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
		
		//set page holder
		pagedHolder = new PagedListHolder<Item>(itemService.findAll());
		pagedHolder.setPageSize(1);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new Item());
		model.addAttribute("categories", Category.values());
		model.addAttribute("measureUnits", MeasureUnit.values());

		return "item/createForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Item anItem, BindingResult result) {
		if (result.hasErrors()) {
			return "item/createForm";
		}
		itemService.save(anItem);

		return "redirect:/item";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		// TODO refactor... repeated code from getCreateForm
		model.addAttribute(itemService.findById(id));
		model.addAttribute("categories", Category.values());
		model.addAttribute("measureUnits", MeasureUnit.values());

		return "item/createForm";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteItem(@PathVariable Long id) {
		itemService.delete(itemService.findById(id));

		return "redirect:/item";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getList(@RequestParam(value = "page", required = false) String pageNr) {
		ModelAndView modelAndView = new ModelAndView("item/list");
//		modelAndView.addObject("itemList", itemService.findAll());
		
		modelAndView.addObject("itemList", pagedHolder);
		
		if ("next".equals(pageNr)){
			pagedHolder.nextPage();
		}else if ("previous".equals(pageNr)){
			pagedHolder.previousPage();
		}
		
		return modelAndView;
	}

}
