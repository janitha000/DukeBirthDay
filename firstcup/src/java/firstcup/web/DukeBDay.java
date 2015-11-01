/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.web;

import firstcup.ejb.DukeBirthdayBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JanithaT
 */
@ManagedBean
@SessionScoped
@Named(value = "DukeBDay")
public class DukeBDay {

    @EJB
    private DukeBirthdayBean dukeBirthdayBean;
    protected int age;
    @NotNull
    protected Date yourBD;
    protected int ageDiff;
    protected int absAgeDiff;
    protected Double averageAgeDifference;
    private static final Logger logger = Logger.getLogger("firstcup.web.DukesBDay");

    /**
     * Creates a new instance of DukeBDay
     */
    public DukeBDay() {
        age = -1;
        yourBD = null;
        ageDiff = -1;
        absAgeDiff = -1;
        averageAgeDifference = -1.0;
    }

    public int getAge() {
        HttpURLConnection connection = null;
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        URL serverAddress = null;

        try {
            serverAddress = new URL("http://localhost:8080/DukesAgeService/webresources/dukesAge");
            connection = (HttpURLConnection) serverAddress.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);

            connection.connect();

            rd = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            age = Integer.parseInt(sb.toString());

        } catch (MalformedURLException e) {
            logger.warning("A MalformedURLException occurred.");
            e.printStackTrace();
        } catch (ProtocolException e) {
            logger.warning("A ProtocolException occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            logger.warning("An IOException occurred");
            e.printStackTrace();
        }

        return age;
    }

    public String processBirthday() {
        this.setAgeDiff(dukeBirthdayBean.getAgeDifference(yourBD));
        logger.info("age diff from dukesbday " + ageDiff);
        this.setAbsAgeDiff(Math.abs(this.getAgeDiff()));
        logger.info("absAgeDiff " + absAgeDiff);
        this.setAverageAgeDifference(dukeBirthdayBean.getAverageAgeDifference());
        logger.info("averageAgeDifference " + averageAgeDifference);
        return "/response.xhtml";
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getYourBD() {
        return yourBD;
    }

    public void setYourBD(Date yourBD) {
        this.yourBD = yourBD;
    }

    public int getAgeDiff() {
        return ageDiff;
    }

    public void setAgeDiff(int ageDiff) {
        this.ageDiff = ageDiff;
    }

    public int getAbsAgeDiff() {
        return absAgeDiff;
    }

    public void setAbsAgeDiff(int absAgeDiff) {
        this.absAgeDiff = absAgeDiff;
    }

    public Double getAverageAgeDifference() {
        return averageAgeDifference;
    }

    public void setAverageAgeDifference(Double averageAgeDifference) {
        this.averageAgeDifference = averageAgeDifference;
    }

}
