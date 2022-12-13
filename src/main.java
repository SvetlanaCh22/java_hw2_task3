// Дана json строка (можно сохранить в файл и читать из файла)
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
// Пример вывода:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

// Чубченко Светлана

import java.io.*;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        // парсим json
        try {
            // задаем строку json
            File json = new File("students.json");
            // создаем массив списков из двух полей из json
            Map<String, String>[] params = mapper.readValue(json, Map[].class);
            for (int i = 0; i < params.length; i++) {
                // выводим
                System.out.println(getQuery(params[i]));
            }

        } catch (IOException e) {
            // если ошибка парсинга
            e.printStackTrace();
        }
    }

    public static String getQuery(Map<String, String> params)
    {
        StringBuilder s = new StringBuilder();
        // переьбираем список имя значение
        for (Map.Entry<String,String> pair : params.entrySet())
        {
            // если параметр не null и не пустой
            if ((pair.getValue() == null) || (pair.getValue().equals(""))) {
                return "";
            } else {
                switch (pair.getKey()) {
                    case "фамилия":
                        s.append("Студент " + pair.getValue());
                        break;
                    case "оценка":
                        s.append(" получил " + pair.getValue());
                        break;
                    case "предмет":
                        s.append(" по предмету " + pair.getValue());
                        break;
                }
            }
        }
        return s.toString();
    }

}