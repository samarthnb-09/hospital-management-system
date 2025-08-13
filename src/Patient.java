import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient
{
    private Connection con;
    private Scanner sc;

    Patient(Connection con , Scanner sc)
    {
        this.con=con;
        this.sc=sc;
    }

    public void addPatient()
    {
        try
        {
            String q="insert into Patient(name , age , gender) values(?,?,?)";
            System.out.println("Enter Name : ");
            sc.nextLine();
            String name =sc.nextLine();
            System.out.println("Enter Age : ");
            int age=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Gender : ");
            String gender=sc.next();
            sc.nextLine();

            PreparedStatement ps=con.prepareStatement(q);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,gender);

            int rows=ps.executeUpdate();

            if(rows>0)
            {
                System.out.println("Patient Added Successfully");
            }
            else
                System.out.println("Failed to add Patient");

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void viewPatients()
    {
        String q="select * from Patient";

        try
        {
           PreparedStatement ps=con.prepareStatement(q);

            ResultSet set=ps.executeQuery();

            System.out.println("+------+--------------------+---------+----------+");
            System.out.println("|  id  |     Name           |   Age   |  Gender  |");
            System.out.println("+------+--------------------+---------+----------+");
            while(set.next())
            {
                System.out.printf("| %-4s | %-18s | %-7s | %-8s |\n",set.getInt(1) , set.getString(2),set.getInt(3),set.getString(4));
            }
            System.out.println("+------+--------------------+---------+----------+");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean checkPatientById(int id)
    {
        String q="select * from patient where id=?";

        try
        {
            PreparedStatement ps=con.prepareStatement(q);
            ps.setInt(1,id);

            ResultSet set=ps.executeQuery();

            if(set.next())
                return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
