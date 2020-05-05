package xmu.edu.cn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ClassRoomConfig {
  @Bean
  public Student student() {
    return new Student();
  }
  @Bean
  public Service teacher(){
	  Teacher p=new Teacher();
	  p.setName("Lai");
	  return p;  
  }
  

  
}