package xmu.edu.cn;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentXmlMain {
  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context =
          new ClassPathXmlApplicationContext(
            "META-INF/spring/student-bean.xml");
   
    Student s = context.getBean(Student.class);
    Teacher t = context.getBean(Teacher.class);
    if(s!=null)
       s.learn();    
    System.out.println(s);
    System.out.println(t);
    context.close();
  }
}
