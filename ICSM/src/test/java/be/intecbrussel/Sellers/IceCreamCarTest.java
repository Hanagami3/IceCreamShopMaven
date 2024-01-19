package be.intecbrussel.Sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.Eatable;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;
import be.intecbrussel.sellers.IceCreamCar;
import be.intecbrussel.sellers.IceCreamSeller;
import be.intecbrussel.sellers.PriceList;
import be.intecbrussel.sellers.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IceCreamCarTest {

    private final PriceList priceList;
    private final Stock stock;
    private IceCreamSeller iceCreamCar;

    {
        priceList = new PriceList();
        stock = new Stock();
        iceCreamCar = new IceCreamCar(priceList, stock);
    }

    @ParameterizedTest
    @MethodSource("orderIceRocketFactory")
    public void orderIceRocketTest(int iceCount, int iceRocketStock, double expectedPrice){

        stock.setIceRockets(iceRocketStock);

        for (int i = 0; i < iceCount; i++) iceCreamCar.orderIceRocket();

        double result = iceCreamCar.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderIceRocketFactory(){
        return Stream.of(
                Arguments.of(3, 5, 1.79),
                Arguments.of(3, 1, 0.6),
                Arguments.of(0, 0, 0),
                Arguments.of(1000_000, 1000_000, 598000),
                Arguments.of(-1,2,0),
                Arguments.of(2,-1,0)


        );
    }

    @ParameterizedTest
    @MethodSource("orderMagnumFactory")
    public void orderMagnumTest(int magnumStock, int basicFlavorCount, int alpinenutsCount, int strawberriesCount, double expectedPrice){

        stock.setMagni(magnumStock);

        for (int i = 0; i < basicFlavorCount; i++) iceCreamCar.orderMagnum(Magnum.MagnumType.BLACKCHOCOLATE);
        for (int i = 0; i < alpinenutsCount; i++) iceCreamCar.orderMagnum(Magnum.MagnumType.ALPINENUTS);
        for (int i = 0; i < strawberriesCount; i++)iceCreamCar.orderMagnum(Magnum.MagnumType.ROMANTICSTRAWBERRIES);

        double result = iceCreamCar.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderMagnumFactory() {
        return Stream.of(
                Arguments.of(1, 0, 0, 1, 0.07),
                Arguments.of(1, -1, 1, 1, 0.05),
                Arguments.of(3, 1, 1, -1, 0.09),
                Arguments.of(5, 1, 1, 1, 0.16),
                Arguments.of(29, 10, 10, 10, 1.49),
                Arguments.of(10_000_000, 1000_000, 1000_000, 1000_000, 156_000),
                Arguments.of(-10000,1, 0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("orderConeFactory")
    public void orderConeTest(int conesStock, int ballsStock,
                              int ConeCount, Cone.Flavor[] balls,
                              double expectedPrice){

        stock.setCones(conesStock);
        stock.setBalls(ballsStock);
        for (int i = 0; i < ConeCount; i++ ) iceCreamCar.orderCone(balls);

        double result = iceCreamCar.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderConeFactory(){
        return Stream.of(
                Arguments.of(1, 3, 10, new Cone.Flavor[]{Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY}, 1.25 ),
                Arguments.of(1, 1, 1, new Cone.Flavor[]{Cone.Flavor.STRAWBERRY, Cone.Flavor.STRAWBERRY}, 0),
                Arguments.of(1, 1, 1, new Cone.Flavor[]{Cone.Flavor.STRAWBERRY}, 0.63),
                Arguments.of(0, 5, 1, new Cone.Flavor[]{Cone.Flavor.MOKKA, Cone.Flavor.LEMON, Cone.Flavor.BANANA}, 0),
                Arguments.of(5, 0, 1, new Cone.Flavor[]{Cone.Flavor.MOKKA, Cone.Flavor.LEMON, Cone.Flavor.BANANA}, 0),
                Arguments.of(5, 3, 3,  new Cone.Flavor[4], 0),
                Arguments.of(10, 5, 1,  new Cone.Flavor[4], 2.5),
                Arguments.of(0, 0, 10, new Cone.Flavor[]{Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY}, 0),
                Arguments.of(1, 2, 98, null, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("orderFactory")
    public void orderTest(int conesStock, int ballsStock, int magnumStock, int iceRocketStock,
                          Cone.Flavor[] balls,
                          Magnum.MagnumType magnum,
                          IceRocket iceRockets,
                          double expectedPrice) {

        stock.setCones(conesStock);
        stock.setBalls(ballsStock);
        stock.setMagni(magnumStock);
        stock.setIceRockets(iceRocketStock);

        iceCreamCar.orderCone(balls);
        iceCreamCar.orderMagnum(magnum);
        if (iceRockets instanceof IceRocket) iceCreamCar.orderIceRocket();

        double result = iceCreamCar.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderFactory(){
        return Stream.of(
                Arguments.of(1, 2, 0, 2,
                        new Cone.Flavor[]{Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY},
                        null,
                        new IceRocket(),
                        1.85),
                Arguments.of(10, 20, 10, 30,
                        null,
                        null,
                        null,
                        0),
                Arguments.of(100, 100, 100, 100,
                        null,
                        Magnum.MagnumType.BLACKCHOCOLATE,
                        null,
                        0.04),
                Arguments.of(0, 0, 0, 0,
                        new Cone.Flavor[]{Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY},
                        null,
                        null,
                        0),
                Arguments.of(10, 5, -3, 10,
                        new Cone.Flavor[5],
                        Magnum.MagnumType.ROMANTICSTRAWBERRIES,
                        new IceRocket(),
                        3.72)
        );
    }


    /*
    @ParameterizedTest
    @MethodSource("orderConeFactory")
    public void orderConeTest(int conesStock, int ballsStock, int strawberryCount, int bananaCount,
                              int chocolateCount, int vanillaCount, int lemonCount, int straciatellaCount,
                              int mokkaCount, int pistacheCount, double expectedPrice){

        stock.setCones(conesStock);
        stock.setBalls(ballsStock);

        List<Eatable> orders = new ArrayList<>();
        for (int i = 0; i < strawberryCount; i++)   orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.STRAWBERRY}));
        for (int i = 0; i < bananaCount; i++)       orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.BANANA}));
        for (int i = 0; i < chocolateCount; i++)    orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}));
        for (int i = 0; i < vanillaCount; i++)      orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.VANILLA}));
        for (int i = 0; i < lemonCount; i++)        orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.LEMON}));
        for (int i = 0; i < straciatellaCount; i++) orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.STRACIATELLA}));
        for (int i = 0; i < mokkaCount; i++)        orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.MOKKA}));
        for (int i = 0; i < pistacheCount; i++)     orders.add(iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.PISTACHE}));


        double result = iceCreamCar.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderConeFactory() {
        return Stream.of(
                Arguments.of(0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0,
                        0),
                Arguments.of(1, 0,
                        0, 0, 0, 0, 1, 0, 0, 0,
                        0),
                Arguments.of(0, 1,
                        0, 0, 0, 0, 1, 0, 0, 0,
                        0),
                Arguments.of(1, 1,
                        0, 0, 0, 0, 1, 0, 0, 0,
                        0.63),
                Arguments.of(1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1,
                        0.63),
                Arguments.of(10, 10,
                        1, 1, 1, 1, 1, 1, 1, 1,
                        5)
        );
    }
     */
}
