import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM");
        Scanner sc=new Scanner(System.in);

        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/Hospital";
        String uname="root";
        String pass="snbawage@123";

        try
        {
            Connection con= DriverManager.getConnection(url ,uname , pass);

            Patient p=new Patient(con, sc);
            Doctor doc=new Doctor(con,sc);
            Appointment ap=new Appointment(con);

            while(true)
            {
                System.out.println("1.Add Patient");
                System.out.println("2.View Patients");
                System.out.println("3.Check Patient By Id");
                System.out.println("4.View Doctors");
                System.out.println("5.Check Doctor By Id");
                System.out.println("6.Book Appointment");
                System.out.println("7.View Appointments");
                System.out.println("8.Exit");


                System.out.println("Enter a Operation: ");
                int choice=sc.nextInt();

                switch (choice) {
                    case 1:
                        p.addPatient();
                        break;
                    case 2:
                        p.viewPatients();
                        break;
                    case 3:
                        System.out.println("Enter a Id to Check Available or Not");
                        int id = sc.nextInt();
                        if (p.checkPatientById(id))
                            System.out.println("Patient is Present");
                        else
                            System.out.println("patient not Found");
                        break;
                    case 4:
                        doc.viewDoctors();
                        break;
                    case 5:
                        System.out.println("Enter a Id to Check Doctor Available or Not");
                        int d_id = sc.nextInt();
                        if (doc.checkDoctorById(d_id))
                            System.out.println("Doctor is Present");
                        else
                            System.out.println("Doctor not Found");
                        break;
                    case 6:
                          {
                            System.out.println("Enter Patient Id :");
                            int p_id=sc.nextInt();
                              System.out.println("Enter Doctor's Id: ");
                              int doc_id=sc.nextInt();
                              Appointment app=new Appointment(con,sc,p_id,doc_id,p,doc);
                              app.bookAppointment();
                            break;
                           }
                    case 7:
                         ap.viewAppointments();
                         break;
                    case 8:
                        System.out.println("Exited Successfully");
                        break;
                    default:
                        System.out.println("Choose a valid option");
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}