package xmu.edu.cn;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import java.io.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ClassRoomConfig.class)
public class PerformanceTest {

//	@Rule
// public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream(); 
  

  // test stuff here...

  //final String standardOutput = ;
  
// @Autowired
// private Audience audience;
	
  @Autowired
  private Service service;
  

  
  /*@Autowired
  private Pianist pianist;
*/
  @Test
  public void play() {  

	//  assertNotNull(performance);
	   assertNotNull(service);
	   service.serve(); 

  }

 
  }
