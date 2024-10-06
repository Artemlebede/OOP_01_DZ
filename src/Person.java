import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// Класс, представляющий человека
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private LocalDate birthDate;
    private Person mother;
    private Person father;
    private List<Person> children;
    public Person(String name, int year, int month, int day) {
        this.name = name;
        this.birthDate = LocalDate.of(year, month, day);
        this.children = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirthYear() {
        return birthDate;
    }
    public void setMother(Person mother) {
        this.mother = mother;
    }
    public void setFather(Person father) {
        this.father = father;
    }
    public void addChild(Person child) {
        this.children.add(child);
    }
    public List<Person> getChildren() {
        return children;
    }
    public Person getMother() {
        return mother;
    }
    public Person getFather() {
        return father;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Имя: ").append(name)
                .append(", Год рождения: ").append(birthDate)
                .append(", Мать: ").append(mother != null ? mother.getName() : "нет")
                .append(", Отец: ").append(father != null ? father.getName() : "нет")
                .append(", Дети: [");

        for (Person child : children) {
            sb.append(child.getName()).append(", ");
        }

        // Удаляем последнюю запятую и пробел, если дети есть
        if (!children.isEmpty()) {
            sb.setLength(sb.length() - 2); // Удаляем ", "
        }
        sb.append("]");

        return sb.toString();
    }
}