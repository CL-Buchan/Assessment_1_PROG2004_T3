import java.time.LocalTime; // Found on and implemented from: https://www.w3schools.com/java/java_date.asp
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Assessment 1 for PROG2004
 * Author: Callam Buchan
 * Student ID: 24427353
 * Creation Date: 14/07/2025
 */

public class CBuchan_A1 {
    
    /* Part 3 – Using classes and objects 
     *
     * Main function for handling program functionality
    */
    public static void main(String[] args) {
        // Parent class objects
        HealthProfessional surgeon = new HealthProfessional(101, "Henry", "Heart Surgeon");
        HealthProfessional physio = new HealthProfessional(102, "Bill", "Rehabilitation");
        HealthProfessional chiropractor = new HealthProfessional(103, "Christina", "Rehabilitation");

        // Child class objects
        GP gp = new GP(104, "Gregory");
        Nurse nurse = new Nurse(105, "Ruby");

        // Stores objects in array
        HealthProfessional[] professionals = {surgeon, physio, chiropractor, gp, nurse};

        // Prints class print methods through parent and child inheritance through for loop iteration
        for (HealthProfessional prof : professionals) {
            prof.printMethod();
        }
        
        System.out.println("------------------------------");

        Appointment appointment = new Appointment();
        
        ArrayList<Appointment> apptObjects = new ArrayList<>(); // Moved higher outside pt.5 for higher positioning to access in prev method

        // Part 5 – Collection of appointments

        // Avaliable times for patient to choose from
        ArrayList<LocalTime> avaliableTimes = new ArrayList<LocalTime>(Arrays.asList(
            LocalTime.of(9, 30), 
            LocalTime.of(12, 30),
            LocalTime.of(14, 30)
        ));

        // Create new appointments
        Appointment appointment1 = new Appointment("Alice", "0412345678", null, gp);
        apptObjects.add(appointment1);
        appointment1.checkPhoneNumber();
        appointment1.chooseAppointmentTime(avaliableTimes, apptObjects);

        Appointment appointment2 = new Appointment("Lisa", "04059678940", null, chiropractor);
        apptObjects.add(appointment2);
        appointment2.checkPhoneNumber();
        appointment2.chooseAppointmentTime(avaliableTimes, apptObjects);

        System.out.println("Current appointments: ");

        for (int a = 0; a < apptObjects.size(); a++) {
            System.out.println((a + 1) + ". "); // Shows index of each appointment from 1 not 0
            apptObjects.get(a).printAppointment();
            System.out.println(" "); // To add space between appointments for easier reading
        }

        Appointment appointment3 = new Appointment("Anthony", "0409786912", null, gp);
        apptObjects.add(appointment3);
        appointment3.checkPhoneNumber();
        appointment3.chooseAppointmentTime(avaliableTimes, apptObjects);

        Appointment appointment4 = new Appointment("Christie", "0494231586", null, gp);
        apptObjects.add(appointment4);
        appointment4.checkPhoneNumber();
        appointment4.chooseAppointmentTime(avaliableTimes, apptObjects);

        Appointment appointment5 = new Appointment("Robbie", "0478429186", null, gp);
        apptObjects.add(appointment5);
        appointment5.checkPhoneNumber();
        appointment5.chooseAppointmentTime(avaliableTimes, apptObjects);

        Appointment appointment6 = new Appointment("Rebecca", "0428563910", null, gp);
        apptObjects.add(appointment6);
        appointment6.checkPhoneNumber();
        appointment6.chooseAppointmentTime(avaliableTimes, apptObjects);

        Appointment appointment7 = new Appointment("Beth", "0458739876", null, physio);
        apptObjects.add(appointment7);
        appointment7.checkPhoneNumber();
        appointment7.chooseAppointmentTime(avaliableTimes, apptObjects);

        Appointment appointment8 = new Appointment("Adam", "0428462857", null, nurse);
        apptObjects.add(appointment8);
        appointment8.checkPhoneNumber();
        appointment8.chooseAppointmentTime(avaliableTimes, apptObjects);

        appointment.printExistingAppointments(apptObjects);

        System.out.println("\nWould you like to cancel any existing appointments?: ");
        appointment.cancelAppointment(apptObjects);

        String[] profDepartments = {
            "Heart Surgeon", 
            "Rehabilitation",
            "General Practice Clinic", 
            "Nurses Ward"
        };

        ArrayList<String> profNames = new ArrayList<>(); // Array list to remove duplicate names

        for (int a = apptObjects.size() - 1; a >= 0; a--) { // Help from: https://www.geeksforgeeks.org/java/java-iterate-array-in-reverse-order/
            String apptDept = apptObjects.get(a).getDesiredProfessional().department;
            
            // Iterates through profDepartments strings
            for (int pd = 0; pd < profDepartments.length; pd++) {
                if (apptDept.equals(profDepartments[pd])) {
                    String profName = apptObjects.get(a).getDesiredProfessional().name;
                    
                    // Check professional name is not in current professional array list
                    if (!profNames.contains(profName)) {
                        profNames.add(profName);
                    }
                    break; // Break/exit from for loop
                }
            }
        }

        // Iterates through professionals names in profNames array list
        System.out.println("\nCurrent professionals linked with appointments: ");
        for (String name : profNames) {
            System.out.println("- " + name);
        }

        System.out.println("\nFinal appointment list: ");
        appointment.printExistingAppointments(apptObjects);
    }
}


// Main HealthProfessional class
class HealthProfessional {
    int ID;
    String name;
    String department;

    // Default constructor
    public HealthProfessional() {
        ID = 0;
        name = "Unknown";
        department = "None";
    }

    // Initializer constructor
    public HealthProfessional(int ID, String name, String department) {
        this.ID = ID;
        this.name = name;
        this.department = department;
    }
    
    // Print method
    public void printMethod() {
        System.out.println("The health professionals' name is: " + name + ", their ID is: " + ID + ", and their allocated department is: " + department);
    }
}


// GP class which inherits HealthProfessional attributes
class GP extends HealthProfessional {
            
    // Default constructor
    public GP() {
        super(0, "Unknown", "None");
    }

    // Initializer constructor
    public GP(int ID, String name) {
        super(ID, name, "General Practice Clinic");
    }

    /* Print method
     * @Override decorator to show the parent method being changed
    */
    @Override
    public void printMethod() {
        System.out.println("The GPs' name is: " + name + ", their ID is: " + ID + ", and their title is: " + department);
    }
}


// GP class which inherits HealthProfessional attributes
class Nurse extends HealthProfessional {

    // Default constructor
    public Nurse() {
        super(0, "Unknown", "None");
    }

    // Initializer constructor
    public Nurse(int ID, String name) {
        super(ID, name, "Nurses Ward");
    }

    /* Print method
     * @Override decorator to show the parent method being changed
    */
    @Override
    public void printMethod() {
        System.out.println("The nurses' name is: " + name + ", their ID is: " + ID + ", and their title is: " + department);
    }
}


// Appointment setting class
class Appointment extends HealthProfessional{

    // Private variables as they are only to be utilised within this class
    private String patientName;
    private String phoneNumber;
    private LocalTime preferredTime;
    private HealthProfessional desiredProfessional;
    
    // Default constructor
    public Appointment() {
        name = "Unknown";
        phoneNumber = "0";
        preferredTime = LocalTime.of(0, 0);
        desiredProfessional = null;
    }

    // Initializer constructor
    public Appointment(String patientName, String phoneNumber, LocalTime preferredTime, HealthProfessional desiredProfessional) {
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.preferredTime = preferredTime;
        this.desiredProfessional = desiredProfessional;
    }

    public void checkPhoneNumber() {
        Scanner ph = new Scanner(System.in);

        String digitsOnly = phoneNumber.replaceAll("\\D", "");

        if (digitsOnly.length() > 10) {
            System.out.println("\nPhone number for " + patientName + " has too many characters.");
            phoneNumber = "";

            while (phoneNumber.isEmpty()) {
                System.out.println("Enter a phone number with 10 digits:");
                String newPh = ph.nextLine();
                String newDigits = newPh.replaceAll("\\D", "");

                if (newDigits.length() > 10) {
                    System.out.println("\nError: Too many digits. Try again.");
                } else if (newDigits.isEmpty()) {
                    System.out.println("\nError: No phone number entered.");
                } else {
                    phoneNumber = newPh;
                    System.out.println("\nNew phone number set for " + patientName);
                }
            }
        }
    }

    // Sets healthprofessional
    public void selectProfessional(HealthProfessional selectedProfessional) {
        this.desiredProfessional = selectedProfessional;
    }

    // Gets healthprofessional
    public HealthProfessional getDesiredProfessional() {
        return this.desiredProfessional;
    }

    // Method to choose appointment times
    public void chooseAppointmentTime(ArrayList<LocalTime> avaliableTimes, ArrayList<Appointment> apptObjects) {
        Scanner chooseTime = new Scanner(System.in);

        if (avaliableTimes.size() < 1) {
            System.out.println("\nNo current avaliable times.");

        } else {
            System.out.println("\nChoose a time: ");

            // Iterates through patient times
            for (int t = 0; t < avaliableTimes.size(); t++) {
                System.out.println((t + 1) + ". " + avaliableTimes.get(t)); // Gets the index of each appointment but starts at 1 not 0
            }

            // Patient to choose time
            int chosenTime = chooseTime.nextInt();

            // Checks if chosenTime is greater than 1 and the chosenTime int is less than the avaliableTimes arrays total length
            if (chosenTime >= 1 && chosenTime <= avaliableTimes.size()) {
                LocalTime formatTime = avaliableTimes.get(chosenTime - 1);
            
                boolean timeTaken = false;

                for (Appointment appt : apptObjects) { // Accesses Appointment class and adds a var name associated to access the arrayList. Help from: https://www.w3schools.com/java/java_howto_loop_through_arraylist.asp
                    if (appt.getChosenTime() != null && appt.getChosenTime().equals(formatTime)) { // Compares the local times to see if they are equal. Help from: https://www.geeksforgeeks.org/java/difference-between-and-equals-method-in-java/
                        timeTaken = true;
                        break;
                    }
                }

                // Checks if timeTaken is false otherwise prints appointment set time
                if (timeTaken) {
                    System.out.println("That time is already taken. Please choose another.");
                } else {
                    avaliableTimes.remove(chosenTime - 1);
                    this.preferredTime = formatTime;
                    System.out.println("Appointment time set to: " + formatTime);

                    System.out.println("\nAvaliable times left: ");

                    for (int t = 0; t < avaliableTimes.size(); t++) {
                        System.out.println((t + 1) + ". " + avaliableTimes.get(t));
                }
            }

            } else {
                System.out.println("Invalid choice of time.");
            }
        }
    }

    public LocalTime getChosenTime() {
        return this.preferredTime;
    }

    // Create appointment
    public boolean createAppointment(String patientName, String phoneNumber, LocalTime preferredTime, HealthProfessional desiredProfessional, ArrayList<Appointment> apptObjects) {
        if (patientName == null || patientName.isEmpty() ||
            phoneNumber == null || phoneNumber.isEmpty() ||
            preferredTime == null || desiredProfessional == null) {
            System.out.println("Cannot create appointment: insufficient information.");
            return false;
        }

        Appointment newAppointment = new Appointment(patientName, phoneNumber, preferredTime, desiredProfessional);
        apptObjects.add(newAppointment);
        return true;
    }

    // Cancel appointment
    public boolean cancelAppointment(ArrayList<Appointment> apptObjects) {
        Scanner cancelAppt = new Scanner(System.in);

        System.out.println("\nEnter mobile number for appointment to cancel: ");
        String mobileNumRef = cancelAppt.nextLine();

        // Uses for loop to iterate through apptObjects array
        for (int i = 0; i < apptObjects.size(); i++) {
            Appointment appt = apptObjects.get(i); // Uses get to get the index where its seen the number

            if (appt.phoneNumber.equals(mobileNumRef)) {
                apptObjects.remove(i); // Removes the whole appointment based on the memorised index
                System.out.println("Appointment cancelled for: " + appt.patientName);
                
                System.out.println("\nUpdated current appointments: ");
                
                for (int r = 0; r < apptObjects.size(); r++) {
                    System.out.println(" ");
                    apptObjects.get(r).printAppointment();
                }

                return true;
            }
        }
        
        // If no number is found it returns false and prints error message
        System.out.println("No appointment found with that phone number.");
        return false;
    }

    // Prints existing appointments
    public void printExistingAppointments( ArrayList<Appointment> apptObjects) {
        System.out.println("\nAll current appointments: ");
        
        for (int a = 0; a < apptObjects.size(); a++) {
            System.out.println(" ");
            apptObjects.get(a).printAppointment();
        }

        System.out.println("------------------------------");
    }

    // Print all instance variables in Appointment class
    public void printAppointment() {
        System.out.println("Your name: " + patientName);
        System.out.println("Your phone number: " + phoneNumber);
        System.out.println("Preferred time: " + preferredTime);
        System.out.println("Chosen health professional: " + desiredProfessional.name + ", Department/Ward: " + desiredProfessional.department);
    }
}
