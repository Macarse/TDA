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
import com.tda.model.paginator.Order;
import com.tda.model.paginator.Paginator;
import com.tda.service.api.ItemService;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
	
	private ItemService itemService;
	private Paginator paginator;

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
		
		//set paginator
		paginator = new Paginator(1, 0, Order.asc);
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
	public ModelAndView getList(@RequestParam(value = "page", required = false) String pageNr) {
		ModelAndView modelAndView = new ModelAndView("item/list");
		
		//set paginator values
		if ("next".equals(pageNr)){
			paginator.setPageIndex(paginator.getPageIndex()+1);
		}else if ("previous".equals(pageNr)){
			paginator.setPageIndex(paginator.getPageIndex()-1);
		}
		
		modelAndView.addObject("paginator", paginator);
		modelAndView.addObject("itemList", itemService.findAllPaged(paginator));
		
		return modelAndView;
	}

	private void setupForm(Model model, Item item) {
		model.addAttribute(item);
		model.addAttribute("categories", Category.values());
		model.addAttribute("measureUnits", MeasureUnit.values());
	}
}
