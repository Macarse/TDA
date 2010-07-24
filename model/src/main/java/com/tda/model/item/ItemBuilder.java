package com.tda.model.item;


public class ItemBuilder {

	private String name;
	private String description;
	private Long quantity;
	private Category category;
	private MeasureUnit measureUnit;

	private ItemBuilder() {
	}

	public static ItemBuilder createItem() {
		return new ItemBuilder();
	}

	public Item build() {
		return new Item(name, description, quantity, category, measureUnit);
	}

	public ItemBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ItemBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public ItemBuilder withQuantity(Long quantity) {
		this.quantity = quantity;
		return this;
	}

	public ItemBuilder withCategory(Category category) {
		this.category = category;
		return this;
	}

	public ItemBuilder withAccountNonExpired(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
		return this;
	}
}
