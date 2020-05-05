package xmu.edu.cn;

import org.springframework.stereotype.Component;


/**
 * {@link Service} with hard-coded input data.
 */
@Component
public class Teacher implements Service {
	private String course="English";
	/**
	 * Reads next record from input
	 */
	public void serve() {		
		System.out.println("Giving the "+ course +" lecture!");	
	}

}
