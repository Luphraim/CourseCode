package xmu.edu.cn;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StudentConfig.class)
//@ContextConfiguration
//@ContextConfiguration(locations={"classpath:/META-INF/spring/student-bean.xml"})
//@ContextConfiguration(locations={"file:/Users/MacBackup/Documents/workspace-sts-3.7.1.RELEASE/DITest/src/main/resources/META-INF/spring/student-bean.xml"})
public class StudentTests2 extends TestCase {
@Autowired 
private Service service;
@Autowired
private Student student;

@Test	
public void studentNotNull() throws Exception {
		assertNotNull(student);
	}

@Test	
public void serviceNotNull() throws Exception {
		assertNotNull(service);
	}


@Test	
public void play() throws Exception {
	    OutputLog log=new OutputLog();//
	    student.learn();
		assertEquals("Giving the English lecture!\n",log.getLog() );
		log.reset();
		System.out.println(log.getLog());
	}

}
