/*
    23.11.2019
    -----
    Класс, описывающий конфигурацию.
*/

package utils;

import gmObjects.characters.Hero;
import gmObjects.creatures.Creature;
import gmObjects.spots.Spot;


public class Config {
    private String title; // Название конфигурации
    private String template; // Шаблон истории
    private Hero[] characters; // Действующие персонажи
    private Spot[] place; // Место действия
    private Creature[] mobs; // Третьестепенные персонажи
    
    /* Конструктор для создания сценария */
    public Config(String title, String template, Hero[] characters, Spot[] place, Creature[] mobs){
        this.title = title;
        this.template = template;
        this.characters = characters;
        this.place = place;
        this.mobs = mobs;
    }

    public Config() {
    }

    /* Метод возврата мобов */
    public Creature[] getMobs(){
        return mobs;
    }
    
    /* Метод возврата локаций */
    public Spot[] getSpots(){
        return place;
    }
    
    /* Метод возврата персонажей */
    public Hero[] getCharacters(){
        return characters;
    }
    
    /* Метод возврата шаблона */
    public String getTemplate(){
        return template;
    }
    
    /* Метод вовзврата названия конфигурации */
    public String getTitle(){
        return title;
    }
}
