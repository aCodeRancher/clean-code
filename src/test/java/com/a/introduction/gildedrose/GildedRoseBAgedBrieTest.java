package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GildedRoseBAgedBrieTest {

	private final String ITEM_NAME = "Aged Brie";

	@ParameterizedTest
	@MethodSource("com.a.introduction.gildedrose.ItemsUtil#getItemsArgs")
	public void  testAgedBrie(int sellin, int quality, int expectedSellin, int expectedQuality) {

		GildedRose app = createGildedRose(sellin,quality);
		app.updateQuality();
		verifyItem(app,expectedSellin, expectedQuality);
	}



	private GildedRose createGildedRose(int sellin , int quality){
		Item item = new Item(ITEM_NAME, sellin, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}

	private void verifyItem(GildedRose app, int expectedSellin , int expectedQuality){
		assertEquals(ITEM_NAME, app.items[0].name);
		assertEquals(expectedSellin, app.items[0].sellIn);
		assertEquals(expectedQuality, app.items[0].quality);
	}
}
