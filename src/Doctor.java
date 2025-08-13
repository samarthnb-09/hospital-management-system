import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor
{
    Connection con;
    Scanner sc;

    Doctor(Connection con , Scanner sc)
    {
        this.con=con;
        this.sc=sc;
    }

    public void viewDoctors()
    {
        String q="select * from Doctors";
        try
        {
        PreparedStatement ps=con.prepareStatement(q);
            ResultSet set=ps.executeQuery();

            System.out.println("+------+--------------------+------------------+");
            System.out.println("|  id  |     Name           |   Specialization |");
            System.out.println("+------+--------------------+------------------+");
            while(set.next())
            {
                System.out.printf("| %-4s | %-18s | %-16s |\n",set.getInt(1) , set.getString(2),set.getString(3));
            }
            System.out.println("+------+--------------------+------------------+");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    public boolean checkDoctorById(int d_id)
    {
        String q="select * from Doctors where id=?";

        try
        {
            PreparedStatement ps=con.prepareStatement(q);
            ps.setInt(1,d_id);

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
