package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.Connection.DBConnection;
import com.bean.UserRegistration;


public class UserDAO {
		DBConnection dbconnection = new DBConnection();
		MessageDAO md = new MessageDAO();
		public void RegisterUser(UserRegistration UserRegistration) throws SQLException {
			String Query = "insert into  register values(?,?,?,?,?)";
			try (Connection connection = dbconnection.getConnection();
					PreparedStatement preparedstatement = connection.prepareStatement(Query);) {
				preparedstatement.setString(1, UserRegistration.getUsername());
				preparedstatement.setString(2, UserRegistration.getPassword());
				preparedstatement.setLong(3, UserRegistration.getMobileno());
				preparedstatement.setInt(4, UserRegistration.getUserid());
				preparedstatement.setString(5, UserRegistration.getEmail());
				int rs = preparedstatement.executeUpdate();
				if (rs > 0) {
					System.out.println("REGISTER SUCCESSFULLY....!!");
					System.out.println("PLEASE SAVE REGISTRATION ID:" + UserRegistration.getUserid());
				} else {
					System.out.println("SOMETHING WENT WRONG.....");
				}
			}
		}
	


		public void LoginUser(String uname, String pass) throws SQLException, Exception {
			String sql = "select * from register where username=? and password=?";
			try (Connection connection = dbconnection.getConnection();
					PreparedStatement preparedstatement = connection.prepareStatement(sql);) {
				preparedstatement.setString(1, uname);
				preparedstatement.setString(2, pass);
				ResultSet resultSet = preparedstatement.executeQuery();
				if (resultSet.next()) {
					System.out.println("Login successfully-----!!!.");
					boolean loginuser = true;
					while (loginuser) {
						System.out.println("----------------------WELCOME TO USER MESSAGING AND RECIEVING PANEL-: "
								+ resultSet.getString(1) + "------------\n");
						System.out.println(" 1.TO SEE REGISTERED CONTACT DETAILS");
						System.out.println(" 2.TO SEND MESSAGES");
						System.out.println(" 3.TO RECIEVE MESSAGES");
						System.out.println(" 4.TO GROUPCHAT ");
						System.out.println(" 5.TO SEE AND UPDATE PROFILE ");
						System.out.println(" 6.TO EXIT ");
						System.out.println("...................ENTER THE OPTION .....................");
						Scanner sc = new Scanner(System.in);
						int useroption = Integer.parseInt(sc.nextLine());
						switch (useroption) {
						case 1:
							System.out.println("-----------VIEW ALL CONTACT DETAILS----------");
							List<UserRegistration> list = md.getAllContacts();
							for (UserRegistration UserRegistration : list) {
								System.out.println("-----REGISTERED CONTACT DETAILS-----");
								System.out.println("USERNAME=" + UserRegistration.getUsername());
								System.out.println("MOBILE NUMBER=" + UserRegistration.getMobileno());
								System.out.println("----------------------------");
							}
							break;
						case 2:
							System.out.println("-----WELCOME THE SENDING PANEL----");
							System.out.println("ENTER THE SENDER NAME");
							String sender_username = sc.nextLine();
							System.out.println("ENTER THE RECIEVER NAME");
							String reciever_username = sc.nextLine();
							System.out.println("ENTER MESSAGE ");
							String message_text = sc.nextLine();
							md.sendMessage(sender_username, reciever_username, message_text);
							break;
						case 3:
							System.out.println("-----WELCOME THE RECIEVING PANEL----");
							System.out.println("ENTER THE RECIEVER NAME");
							String reciever_username1 = sc.nextLine();
							md.receiveMessages(reciever_username1);
							break;
						case 4:
							Statement stmt = null;
							stmt = connection.createStatement();
							int choice;
							do {
								System.out.println("1. CREATE GROUP");
								System.out.println("2. JOIN GROUP");
								System.out.println("3. SEND MESSAGE");
								System.out.println("4. ADD MEMBER");
								System.out.println("5. EXIT");
								System.out.println("...................ENTER THE OPTION .....................");
								choice = sc.nextInt();
								sc.nextLine();
								switch (choice) {
								case 1:
									createGroup(stmt, sc);
									break;
								case 2:
									joinGroup(stmt, sc);
									break;
								case 3:
									sendMessage(stmt, sc);
									break;
								case 4:
									addMember(stmt, sc);
									break;
								case 5:
									System.out.println("EXIT TO GROUP CHAT!!!!!!");
									break;
								default:
									System.out.println("Invalid choice. Please try again.");
								}
							} while (choice != 5);
						
						case 5:

							System.out.println("---------------WELCOME TO PROFILE MANAGEMENT!!!!----------------");
							int choice1;
							do {
								System.out.println("1. Update Username");
								System.out.println("2. Update Mobile Number");
								System.out.println("3. Update Password");
								System.out.println("4. View Profile");
								System.out.println("5. Exit");

								System.out.print("Enter your choice: ");
								choice1 = sc.nextInt();
								sc.nextLine();
								switch (choice1) {
								case 1:
									updateUsername(connection);
									break;
								case 2:
									updateMobileNumber(connection);
									break;
								case 3:
									updatePassword(connection);
									break;
								case 4:
									viewProfile(connection);
									break;
								case 5:
									System.out.println("EXIT TO PROFILE MANAGEMENT!!!!!");
									return;
								default:
									System.out.println("Invalid choice. Please try again.");
								}
							} while (choice1 != 6);

						}
					}
				}else {
					System.out.println("LOGIN FAILED");
				}
			}
		}
		private static void updateUsername(Connection connection) throws SQLException {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your userid: ");
			int userid = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter new username: ");
			String newUsername = scanner.nextLine();
			String SQL = "UPDATE register SET username = ? WHERE userid = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				preparedStatement.setString(1, newUsername);
				preparedStatement.setInt(2, userid);

				int rs = preparedStatement.executeUpdate();

				if (rs > 0) {
					System.out.println("Username updated successfully!");
				} else {
					System.out.println("Failed to update username.");
				}
			}
		}

		private static void updateMobileNumber(Connection connection) throws SQLException {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your userid: ");
			int userid = scanner.nextInt();
			System.out.print("Enter new mobile number: ");
			long newMobileNumber = scanner.nextLong();
			scanner.nextLine();
			String SQL = "UPDATE register SET mobileno = ? WHERE userid = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				preparedStatement.setLong(1, newMobileNumber);
				preparedStatement.setInt(2, userid);

				int rs = preparedStatement.executeUpdate();

				if (rs > 0) {
					System.out.println("Mobile number updated successfully!");
				} else {
					System.out.println("Failed to update mobile number.");
				}
			}
		}

		private static void updatePassword(Connection connection) throws SQLException {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your userid: ");
			int userid = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter new password: ");
			String newPassword = scanner.nextLine();
			String SQL = "UPDATE register SET password = ? WHERE userid = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				preparedStatement.setString(1, newPassword);
				preparedStatement.setInt(2, userid);

				int rs = preparedStatement.executeUpdate();

				if (rs > 0) {
					System.out.println("Password updated successfully!");
				} else {
					System.out.println("Failed to update password.");
				}
			}
		}

		private static void viewProfile(Connection connection) throws SQLException {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your userid: ");
			int userid = scanner.nextInt();
			scanner.nextLine();
			String SQL = "SELECT * FROM register WHERE userid = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				preparedStatement.setInt(1, userid);

				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					String username = resultSet.getString("username");
					String mobileNumber = resultSet.getString("mobileno");
					String password = resultSet.getString("password");

					System.out.println("Username: " + username);
					System.out.println("Mobile Number: " + mobileNumber);
					System.out.println("Password: " + password);
				} else {
					System.out.println("User not found.");
				}
			}
		}

		private static void createGroup(Statement stmt, Scanner scanner) throws SQLException {
			System.out.print("Enter group name: ");
			String groupName = scanner.nextLine();
			String SQL = "INSERT INTO groupsu (name) VALUES ('" + groupName + "')";
			stmt.executeUpdate(SQL);
			System.out.println("Group created successfully!!" + groupName + "");
		}

		private static void joinGroup(Statement stmt, Scanner scanner) throws SQLException {
			System.out.print("Enter your name: ");
			String memberName = scanner.nextLine();
			ResultSet rs = stmt.executeQuery("SELECT * FROM groupsu");
			List<Integer> groupIds = new ArrayList<>();
			System.out.println("Available groups:");
			while (rs.next()) {
				int groupId = rs.getInt("id");
				String groupName = rs.getString("name");
				System.out.println(groupId + ". " + groupName);
				groupIds.add(groupId);
				System.out.println("********************************");
			}

			System.out.print("Enter the group ID to join: ");
			int selectedGroupId = scanner.nextInt();
			if (groupIds.contains(selectedGroupId)) {
				String SQL = "INSERT INTO members (group_id, member_name) VALUES (" + selectedGroupId + ", '" + memberName
						+ "')";
				stmt.executeUpdate(SQL);
				System.out.println(memberName + "Joined the group successfully!");
			} else {
				System.out.println("Invalid group ID.");
			}
		}

		private static void sendMessage(Statement stmt, Scanner scanner) throws SQLException {
			System.out.print("Enter your group ID: ");
			int groupId = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter your message: ");
			String message = scanner.nextLine();
			String SQL = "INSERT INTO message (group_id, message) VALUES (" + groupId + ", '" + message + "')";
			stmt.executeUpdate(SQL);
			System.out.println(message + "Message sent successfully to the group" + groupId);
		}

		private static void addMember(Statement stmt, Scanner scanner) throws SQLException {
			System.out.print("Enter group ID: ");
			int groupId = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter member name: ");
			String memberName = scanner.nextLine();

			String SQL = "INSERT INTO members (group_id, member_name) VALUES (" + groupId + ", '" + memberName + "')";
			stmt.executeUpdate(SQL);
			System.out.println(memberName + " added successfully to group" + groupId);
		}
}
