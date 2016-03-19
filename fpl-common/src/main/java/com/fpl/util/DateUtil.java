package com.fpl.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    private static final String DEFAULT_FORMATE = "dd/MM/yyyy";
    
    private DateUtil() {}
    
    public static String getFormattedDate(final Date thisDate) {
        return getFormattedDate(thisDate,DEFAULT_FORMATE);
    }
    
    public static Date getFormattedDate(final String thisDate) {
        return getFormattedDate(thisDate, DEFAULT_FORMATE);
    }
    
    public static String getFormattedDate(final Date thisDate, final String format) {
        String returnVal = "";
        if ((thisDate != null) && (format != null)) {
            final SimpleDateFormat formatter = new SimpleDateFormat(format);
            returnVal = formatter.format(thisDate);
        }
        return returnVal;
    }
    
    public static Date getTimeTruncatedDate(final Date thisDate) {
        Date returnDate = null;
        if (thisDate != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            final String date = sdf.format(thisDate);
            returnDate = sdf.parse(date, new ParsePosition(0));
        }
        return returnDate;
    }
    
    public static Date getFormattedDate(final String thisDate,final String format) {
        Date returnDate = null;
        if (!StringUtil.isEmpty(thisDate) && !StringUtil.isEmpty(format)) {
            final SimpleDateFormat formatter = new SimpleDateFormat(format);
            try {
                returnDate = formatter.parse(thisDate);
            } catch(final ParseException pe){
                pe.printStackTrace();
            }
        }
        return returnDate;
    }
    
    public static boolean isValidDate(final String dateText, final String separator, final String format) {
        boolean isValid = true;
        if (StringUtil.isEmpty(dateText)) {
            isValid = false;
        } else {
            final String[] dateArray = dateText.split(separator);
            if((dateArray != null) && (dateArray.length == 3)) {
                try {
                    final int date = Integer.parseInt(dateArray[0]);
                    final int month = Integer.parseInt(dateArray[1]);
                    final String year = String.valueOf(Integer.parseInt(dateArray[2]));
                    if((date > 31) || (month > 12)) {
                        isValid = false;
                    } else  if((year == null) || (year.length() > 4) || (year.length() < 4)) {
                        isValid = false;
                    } else {
                        final SimpleDateFormat formatter = new SimpleDateFormat(format);
                        formatter.setLenient(false);
                        isValid = (formatter.parse(dateText, new ParsePosition(0)) != null);
                    }
                } catch (final NumberFormatException e) {
                    isValid = false;
                }
            } else {
                isValid = false;
            }
        }
        return isValid;
    }
    
    /**
     * This method returns the next Date for the given Date
     * @param date
     * @return Date
     */
    public static Date getNextDate(final Date inputDate) {
        Date returnDate = null;
        if(inputDate != null) {
            returnDate = getAddedDate(inputDate, 1);
        }
        return returnDate;
    }

    /**
     * This method returns the previous Date for the given Date
     * @param date
     * @return Date
     */
    public static Date getPreviousDate(final Date inputDate) {
        return inputDate == null ? inputDate : getAddedDate(inputDate, -1);
    }
    
    public static Date getAddedDate(final Date inputDate, final int num) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }
    
    public static int compareDate(Date fromDate, Date toDate) {
		fromDate = getTimeTruncatedDate( fromDate );
		toDate = getTimeTruncatedDate( toDate );
		int diff = 0;
		if((fromDate == null) && (toDate == null)) {
			diff = 0;
		} else if((fromDate == null) && (toDate != null)) {
			diff = 1;
		} else if((fromDate != null) && (toDate == null)) {
			diff = -1;
		} else if((fromDate != null) && (toDate != null)){
			if(fromDate.equals(toDate)) {
				diff = 0;
			} else if(fromDate.after(toDate)) {
				diff = 1;
			} else if(fromDate.before(toDate)) {
				diff = -1;
			}
		}
		return diff;
	}
    
    public static int getDateDiff(final Date fromDate, final Date toDate) {
    	return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }
    
    public static int hoursDifference(Date fromDate, Date toDate) {

        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (int) (fromDate.getTime() - toDate.getTime()) / MILLI_TO_HOUR;
    }
   
    public static String getDateTime()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		return sdf.format(now);
    }
    public static String getDate()
    {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		return sdf.format(now);
    }
    public static String getTime12()
    {
    	SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSS");
		Date now = new Date();
		return sdf.format(now);
    }
}
