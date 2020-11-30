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
public class Task3 implements Task {

    // !!! Редактируйте этот метод !!!
    private List<Person> sort(Collection<Person> persons) {
        List<Person> sortedPerson =
                persons.stream().collect(Collectors.toList());

        Collections.sort(sortedPerson, new Comparator<Person>() {
            public int compare(Person obj1, Person obj2) {
                if (obj1.getSecondName().compareTo(obj2.getSecondName()) == 0)
                {
                    if (obj1.getFirstName().compareTo(obj2.getFirstName()) == 0) {
                        return obj1.getCreatedAt().compareTo(obj2.getCreatedAt());
                    }
                    return obj1.getFirstName().compareTo(obj2.getFirstName());
                }

                 return   obj1.getSecondName().compareTo(obj2.getSecondName());
            }
        });

        return sortedPerson;
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
