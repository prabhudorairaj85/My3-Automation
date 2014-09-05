package util;

import java.util.Date; 
import java.text.SimpleDateFormat; 

/*
 * @TimeTool utility for time related functions,

 * @Date:12-07-2014.
 */

public class TimeUtility {
	
	/** Return the current time in "MMM dd,yyyy HH:mm:ss" format. */ 
	public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        return sdf.format(new Date().getTime()); 
	}

}
