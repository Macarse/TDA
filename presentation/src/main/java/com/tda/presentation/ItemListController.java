package com.tda.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tda.service.api.ItemService;

@Controller
@RequestMapping(value = "/items")
public class ItemListController {

	private ItemService itemService;

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getList() {
		ModelAndView modelAndView = new ModelAndView("itemList");
		modelAndView.addObject("itemList", itemService.findAll());
		
		return modelAndView;
	}

}
