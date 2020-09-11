/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Email Class
 */
public class Email implements Serializable, Comparable {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     * Email Constructor
     *
     * @param to
     * @param cc
     * @param bcc
     * @param subject
     * @param body
     * @param timestamp
     */
    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timestamp) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    /**
     * Gets the "To" within the email
     *
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the "To" within the email
     *
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the "CC" within the email
     *
     * @return
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the "CC" within the email
     *
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Gets the "BCC" within the email
     *
     * @return
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets the "BCC" within the email
     *
     * @param bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * Gets the subject of the email
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the body of the email
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of the email
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets the timestamp of the email
     *
     * @return
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the email
     *
     * @param timestamp
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * toString method which converts the email into a string
     *
     * @return
     */
    public String toString() {
        return "\n" +
                "To:  " + to + "\n" +
                "CC: " + cc + "\n" +
                "BCC: " + bcc + "\n" +
                "Subject: = " + subject + "\n" +
                "" + body;
    }

    /**
     * compareTo method which compares two emails by their timestamps
     *
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        Email email = (Email) o;
        return this.getTimestamp().compareTo(email.getTimestamp());
    }
}
