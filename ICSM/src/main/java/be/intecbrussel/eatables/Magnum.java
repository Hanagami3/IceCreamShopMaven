package be.intecbrussel.eatables;

public class Magnum implements Eatable {

    public enum MagnumType {

        MILKCHOCOLATE,
        WHITECHOCOLATE,
        BLACKCHOCOLATE,
        ALPINENUTS,
        ROMANTICSTRAWBERRIES;

    }

    private MagnumType type;

    public Magnum() {

    }

    public Magnum(MagnumType type){
        this.type = type;
    }

    public MagnumType getType() {
        return type;
    }

    @Override
    public void eat() {
        System.out.println("Een klant heeft een " + type.toString().toLowerCase() + " magnum gegeten");
    }

}
