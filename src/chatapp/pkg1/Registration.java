
package chatapp.pkg1;

public class Registration {
    
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
    
    }

    
    

