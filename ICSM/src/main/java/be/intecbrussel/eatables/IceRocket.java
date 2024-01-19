package be.intecbrussel.eatables;

public class IceRocket implements Eatable{

    public IceRocket(){

    }

    @Override
    public void eat() {
        System.out.println("Een klant heeft een raketijsje gegeten");
    }
}
