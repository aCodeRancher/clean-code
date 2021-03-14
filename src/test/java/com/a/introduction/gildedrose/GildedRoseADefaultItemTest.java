package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

	private  int sellin;
	private  int quality;
	private  final String DEFAULT_ITEM = "DEFAULT_ITEM";
	private final int EXPIRED_SELLIN = -1;
	private final int NON_EXPIRED_SELLIN = 15;
	private final int QUALITY = 3;

	@Test
	public void defaulItem_nonExpiredSellin_quantity_decreaseBy1() {
		setSellin(NON_EXPIRED_SELLIN);
		setQuality(QUALITY);
		GildedRose app = createGildedRose(getSellin(),getQuality());
		app.updateQuality();

		int expectedSellin = NON_EXPIRED_SELLIN -1 ;
		int expectedQuantity = QUALITY - 1;
		verifyItem(app, expectedSellin,expectedQuantity);
	}


	@Test
	public void defaultItem_expiredSellin_quantity_decreaseBy2() {
		setSellin(EXPIRED_SELLIN);
		setQuality(QUALITY);
        GildedRose app = createGildedRose(getSellin(),getQuality());
        app.updateQuality();

		int expectedSellin = EXPIRED_SELLIN -1 ;
		int expectedQuantity = QUALITY - 2;
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

	private void setQuality(int value){
		quality =  value;
	}
	private int getQuality(){
		return quality;
	}

	private void verifyItem(GildedRose app, int expectedSellin, int expectedQuantity){
		assertEquals(DEFAULT_ITEM, app.items[0].name);
		assertEquals(expectedSellin, app.items[0].sellIn);
		assertEquals(expectedQuantity, app.items[0].quality);
	}
}