import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Класс, представляющий генеалогическое древо
class FamilyTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Person> people;
    public FamilyTree() {
        this.people = new ArrayList<>();
    }
    public void addPerson(Person person) {
        this.people.add(person);
    }
    public List<Person> getChildren(Person parent) {
        return parent.getChildren();
    }
    public Person findPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
    public List<Person> getPeople() {
        return people;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Генеалогическое древо:\n");

        // Проверяем, есть ли люди в древе
        if (people.isEmpty()) {
            sb.append("Нет данных о людях.\n");
        } else {
            for (Person person : people) {
                sb.append(person.toString()).append("\n"); // Используем метод toString() класса Person
            }
        }

        return sb.toString();
    }
}