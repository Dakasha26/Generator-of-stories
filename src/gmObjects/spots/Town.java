/*
    25.11.2019
    -----
    Класс, описывающий город.
 */

package gmObjects.spots;


public class Town extends Spot{
    
    public Town(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.type = "город";
    }
}
