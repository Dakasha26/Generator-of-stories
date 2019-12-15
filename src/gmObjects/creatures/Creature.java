/*
    23.11.2019
    -----
    Абстрактный класс, описывающий создание.
*/

package gmObjects.creatures;



public abstract class Creature implements Mob {
    protected String type; // Тип моба
    protected String name; // Имя моба
    
    @Override
    public void die() {
        System.out.println("Моб " + type + " умер");
    }

    @Override
    public String[] getSpecialActions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getLoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* Методы возврата значений */
    public String getType(){
        return type;
    }
    
    public String getName(){
        return name;
    }
}
