package com.main;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import com.bean.UserRegistration;
import com.dao.UserDAO;

public class Application {
	public static void main(String[] args) throws SQLException, Exception {
		UserDAO ud=new UserDAO();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("---------------------WELCOME TO SIMPLE CHAT APPLICATION -------------------------");
			boolean option = true;
			while (option) {
				System.out.println("PRESS 1 REGISTRATION ");
				System.out.println("PRESS 2 LOGIN");
				System.out.println("PRESS 3 EXIT");
				System.out.println("----------------------------ENTER THE OPTION-----------------------------");
				int inneroption = Integer.parseInt(sc.nextLine());
				switch (inneroption) {
				case 1:
					System.out.println("ENTER THE USER NAME");
					String username = sc.nextLine();
					System.out.println("CREATE PASSWORD");
					String password = sc.nextLine();
					System.out.println("ENTER MOBILE NUMBER");
					long mobileno = sc.nextLong();
					sc.nextLine();
					System.out.println("ENTER YOUR EMAIL");
					String email=sc.nextLine();
					Random random = new Random();
					int userid = random.nextInt(99999);
					ud.RegisterUser(new UserRegistration(username,password,mobileno,userid,email));
					break;
				case 2:
					System.out.println("ENTER THE USER NAME");
					String uname = sc.nextLine();
					System.out.println("ENTER THE PASSWORD");
					String pass = sc.nextLine();
					ud.LoginUser(uname, pass);
					break;
				case 3:
					option = false;
					System.out.println("THANK YOU");
					break;

				default:
					System.out.println("INVALID OPTION");
					break;
				}
			}
		}

	}


		

	}


