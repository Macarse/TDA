package com.tda.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.item.Item;
import com.tda.model.item.ItemBuilder;
import com.tda.persistence.dao.ItemDAO;
import com.tda.persistence.paginator.Paginator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/test-datasource.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PaginatorTest {

	@Autowired
	private ItemDAO itemDAO;
	private Paginator paginator;

	@Before
	public void setUp() throws Exception {
		Item item1 = ItemBuilder.createItem().withName("Item1").build();
		Item item2 = ItemBuilder.createItem().withName("Item2").build();
		Item item3 = ItemBuilder.createItem().withName("Item3").build();

		itemDAO.save(item1);
		itemDAO.save(item2);
		itemDAO.save(item3);

		paginator = new Paginator(1);
		itemDAO.findAllPaged(paginator);

		return;
	}

	@Test
	public void numberOfPagesShouldBeThree() {
		assertEquals(paginator.getPageCount(), 3);
	}

	@Test
	public void resultsPerPageShouldBeOne() {
		assertEquals(paginator.getResultsPerPage(), 1);
	}

	@Test
	public void initialIndexShouldBeOne() {
		assertEquals(paginator.getPageIndex(), 1);
	}

	@Test
	public void totalResultCountShouldBeThree() {
		assertEquals(paginator.getTotalResultsCount(), 3);
	}

	@Test
	public void invalidLastPage() {
		paginator.getPages();
		assertFalse(paginator.isLastPage());
	}

	@Test
	public void validLastPage() {
		paginator.setPageIndex(3);
		assertTrue(paginator.isLastPage());
	}

	@Test
	public void invalidFirstPage() {
		paginator.setPageIndex(2);
		assertFalse(paginator.isFirstPage());
	}

	@Test
	public void validFirstPage() {
		paginator.setPageIndex(1);
		assertTrue(paginator.isFirstPage());
	}

	@Test
	public void getPagesShouldBeOneTwoThree() {
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(1);
		integers.add(2);
		integers.add(3);
		assertEquals(paginator.getPages(), integers);
	}
}
