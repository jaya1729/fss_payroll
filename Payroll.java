package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class Payroll {
	static BufferedReader br;

	public static void main(String[] args) throws  NumberFormatException, IOException, ClassNotFoundException, SQLException {
		try  {
			// login screen
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter User Name:");
			String username = br.readLine();
			System.out.println("Enter Password:");
			String password = br.readLine();
			//validation of user
			if(username.matches("[a-zA-Z]+$")) {
				   System.out.println("thats good you have entered characters");
				}
				else {
				     System.out.println("enter characters only for username");
				}
			if ("user1".equals(username) && "user@123".equals(password)) {
				System.out.println("Enter your Role:");
				System.out.println("1.Administrator\n2.Employee");
				int role = Integer.parseInt(br.readLine());
				switch (role) {
				case 1:
					adminlogin();
					break;
				case 2:
					employeeLogin();
					break;
				}

			} else {
				System.out.println("Invalid Username 'or' Password !!");
			}

			}
		finally {
			br.close();
		}

	}
         private static void adminlogin() throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
     		br = new BufferedReader(new InputStreamReader(System.in));
    		System.out.println("Enter User Name:");
    		String username = br.readLine();
    		System.out.println("Enter Password:");
    		String password = br.readLine();
    		//validation of user
    		if(username.matches("[a-zA-Z]+$")) {
 			   System.out.println("thats good you have entered characters");
 			}
 			else {
 			     System.out.println("enter characters only for username");
 			}
    		if ("admin1".equals(username) && "admin@123".equals(password))
    		{
            
    		int response;
    		do {
    			//displaying the menu for administrator
    			System.out.println("\nMenu for Administrator");
    			System.out.println(
    					"1.Add Employee\n2.Search Employee\n3.Delete Employee\n4.View Employees List\n5.update employee\n6.Generate Payslip\n7.Logout");
    			 System.out.println("Enter operation you want to perform by choosing above options:");
    			response = Integer.parseInt(br.readLine());
    			switch (response) {
    			case 1:
    				addemployee();
    				break;
    			case 2:
    				searchemployee();
    				break;
    			case 3:
    				deleteemployee();
    				break;
    			case 4:
    				viewemployees();
    				break;
    			case 5:
    				updateemployee();
    				break;
    			case 6:
    				generatepayslip();
    				break;
    			case 7:
    				System.out.println("----Logged out Succesfully----");
    				break;
    				default :
    					System.out.println("Invalid Selection...!");
    					break;
    			}
    		} while (response != 7);
    	}
    		else
    			System.out.println("invalid crendtials");
    	}


		private static void generatepayslip()throws IOException,SQLException {
			try  {
				br = new BufferedReader(new InputStreamReader(System.in));
				//establishing connection with database
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Jaya@1234");
				System.out.println("Connection Established");
				System.out.println("Enter Employee ID to Generate Payslip:");
				int emp_id = Integer.parseInt(br.readLine());
                //statement for displaying records
				PreparedStatement stmt2 = con.prepareStatement("select * from managepayroll where emp_id=" + emp_id + "");
				System.out.println("*************************************************");
				System.out.println("|------------PAY SLIP FOR EMPLOYEE--------------|");
				System.out.println("*************************************************");
				//executing the query using execute query method
				ResultSet rs = stmt2.executeQuery();

				while (rs.next()) {
					//displaying the records of employee pay slip
					System.out.printf("\nEmployeee ID           :" + rs.getInt(1));
					System.out.printf("\nEmployeee Department   :" + rs.getString(2));
					System.out.printf("\nEmployeee Basic Pay    :" + rs.getFloat(3));
					System.out.printf("\nEmployeee HRA          :" + rs.getFloat(4));
					System.out.printf("\nEmployeee DA           :" + rs.getFloat(5));
					System.out.printf("\nEmployeee Bonus        :" + rs.getFloat(6));
					System.out.printf("\nEmployeee PF           :" + rs.getFloat(7));
					System.out.printf("\nEmployeee Net Payable  :" + rs.getFloat(8));
					System.out.println("(In Hand Amount)");
					System.out.println("\n**************************************************");
					System.out.println("|------------******FSS-Corp*******---------------|");
					System.out.println("**************************************************");
				}
			} catch (IOException e) {
				System.out.println(e);
			}



		}
		private static void updateemployee()throws NumberFormatException, IOException, SQLException {
			  br=new BufferedReader(new InputStreamReader(System.in));
                     System.out.println("Enter the employee id");
				int emp_id=Integer.parseInt(br.readLine());
				//taking user input to update the record
			    	System.out.println("Updated first name enter 1");
			    	System.out.println("Updated last name enter 2");
			    	System.out.println("Updated gender enter 3");
			    	System.out.println("update age enter 4");
			    	System.out.println("Updated phone number enter 5");
			    	System.out.println("Updated email enter 6");
			    	System.out.println("update salary enter 7");
			    	System.out.println("Updated Designation enter 8");

			    	int num=Integer.parseInt(br.readLine());

			    	String url="jdbc:mysql://localhost:3306/project";
			    	String uname="root";
			    	String password="Jaya@1234";
			    	try {
			    		//connecting the database
			    		Connection con= DriverManager.getConnection(url, uname, password);


			  		if(num==1) {
			  			System.out.println("Enter the first name");
			  			String firstName=br.readLine();
			  			//statement to update record
						PreparedStatement st=con.prepareStatement("update employee set firstName="+"'"+firstName+"'" +" where emp_id = "+emp_id+"");
                        //updating the record using execute update method  
			    		 st.executeUpdate();

			    		 System.out.println("data updated succesfully");
			  		}
			  		if(num==2) {
			  			System.out.println("Enter the last name");
			  			String lastName=br.readLine();
			  		    //statement to update record
                          PreparedStatement st=con.prepareStatement("update employee set last_name="+"'"+lastName+"'" +"where emp_ID="+emp_id+"");
                        //updating the record using execute update 
			    		 st.executeUpdate();
                  
			    		 System.out.println("data updated succesfully");
			  		}
			  		if(num==3) {
			  			System.out.println("Enter the Gender");
			  			String gender=br.readLine();
			  		//statement to update record
			  			PreparedStatement st=con.prepareStatement("update employee set gender="+"'"+gender+"'" +"where emp_ID="+emp_id+"");
			  		//updating the record using execute update
			    		 st.executeUpdate();

			    		 System.out.println("data updated succesfully");
			  		}
			  		if(num==4) {
			  			System.out.println("Enter the age");
			  			int age=Integer.parseInt(br.readLine());
			  		//statement to update record
			  			PreparedStatement st=con.prepareStatement("update employee set age="+"'"+age+"'" +"where emp_ID="+emp_id+"");
			    		 st.executeUpdate();
			    	//updating the record using execute update method
                         System.out.println("data updated succesfully");
			  		}
			  		if(num==5) {
			  			System.out.println("Enter the phone number");
			  			int phonenum=Integer.parseInt(br.readLine());
			  			//statement to update record
			  			PreparedStatement st=con.prepareStatement("update employee set contact_number="+"'"+phonenum+"'" +"where emp_ID="+emp_id+"");
			  		//updating the record using execute update method
			    		 st.executeUpdate();

			    		 System.out.println("data updated succesfully");
			  		}

			  		if(num==6) {
			  			System.out.println("Enter the email");
			  			String email=br.readLine();
			  			//statement to update record
			  			PreparedStatement st=con.prepareStatement("update employee set emp_email="+"'"+email+"'" +"where emp_ID="+emp_id+"");
			  			//updating the record using execute update method
			    		 st.executeUpdate();

			    		 System.out.println("data updated succesfully");
			  		}
			  		if(num==7) {
			  			System.out.println("Enter the Basic Salary");
			  			float salary=Float.parseFloat(br.readLine());
			  			//statement to update record
			  			PreparedStatement st=con.prepareStatement("update salary_bonus set monthly_amount="+"'"+salary+"'" +"where emp_ID="+emp_id+"");
			  			//updating the record using execute update method
			    		 st.executeUpdate();
			    		 System.out.println("data updated succesfully");
			  		}
			  		if(num==8) {
			  			System.out.println("Enter the Designation");
			  			String designation=br.readLine();
			  		    //statement to update record
			  			PreparedStatement st=con.prepareStatement("update employee set Designation="+"'"+designation+"'" +"where emp_ID="+emp_id+"");
			  		    //updating the record using execute update method
			    		 st.executeUpdate();

			    		 System.out.println("data updated succesfully");
			  		}
			  		if(num<0) {
			  			System.out.println("choose correct option");
			  		}
			  		if(num>8) {
			  			System.out.println("choose correct option");
			  		}

			    	} catch (Exception e) {


			    		e.printStackTrace();
			    	}


		}
		private static void viewemployees()throws ClassNotFoundException, SQLException {

			String url="jdbc:mysql://localhost:3306/project";
			String uname="root";
			String password="Jaya@1234";
			try {
				//connecting to database
				Connection con= DriverManager.getConnection(url, uname, password);
				//statement to execute the query
				 PreparedStatement st=con.prepareStatement("select * from employee  ");
				 //executing the query using execute query method
				ResultSet rs=st.executeQuery();
				 System.out.println("Emp Id  First_Name   Last_Name     Gender  Age   PhoneNumber   Emp_Email       ");
				 //displaying the records
				while(rs.next()) {
					String EmployeeDetails="";
					for(int i=1;i<=7;i++) {
						EmployeeDetails += rs.getString(i)+ "     ";
					}
					System.out.println(EmployeeDetails);
				}

			} catch (SQLException e) {


				e.printStackTrace();
			}


		}
		private static void deleteemployee()throws NumberFormatException, IOException, SQLException {
			  br=new BufferedReader(new InputStreamReader(System.in));

				System.out.println("Enter the employee id");
				int emp_id=Integer.parseInt(br.readLine());
				System.out.println("Are you sure you want to delete");
				System.out.println("1.yes\n2.no\nEnter your option");
				int n=Integer.parseInt(br.readLine());
				switch(n) {
				case 1:
			    	String url="jdbc:mysql://localhost:3306/project";
			    	String uname="root";
			    	String password="Jaya@1234";
			    	try {
			    		//connecting to database
			    		Connection con= DriverManager.getConnection(url, uname, password);
			    		//statement to delete a record
			    		 PreparedStatement st=con.prepareStatement("delete from employee where emp_ID="+emp_id+"");
                        //executing the delete record by using execute update to delete the record
			    		 int rows=st.executeUpdate();
			    		 if(rows==0) {
			    			 System.out.println("no records found");
			    		 }
			    		 else   
			    		 System.out.println("data deleted succesfully");


			    	} catch (Exception e) {


			    		 e.printStackTrace();
			    	}
			    	break;
				case 2:
					System.out.println("the record is not deleted");
					break;
				default:
					System.out.println("Enter the valid option");
					break;

				}


		}
		private static void searchemployee()throws NumberFormatException, IOException, ClassNotFoundException, SQLException  {
			  br=new BufferedReader(new InputStreamReader(System.in));
				String url="jdbc:mysql://localhost:3306/project";
				String uname="root";
				String password="Jaya@1234";
				//connecting to database
				 Connection con= DriverManager.getConnection(url, uname, password);

				 System.out.println("Enter the employee id");
				 int emp_id=Integer.parseInt(br.readLine());
				 //statement to display employee record
				 PreparedStatement st=con.prepareStatement("select * from employee where emp_id = "+emp_id+"");
                 //executing query using execute query
			     ResultSet rs= st.executeQuery();
             String EmployeeDetails="";
             if(rs!=null) {
			        System.out.println("Emp Id  First_Name  Last_Name  Gender  Age   PhoneNumber   Emp_Email         Basicsalary  Designation\n");
			    //displaying the records
			    while(rs.next()) {
				EmployeeDetails="";
				for(int i=1;i<=9;i++) {
					EmployeeDetails += rs.getString(i)+ "    ";
				}
			     
					System.out.println(EmployeeDetails);
			 }
             }
             else {
            	 System.out.println("no record found");
             }
             
				
       }

		public static void addemployee()throws  NumberFormatException, IOException, ClassNotFoundException, SQLException {
			  br=new BufferedReader(new InputStreamReader(System.in));
				String url="jdbc:mysql://localhost:3306/project";
				String uname="root";
				String password="Jaya@1234";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					//connecting to database
					Connection con= DriverManager.getConnection(url, uname, password);
					//statement to insert values
					PreparedStatement ps= con.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?) ");
					//taking user credentials.
					System.out.println("enter the employee id");
					int emp_id=Integer.parseInt(br.readLine());
					ps.setInt(1, emp_id);
                    System.out.println("enter the employee first name");
					String firstName=br.readLine();
					ps.setString(2, firstName);
					System.out.println("enter the employee last name ");
					String last_Name=br.readLine();
					ps.setString(3, last_Name);
					System.out.println("enter the employee gender ");
					String gender=br.readLine();
					ps.setString(4, gender);
					System.out.println("enter the employee age ");
					int age=Integer.parseInt(br.readLine());
					ps.setInt(5, age);
					System.out.println("enter the employee contact number");
					String contact_number=br.readLine();
					ps.setString(6, contact_number);
					System.out.println("enter the employee email id ");
					String emp_email=br.readLine();
					ps.setString(7, emp_email);
					System.out.println("enter the salary of employee");
					float monthly_amount=Float.parseFloat(br.readLine());
					ps.setFloat(8,monthly_amount);
					System.out.println("enter the employee designation ");
					String designation=br.readLine();
                    ps.setString(9, designation);
                    //updating the table.
					ps.executeLargeUpdate();

				}
					catch (Exception e) {
					    e.printStackTrace();
					}


		}
		private static void employeeLogin() throws NumberFormatException, IOException {
			 br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter User Name:");
			String username = br.readLine();
			System.out.println("Enter Password:");
			String password = br.readLine();
			//validating user
			if(username.matches("[a-zA-Z]+$")) {
			   System.out.println("thats good you have entered characters");
			}
			else {
			     System.out.println("enter characters only for username");
			}
			
			if ("employee".equals(username) && "employee123".equals(password))

			{
				int response1;
				do {
					//displaying menu for employee.
					System.out.println("\n                         ");
					System.out.println("|-------------------------|");
					System.out.println("| Menu for Employee       | ");
					System.out.println("|-------------------------|");
					System.out.println("1.View Profile \n2.View Payslip\n3.Logout");
					System.out.println("****Select an Option to Proceed****");
					response1 = Integer.parseInt(br.readLine());
					switch (response1) {
					case 1:
						viewprofile();
						break;
					case 2:
						viewpayslip();
						break;
					case 3:
						System.out.println("----Logged out Succesfully----");
						break;
					default:
						System.out.println("Invalid Selection...!");
						break;
					}
				} while (response1 != 3);
			} else {
				System.out.println("Invalid Username 'or' Password !!");
				System.out.println("!!!!Please Enter a Valid Details!!! ");
			}


	       }
		private static void viewprofile() throws NumberFormatException, IOException {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("enter employee id");
			int emp_id=Integer.parseInt(br.readLine());

			String url="jdbc:mysql://localhost:3306/project";
			String uname="root";
			String password="Jaya@1234";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//connecting to database
				Connection con= DriverManager.getConnection(url, uname, password);
				//statement for executing query
				 PreparedStatement st=con.prepareStatement("select * from employee where emp_id ="+emp_id+"");
				 //executing query method to get records of table
				ResultSet rs=st.executeQuery();
				 System.out.println("Emp Id  First_Name   Last_Name     Gender  Age   PhoneNumber   Emp_Email       ");
				 //displaying the records
				while(rs.next()) {
					String EmployeeDetails="";
					for(int i=1;i<=7;i++) {
						EmployeeDetails += rs.getString(i)+ "     ";
					}
					System.out.println(EmployeeDetails);
				}

			} catch (ClassNotFoundException | SQLException e) {


				e.printStackTrace();
			}

			
		}
		private static void viewpayslip() {
			try  {
				br = new BufferedReader(new InputStreamReader(System.in));
				//connecting to database
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Jaya@1234");
				System.out.println("Connection Established");
				System.out.println("Enter your Employee ID to View Payslip:");
				int emp_id = Integer.parseInt(br.readLine());
                //statement to execute the query.
				PreparedStatement stmt2 = con.prepareStatement("select * from managepayroll where emp_id=" + emp_id + "");
				System.out.println("*************************************************");
				System.out.println("|------------PAY SLIP FOR EMPLOYEE--------------|");
				//executing the query 
				ResultSet rs = stmt2.executeQuery();
                //displaying the records
				while (rs.next()) {
					System.out.printf("\nEmployeee ID           :" + rs.getInt(1));
					System.out.printf("\nEmployeee Department   :" + rs.getString(2));
					System.out.printf("\nEmployeee Basic Pay    :" + rs.getFloat(3));
					System.out.printf("\nEmployeee HRA          :" + rs.getFloat(4));
					System.out.printf("\nEmployeee DA           :" + rs.getFloat(5));
					System.out.printf("\nEmployeee Bonus        :" + rs.getFloat(6));
					System.out.printf("\nEmployeee PF           :" + rs.getFloat(7));
					System.out.printf("\nEmployeee Net Payable  :" + rs.getFloat(8));
					System.out.println("(In Hand Amount)");
					System.out.println("\n**************************************************");
					System.out.println("|------------******FSS-Corp*******---------------|");
					System.out.println("**************************************************");
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			
		}


}
