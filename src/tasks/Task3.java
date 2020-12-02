package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
/*Первый вариант был некрасив - через Collections.sort(persons, new Comparator<Person>() {перебор вариантов if-ами})
потом нашла thenComparing
 */
public class Task3 implements Task {

    // !!! Редактируйте этот метод !!!
    private List<Person> sort(Collection<Person> persons) {
        List<Person> sortedPersons =
                persons.stream()
                        .sorted(Comparator.comparing(Person::getSecondName)
                                .thenComparing(Person::getFirstName)
                                .thenComparing(Person::getFirstName))
                        .collect(Collectors.toList());

        return sortedPersons;
    }

    @Override
    public boolean check() {
        Instant time = Instant.now();
        List<Person> persons = List.of(
                new Person(1, "Oleg", "Ivanov", time),
                new Person(2, "Vasya", "Petrov", time),
                new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
                new Person(4, "Oleg", "Ivanov", time.plusSeconds(1))
        );
        List<Person> sortedPersons = List.of(
                new Person(1, "Oleg", "Ivanov", time),
                new Person(4, "Oleg", "Ivanov", time.plusSeconds(1)),
                new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
                new Person(2, "Vasya", "Petrov", time)
        );
        return sortedPersons.equals(sort(persons));
    }
}
