/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.webservice;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author JanithaT
 */
@Path("dukeAge")
public class dukeAgeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of dukeAgeResource
     */
    public dukeAgeResource() {
    }

    /**
     * Retrieves representation of an instance of
     * firstcup.webservice.dukeAgeResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        Calendar dukesBday = new GregorianCalendar(1990, Calendar.AUGUST, 5);
        Calendar now = GregorianCalendar.getInstance();

        int dukeAge = now.get(Calendar.YEAR) - dukesBday.get(Calendar.YEAR);
        dukesBday.add(Calendar.YEAR, dukeAge);

        if (now.before(dukesBday)) {
            dukeAge--;
        }

        return "" + dukeAge;
    }

}
