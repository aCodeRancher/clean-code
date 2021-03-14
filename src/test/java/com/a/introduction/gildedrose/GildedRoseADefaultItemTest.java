package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GildedRoseADefaultItemTest {

	private  final String DEFAULT_ITEM = "DEFAULT_ITEM";


	@ParameterizedTest
	@MethodSource("com.a.introduction.gildedrose.ItemsUtil#getItemsArgs")
	public void defaulItem_nonExpiredSellin_quantity_decreaseBy1(int nonExpiredSellin, int quality,
																 int expected_sellin, int expectedQuality) {

		GildedRose app = createGildedRose(nonExpiredSellin,quality);
		app.updateQuality();
        verifyItem(app, expected_sellin, expectedQuality);
	}


	@ParameterizedTest
	@MethodSource("com.a.introduction.gildedrose.ItemsUtil#getItemsArgs")
	public void defaultItem_expiredSellin_quantity_decreaseBy2(int expiredSellin, int quality,
															    int expected_sellin, int expectedQuality) {

        GildedRose app = createGildedRose(expiredSellin,quality);
        app.updateQuality();
        verifyItem(app, expected_sellin, expectedQuality);
	}

	private GildedRose createGildedRose(int sellin, int quantity){
		Item item = new Item(DEFAULT_ITEM, sellin, quantity);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}


	private void verifyItem(GildedRose app, int expectedSellin, int expectedQuantity){
		assertEquals(DEFAULT_ITEM, app.items[0].name);
		assertEquals(expectedSellin, app.items[0].sellIn);
		assertEquals(expectedQuantity, app.items[0].quality);
	}
}