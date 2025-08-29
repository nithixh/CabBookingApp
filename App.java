import java.util.*;
class Users{
    String name;
    String email;
    String password;
    String phone;
    int rides;
    ArrayList<String> ride=new ArrayList<>();
    Users(){};
    Users(String name, String email, String password, String phone){
        this.name=name;
        this.email=email;
        this.password=password;
        this.phone=phone;
        rides++;
    }
    void display(){
        System.out.println("Name : "+name);
        System.out.println("email : "+email);
        System.out.println("Name : "+phone);
    }
}
class Drivers{
    int id;
    String name;
    String cab;
    boolean available;
    Drivers(int id,String name, String cab, boolean available){
        this.id=id;
        this.name=name;
        this.cab=cab;
        this.available=available;
    }
    void display(){
        System.out.println("ID : "+id);
        System.out.println("Name : "+name);
        System.out.println("Cab Type : "+cab);
    }
}
public class App {
    private static ArrayList<Users> users =new ArrayList<>();
    private static ArrayList<Drivers> drivers=new ArrayList<>();
    private static Scanner sc=new Scanner(System.in);
    private static double totalamount=0;
    private static int totalrides=0;
    private static void usersignup(){
        System.out.println("**********Enter Details for sign up**********\n");
        System.out.print("Enter name: ");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.print("Enter email: ");
        String email=sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone=sc.nextLine();
        while(phone.length()!=10){
            System.out.print("Enter valid phone number : ");
            phone=sc.nextLine();
        }
        System.out.print("Enter password: ");
        String password=sc.nextLine();
        while(password.equals(email) || password.equals(phone)){
            System.out.print("Enter a strong password : ");
            password=sc.nextLine();
        }
        for(Users u:users){
            if (u.email.equals(email) && u.phone.equals(phone)){
                System.out.println("User with the given email and phone number already exists... Try again with another email\n");
                return;
            }
            if (u.email.equals(email) && !u.phone.equals(phone)){
                System.out.println("User with the given email already exists... Try again with another email\n");
                return;
            }
            if (!u.email.equals(email) && u.phone.equals(phone)){
                System.out.println("User with the given phone number already exists... Try again with another email\n");
                return;
            }
        }
        Users newuser=new Users(name,email,password,phone);
        users.add(newuser);
        System.out.println("Signup successful! Proceed to login\n");
    }
    private static String userlogin(){
        System.out.println("**********Enter Details for login**********\n");
        System.out.print("Enter email/phone number : ");
        sc.nextLine();
        String email=sc.nextLine();
        System.out.print("Enter password : ");
        String password=sc.nextLine();
        for(Users u:users){
            if(u.email.equals(email) && u.password.equals(password)){
                System.out.println("Login successful!\n");
                return u.email;
            }
            if(u.phone.equals(email) && u.password.equals(password)){
                System.out.println("Login successful!\n");
                return u.email;
            }
        }
        System.out.println("Login failed! Invalid credentials\n");
        return null;
    }
    private static void userdashboard(String email){
        Users user=new Users();
        for(Users u: users){
            if(u.email.equals(email)){
                user=u;
                break;
            }
        }
        System.out.println("Welcome! "+user.name+"\n");
        while(true){
            System.out.println("1. Book Cab");
            System.out.println("2. My Rides");
            System.out.println("3. Logout");
            System.out.print("Choose an option : ");
            int option=sc.nextInt();
            if(option==1){
                //System.out.println("Book Cabs");
                bookcab(user);
            }
            else if(option==2){
                //System.out.println("My Rides");
                myrides(user);
            }
            else if(option==3){
                System.out.println("Logging out...\n");
                break;
            }else{
                System.out.println("Invalid option\n");
            }
        }
    }
    private static double fare(double distance){
        return distance*10;
    }
    private static boolean adminlog(){
        System.out.print("Enter Admin name : ");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.print("Password : ");
        String pass=sc.nextLine();
        if((name.equals("nirmalkumar")&& pass.equals("1234")) || (name.equals("nithishkumar")&& pass.equals("5678"))){
            System.out.println("log in successful");
            return true;
        }
        System.out.println("Invalid username or password");
        return false;
    }
    private static void admindash(){
        System.out.println("**********ADMIN DASHBOARD**********\n");
        int option;
        while(true){
            System.out.println("1. Manage Drivers");
            System.out.println("2. View Rides");
            System.out.println("3. Reports");
            System.out.println("4. Logout");
            System.out.print("Enter an option : ");
            option=sc.nextInt();
            if(option==1){
                managedrivers();
            }
            else if(option==2){
                viewrides();
            }else if(option==3){
                report();
            }else if(option==4){
                System.out.println("Logging out...\n");
                return;
            }else{
                System.out.println("Invalid option\n");
            }
        }
    }
    private static void report(){
        System.out.println("Total drivers : "+drivers.size());
        System.out.println("Total Rides : "+totalrides);
        System.out.println("Earnings : "+totalamount+"\n");
    }
    private static void managedrivers(){
        while(true){
            System.out.println("1. Display Driver details");
            System.out.println("2. Add drivers");
            System.out.println("3. Remove drivers");
            System.out.println("4. Exit");
            System.out.print("Enter an option : ");
            int option=sc.nextInt();
            if(option==1){
                for(Drivers d:drivers){
                    d.display();
                    System.out.println("----------------------------------");
                }
                System.out.println();
            }else if(option==2){
                System.out.print("Enter ID : ");
                int id=sc.nextInt();
                System.out.print("Enter Name : ");
                sc.nextLine();
                String name=sc.nextLine();
                System.out.print("Enter Cab Type : ");
                String cab=sc.nextLine();
                drivers.add(new Drivers(id, name, cab, true));
                System.out.println("Driver added successfully\n");
            }
            else if(option==3){
                System.out.print("Enter ID to remove : ");
                sc.nextLine();
                int id=sc.nextInt();
                for(Drivers d: drivers){
                    if(d.id==id){
                        drivers.remove(d);
                        System.out.println("Driver removed successfully\n");
                        break;
                    }
                }
            }else if(option==4){
                System.out.println("Exit\n");
                break;
            }else{
                System.out.println("Invalid Option\n");
            }
        }
    }
    private static void viewrides(){
        for(Users u: users){
            System.out.println("USER : "+u.name);
            System.out.println("Pickup     Drop     Cab type     Driver's name     Distance     Fare");
            
            for(String s:u.ride){
                System.out.println(s);
            }
        
        
        }
    }
    private static void bookcab(Users user){
        System.out.print("Enter pickup location : ");
        sc.nextLine();
        String pick=sc.nextLine();
        System.out.print("Enter drop location : ");
        String drop=sc.nextLine();
        while(pick.equals(drop)){
            System.out.println("Pick up and Drop locations should not be same");
            System.out.print("Enter a valid drop location : ");
            drop=sc.nextLine();
        }
        System.out.print("Enter cab type [Sedan/Mini/SUV] : ");
        String cab=sc.nextLine();
        System.out.print("Enter distance : ");
        double distance=sc.nextDouble();
        System.out.println("\n**********Driver Details**********\n");
        for(Drivers d: drivers){
            if(d.available==true){
                d.display();
                System.out.println("------------------------------");
            }
        }
        System.out.println();
        System.out.print("Enter driver's name to book : ");
        sc.nextLine();
        String n=sc.nextLine();
        System.out.println("==========JOURNEY DETAILS==========\n");
        System.out.println("Pickup location : "+pick);
        System.out.println("Drop location : "+drop);
        System.out.println("Cab type : "+cab);
        System.out.println("Driver's name : "+n);
        System.out.println("Distance : "+distance);
        System.out.println("Fare : "+fare(distance));
        System.out.print("Confirm?[yes/no] : ");
        String confirm=sc.nextLine();
        if(confirm.equals("yes") || confirm.equals("YES") || confirm.equals("Yes")){
            user.ride.add(pick+"     "+drop+"     "+cab+"     "+n+"     "+distance+"     "+fare(distance));
            totalamount+=fare(distance);
            totalrides++;
        }else{
            System.out.println("Ride cancelled...\n");
        }
    }
    private static void myrides(Users u){
        System.out.println("**********MY RIDES**********\n");
        System.out.println("Pickup     Drop     Cab type     Driver's name     Distance     Fare");
        System.out.println("-----------------------------------------------------------------------\n");
        for (String s:u.ride){
            System.out.println(s);
            System.out.println("----------------------------------------------------------");
        }
        System.out.println();
    }
    public static void main(String[] args){
        drivers.add(new Drivers(1,"Nithish","Sedan",true));
        drivers.add(new Drivers(2,"Nirmal","Mini",true));
        drivers.add(new Drivers(3,"Loganath","SUV",true));
        users.add(new Users("Nithish","nithish@gmail.com","1234","1234567890"));
        users.add(new Users("Nirmal","nirmal@gmail.com","5678","0987654321"));
        System.out.println("Welcome to Cab Booking App");
        while(true){
            System.out.println("1. User Signup");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4 .Exit");
            System.out.print("Choose an option : ");
            int option=sc.nextInt();
            if(option==1){
                usersignup();
            }
            else if(option==2){
                String log=userlogin();
                if(log!=null){
                    userdashboard(log);
                }
            }
            else if(option==3){
                boolean adminlog=adminlog();
                if(adminlog==true){
                    admindash();
                }
            }
            else if(option==4){
                System.out.println("Exit");
                break;
            }else{
                System.out.println("invalid option, choose a valid option\n");
            }
        }
    }
}