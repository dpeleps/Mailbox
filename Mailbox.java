/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Mailbox Class which represents an email box and contains all of the folders
 */
public class Mailbox implements Serializable {
    private Folder inbox; //all new emails go here
    private Folder trash; //when an email is deleted, it is moved here
    private ArrayList<Folder> folders; //stores all the custom folders in the mailbox
    public static Mailbox mailbox; //stores the main mailbox that will be used by the application instantiated in the main method

    /**
     * Mailbox constructor that creates the Inbox, Trash, and the array of folders
     */
    public Mailbox() {
        folders = new ArrayList<>();
        inbox = new Folder("Inbox");
        trash = new Folder("Trash");
    }

    /**
     * Gets the inbox
     *
     * @return
     */
    public Folder getInbox() {
        return inbox;
    }

    /**
     * Sets the Inbpx
     *
     * @param inbox
     */
    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    /**
     * Gets the Trash
     *
     * @return
     */
    public Folder getTrash() {
        return trash;
    }

    /**
     * Sets the Trash
     *
     * @param trash
     */
    public void setTrash(Folder trash) {
        this.trash = trash;
    }

    /**
     * Gets the folders
     *
     * @return
     */
    public ArrayList<Folder> getFolders() {
        return folders;
    }

    /**
     * Sets the folders
     *
     * @param folders
     */
    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    /**
     * Adds a folder to the list of custom folders
     *
     * @param folder
     * @throws IllegalArgumentException
     */
    public void addFolder(Folder folder) throws IllegalArgumentException {
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equalsIgnoreCase(folder.getName())) {
                throw new IllegalArgumentException("Folder with this name is already added.");
            }
        }
        if (folder.getName().equalsIgnoreCase("inbox") || folder.getName().equalsIgnoreCase("trash")) {
            throw new IllegalArgumentException("Cannot add folders with names \"Inbox\" or \"Trash\".");
        } else {
            folders.add(folder);
        }
    }

    /**
     * deleted a folder from the list of custom folders
     *
     * @param name
     * @throws IllegalArgumentException
     */
    public void deleteFolder(String name) throws IllegalArgumentException {
        if (name.equalsIgnoreCase("inbox") || name.equalsIgnoreCase("trash")) {
            throw new IllegalArgumentException("You cannot remove the Inbox or Trash folders.");
        }
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equalsIgnoreCase(name)) {
                folders.remove(folders.get(i));
                break;
            }
        }
    }

    /**
     * composeEmail method which creates the contents of the email
     */
    public void composeEmail() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter recipient (To): ");
        String to = input.nextLine();

        System.out.print("\nEnter carbon copy recipients (CC): ");
        String cc = input.nextLine();

        System.out.print("\nEnter blind carbon copy recipients (BCC): ");
        String bcc = input.nextLine();

        System.out.print("\nEnter subject line: ");
        String subject = input.nextLine();

        System.out.print("\nEnter body: ");
        String body = input.nextLine();

        GregorianCalendar time = new GregorianCalendar();
        Date date = new Date();
        time.setTime(date);

        Email email = new Email(to, cc, bcc, subject, body, time);

        inbox.addEmail(email);

        System.out.println("\nEmail successfully added to Inbox.");
    }

    /**
     * Moves an email to the trash
     *
     * @param email
     */
    public void deleteEmail(Email email) {
        trash.addEmail(email);
        System.out.println("\n\"" + email.getSubject() + "\" has been successfully moved to the trash.");
    }

    /**
     * Removes all emails from the trash folder.
     */
    public void clearTrash() {
        int numberOfItemsCleared = trash.getEmails().size();
        trash.getEmails().clear();
        System.out.println(numberOfItemsCleared + " item(s) successfully deleted.");
    }

    /**
     * moves an email to a different location
     *
     * @param email
     * @param target
     */
    public void moveEmail(Email email, Folder target) {
        if (!folders.contains(target)) {
            inbox.addEmail(email);

            System.out.println("\n\"" + email.getSubject() + "\" moved to Inbox.");
        } else {
            target.addEmail(email);

            System.out.println("\n\"" + email.getSubject() + "\" successfully moved to " + target.getName());
        }
    }

    /**
     * Gets the current folder
     *
     * @param name
     * @return
     */
    public Folder getFolder(String name) {
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equalsIgnoreCase(name))
                return folders.get(i);
        }
        return null;
    }

    /**
     * toString method that prints the content of the mailbox
     */
    public void toStringMailbox() {
        System.out.println("Mailbox:");
        System.out.println("--------");
        System.out.println("Inbox");
        System.out.println("Trash");
        for (int i = 0; i < folders.size(); i++) {
            System.out.println(folders.get(i).getName());
        }
    }

    /**
     * Main method used to display the menu to the user.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("mailbox.obj");

        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            mailbox = (Mailbox) objectInputStream.readObject();
            objectInputStream.close();
        } else {
            System.out.println("Previous save not found, starting with an empty mailbox.\n");
            mailbox = new Mailbox();
        }

        Scanner input = new Scanner(System.in);
        String choice = "";

        try {
            while (!choice.equalsIgnoreCase("Q")) {
                mailbox.toStringMailbox();
                System.out.println("\nA – Add folder");
                System.out.println("R – Remove folder");
                System.out.println("C – Compose email");
                System.out.println("F – Open folder");
                System.out.println("I – Open Inbox");
                System.out.println("T – Open Trash");
                System.out.println("E – Empty Trash");
                System.out.println("Q - Exit\n");
                System.out.print("Enter a user option: ");

                choice = input.nextLine();

                switch (choice.toUpperCase()) {
                    case "A":
                        System.out.print("\nEnter folder name: ");
                        Folder folder = new Folder(input.nextLine());
                        mailbox.addFolder(folder);

                        System.out.println();

                        break;
                    case "R":
                        System.out.print("Enter the folder you want to remove: ");
                        mailbox.deleteFolder(input.nextLine());
                        System.out.println();

                        break;
                    case "C":
                        mailbox.composeEmail();
                        System.out.println();

                        break;
                    case "F":
                        System.out.print("\nEnter folder name: ");
                        String fName = input.nextLine();

                        Folder f = mailbox.getFolder(fName);
                        f.printFolder();

                        System.out.println();

                        mailbox.subMenu(f);
                        break;
                    case "I":
                        mailbox.getInbox().printFolder();
                        mailbox.subMenu(mailbox.getInbox());

                        break;
                    case "T":
                        mailbox.subMenu(mailbox.getTrash());
                        System.out.println();

                        break;
                    case "E":
                        System.out.println();
                        mailbox.clearTrash();
                        System.out.println();

                        break;
                    case "Q":
                        System.out.println("\nProgram successfully exited and mailbox saved.");
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("mailbox.obj"));
        outputStream.writeObject(mailbox);
        outputStream.close();
        System.exit(0);
    }

    /**
     * prints the content of the folders
     */
    public void printFolders() {
        System.out.println("Folders: ");
        System.out.println(inbox.getName());
        System.out.println(trash.getName());
        for (int i = 0; i < folders.size(); i++) {
            System.out.println(folders.get(i).getName());
        }
    }

    /**
     * subMenu method which is accessed from within the original menu options
     *
     * @param folder
     */
    public void subMenu(Folder folder) {
        Scanner input = new Scanner(System.in);
        String choice = "";

        try {
            while (!choice.equalsIgnoreCase("R")) {
                System.out.println("\nM – Move email");
                System.out.println("D – Delete email");
                System.out.println("V – View email contents");
                System.out.println("SA – Sort by subject ascending");
                System.out.println("SD – Sort by subject descending");
                System.out.println("DA – Sort by date ascending");
                System.out.println("DD – Sort by date descending");
                System.out.println("R – Return to main menu\n");
                System.out.print("Enter a user option: ");

                choice = input.nextLine();

                switch (choice.toUpperCase()) {
                    case "M":
                        System.out.println();
                        System.out.print("Enter the index of the email to move: ");
                        int index = Integer.parseInt(input.nextLine());

                        System.out.println();


                        printFolders();

                        System.out.println();

                        Email email = folder.removeEmail(index - 1);

                        System.out.print("Select a folder to move \"" + email.getSubject() + "\" to: ");
                        String folderToMoveTo = input.nextLine();
                        mailbox.moveEmail(email, mailbox.getFolder(folderToMoveTo));

                        break;
                    case "D":
                        System.out.println();
                        System.out.print("Enter email index: ");
                        index = Integer.parseInt(input.nextLine());

                        mailbox.deleteEmail(folder.removeEmail(index - 1));

                        break;
                    case "V":
                        System.out.println();
                        System.out.print("Enter email index: ");
                        index = Integer.parseInt(input.nextLine());

                        System.out.println(folder.getEmails().get(index - 1).toString());

                        break;
                    case "SA":
                        folder.sortBySubjectAscending();

                        break;
                    case "SD":
                        folder.sortBySubjectDescending();

                        break;
                    case "DA":
                        folder.sortByDateAscending();

                        break;
                    case "DD":
                        folder.sortByDateDescending();

                        break;
                }
                if (!choice.equalsIgnoreCase("R")) {
                    folder.printFolder();
                } else {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }
}


