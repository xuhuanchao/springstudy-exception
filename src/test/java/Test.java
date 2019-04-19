import java.util.ArrayList;
import java.util.List;

public class Test {

    private final int num = 1;

    public Test(){

    }

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.num);
        List<Student> list = new ArrayList<>();
        Student s1 = t.new Student();
        list.add(s1);
        list.add(s1);
        list.add(null);
        System.out.println(list);


        String s = new String("111");
        String intern = s.intern();
        System.out.println(intern);



        String str1 = new StringBuilder("ja1").append("va").toString();
        System.out.println(str1.intern() == str1);

    }

    class Student{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


