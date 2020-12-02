package tasks;

import common.Area;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.disjoint;
import static java.util.function.Function.identity;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
/*Предположим, что это класс-вспомогательная библиотека, для получения разных статистик*/
public class Task8 implements Task {


    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй

    /*
    т.к. нет задачи что-то удалять, быстрее просто пропустить.
    Название изменено, чтобы соответствовало результату:
    почему класс должен знать, что первая персона фальшивая? Думаю это знает внешний мир, который вызывает метод -
    есть фальшивая - вызовет этот, нет фальшивой - сделать другой.
    Или сделать одну функцию - но передавать флаг необходимости выкинуть первую?

     */
    public List<String> getPersonsFirstNamesWithoutFirst(List<Person> persons) {
        return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
    }

    //ну и различные имена тоже хочется
    /*
    А здесь есть задача выкинуть первую? Если есть - можно просто использовать функцию, определенную выше;
    но так как этого явно не задано, то реализация такая.
    Distinct не нужен был, т.к. set и так хранить только различные значения
     */
    public Set<String> getDistinctNames(List<Person> persons) {
        /*
        если без первой то
        return new HashSet<>(getPersonsFirstNamesWithoutFirst(persons);
         */
        return persons.stream().map(Person::getFirstName).collect(Collectors.toSet());
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    /*Вообще наверно все методы можно сделать статичными, потому что они не зависят от конкретного объекта, а все только
    выполняют какие-то действия с входными данными
    */
    public static String getPersonFullName(Person person) {
        return Stream.of(person.getFirstName(), person.getMiddleName(), person.getSecondName())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(" "));
    }

    // словарь id персоны -> ее имя

    public Map<Integer, String> getPersonNamesMap(Collection<Person> persons) {
        Map<Integer, String> map = persons.stream().collect(Collectors.toMap(Person::getId, Task8::getPersonFullName));
        return map;
    }

    // есть ли совпадающие в двух коллекциях персоны?
    /* а может тут надо было еще возвращать само совподение, если есть?*/

    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {

        return !disjoint(persons1, persons2);
    }

    //Метод не вписывается, т.к. остальные работают с коллекциями персон
    //и зачем тогда count вынесен в переменные класса непонятно
    public long countEven(Stream<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0).count();
    }

    @Override
    public boolean check() {
        // System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
        /*не уверена, что хочется оставлять объявление этих переменных тут. Может быть здесь сделать логику
        - внешний мир вызывает какие-то функции, функции по итогам меняют их,
         а затем проверяется состояние объекта, методом чек?
         */
        boolean codeSmellsGood = false;
        boolean reviewerDrunk = true;

        List<Person> persons = List.of(
                new Person(1, "Oleg", Instant.now()),
                new Person(2, "Vasya", Instant.now()),
                new Person(3, "Vasya", Instant.now())
        );
        persons.get(2).setSecondName("Popov");
        Map<Integer, String> mapForCheck = Map.of(1, "Oleg", 2, "Vasya", 3, "Vasya Popov");
        List<Person> personsForCommon = List.of(
                new Person(4, "Grek", Instant.now()),
                new Person(2, "Vasya", Instant.now())
        );

        if (getPersonsFirstNamesWithoutFirst(persons).equals(List.of("Vasya", "Vasya"))
                && getDistinctNames(persons).equals(Set.of("Vasya", "Oleg"))
                && getPersonFullName(persons.get(2)).equals("Vasya Popov")
                && getPersonNamesMap(persons).equals(mapForCheck)
                && hasSamePersons(persons, personsForCommon)
        ) return reviewerDrunk;
        else
            return codeSmellsGood;
    }
}
