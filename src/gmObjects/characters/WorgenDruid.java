/*
    25.11.2019
    -----
    Класс, описывающий воргена-друида
 */

package gmObjects.characters;


public class WorgenDruid extends Hero {
    
    
    /* Конструктор класса */
    public WorgenDruid(String name){
        this.name = name;
        this.job = "друид";
        this.race = "ворген";
        setWeapon();
        setFood();
}

    @Override
    public void doSpecialAction() {
        System.out.println(name + " нашёл где-то здесь кролика, а теперь кормит его с рук");
    }
}
