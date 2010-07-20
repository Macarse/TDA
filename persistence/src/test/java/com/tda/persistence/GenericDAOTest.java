package com.tda.persistence;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tda.model.Category;
import com.tda.model.Item;
import com.tda.model.MeasureUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring-persistence.xml"})
public class GenericDAOTest {
	/*
	 * GenericDAO tests using the ItemDAO implementation
	 */
	@Autowired
	ItemDAO itemService;
	static List<Item> testItems = new ArrayList<Item>();
	
	//Variables used in tests
	int itemIndexForTest = 0;
	String updateStringForTest = "UPDATED";
	
	@BeforeClass  
	public static void runBeforeClass() {  
		Item item1 = new Item();
		item1.setCategory(Category.medical);
		item1.setDescription("Jeringas esterilizadas para inyecciones");
		item1.setMeasureUnit(MeasureUnit.unit);
		item1.setName("JeringaInyeccion");
		item1.setQuantity(480L);
		testItems.add(item1);
		
		Item item2 = new Item();
		item2.setCategory(Category.misc);
		item2.setDescription("Gasoil para grupo electrogeno");
		item2.setMeasureUnit(MeasureUnit.lts);
		item2.setName("Gasoil");
		item2.setQuantity(1500L);
		testItems.add(item2);
		
		Item item3 = new Item();
		item3.setCategory(Category.medical);
		item3.setDescription("Jeringas esterilizadas para sacar sangre");
		item3.setMeasureUnit(MeasureUnit.unit);
		item3.setName("JeringaSangre");
		item3.setQuantity(100L);
		testItems.add(item3); 
	}  
	  
	@AfterClass  
	public static void runAfterClass() {  
	    testItems.clear();
	}  
	
	@Before  
	public void runBeforeEveryTest() {
		for( Item item : testItems )
			itemService.save(item);
	}  
	  
	@After  
	public void runAfterEveryTest() {
		for( Item item : testItems) {
			try {
				itemService.delete(item);
			} catch( Exception e ) {
				//In case a delete test has deleted an item before
			}
		}
	}
	
	@Test
	public void TestGenericDAOSave() {
		assertEquals(itemService.findAll().size(), testItems.size());
	}

	@Test
	public void TestGenericDAODelete() {
		itemService.delete(testItems.get(itemIndexForTest));
		assertEquals(itemService.findAll().size(), testItems.size()-1);
	}
    
	@Test
	public void TestGenericDAOUpdate() {
		testItems.get(itemIndexForTest).setName(updateStringForTest);
		itemService.update(testItems.get(itemIndexForTest));
		assertEquals(itemService.findById(testItems.get(itemIndexForTest).getId()).getName(), updateStringForTest);
	}

	@Test
	public void TestGenericDAOFindById() {
		Item testItem = itemService.findById(testItems.get(itemIndexForTest).getId());
		assertEquals(testItem.getName(), testItems.get(itemIndexForTest).getName());
	}

	@Test
	public void TestGenericDAOFindAll() {
		assertEquals(itemService.findAll().size(), testItems.size());
	}

	@Test
	public void TestGenericDAODeleteById() {
		//itemService.deleteById(testItems.get(itemIndexForTest).getId());
		//assertEquals(itemService.findAll().size(), testItems.size()-1);
	}

	@Test
	public void TestGenericDAOCount() {
		assertEquals(itemService.count(), testItems.size());
	}

	@Test
	public void TestGenericDAOFindByExample() {
		Item testItem = new Item();
		testItem.setMeasureUnit(MeasureUnit.unit);
		
		int correctSize = 0;
		for( Item item : testItems )
			if( item.getMeasureUnit() == MeasureUnit.unit )
				correctSize++;
		
		assertEquals(itemService.findByExample(testItem).size(), correctSize);
	}
}
