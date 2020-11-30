package tasks;

import common.Person;
import common.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
/*TODO будем считать что это класс вспомогательных методов
public class Task8 implements Task {

    private long count;
    //TODO переменные вынесены из метода check
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = true;

    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй

    //TODO Функция получает персон, расчитывая, что первой в колекции идет фальшивая персона
    public List<String> getNames(List<Person> persons) {
        if (persons.size() == 0) {
            return Collections.emptyList();
        }
        persons.remove(0);
        return persons.stream().map(Person::getFirstName).collect(Collectors.toList());
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
        return getNames(persons).stream().distinct().collect(Collectors.toSet());
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    //TODO здесь имя функции лучше сменить чтобы отражало реально назначение метода
    public String convertPersonToString(Person person) {
        String result = "";
        if (person.getSecondName() != null) {
            result += person.getSecondName();
        }

        if (person.getFirstName() != null) {
            result += " " + person.getFirstName();
        }

        if (person.getSecondName() != null) {
            result += " " + person.getSecondName();
        }
        return result;
    }

    // словарь id персоны -> ее имя
    //TODO название метода не соответствует назначению
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
        Map<Integer, String> map = new HashMap<>(1);
        for (Person person : persons) {
            if (!map.containsKey(person.getId())) {
                map.put(person.getId(), convertPersonToString(person));
            }
        }
        return map;
    }

    // есть ли совпадающие в двух коллекциях персоны?
    //TODO здесь наверняка есть более удобный метод
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        boolean has = false;
        for (Person person1 : persons1) {
            for (Person person2 : persons2) {
                if (person1.equals(person2)) {
                    has = true;
                }
            }
        }
        return has;
    }

    //TODO сейчас не используется, считаю что метод здесь не нужен по логике. вместо отдельного счетчика можно использовать count
    public long countEven(Stream<Integer> numbers) {
        count = 0;
        numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
        return count;
    }

    @Override
    public boolean check() {
        System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
        return codeSmellsGood || reviewerDrunk;
    }
}
