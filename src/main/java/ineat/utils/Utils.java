package ineat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ineat.exception.ConversionException;

public class Utils {
	
	static Logger logger = LoggerFactory.getLogger(Utils.class);


	/** 
	 * Matrice correspond aux chiffres romains sur une base 10
	 * 				0	1	2	3	4	5	6	7	8	 	9
	 * unite		''	I	II	III	IV	V	VI 	VII VIII	IX
	 * dizaine		''  X	XX	XXX	XL	L	LX	LXX	LXXX 	XC
	 * centaine		''	C	CC	CCC	CD	D	DC	DCC	DCCC	CM	
	 * millier		'' 	M	MM	MMM	
	 */
	public static final String romans[][] = { { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
			{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
			{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
			{ "", "M", "MM", "MMM", "", "", "", "", "", "" } };

	/**
	 * 
	 * Methode de conversion d'un chiffre arabe en chiffre romain. <br/>
	 * Ecxeption si le nombre n'est pas formé que d'entier ou si le nombre est supérieur à 3999
	 * 
	 * @param ligne : nombre à convertir
	 * @return nombre converti
	 * @throws ConversionException
	 */
	public static String convertArabicNumberToRomanNumber(String ligne) throws ConversionException {
		
		logger.debug("[ENTER] convertArabeToRoman : " + ligne);
		
		// Regex de chaine d'entier
		Pattern pattern = Pattern.compile("(\\d+)");
		Matcher matcher = pattern.matcher(ligne);

		// Test pour vérifier que la chaine n'est composée que d'entier
		if (!matcher.matches()) {
			logger.error("Le nombre doit être composé que d'entier");
			throw new ConversionException("NaN");
		}

		StringBuilder txt = new StringBuilder();
		int i = 0;

		// Test si le nombre est bien inférieur strictement à 4000
		try {
			if (Integer.parseInt(ligne) > 3999) {
				logger.error("Le nombre doit être inférieur ou égal à 3999");
				throw new ConversionException("Le nombre doit être inférieur à 3999");
			}
		} catch (NumberFormatException e) {
			logger.error("Le nombre doit être composé que d'entier"); 
			throw new ConversionException("NaN");
		}

		// On boucle sur chaque caractère et on fait la correspondance avec la matrice pour obtenir la conversion
		for (char c : ligne.toCharArray()) {

			txt.append(romans[ligne.length() - i - 1][Character.getNumericValue(c)]);
			i++;
		}
		
		logger.debug("[EXIT] convertArabeToRoman : " + txt.toString());

		return txt.toString();
	}

	/**
	 * 
	 * Methode de conversion d'un chiffre romain en chiffre arabe. <br/>
	 * Ecxeption si le nombre n'est pas formé que de caracteres compris entre 'I, V, X, L, C, D, M' ou si le nombre est supérieur à 3999
	 * 
	 * @param ligne : nombre à convertir
	 * @return nombre converti
	 * @throws ConversionException
	 */
	public static String convertRomanNumberToArabicNumber(String romanNumber) throws ConversionException {

		int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();
        
        // Regex de chaine d'entier
 		Pattern pattern = Pattern.compile("[IVXLCDM]*");
 		Matcher matcher = pattern.matcher(romanNumber);

 		// Test pour vérifier que la chaine n'est composée que d'entier
 		if (!matcher.matches()) {
 			logger.error("Le nombre doit être composé des carateres suivante : I, V, X, L, C, D, M");
 			throw new ConversionException("Le nombre doit être composé des carateres suivante : I, V, X, L, C, D, M");
 		}
        
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        
        if (decimal > 3999) {
			logger.error("Le nombre doit être inférieur ou égal à 3999");
			throw new ConversionException("Le nombre doit être inférieur à 3999");
		}
        
		return String.valueOf(decimal);
	}
	
	public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
	
	
}
