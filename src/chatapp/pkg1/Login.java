
package chatapp.pkg1;

import java.util.Scanner;

public class Login {

public static void main(String[] args){
        
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Enter Username: ");
    String userName = scanner.nextLine();
    
    System.out.println("Enter Password: ");
    String password = scanner.nextLine();
    
    System.out.println("Enter your Cellphone No: ");
    String cellPhoneNumber = scanner.nextLine();
    
    Login test = new Login();
    
    boolean usernameResult = test.checkUserName(userName);
        System.out.println(usernameResult);
    
    boolean passwordComplexibilityResult = test.checkPasswordComplexity(password);
        System.out.println(passwordComplexibilityResult);
        
    boolean cellphoneNumberResult = test.checkCellPhoneNumber(cellPhoneNumber);
        System.out.println(cellphoneNumberResult);
    }
    
    public boolean checkUserName(String username){
        if(username.contains("_") && username.length() <= 5){
            System.out.println("Username successfully captured.");
          return true;
    }
        else{
            System.out.println("Username is not correctly formatted; please ensure that username contains an underscore and is no more than five characters in length.");
          return false;
        }
    }
    
    public boolean hasUpper(String password){
        return password.matches(".*[A-Z].*");
    }
    
    public boolean hasDigit(String password){
        return password.matches(".*[0-9].*");
    }
    public boolean hasSpecial(String password){
        return password.matches(".*[^a-zA-Z0-9].*");
    }        
    
    public boolean checkPasswordComplexity(String password){
        if(hasUpper(password) && password.length()>= 8 && hasDigit(password) && hasSpecial(password)){
            System.out.println("Password successfully captured.");
          return true;
        }
        else{
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight charcters, a capital letter, a number, and a special letter");
          return false;
        }
    }

    public boolean checkCellPhoneNumber(String cellPhoneNumber){
    //Phone Number:
    if(cellPhoneNumber.matches("^(\\+27|0)\\d{9}$")){
        System.out.println("Cellphone number successfully added");
        return true;
    }
    else{
        System.out.println("Cellphone number incorrectly formatted or does not contain international code");
        return false;
        }
    }
        String userName;
        String password;
        String cellPhoneNumber;
    
    public String registerUser(String userName, String password, String cellPhoneNumber){
        boolean validUserName = checkUserName(userName);
        boolean validPassword = checkPasswordComplexity (password);
        boolean validCellPhoneNumber = checkCellPhoneNumber(cellPhoneNumber);
        
        if(!validUserName){
            return "Username is not correctly formatted; please ensure that username contains an underscore and is no more than five characters in length.";
        }
        else if(!validPassword){
            return "Cellphone number incorrectly formatted; or does not contain international code";
        }
        else if (!validCellPhoneNumber){
            return "Cellphone number incorrectly formatted or does not contain international code";
        }
        else{
            this.userName = userName;
            this.password = password;
            this.cellPhoneNumber = cellPhoneNumber;
            return "User " + userName + " successfully registered";
        }
    }
    
    public boolean loginUser(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your Username: ");
        String username = scanner.nextLine();
        
        System.out.println("Please enter your Password: ");
        String password = scanner.nextLine();
        if(username.equals(this.userName) && password.equals(this.password)){
        return true;
        }
        else{
        return false;
        }
    }
    
    public String returnLoginMessage(boolean loginSuccess){
        if(loginSuccess){
        return "Welcome " + this.userName + ", it is great to see you again.";
        }
        else{
        return "Username or password is incorrect, please try again.";
        }
    }
}

