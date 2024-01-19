package be.intecbrussel.Sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;
import be.intecbrussel.sellers.IceCreamSalon;
import be.intecbrussel.sellers.IceCreamSeller;
import be.intecbrussel.sellers.PriceList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class IceCreamSalonTest {

    private final PriceList priceList;
    private IceCreamSeller iceCreamSalon;

    {
        this.priceList = new PriceList();
        this.iceCreamSalon = new IceCreamSalon(priceList);
    }


    @ParameterizedTest
    @MethodSource("orderIceRocketFactory")
    public void orderIceRocketTest(int iceCount, double expectedPrice) {
        for (int i = 0; i < iceCount; i++) {
            iceCreamSalon.orderIceRocket();
        }
        double result = iceCreamSalon.getProfit();
        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderIceRocketFactory() {
        //price: 2.99 profit: 0.2
        return Stream.of(
                Arguments.of(2, 1.2),
                Arguments.of(3, 1.79),
                Arguments.of(0, 0),
                Arguments.of(-8, 0),
                Arguments.of(-999999999, 0),
                Arguments.of(100, 59.8),
                Arguments.of(1000_000, 598_000)

        );
    }

    @ParameterizedTest
    @MethodSource("orderMagnumFactory")
    public void orderMagnumTest(int basicFlavorCount, int alpinenutsCount, int strawberriesCount, double expectedPrice) {
        //basePrice 3.25 profit: 0.01
        //price
        for (int i = 0; i < basicFlavorCount; i++) iceCreamSalon.orderMagnum(Magnum.MagnumType.BLACKCHOCOLATE);         //*1.3
        for (int i = 0; i < alpinenutsCount; i++)  iceCreamSalon.orderMagnum(Magnum.MagnumType.ALPINENUTS);              //*1.5
        for (int i = 0; i < strawberriesCount; i++)iceCreamSalon.orderMagnum(Magnum.MagnumType.ROMANTICSTRAWBERRIES);   //*2

        double result = iceCreamSalon.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderMagnumFactory() {
        return Stream.of(
                Arguments.of(0, 0, 1, 0.07), //0,845
                Arguments.of(-1, 1, 1, 0.11), //0,24375
                Arguments.of(20, 5, 37, 3.49),
                Arguments.of(10, 10, 10, 1.56),
                Arguments.of(-5, -69, -963, 0),
                Arguments.of(0, 0, 0, 0),
                Arguments.of(-10000, -1000000, 1000, 65),
                Arguments.of(1000_000, 1000_000, 1000_000, 156_000)
        );
    }

    @ParameterizedTest
    @MethodSource("orderConeFactory")
    public void orderConeTest(int ballsCount, double expectedPrice) {
        //price: 2.5 profit: 0.25

        for (int i = 0; i < ballsCount; i++) iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.STRAWBERRY});

        double result = iceCreamSalon.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderConeFactory() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 0.63),
                Arguments.of(8, 5),
                Arguments.of(-999999999, 0),
                Arguments.of(1000_000, 625000)

        );
    }

    @ParameterizedTest
    @MethodSource("ordersFactory")
    public void ordersTest(Cone.Flavor[] balls, Magnum.MagnumType magnums, IceRocket iceRockets, double expectedPrice) {

        iceCreamSalon.orderCone(balls);
        iceCreamSalon.orderMagnum(magnums);
        if (iceRockets != null) iceCreamSalon.orderIceRocket();

        double result = iceCreamSalon.getProfit();
        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> ordersFactory() {
        return Stream.of(
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.PISTACHE}, Magnum.MagnumType.ROMANTICSTRAWBERRIES, null, 0.69),
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.STRACIATELLA}, null, null, 0.63),
                Arguments.of(new Cone.Flavor[0], null, null, 0),
                Arguments.of(new Cone.Flavor[1000], null, null, 625),
                Arguments.of(null, null, null, 0),
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.BANANA, Cone.Flavor.LEMON}, null, new IceRocket(), 1.85),
                Arguments.of(null, null, new IceRocket(), 0.6),
                Arguments.of(null, Magnum.MagnumType.MILKCHOCOLATE, null, 0.04),
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, Magnum.MagnumType.ROMANTICSTRAWBERRIES, new IceRocket(), 1.29),
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.VANILLA, Cone.Flavor.MOKKA}, Magnum.MagnumType.ALPINENUTS, new IceRocket(), 1.9)

        );

    }
}







    /*
    @ParameterizedTest
    @MethodSource("orderConeFactory")
    public void orderConeTest( int strawberryCount, int bananaCount, int chocolateCount,
                               int vanillaCount, int lemonCount, int straciatellaCount,
                               int mokkaCount, int pistacheCount, double expectedPrice){
        //price: 2.5 profit: 0.25

        List<Eatable> orders = new ArrayList<>();
        for (int i = 0; i < strawberryCount; i++)   orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.STRAWBERRY}));
        for (int i = 0; i < bananaCount; i++)       orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.BANANA}));
        for (int i = 0; i < chocolateCount; i++)    orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}));
        for (int i = 0; i < vanillaCount; i++)      orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.VANILLA}));
        for (int i = 0; i < lemonCount; i++)        orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.LEMON}));
        for (int i = 0; i < straciatellaCount; i++) orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.STRACIATELLA}));
        for (int i = 0; i < mokkaCount; i++)        orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.MOKKA}));
        for (int i = 0; i < pistacheCount; i++)     orders.add(iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.PISTACHE}));


        double result = iceCreamSalon.getProfit();

        Assertions.assertEquals(expectedPrice, result);
    }

    public static Stream<Arguments> orderConeFactory() {
        return Stream.of(
                Arguments.of( 0,0,0,0,0,0,0,0,0),
                Arguments.of( 1,0,0,0,0,0,0,0,0.63),
                Arguments.of(1,1,1,1,1,1,1,1,5)
        );
    }*/

