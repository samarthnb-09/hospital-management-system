import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment
{
    int d_id;
    int p_id;
    Connection con;
    Patient p;
    Doctor d;
    Scanner sc;

    Appointment(Connection con,Scanner sc,int p_id , int d_id , Patient p , Doctor d)
    {
        this.con=con;
        this.sc=sc;
        this.d_id=d_id;
        this.p_id=p_id;
        this.p=p;
        this.d=d;
    }

    Appointment(Connection con)
    {
        this.con=con;
    }

    public boolean bookAppointment()
    {
        if(!p.checkPatientById(p_id))
        {
            System.out.println("Invalid Patient Id");
            return false;
        }
        if(!d.checkDoctorById(d_id))
        {
            System.out.println("Invalid Doctor Id");
            return false;
        }

        String q="select * from Appointment where date=?";
        String add="insert into Appointment(p_id,d_id,Date) values (?,?,?)";
        System.out.println("Enter Date of Appointment(YYYY-MM-DD):");
        String date=sc.next();
        try
        {
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1,date);

            ResultSet set=ps.executeQuery();

            if(set.next())
            {
                System.out.println("Date is Already Booked...Try Another Date");
                return false;
            }
            else
            {
                PreparedStatement insert=con.prepareStatement(add);

                insert.setInt(1,p_id);
                insert.setInt(2,d_id);
                insert.setString(3,date);

                int rows=insert.executeUpdate();
                if(rows<0){
                    System.out.println("Failed to book Appointmnet");
                    return false;
                }
                else
                {
                System.out.println("Appointment Booked");
                }
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public void viewAppointments()
    {
        String q="select * from appointment";
        try
        {
        PreparedStatement ps=con.prepareStatement(q);

        ResultSet set=ps.executeQuery();

            System.out.println("+--------+-------------+---------+-------------+");
            System.out.println("|  A_id  |    p_id     |   d_id  |  Date       |");
            System.out.println("+------+---------------+---------+-------------+");
            while(set.next())
            {
                System.out.printf("| %-6s | %-11s | %-7s | %-11s |\n",set.getInt(1) , set.getInt(2),set.getInt(3),set.getString(4));
            }
            System.out.println("+------+---------------+---------+-------------+");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
