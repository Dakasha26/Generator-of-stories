/*
    25.11.2019  
    -----
    Класс, описывающий дворфа-разбойника
 */

package gmObjects.characters;



public class DwarfRogue extends Hero{
    
    /* Конструктор класса */
    public DwarfRogue(String name){
        this.name = name;
        this.job = "разбойник";
        this.race = "дворф";
        setWeapon();
        setFood();
    }

    @Override
    public void doSpecialAction() {
        System.out.println(name + " начал стрелять в воздух из " + weapon);
    }

}
