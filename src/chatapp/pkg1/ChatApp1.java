
package chatapp.pkg1;

import java.util.Scanner;

public class ChatApp1 {

    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        Login test = new Login();
        
        while(true){
            System.out.println("Welcome To The ChatApp");
            System.out.println("New Or Already Have an Account? Select your option here: (1=Register, 2=Login, 3=Exit) ");
            int choose = scanner.nextInt();
            scanner.nextLine();
            
        if(choose == 1){
            System.out.println("Create your Username: ");
            String UserName = scanner.nextLine();
            
            System.out.println("Please enter your Password: ");
            String password = scanner.nextLine();
            
            System.out.println("Please enter your Cellphone Number: ");
            String cellPhoneNumber = scanner.nextLine();
            
            String registerMessage = test.registerUser(UserName, password, cellPhoneNumber);
            System.out.println(registerMessage);
            }
        else if(choose == 2){
            boolean loginSuccess = test.loginUser();
            String loginMessage = test.returnLoginMessage(loginSuccess);
            System.out.println(loginMessage);

            if(loginSuccess){
                Message.quickChat();
            }
            }
        else {
            System.out.println("Thank you for using ChatApp. Goodbye!");
            break;
            }
        }
        }
    }


 
