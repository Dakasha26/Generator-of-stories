/*
    23.11.2019
    -----
    Абстрактный класс, описывающий место действий.
 */

package gmObjects.spots;


public abstract class Spot implements Destroyable {
    protected String name; // Название места
    protected int capacity; // Вместительность места
    protected String type; // Тип места (поселение, бивуак)
    

    
    @Override
    public void destroyYourself() {
        System.out.println("Локация " + name + " была уничтожена");
    }
    
    /* Методы возврата */
    public String getName(){
        return name;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public String getType(){
        return type;
    }
}
