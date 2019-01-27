package domain.files;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.files.Client.Sex;

public final class MedicareParser {

	public static Date getDOB(String med) throws Exception {
		if (med.length() > 10) {
			int year = Integer.parseInt(med.substring(4, 6));
			int month = Integer.parseInt(med.substring(6, 8));
			int day = Integer.parseInt(med.substring(8, 10));
			month--;
			if (0 == month || 0 == day) {
				String medParserException1="The medicare number ";
				String medParserException2=" has an invalid day of birth.";
				throw new Exception(medParserException1 + med
						+ medParserException2);
			}
			if (day > 62) {
				year += 2000;
				day -= 62;
			} else {
				year += 1900;
			}
			if (month > 50) {
				month -= 50;
			}
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			return cal.getTime();
		} else {
			String medParserException3="The medicare number "; 
			String medParserException4=" is invalid.";
			throw new Exception(medParserException3 + med + medParserException4);
		}
	}

	public static Sex getSex(String med) throws Exception {
		if (med.length() > 10) {
			int month = Integer.parseInt(med.substring(6, 8));
			if (0 == month) {
				String medParserException5="The medicare number ";
				String medParserException6=" has an invalid day of birth";
				throw new Exception(medParserException5 + med
						+ medParserException6);
			}
			if (month > 50) {
				return Sex.Female;
			} else {
				return Sex.Male;
			}
		} else {
			String medParserException7="The medicare number ";
			String medParserException8=" is invalid.";
			throw new Exception(medParserException7 + med + medParserException8 );
		}
	}

	public static boolean isMedicareValid(String med) {
		if (null != med && (12 == med.length())) {
			Pattern p = Pattern.compile("^[A-Z]{4}[0-9]{8}$");
			Matcher m = p.matcher(med);
			return m.matches();
		} else {
			return false;
		}
	}
}
