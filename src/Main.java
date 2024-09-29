import java.io.*;
import java.util.ArrayList;
import java.util.List;
// Интерфейс для операций с файлами
interface FileOperations {
    void saveToFile(FamilyTree familyTree, String fileName) throws
            IOException;
    FamilyTree loadFromFile(String fileName) throws IOException,
            ClassNotFoundException;
}
// Реализация интерфейса для операций с файлами
class FileOperationsImpl implements FileOperations {
    @Override
    public void saveToFile(FamilyTree familyTree, String fileName)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new
                FileOutputStream(fileName))) {
            oos.writeObject(familyTree);
        }
    }
    @Override
    public FamilyTree loadFromFile(String fileName) throws
            IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(fileName))) {
            return (FamilyTree) ois.readObject();
        }
    }
}


// Главный класс с точкой входа
public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();
// Создаем людей
        Person artem = new Person("Артем", 2002);
        Person angelina = new Person("Ангелина", 2003);
        Person sonya = new Person("Соня", 2021);
// Устанавливаем родительские связи
        sonya.setMother(angelina);
        sonya.setFather(artem);
        artem.addChild(sonya);
        angelina.addChild(sonya);
// Добавляем людей в древо
        familyTree.addPerson(artem);
        familyTree.addPerson(angelina);
        familyTree.addPerson(sonya);
// Создаем объект для работы с файлами
        FileOperations fileOps = new FileOperationsImpl();
// Сохраняем генеалогическое древо в файл
        try {
            fileOps.saveToFile(familyTree, "familyTree.dat");
            System.out.println("Family tree saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
// Загружаем генеалогическое древо из файла
        FamilyTree loadedFamilyTree = null;
        try {
            loadedFamilyTree =
                    fileOps.loadFromFile("familyTree.dat");
            System.out.println("Family tree loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
// Проверяем, что древо загрузилось правильно
        if (loadedFamilyTree != null) {
            for (Person person : loadedFamilyTree.getPeople()) {
                System.out.println("Loaded person: " +
                        person.getName() + ", born in " + person.getBirthYear());
            }
        }
    }
}