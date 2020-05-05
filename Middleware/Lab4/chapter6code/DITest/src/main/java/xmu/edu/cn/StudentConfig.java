package xmu.edu.cn;

//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
@Configuration
public class StudentConfig {
	@Bean 
	public Service service(){
		return new Teacher();
	}
	@Bean 
	public Student student(){
		return new Student(service());
	}
}
