import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();

    list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        for(int s : list){
            System.out.println(s);
        }


Optional<Integer> newList = list.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * 2)
        .sorted()
        .reduce((a,b) -> a + b);

        System.out.println(newList);

        StudentRecord student1 = new StudentRecord("Alice", 20);
        StudentRecord student2 = new StudentRecord("Bob", 22);
        student1 = student1.withAge(24);
        System.out.println(student1);
        System.out.println(student1);

    }

}
