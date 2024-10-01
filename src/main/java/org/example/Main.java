package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ParseException {

        //сохраним содержимое файла в строку:
        String json = readString("data.json");

        //преобразуем json-строку в список сотрудников
        List<Employee> list = jsonToList(json);

        //выведем список сотрудников в консоль:
        for (Employee emp:list){
            System.out.println(emp);
        }

    }

    //метод, преобразующий json-строку в список объектов класса Employee
    private static List<Employee> jsonToList(String json) throws ParseException {
        //Создадим пустой список сотрудников
        List<Employee> empList = new ArrayList<>();
        //инициализируем парсер
        JSONParser jsonParser = new JSONParser();
        //получим массив json-объектов
        JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
        //создадим объек gson для преобразования в цикле
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //пройдёмся по всем элементам json-массива
        for(Object gsObj:jsonArray){
            //приведём тип json к объекту Employee
            Employee employee  = gson.fromJson(String.valueOf(gsObj),Employee.class);
            empList.add(employee);
        }
        //вернём список сотрудников
        return empList;
    }

    //метод чтения строки формата json из соответствующего файла
    private static String readString(String jsonName) {
        //создадим переменную для записи строк из файла:
        StringBuilder output = new StringBuilder();
        //инициализируем поток для буферизованного чтения из файла в блоке try с ресурсами
        try (BufferedReader br = new BufferedReader(new FileReader(jsonName))) {
            //чтение построчно
            String s;
            //последовательно добавляем строки из файла, пока следующая строка не окажется пустой
            while ((s = br.readLine()) != null) {
                output.append(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //вернем считанные строку
        return output.toString();
    }
}