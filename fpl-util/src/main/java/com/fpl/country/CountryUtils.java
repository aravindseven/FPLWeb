package com.fpl.country;

import com.fpl.core.subsciption.fpl.CountryPV;

public class CountryUtils {

 public static float calculateTax(CountryPV countryPV,float amount)
 {
	 float taxAmount=countryPV.getSaleTaxRate();

	 return amount*taxAmount/100;
 }
	
}
