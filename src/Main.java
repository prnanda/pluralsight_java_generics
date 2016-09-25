import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Person don = new Person("Don Draper", 40);
        Person peggy = new Person("Peggy Olson", 30);

        //Arrays are not great in Java

        Person[] madMen = {don, peggy};
        System.out.println(Arrays.toString(madMen)); // No good to string implementation

        // Difficult to add elements
        Person bert = new Person("Bert Cooper",  55);
        // madMen[2] = bert; // java.lang.ArrayIndexOutOfBoundsException: 2
        // One has to copy the array with a length that is greater than the original array - inefficient

        // Hard to check if an element is present in an Array

        // Lists are better ArrayList is a generic
        List<Person> madMenList= new ArrayList<>();
        madMenList.add(don);
        madMenList.add(peggy);

        System.out.println(madMenList);

        // Lists re-size on their own
        madMenList.add(bert);

        System.out.println(madMenList);

        //madMenList.add(new Object()); // Type Safety - Compiler will not allow this
        // Lists have other useful methods
        System.out.println(madMenList.size());

        System.out.println(madMenList.get(2));

        // We can iterate over all the elements easily - for each loop
        for (Person person: madMenList){
            System.out.println(person);
        }

        // Or use an iterator
        final Iterator<Person> iterator = madMenList.iterator();
        while (iterator.hasNext()){
            final Person person = iterator.next();
            System.out.println(person);
        }

        // ----------------------------------------------------------------------------------------
        // Sets are a collection that have unique elements, but there is no defined order

        madMenList.add(don);
        System.out.println(madMenList); // This is allowed

        Set<Person> madMenSet = new HashSet<>(); // Sets prevent duplicates
        madMenSet.add(don);
        madMenSet.add(peggy);
        madMenSet.add(bert);

        System.out.println(madMenSet.contains(don));


        // ----------------------------------------------------------------------------------------
        // Maps are like dictionaries - Unique key, value pairs - 2 generic parameters

        Map<String, Person> madMenMap = new HashMap<>();
        madMenMap.put(don.getName(), don);
        madMenMap.put(peggy.getName(), peggy);
        madMenMap.put(bert.getName(), bert);

        // Keys have to be unique

        // Looping through a Map
        for (Map.Entry<String, Person> entry : madMenMap.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Collections.sort(madMenList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        });

        final Person youngestCastMember = min(madMenList, new AgeComparator());
        System.out.println("\nThe youngest person is: " + youngestCastMember);



    }


        public static <T> T min(List<T> values, Comparator<T> comparator) {
            if (values.isEmpty()) {
                throw new IllegalArgumentException("List is empty, cannot find minimum");
            }

            T lowestElement = values.get(0);

            for (int i = 1; i < values.size(); i++) {
                final T element = values.get(i);
                if (comparator.compare(element, lowestElement) < 0) {
                    lowestElement = values.get(i);
                }
            }

            return(lowestElement);
        }

}
