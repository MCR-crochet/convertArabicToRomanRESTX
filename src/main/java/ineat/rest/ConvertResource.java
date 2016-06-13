package ineat.rest;

import ineat.domain.Response;
import ineat.exception.ConversionException;
import ineat.utils.Utils;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component @RestxResource
public class ConvertResource {

	
    /**
     * 
     * Service de conversion de chiffres arabes en chiffres romains
     * 
     * @param arabicNumber
     * @return Response
     */
    @GET("/convertArabicToRoman")
    @PermitAll
    public Response convertArabicToRoman(String arabicNumber) {
        try {
			return new Response().setResponse(Utils.convertArabicNumberToRomanNumber(arabicNumber));
		} catch (ConversionException e) {

			return new Response().setResponse("-10", e.getMessage());
		}
    }

    /**
     * 
     * Service de conversion de chiffres romains en chiffres arabes
     * 
     * @param romanNumber
     * @return Response
     */
    @GET("/convertRomanToArabic")
    @PermitAll
    public Response convertRomanToArabic(String romanNumber) {
    	try {
			return new Response().setResponse(Utils.convertRomanNumberToArabicNumber(romanNumber));
		} catch (ConversionException e) {

			return new Response().setResponse("-10", e.getMessage());
		}
    }

}
