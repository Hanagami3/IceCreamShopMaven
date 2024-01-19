package be.intecbrussel.application;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.Eatable;
import be.intecbrussel.eatables.Magnum;
import be.intecbrussel.sellers.*;

import java.util.ArrayList;
import java.util.Arrays;

public class IceCreamApp2 {

    public static void main(String[] args) {
//      -- Stock aan het begin
        /*iceRockets = 2;
        cones = 3;
        balls =7;
        magni = 5;*/

        PriceList priceList = new PriceList();
        Stock stock = new Stock();
        IceCreamSeller iceCreamSeller = new IceCreamCar(priceList, stock);

        ArrayList<Eatable> orders = new ArrayList<>();

        orders.add(iceCreamSeller.orderIceRocket());
        orders.add(iceCreamSeller.orderIceRocket());
        orders.add(iceCreamSeller.orderIceRocket());

        orders.add(iceCreamSeller.orderCone(Arrays.stream(Cone.Flavor.values())
                .filter(f -> f.equals(Cone.Flavor.PISTACHE) || f.equals(Cone.Flavor.STRACIATELLA))
                .toArray(Cone.Flavor[]::new)));
        orders.add(iceCreamSeller.orderCone(Arrays.stream(Cone.Flavor.values())
                .filter(f -> f.equals(Cone.Flavor.BANANA) || f.equals(Cone.Flavor.CHOCOLATE) || f.equals((Cone.Flavor.LEMON)))
                .toArray(Cone.Flavor[]::new)));
        orders.add(iceCreamSeller.orderCone(Arrays.stream(Cone.Flavor.values())
                .filter(f -> f.equals(Cone.Flavor.CHOCOLATE) || f.equals(Cone.Flavor.VANILLA))
                .toArray(Cone.Flavor[]::new)));

        orders.add(iceCreamSeller.orderMagnum(Magnum.MagnumType.ROMANTICSTRAWBERRIES));
        orders.add(iceCreamSeller.orderMagnum(Magnum.MagnumType.BLACKCHOCOLATE));

        System.out.println();
        System.out.println("De bestellingen die de klanten heb gegeten");
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i) != null) {
                System.out.print((i+1) + ". ");
                orders.get(i).eat();
            }
        }
        System.out.println();
        System.out.println("Je heeft " + orders.size() +" bestellingen gehad met een voordeel van " + iceCreamSeller.getProfit() + " euros");

    }
}
