package xmu.edu.cn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentJavaMain {
  public static void main(String[] args) throws Exception {
    	  ApplicationContext context = 
			  new AnnotationConfigApplicationContext(StudentConfig.class);
    
    Student s = context.getBean(Student.class);
    Teacher t = context.getBean(Teacher.class);
    if(s!=null)
       s.learn();    
    System.out.println(s);
    System.out.println(t);
    //context.close();
  }
}
