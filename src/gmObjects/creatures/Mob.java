/*
    23.11.2019
    -----
    Интерфейс моба.
 */

package gmObjects.creatures;



public interface Mob {
    
    /* Смерть моба */
    public void die();
    
    /* Выдача списка специальных действий */
    public String[] getSpecialActions();
    
    /* Выдача списка падающего лута */
    public String[] getLoot();
        
}
