/*
    25.11.2019
    -----
    Класс, описывающий воргена-разбойника.
 */

package gmObjects.characters;



public class WorgenRogue extends Hero {
    
    /* Конструктор класса */
    public WorgenRogue(String name){
        this.name = name;
        this.job = "разбойник";
        this.race = "ворген";
        setWeapon();
        setFood();
    }

    @Override
    public void doSpecialAction() {
        System.out.println(name + " потерял кошелёк");
    }
}
