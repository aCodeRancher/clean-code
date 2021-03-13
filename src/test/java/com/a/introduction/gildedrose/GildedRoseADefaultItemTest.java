package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

	private  int sellin;
	private  int quantity;
	private  final String DEFAULT_ITEM = "DEFAULT_ITEM";


	@Test
	public void testUpdateQualityDefault1() {
		setSellin(15);
		setQuantity(3);
		GildedRose app = createGildedRose(getSellin(),getQuantity());
		app.updateQuality();

		int expectedSellin = 14;
		int expectedQuantity = 2;
		verifyItem(app, expectedSellin,expectedQuantity);
	}


	@Test
	public void testUpdateQualityForExpiredItem() {
		setSellin(-1);
		setQuantity(3);
        GildedRose app = createGildedRose(getSellin(),getQuantity());
        app.updateQuality();

		int expectedSellin = -2;
		int expectedQuantity = 1;
		verifyItem(app, expectedSellin, expectedQuantity);
	}

	private GildedRose createGildedRose(int sellin, int quantity){
		Item item = new Item(DEFAULT_ITEM, sellin, quantity);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}
	private void setSellin(int value){
		sellin =  value;
	}
	private int getSellin(){
		return sellin;
	}

	private void setQuantity(int value){
		quantity =  value;
	}
	private int getQuantity(){
		return quantity;
	}

	private void verifyItem(GildedRose app, int expectedSellin, int expectedQuantity){
		assertEquals(DEFAULT_ITEM, app.items[0].name);
		assertEquals(expectedSellin, app.items[0].sellIn);
		assertEquals(expectedQuantity, app.items[0].quality);
	}
}