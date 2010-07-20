package com.tda.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tda.model.Category;
import com.tda.model.Item;
import com.tda.model.MeasureUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-service.xml", "classpath:spring-persistence.xml"})
public class ItemServiceTest {
	@Autowired
	ItemService itemService;
	List<Item> testItems = new ArrayList<Item>();
	
	@Before  
	public void runBeforeEveryTest() {
		Item item1 = new Item();
		item1.setCategory(Category.medical);
		item1.setDescription("Jeringas esterilizadas para inyecciones");
		item1.setMeasureUnit(MeasureUnit.unit);
		item1.setName("JeringaInyeccion");
		item1.setQuantity(480L);
		itemService.save(item1);
		testItems.add(item1);
		
		Item item2 = new Item();
		item2.setCategory(Category.misc);
		item2.setDescription("Gasoil para grupo electrogeno");
		item2.setMeasureUnit(MeasureUnit.lts);
		item2.setName("Gasoil");
		item2.setQuantity(1500L);
		itemService.save(item2);
		testItems.add(item2);
		
		Item item3 = new Item();
		item3.setCategory(Category.medical);
		item3.setDescription("Jeringas esterilizadas para sacar sangre");
		item3.setMeasureUnit(MeasureUnit.unit);
		item3.setName("JeringaSangre");
		item3.setQuantity(100L);
		itemService.save(item3);
		testItems.add(item3);
	}  
	  
	@After  
	public void runAfterEveryTest() {
		for( Item item : testItems) {
			itemService.delete(item);
		}
	}  
	
	@Test
	public void testItemServiceFindByName() {
		List<Item> items = itemService.findByName("Jeringa");
		assertEquals(items.size(), 2);
	}
	
	@Test
	public void testItemServiceFindByRange() {
		List<Item> items = itemService.findByQuantityRange(200L, 5000L);
		assertEquals(items.size(), 2);
	}
	
	@Test
	public void testItemServiceUpdate() {
		testItems.get(0).setName("NOMBRE");
		itemService.update(testItems.get(0));
		assertEquals(itemService.findById(testItems.get(0).getId()).getName(), "NOMBRE");
	}
}
