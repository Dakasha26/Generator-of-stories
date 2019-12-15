/*
    23.11.2019
    ------
    Интерфейс, описывющий способность к речевой деятельности.
 */

package gmObjects.characters;


public interface Talkable {
    
    /* Сказать обычную фразу */
    public void say(String phrase);
    
    /* Подумать что-то про себя */
    public void think(String phrase);
    
}
