/*
    24.11.2019
    -----
    Класс, описывающий поселение
 */

package gmObjects.spots;



public class Settlement extends Spot{
    
    public Settlement(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.type = "бивуак";
    }
}
