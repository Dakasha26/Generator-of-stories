/*
    23.11.2019
    -----
    Класс, описывающий меню.
 */


import gmObjects.characters.DwarfRogue;
import gmObjects.characters.Hero;
import gmObjects.characters.WorgenDruid;
import gmObjects.characters.WorgenRogue;
import gmObjects.creatures.Creature;
import gmObjects.creatures.Murloc;
import gmObjects.creatures.Villain;
import gmObjects.spots.Settlement;
import gmObjects.spots.Spot;
import gmObjects.spots.Town;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.parser.ParseException;
import utils.Config;
import utils.Explorer;
import utils.TemplateParser;

public class Menu {
    
    /* Запуск меню игры */
    public static void start(){
        System.out.println("Добро пожаловать в программу, генерирующую истории по Вселенной WoW");
        do{
            try{
                System.out.println("Вы находитесь в главном меню. Вам доступны следующие действия:");
                System.out.println("\t1. Выбрать сценарий");
                System.out.println("\t2. Создать собственный сценарий (не рекомендуется к использованию обычными пользователями)");
                System.out.println("\t3. Завершить работу программы");
                System.out.print(">> ");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                int num = Integer.parseInt(in.readLine());
                switch(num){
                    case(1):
                        selectConfig();
                        break;
                    case(2):
                        setConfig();
                        break;
                    case(3):
                        System.out.println("Работы программы завершена");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введите цифру, соответствующую полходящему Вам варианту");
                        break;
                }
            } catch(IOException | NumberFormatException e){
                System.out.println("Поддерживаются только цифры");
            }
        } while(true);
    }
    
    /* Метод выбора сценария из найденных */
    private static void selectConfig() {
        int num; // Пользовательский ввод
        Config config = null;
        
        do{
            System.out.println("Программа автоматически просмотрела конфигурации в папке configs корневой директории проекта и нашла там следующие сценарии: ");
            if(Explorer.searchConfigsNames().length == 0){
                System.out.println("В папке configs не найдено ни одной конфигруации");
                System.out.println("Поместите в configs какую-нибудь конфигурацию и запустите программу снова");
                System.exit(0);
            } else{
                for(int i = 0; i < Explorer.searchConfigsNames().length; i++)
                    System.out.println("\t" + (i+1) + ". " + Explorer.searchConfigsNames()[i]);
                System.out.println("Выберите сценарий: ");

                do{
                    try{
                        System.out.print(">> ");
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        num = Integer.parseInt(in.readLine());
                        if(num > 0 && num < (Explorer.searchConfigsNames().length + 1))
                            break;
                        else
                            System.out.println("Введите число, соответствующее найденному сценарию");
                    } catch(NumberFormatException | IOException e){
                        System.out.println("Поддерживаются только цифры");
                    }
                } while(true);

                try{
                    config = Explorer.openConfig(num);
                } catch(IOException | ParseException | NullPointerException e){
                    System.out.println("Не удалось открыть конфигурацию. Возможно, файл повреждён. Попробуйте выбрать другой сценарий");
                }
            }
        } while(config == null);
        runScenario(config); 
    }
    
    /* Метод создания конфигурации */
    private static void setConfig(){
        Hero[] characters = new Hero[3];
        DwarfRogue dwarf1 = new DwarfRogue("Кевин");
        WorgenRogue worgen1 = new WorgenRogue("Стивен");
        WorgenDruid worgen2 = new WorgenDruid("Джон");
        characters[0] = dwarf1;
        characters[1] = worgen1;
        characters[2] = worgen2;
        Settlement spot1 = new Settlement("Ясеневый лес", 50);
        Town spot2 = new Town("Штормград", 10);
        Spot[] spots = new Spot[2];
        spots[0] = spot1;
        spots[1] = spot2;
        
        Creature[] mobs = new Creature[2];
        Murloc mob1 = new Murloc("");
        Villain mob2 = new Villain("");
        mobs[0] = mob1;
        mobs[1]= mob2;
        
        Config config = new Config("Стычка с мобами", 
        "Однажды в @setPlace& собрались @setCharacters&. Они классно проводили время, ужиная @getEat&. Вдруг из ближайшего тёмного угла выскочили @setMobs&. Тогда @selectCharacter& использовал @getWeapon& и убил @killMobs&. Вот и сказочке конец.",
        characters, spots, mobs);
        try{
            Explorer.createConfig(config);
        } catch(IOException e){
            System.out.println("Не удалось создать конфигурацию");
        }
    }
    
    /* Метод запуска сценария */
    private static void runScenario(Config config){
        System.out.println("Вы сами создаёте историю. Просто делайте выборы");
        TemplateParser parser = new TemplateParser(config.getTemplate());
        parser.narrate(config);
        
        
    }
}
