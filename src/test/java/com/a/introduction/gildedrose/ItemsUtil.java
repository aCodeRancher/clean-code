package com.a.introduction.gildedrose;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class ItemsUtil {
    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final int EXPIRED_SELLIN = -1;
    private static final int NON_EXPIRED_SELLIN = 15;
    private static final int QUALITY = 3;
    private static final int expectedNon_ExpiredSellin = NON_EXPIRED_SELLIN -1 ;
    private static final int expectedDecreaseby1Quantity = QUALITY - 1;
    private static final int expectedExpiredSellin = EXPIRED_SELLIN -1 ;
    private static final int expectedDecreaseby2Quantity = QUALITY - 2;

    public static Stream<Arguments> getItemsArgs(){
         return Stream.of(Arguments.of(NON_EXPIRED_SELLIN, QUALITY,expectedNon_ExpiredSellin,expectedDecreaseby1Quantity),
                          Arguments.of(EXPIRED_SELLIN,QUALITY, expectedExpiredSellin, expectedDecreaseby2Quantity));
    }
}
