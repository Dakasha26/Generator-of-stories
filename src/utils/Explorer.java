/*
    23.11.2019
    -----
    Класс для работы с конфигурациями на уровне файлов.
    -----
    1. Находит и возвращает все конфигурации в папке configs.
    2. Открывает выбранную конфигурацию.
    3. Создаёт файл конфигурации.
*/

package utils;

import gmObjects.characters.*;
import gmObjects.creatures.*;
import gmObjects.spots.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Explorer {
    private static final File DIR = new File(".\\configs\\");
    
    
    public static String[] searchConfigsNames(){
        String[] foundFiles = DIR.list();
        
        int filesNumber = 0;
        for(String FileName : foundFiles)
            if(FileName.contains(".json"))
                filesNumber += 1;
        String[] configsNames = new String[filesNumber];
        
        int index = 0;
        for(String FileName : foundFiles)
            if(FileName.contains(".json")){
                configsNames[index] = FileName;
                index += 1;
            }
        return configsNames;
    }
    
    /* Открывает конфигурацию согласно её порядковому номеру */
    public static Config openConfig(int configIndex) throws IOException, ParseException, NullPointerException{
        File configFile = new File(DIR + "\\" + Explorer.searchConfigsNames()[configIndex-1]);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(configFile));
        
        String title = (String)jsonObject.get("title");
        String template = (String)jsonObject.get("template");
        
        Hero[] heros = parseCharacters((JSONArray)jsonObject.get("characters"));
        Spot[] spots = parseSpots((JSONArray)jsonObject.get("place"));
        Creature[] mobs = parseMobs((JSONArray)jsonObject.get("creatures"));
        
        Config config = new Config(title, template, heros, spots, mobs);
        return config;
    }
    
    private static Spot[] parseSpots(JSONArray listSpots){
        Spot[] spots = new Spot[listSpots.size()];
        Iterator iterator = listSpots.iterator();
        int counter = 0;
        while(iterator.hasNext()){
            JSONObject place = (JSONObject) iterator.next();
            if("town".equals(place.get("type").toString()))
                spots[counter] = new Town(place.get("name").toString(), (int)place.get("capacity"));
            else
                spots[counter] = new Settlement(place.get("name").toString(), Integer.parseInt(place.get("capacity").toString()));
            counter += 1;
        }
        return spots;
    }
    
    private static Hero[] parseCharacters(JSONArray characters){
        Iterator iterator = characters.iterator();
        Hero[] heros = new Hero[characters.size()];
        int counter = 0;
        while(iterator.hasNext()){
            JSONObject character = (JSONObject) iterator.next();
            switch(character.get("race").toString()){
                case("ворген"):
                    if("друид".equals(character.get("job").toString()))
                        heros[counter] = new WorgenDruid((String)character.get("name"));
                    else if ("разбойник".equals(character.get("job").toString()))
                        heros[counter] = new WorgenRogue((String)character.get("name"));
                    else
                        heros[counter] = new DwarfRogue("Дефолтный дворф");
                    break;
                case("дворф"):
                    if("разбойник".equals(character.get("job").toString()))
                        heros[counter] = new DwarfRogue((String)character.get("name"));
                    break;
                default:
                    heros[counter] = new DwarfRogue((String)character.get("name"));
            }
            counter += 1;
        }
        return heros;
    }
    
    private static Creature[] parseMobs(JSONArray creatures){
        Creature[] mobs = new Creature[creatures.size()];
        int counter = 0;
        Iterator iterator = creatures.iterator();
        JSONObject mob;
        while(iterator.hasNext()){
            mob = (JSONObject)iterator.next();
            switch(mob.get("type").toString()){
                case("разбойник"):
                    mobs[counter] = new Villain((String)mob.get("name"));
                    break;
                case("мурлок"):
                    mobs[counter] = new Murloc((String)mob.get("name"));
                    break;
                default:
                    mobs[counter] = new Murloc((String)mob.get("name"));
            }
            counter += 1;
        }
        return mobs;
    }
   
    public static void createConfig(Config config) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", config.getTitle());
        jsonObject.put("template", config.getTemplate());
        
        JSONArray list = new JSONArray();
        for(int i = 0; i < config.getCharacters().length; i++){
            JSONObject character = new JSONObject();
            character.put("name", config.getCharacters()[i].getName());
            character.put("race", config.getCharacters()[i].getRace());
            character.put("job", config.getCharacters()[i].getJob());
            character.put("weapon", config.getCharacters()[i].getWeapon());
            character.put("food", config.getCharacters()[i].getFood());
            list.add(character);
        }
        jsonObject.put("characters", list);
        
        list = new JSONArray();
        for(int i = 0; i < config.getSpots().length; i++){
            JSONObject spot = new JSONObject();
            spot.put("name", config.getSpots()[i].getName());
            spot.put("capacity", config.getSpots()[i].getCapacity());
            spot.put("type", config.getSpots()[i].getType());
            list.add(spot);
        }
        jsonObject.put("place", list);
        
        list = new JSONArray();
        for(int i = 0; i < config.getMobs().length; i++){
            JSONObject mob = new JSONObject();
            mob.put("name", config.getMobs()[i].getName());
            mob.put("type", config.getMobs()[i].getType());
            list.add(mob);
        }
        jsonObject.put("creatures", list);
        
        FileWriter file = new FileWriter(DIR + "\\" + config.getTitle() + ".json");
        System.out.println(jsonObject.toJSONString());
        file.write(jsonObject.toJSONString());
        file.flush();
        System.out.println("Конфигурация успешно сохранена");
    }
    
    
    
}
