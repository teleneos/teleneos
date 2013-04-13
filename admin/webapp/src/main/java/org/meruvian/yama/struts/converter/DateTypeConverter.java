/**
 * 
 */
package org.meruvian.yama.struts.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

/**
 * @author Dian Aditya
 * 
 */
public class DateTypeConverter extends StrutsTypeConverter {
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat(
			"dd-MM-yyyy");

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0
				|| values[0].trim().length() == 0) {
			return null;
		}

		try {
			return DATETIME_FORMAT.parse(values[0]);
		} catch (ParseException e) {
			throw new TypeConversionException(
					"Unable to convert given object to date: " + values[0]);
		}
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o != null && o instanceof Date) {
			return DATETIME_FORMAT.format(o);
		} else {
			return null;
		}
	}

}
