/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.ejb;

import firstcup.entity.FirstcupUser;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JanithaT
 */
@Stateless
public class DukeBirthdayBean {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger("firstcup.ejb.DukeBirthdayBean");

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public int getAgeDifference(Date date) {
        int ageDifference;

        Calendar theirBirthday = new GregorianCalendar();
        Calendar dukesBirthday = new GregorianCalendar(1990, Calendar.AUGUST, 05);
        theirBirthday.setTime(date);

        ageDifference = dukesBirthday.get(Calendar.YEAR)
                - theirBirthday.get(Calendar.YEAR);
        logger.info("Raw ageDifference is: " + ageDifference);
        if (dukesBirthday.before(theirBirthday) && (ageDifference > 0)) {
            ageDifference--;
        }

        FirstcupUser user = new FirstcupUser(date, ageDifference);
        em.persist(user);

        logger.info("Final ageDifference is: " + ageDifference);

        return ageDifference;
    }

    public Double getAverageAgeDifference() {
        Double avgAgeDiff
                = (Double) em.createNamedQuery("findAverageAgeDifferenceOfAllFirstcupUsers")
                .getSingleResult();
        logger.info("Average age difference is: " + avgAgeDiff);
        return avgAgeDiff;
    }

    public DukeBirthdayBean() {
    }

}
