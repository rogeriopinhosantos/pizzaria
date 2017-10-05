package br.com.rps.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleUtils {

	
	public static String buscaRotulo(String nomeChave, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("bundle", locale);
		return bundle.getString(nomeChave);
	}
	
}
