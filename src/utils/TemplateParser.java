/*
    24.11.2019
    -----
    Класс для анализа шаблонов историий.
    Команды, распознающиеся парсером: 
        1. @setPlace& - пользователь выбирает место из предложенных
        2. @setCharacters& - пользователь выбирает несколько персонажей
        3. @setCharacter& - -''- один персонаж
        4. @getEat& - персонажи едят
        5. @setMobs& - задаётся несколько мобов
        6. @setMob& - задётся один моб
        7. @selectCharacter& - выбирается один персонаж из участвующих
        8. @getWeapon& - персонаж показывает своё оружие
        9. @killMobs& - убить всех мобов
*/

package utils;

import gmObjects.characters.Hero;
import gmObjects.creatures.Creature;
import gmObjects.spots.Spot;
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class TemplateParser {
    private String progress; // Остаток сценария
    private Spot place; // Место действий
    private Hero[] characters = new Hero[1]; // Выбранные герои
    private Hero mainCharacter; // Главный герой
    private Creature[] creatures = new Creature[1]; // Выбранные мобы
    
    
    public TemplateParser(String template){
        this.progress = template;
    }
    
    /* Повествование по шаблону */
    public void narrate(Config config){
        String word = "";
        BufferedReader in;
        int num; // Пользовательский ввод
        
        while(progress.length() != 0){
            try{
                word = progress.substring(0, progress.indexOf("@")) + "...";
            } catch(Exception e){
                System.out.println(progress);
                narrateSecond(config);
                break;
            }
            progress = progress.substring(progress.indexOf("@"));
            System.out.println(word);
            word = progress.substring(0, progress.indexOf("&"));
            progress = progress.substring(progress.indexOf("&"));
            if(word.equals("@setPlace")){
                do{
                    try{
                        System.out.println("Выберите место действий: ");
                        for(int i = 0; i < config.getSpots().length; i++)
                            System.out.println("\t" + (i+1) + ". " + config.getSpots()[i].getName());
                        System.out.print(">> ");
                        in = new BufferedReader(new InputStreamReader(System.in));
                        num = Integer.parseInt(in.readLine())-1;
                        if(num > config.getSpots().length || num < 0)
                            System.out.println("Введите цифру, соотвествующую варианту");
                        else{
                            place = config.getSpots()[num];
                            break;
                        }
                    } catch(Exception e){
                        System.out.println("Некорректный ввод");
                    }
                } while(true);
            } else if(word.equals("@setCharacters")){
                do{
                    try{
                        System.out.println("Введите несколько участвующих персонажей, разделяя цифры пробелами. Персонажи могут повторяться: ");
                        for(int i = 0; i < config.getCharacters().length; i++)
                            System.out.println("\t" + (i+1) + ". " + config.getCharacters()[i].getRace()+ "-" + config.getCharacters()[i].getJob() + " " + config.getCharacters()[i].getName());
                        System.out.print(">> ");
                        in = new BufferedReader(new InputStreamReader(System.in));
                        String[] commands = in.readLine().split(" ");
                        characters = new Hero[commands.length];
                        for(int i = 0; i < commands.length; i++){
                            num = Integer.parseInt(commands[i]) - 1;
                            if(num > config.getSpots().length || num < 0){
                                System.out.println("Введите цифры, соотвествующие вариантам");
                                characters = null;
                                break;
                            } else{
                                characters[i] = config.getCharacters()[num];
                            }
                        }
                        if(characters != null)
                            break;
                    } catch(Exception e){
                        System.out.println("Некорректный ввод");
                    }
                } while(true);
            } else if(word.equals("@getEat")){
                for(int i = 0; i < characters.length; i++)
                    System.out.print(characters[i].getFood() + " ");
                System.out.println("");
            } else if(word.equals("@setMobs")){
                do{
                    try{
                        System.out.println("Введите несколько мобов, разделяя цифры пробелами. Мобы могут повторяться: ");
                        for(int i = 0; i < config.getMobs().length; i++)
                            System.out.println("\t" + (i+1) + ". " + config.getMobs()[i].getType());
                        System.out.print(">> ");
                        in = new BufferedReader(new InputStreamReader(System.in));
                        String[] commands = in.readLine().split(" ");
                        creatures = new Creature[commands.length];
                        for(int i = 0; i < commands.length; i++){
                            num = Integer.parseInt(commands[i]) - 1;
                            if(num > config.getMobs().length || num < 0){
                                System.out.println("Введите цифры, соотвествующие вариантам");
                                creatures = null;
                                break;
                            }else{
                                creatures[i] = config.getMobs()[num];
                            }
                        }
                        if(creatures != null)
                            break;
                    } catch(Exception e){
                        System.out.println("Некорректный ввод");
                    }
                } while(true);
            } else if(word.equals("@selectCharacter")){
                do{
                    try{
                        System.out.println("Выберите персонажа: ");
                        for(int i = 0; i < characters.length; i++)
                            System.out.println("\t" + (i+1) + ". " + characters[i].getName());
                        System.out.print(">> ");
                        in = new BufferedReader(new InputStreamReader(System.in));
                        num = Integer.parseInt(in.readLine()) - 1;
                        if(num > characters.length || num < 0)
                            System.out.println("Введите цифру, соответствующую варианту");
                        else{
                            mainCharacter = characters[num];
                            break;
                        }
                    } catch(Exception e){
                        System.out.println("Некорректный ввод");
                    }
                } while(true);
            } else if(word.equals("@getWeapon")){
                System.out.println(mainCharacter.getWeapon());
            } else if(word.equals("@killMobs")){
                for(int i = 0; i < creatures.length; i++)
                    System.out.println("\t" + (i+1) + ". " + creatures[i].getType() + " " + creatures[i].getName());
            } else{
                System.out.println("Команда не распознана");
            }
            
            try{
                if(progress.indexOf(" ") < progress.indexOf("."))
                    progress = progress.substring(progress.indexOf(" ")+1);
                else 
                    progress = progress.substring(progress.indexOf(".")+2);
            } catch(Exception e){
                break;
            }
        }
    }
    
    /* Второй ход программы, когда рассказывается вся история */
    public void narrateSecond(Config config){
        String word = "";
        BufferedReader in;
        int num; // Пользовательский ввод
        progress = config.getTemplate();
        
        System.out.println("\n\nВаша история: \n");
        
        while(progress.length() != 0){
            if(progress.indexOf("@") != -1)
                word = progress.substring(0, progress.indexOf("@"));
            else {
                System.out.println(progress);
                break;
            }
            progress = progress.substring(progress.indexOf("@"));
            System.out.print(word);
            word = progress.substring(0, progress.indexOf("&"));
            progress = progress.substring(progress.indexOf("&"));
            
            String str = ""; // Строка, которая создана для вставки в историю
            if(word.equals("@setPlace")){
                System.out.print(place.getName() + " ");
            } else if(word.equals("@setCharacters")){
                for(int i = 0; i < characters.length; i++)
                   str += characters[i].getRace() + "-" + characters[i].getJob() + " " + characters[i].getName() + ", ";
                str = str.substring(0, str.length()-2);
                System.out.print(str);    
            } else if(word.equals("@getEat")){
                for(int i = 0; i < characters.length; i++)
                   str += characters[i].getFood() + ", ";
                str = str.substring(0, str.length()-2);
                System.out.print(str);   
            } else if(word.equals("@setMobs")){
                for(int i = 0; i < creatures.length; i++)
                    str += creatures[i].getType() + ", ";
                str = str.substring(0, str.length()-2);
                System.out.print(str);   
            } else if(word.equals("@selectCharacter")){
                System.out.print(mainCharacter.getName() + " ");  
            } else if(word.equals("@getWeapon")){
                System.out.print(mainCharacter.getWeapon() + " ");
            } else if(word.equals("@killMobs")){
                for(int i = 0; i < creatures.length; i++)
                    str += creatures[i].getType() + "" + creatures[i].getName() + ", ";
                str = str.substring(0, str.length()-2);
                System.out.print(str);   
                creatures = null;
            } else{
                System.out.println("Команда не распознана");
            }
            
            try{
                if(progress.indexOf(" ") < progress.indexOf(".")){
                    progress = progress.substring(progress.indexOf(" ")+1);
                } else{
                    System.out.print(". ");
                    progress = progress.substring(progress.indexOf(".")+2);
                }
            } catch(Exception e){
                break;
            }
        }
        System.out.println("\n");
    }
}