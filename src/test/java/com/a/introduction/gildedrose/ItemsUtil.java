package com.a.introduction.gildedrose;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class ItemsUtil {


    private static final int EXPIRED_SELLIN = -1;
    private static final int NON_EXPIRED_SELLIN = 4;
    private static final int QUALITY = 3;
    private static final int expectedNon_ExpiredSellin = NON_EXPIRED_SELLIN -1 ;
    private static final int expectedIncreaseby1Quality = QUALITY + 1;

    private static final int expectedExpiredSellin = EXPIRED_SELLIN -1 ;
    private static final int expectedIncreaseby2Quality = QUALITY + 2;
    private static final int QUALITY_IS_50 = 50;

    public static Stream<Arguments> getItemsArgs(){
        return Stream.of(Arguments.of(NON_EXPIRED_SELLIN, QUALITY,expectedNon_ExpiredSellin,expectedIncreaseby1Quality),
                Arguments.of(EXPIRED_SELLIN,QUALITY, expectedExpiredSellin, expectedIncreaseby2Quality),
                Arguments.of(NON_EXPIRED_SELLIN,QUALITY_IS_50, expectedNon_ExpiredSellin, QUALITY_IS_50));
    }
}
