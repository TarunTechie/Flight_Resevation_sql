import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
public class passenger extends Flight
{
    static Scanner sc = new Scanner(System.in);
    static File p_file=new File("lib//Passenger.txt");
    static String p_details[]={"ADHAR NUMBER","PASSENGER NAME","EMAIL ID","PHONE NUMBER","ADDRESS","DATE OF BIRTH"};
    static String passenger[]=new String[10];//contains the details from the file
    //adds passenger

   public void Add_passenger()throws FileNotFoundException
    {
        String details[]=new String[10];
        PrintWriter pw=new PrintWriter(p_file);
        System.out.println("Enter the number of passengers");
        int n=sc.nextInt();
        for(int i=0;i<n;i++)
        {
            String temp="";
            System.out.println("PASSENGER "+(i+1));
            for(String d:p_details)
            {
                System.out.println("Enter "+d);
                temp=temp+sc.next()+",";
            }
            details[i]=temp;
        }
        for(String d:details)
        {
            if(d==null)
            break;
            pw.write(d+'#');//writes to the file
        }
        pw.close();
    }
    //displays passenger details
    public void Display_details()
    {
        System.out.println(Arrays.toString(p_details).replace(",",'\t'+"|"));
        for(String d:passenger)
        {
            if (d==null)
            break;
            System.out.println(d.replace(",","\t"+"|"));
        }
    }
    //deleted passenger
    public void Delete_Passenger(String an)throws FileNotFoundException
    {
        PrintWriter pw=new PrintWriter(p_file);
        boolean find=true;
        for(int i=0;i<passenger.length;i++)
        {
            if(passenger[i]==null)
            break;
            if(passenger[i].startsWith(an))
            {
                passenger[i]="del";
                find=false;
                break;
            }
        }
        if(find)
        {
            System.out.println("ADHAR NUMBER NOT FOUND");
        }
        else
        {
        for(String d:passenger)
        {
            if(d==null)
            {
                break;
            }
            else if(d.equals("del"))
            {
                continue;
            }
            else
            {
                pw.write(d+"#");
            }
        }
        }
        pw.close();
    }
    //searchs for passenger
    public void Search_passenger(String an)
    {
        boolean f=true;
        for(String d:passenger)
        {
            if(d==null)
            break;
            if(d.startsWith(an))
            {
                System.out.println(Arrays.toString(p_details).replace(",",'\t'+"|"));
                System.out.println(d.replace(",",'\t'+"|"));
                f=false;
                break;
            }
        }
        if(f)
            System.out.println("PASSENGER NOT FOUND");
    }
    //modifies passenger details
    public void Modify_Passenger(String an)throws FileNotFoundException
    {
        PrintWriter pw=new PrintWriter(p_file);
        boolean find=true;
        String temp[]=new String[p_details.length];
        String changed="";
        int p=0;
        for(int i=0;i<passenger.length;i++)
        {
            if(passenger[i]==null)
            break;
            if(passenger[i].startsWith(an))
            {
                temp=passenger[i].split(",");
                find=false;
                p=i;
                break;
            }
        }
            if(find)
            {
                System.out.println("ADHAR NUMBER NOT FOUND");
            }
            else
            {
                System.out.println(Arrays.toString(temp));
                for (int i=0;i<p_details.length;i++) 
                {
                    System.out.println("Enter "+i+" to change "+p_details[i]);
                }
                int m=sc.nextInt();
                System.out.println("ENTER THE NEW "+p_details[m]);
                temp[m]=sc.next();
            for(String d:temp)
            {
                changed=changed+d+",";
            }
            passenger[p]=changed;
            for(String d:passenger)
            {
                if(d==null)
                break;
                pw.write(d+'#');//writes to the file
            }
           }
            pw.close();
        }
    //reads the file
    public void read_passenger() throws FileNotFoundException
    {
        int n=0;
        Arrays.fill(passenger,null);
        Scanner f=new Scanner(p_file);
        f.useDelimiter("#");
        while(f.hasNext())
        {
            passenger[n]=f.next();
            n++;
        }
    }
    //calls all the functions under the passenger module
    public void passengercall()throws FileNotFoundException
    {
        passenger p=new passenger();
        boolean control=true;
        while(control)
        {
            System.out.println("Enter 1 to Add A Passenger");
            System.out.println("Enter 2 to Display Passenger Details");
            System.out.println("Enter 3 to Delete A Passenger");
            System.out.println("Enter 4 to Search For A Passenger");
            System.out.println("Enter 5 to Modify Details of a passenger");
            System.out.println("Enter 0 to exit passenger details module");
            int ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                    p.Add_passenger();
                    break;
                case 2:
                    p.read_passenger();
                    p.Display_details();
                    break;
                case 3:
                    p.read_passenger();
                    System.out.print("Enter the ADHAR NUMBER of the passenger to be deleted- ");
                    p.Delete_Passenger(sc.next());
                    break;
                case 4:
                    p.read_passenger();
                    System.out.print("Enter the ADHAR NUMBER of the passenger to search- ");
                    p.Search_passenger(sc.next());
                    break;
                case 5:
                    p.read_passenger();
                    System.out.print("Enter the ADHAR NUMBER of the passenger to modify details- ");
                    p.Modify_Passenger(sc.next());
                    break;
                case 0:
                    control=false;
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }
    }
}