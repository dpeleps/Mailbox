/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Folder class which contains all the methods to create a custom folder
 */
public class Folder implements Serializable {
    private ArrayList<Email> emails;
    private String name; // name of the folder
    private String currentSortingMethod = "DD";

    /**
     * Default constructor
     */
    public Folder(String name) {
        emails = new ArrayList<>();
        setName(name);
    }

    /**
     * Gets all the emails
     *
     * @return
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * Sets the emails
     *
     * @param emails
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * Gets the name of the folder
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the folder
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current sorting method
     *
     * @return
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * Sets the current sorting method
     *
     * @param currentSortingMethod
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * Adds an email to the folder according to the current sorting method.
     *
     * @param email
     */
    public void addEmail(Email email) {
        emails.add(email);

        if (currentSortingMethod == null || currentSortingMethod.equalsIgnoreCase("SA"))
            sortBySubjectAscending();
        else if (currentSortingMethod.equalsIgnoreCase("SD"))
            sortBySubjectDescending();
        else if (currentSortingMethod.equalsIgnoreCase("DA"))
            sortByDateAscending();
        else if (currentSortingMethod.equalsIgnoreCase("DD"))
            sortByDateDescending();
    }

    /**
     * Removes an email from the folder by index.
     *
     * @param index
     * @return
     */
    public Email removeEmail(int index) {
        return emails.remove(index);
    }


    /**
     * Sorts the emails alphabetically by subject in ascending order.
     */
    public void sortBySubjectAscending() {
        Collections.sort(emails, new SubjectComparator());
    }

    /**
     * Sorts the emails alphabetically by subject in descending order.
     */
    public void sortBySubjectDescending() {
        Collections.sort(emails, Collections.reverseOrder(new SubjectComparator()));
    }

    /**
     * Sorts the emails by date in ascending order.
     */
    public void sortByDateAscending() {
        Collections.sort(emails);
    }

    /**
     * Sorts the emails by date in descending order.
     */
    public void sortByDateDescending() {
        Collections.sort(emails, Collections.reverseOrder());
    }

    /**
     * Prints the contents of the folder
     */
    public void printFolder() {
        System.out.println("\n" + this.getName() + "\n");
        if (emails.isEmpty()) {
            System.out.println(this.getName() + " is empty.");
        } else {
            System.out.printf("%-5s | %-30s | %-15s\n", "Index", "Time", "Subject");
            System.out.println("--------------------------------------------------------");
            for (int i = 0; i < emails.size(); i++) {
                System.out.printf("%-5s | %-30s | %-15s\n", i + 1, emails.get(i).getTimestamp().getTime(), emails.get(i).getSubject());
            }
        }
    }


}
