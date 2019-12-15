/*
    23.11.2019
    -----
    Абстрактный класс, описывающий персонажа истории.
 */

package gmObjects.characters;

import gmObjects.creatures.Creature;

public abstract class Hero implements Talkable{
    protected String name; // Имя персонажа
    protected String race; // Раса персонажа
    protected String job; // Класс персонажа
    protected String weapon; // Оружие персонажа
    protected String food; // Любимая пища 
    
    
    @Override
    public void say(String phrase) {
        System.out.println(name + ": " + phrase);
    }

    @Override
    public void think(String phrase) {
        System.out.println(name + ": ~~" + phrase + "~~");
    }

    /* Метод, реализующий специальное действие персонажа */
    public abstract void doSpecialAction();
    
    /* Метод, реализующий убийство, которое совершает персонаж */
    public void kill(Creature aim){
        aim.die();
    }
    
    /* Метод, задающий оружие */
    protected void setWeapon(){
        if(race == "дфорф" && job == "разбойник")
            this.weapon = "короткие кинжалы";
        else if(race == "ворген" && job == "разбойник")
            this.weapon = "длинные кинжалы";
        else if(race == "ворген" && job == "друид")
            this.weapon = "обличье зверя";
        else
            this.weapon = "мушкет";
    }
    
    protected void setFood(){
        if(race == "ворген")
            this.food = "мясной стейк";
        else if(race == "дворф")
            this.food = "пинта пива";
    }
    
    /* Методы возврата значений */
    public String getName(){
        return name;
    }
    
    public String getRace(){
        return race;
    }
    
    public String getJob(){
        return job;
    }
    
    public String getWeapon(){
        return weapon;
    }
    
    public String getFood(){
        return food;
    }
}
