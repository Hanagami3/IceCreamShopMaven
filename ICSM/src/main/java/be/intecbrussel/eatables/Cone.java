package be.intecbrussel.eatables;

public class Cone implements Eatable {

    public enum Flavor {

        STRAWBERRY,
        BANANA,
        CHOCOLATE,
        VANILLA,
        LEMON,
        STRACIATELLA,
        MOKKA,
        PISTACHE;
    }

    public Flavor[] balls;

    public Cone(){
        this.balls = Flavor.values();

    }

    public Cone(Flavor[] balls){
        this.balls = balls;

    }
    @Override
    public void eat() {
        System.out.println("Een klant heeft een hoorntje gegeten met " + balls.length + " ijsballen met de smaken van: ");
        for (Flavor ball : balls)
            System.out.println("  - " +ball.toString().toLowerCase());
    }

}
