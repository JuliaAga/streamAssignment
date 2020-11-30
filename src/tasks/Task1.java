package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */

/*
Первый  прямолинейный вариант был: создать лист, и перебором по personsIds искать в persons
нужного и добавлять в лист. Но в таком случае для каждого id в худшем случае перебирался бы весь set (т.к. ищем по
полю в объекте).
Второй вариант (финальный): создать hashMap (id, персона), перебором по personsId за время O(n) получать из map нужного
за время O(1).
*/

public class Task1 implements Task {

    // !!! Редактируйте этот метод !!!
    private List<Person> findOrderedPersons(List<Integer> personIds) {
        Set<Person> persons = PersonService.findPersons(personIds);
        Map<Integer, Person> personsMap = persons.stream().collect(Collectors.toMap(Person::getId, identity()));
        List<Person> personsOutput =
                personIds.stream().map(id -> personsMap.get(id)).collect(Collectors.toList());
        return personsOutput;
    }

    @Override
    public boolean check() {
        List<Integer> ids = List.of(1, 2, 3);

        return findOrderedPersons(ids).stream()
                .map(Person::getId)
                .collect(Collectors.toList())
                .equals(ids);
    }

}
