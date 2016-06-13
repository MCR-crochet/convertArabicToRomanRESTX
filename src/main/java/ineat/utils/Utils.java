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
				throw new ConversionException("Nombre supérieur à 3999");
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
	
	
}
