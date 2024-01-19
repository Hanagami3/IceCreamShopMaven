package be.intecbrussel.sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;

public class IceCreamSalon implements IceCreamSeller{

    private PriceList priceList;
    private double totalProfit;

    public IceCreamSalon(PriceList priceList){
        this.priceList = priceList;
    }


    @Override
    public double getProfit() {

        return Math.round(totalProfit*100.0)/100.0;
    }

    @Override
    public Cone orderCone(Cone.Flavor[] balls) {
        Cone cone = new Cone(balls);
        if (balls != null) {
            for (int i = 0; i < balls.length; i++) totalProfit += priceList.getBallPrice() * 0.25;
        }
        return cone;
    }

    @Override
    public IceRocket orderIceRocket() {
        IceRocket iceRocket = new IceRocket();
        totalProfit += priceList.getRocketPrice() * 0.2;
        return iceRocket;
    }

    @Override
    public Magnum orderMagnum(Magnum.MagnumType type) {
        Magnum magnum = new Magnum(type);
        totalProfit += priceList.getMagnumPrice(type) * 0.01;
        return magnum;
    }

    @Override
    public String toString() {
        return "IceCreamSalon{" +
                "priceList=" + priceList +
                ", totalProfit=" + totalProfit +
                '}';
    }
}
