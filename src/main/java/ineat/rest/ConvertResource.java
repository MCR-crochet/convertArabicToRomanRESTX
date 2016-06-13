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
     * Say hello to currently logged in user.
     *
     * Authorized only for principals with Roles.HELLO_ROLE role.
     *
     * @return a Message to say hello
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
     * Say hello to anybody.
     *
     * Does not require authentication.
     *
     * @return a Message to say hello
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
