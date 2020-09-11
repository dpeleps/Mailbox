/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

import java.util.Comparator;

/**
 * SubjectComparator Class which compares the content of two emails based on their subjects.
 */
public class SubjectComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;

      return e1.getSubject().compareToIgnoreCase(e2.getSubject());
    }
}
