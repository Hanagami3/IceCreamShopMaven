package be.intecbrussel.sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;

import java.util.Optional;
import java.util.stream.Collectors;

public class IceCreamCar implements IceCreamSeller {

    private PriceList priceList;
    private Stock stock;
    private double profit;

    public IceCreamCar(PriceList priceList, Stock stock) {
        this.priceList = priceList;
        this.stock = stock;
    }


    @Override
    public Cone orderCone(Cone.Flavor[] balls) {

        Cone cone = prepareCone(balls);

        if (cone != null) {
            for (int i = 0; i < balls.length; i++) {
                profit += priceList.getBallPrice() * 0.25;
            }
        }
        return cone;
    }

    private Cone prepareCone(Cone.Flavor[] balls) {

        if (balls != null) {
            if (stock.getCones() > 0 && stock.getBalls() >= balls.length) {
                Cone cone = new Cone(balls);
                stock.setCones(stock.getCones() - 1);
                stock.setBalls(stock.getBalls() - balls.length);
                return cone;
            }
        }
        System.out.println("NO MORE ICECREAM");
        return null;
    }


    @Override
    public IceRocket orderIceRocket() {

        IceRocket iceRocket = prepareIceRocket();

        if (iceRocket != null) {
            profit += priceList.getRocketPrice() * 0.20;
        }
        return iceRocket;
    }
    private IceRocket prepareIceRocket(){

        if (stock.getIceRockets() > 0) {
            IceRocket iceRocket = new IceRocket();
            stock.setIceRockets(stock.getIceRockets() - 1);
            return iceRocket;
        }

        System.out.println("NO MORE ICECREAM");
        return null;

        /*
        Stock stock = this.stock;
        IceRocket iceRocket ;
        if (stock.getIceRockets() > -1) {
            iceRocket = new IceRocket();
            return iceRocket;
        }
        else {
            System.out.println("NO MORE ICE ROCKET");
            return null;
        }
         */
    }

    @Override
    public Magnum orderMagnum(Magnum.MagnumType type) {

        if (prepareMagnum(type) != null) {
            if (stock.getMagni() > 0)
                profit += priceList.getMagnumPrice(type) * 0.01;
            stock.setMagni(stock.getMagni()-1);
            return prepareMagnum(type);
        }
        else
            return null;
    }
    private Magnum prepareMagnum(Magnum.MagnumType type){
        Stock stock = this.stock;
        Magnum magnum ;
        if (stock.getMagni() > -1) {

            magnum = new Magnum(type);
            return magnum;
        }
        else {
            System.out.println("NO MORE MAGNUM");
            return null;
        }
    }

    @Override
    public double getProfit() {
        return profit = Math.round(profit*100.0)/100.0;
    }
}
