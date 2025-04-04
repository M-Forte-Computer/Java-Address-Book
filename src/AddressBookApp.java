import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }

    public void deleteContact(String name) {
        boolean found = false;
        Iterator<Contact> it = contacts.iterator();
        while (it.hasNext()) {
            Contact c = it.next();
            if (c.getName().equalsIgnoreCase(name)) {
                it.remove();
                found = true;
                System.out.println("Contact deleted successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    public void updateContact(String name, String newPhone, String newEmail) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.setPhone(newPhone);
                c.setEmail(newEmail);
                System.out.println("Contact updated successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    public void searchContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                System.out.println(c);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void exportContacts(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Contact c : contacts) {
                writer.println(c.getName() + "," + c.getPhone() + "," + c.getEmail());
            }
            System.out.println("Contacts exported successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting contacts: " + e.getMessage());
        }
    }

    public void importContacts(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    addContact(new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
            System.out.println("Contacts imported successfully.");
        } catch (IOException e) {
            System.out.println("Error importing contacts: " + e.getMessage());
        }
    }
}

public class AddressBookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        int choice;

        do {
            System.out.println("\n--- Address Book Menu ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Search Contact");
            System.out.println("6. Export Contacts");
            System.out.println("7. Import Contacts");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");


            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    addressBook.addContact(new Contact(name, phone, email));
                    break;
                case 2:
                    System.out.print("Enter name to delete: ");
                    addressBook.deleteContact(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter name to update: ");
                    String uname = scanner.nextLine();
                    System.out.print("Enter new phone: ");
                    String uphone = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String uemail = scanner.nextLine();
                    addressBook.updateContact(uname, uphone, uemail);
                    break;
                case 4:
                    addressBook.displayContacts();
                    break;
                case 5:
                    System.out.print("Enter name to search: ");
                    addressBook.searchContact(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter filename to export to: ");
                    addressBook.exportContacts(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Enter filename to import from: ");
                    addressBook.importContacts(scanner.nextLine());
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
