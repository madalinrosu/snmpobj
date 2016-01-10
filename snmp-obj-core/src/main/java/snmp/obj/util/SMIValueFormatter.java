package snmp.obj.util;

import java.text.MessageFormat;

public final class SMIValueFormatter {

	private static final String TIMETICKS_PATTERN =
					"{0,choice,0#|1#1 day, |1<{0,number,integer} days, }"+
					"{1,number,integer}:{2,number,00}:{3,number,00}.{4,number,00}";

	private SMIValueFormatter() {
	}

	public static String formatTimeTicks(long value) {
	    long hseconds, seconds, minutes, hours, days;

	    days = value / 8640000;
	    value %= 8640000;

	    hours = value / 360000;
	    value %= 360000;

	    minutes = value / 6000;
	    value %= 6000;

	    seconds = value / 100;
	    value %= 100;

	    hseconds = value;

	    return MessageFormat.format(TIMETICKS_PATTERN, days, hours, minutes, seconds, hseconds);
	}
	
    public static String formatDateAndTime(String octetString) {
    	if ( octetString == null || "".equals( octetString ) )
    		return octetString;

    	// sample 07:d9:01:04:16:34:06:02:2b:00:00
    	String[] octets = octetString.split(":");
    	if( !(octets.length == 8 || octets.length == 11) ) // bad value
    		return octetString;
    	
    	Integer year = Integer.parseInt(octets[0]+octets[1], 16);
    	Integer month = Integer.parseInt(octets[2], 16);
    	Integer day = Integer.parseInt(octets[3], 16);
    	Integer hour = Integer.parseInt(octets[4], 16);
    	Integer min = Integer.parseInt(octets[5], 16);
    	Integer sec = Integer.parseInt(octets[6], 16);
    	Integer decisec = Integer.parseInt(octets[7], 16);

    	StringBuilder buff = new StringBuilder();
    	buff.append( String.format("%d-%d-%d,%d:%d:%d.%d", year, month, day, hour, min, sec, decisec) );
    	if(octets.length == 11) {
        	char dirFromUTC = (char)Integer.parseInt(octets[8], 16);
        	Integer hoursFromUTC = Integer.parseInt(octets[9], 16);
        	Integer minsFromUTC = Integer.parseInt(octets[10], 16);
    		buff.append( String.format(",%s%d:%d", dirFromUTC, hoursFromUTC, minsFromUTC));
    	}
    	
    	return buff.toString();
    }

}
