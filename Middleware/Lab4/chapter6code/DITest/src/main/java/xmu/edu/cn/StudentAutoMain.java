package xmu.edu.cn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes=StudentAutoConfig.class)
@Component
public class StudentAutoMain {
	@Autowired
	private Service service;
	@Autowired
	private Student student;

	
  public void play() throws Exception {    	  
          student.learn();      
  }
  public static void main(String[] args) throws Exception {
	  ApplicationContext context = new AnnotationConfigApplicationContext(StudentAutoConfig.class);
	  StudentAutoMain s = context.getBean(StudentAutoMain.class);
	  s.play();
  }
}
