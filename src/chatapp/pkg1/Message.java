
package chatapp.pkg1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;
        
              
public class Message {

    String messageID;
    int messageNumber;
    String recipient;
    String message;
    
    static String sentText ="";
    static String storedText = "";
    static int numSent = 0;
    

    
    public Message(String recipient, String message){
        
    
        this.recipient = recipient;
        this.message = message;
    
        Random random = new Random();
        messageID = "";
            messageID = messageID + random.nextInt(1000000000) + 999999999;
    }
    public boolean checkMessageID(){
        return messageID.length()<=1000000000;
    }
    
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Please enter a message of no more than 250 caharacters.";
        }
    }
    
    public boolean checkRecipientCell(String cellphone){
        String regex = "\\+27[0-9]{9}";
    
        return cellphone.matches(regex);
}
    
    public String createMessageHash(){
        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
        
        hash = hash.replace(",", "").replace(".", "").replace("!", "");
        return hash.toUpperCase();
    }
    
    public String getDetails() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + createMessageHash() + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + message;
    }
    
    
    public String sentMessage(int choice) {
        
        Scanner scan = new Scanner(System.in);

    System.out.println("Which action would you like to perform: ");
    System.out.println("1. Send Message.");
    System.out.println("2. Disregard Message.");
    System.out.println("3. Store message to send later.");
    choice = scan.nextInt();

        if (choice == 1) {
            sentText = sentText + getDetails() ;
            numSent = numSent + 1;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            writeJsonfile();
            return "Message successfully stored.";
        } else {
            return "Please enter the correct option.";
        }
    }
    
      public static String printMessages() {
        return sentText;
      }
 
    public static int returnTotalMessages() {
        return numSent;
    }


    public void writeJsonfile(){
        String json = "{\"messageID\": \"" + messageID + "\", "
            + "\"messageHash\": \"" + createMessageHash() + "\", "
            + "\"recipient\": \"" + recipient + "\", "
            + "\"message\": \"" + message.replace("\"", "'") + "\"}";

    // add it to the stored text (a comma goes between messages)
    if (storedText.equals("")) {
        storedText = json;
    } else {
        storedText = storedText + ",\n" + json;
    }

    String file = "[\n" + storedText + "\n]";

    try {
        Files.writeString(Path.of("messages.json"), file);
        System.out.println("Message saved to JSON File");
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
    }
    public static void readJsonfile(){
        try{
            String jsonfile = Files.readString(Path.of("user.json"));
            
            System.out.println("JSON file Content: ");
            System.out.println(jsonfile);
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }}
    
     public static void quickChat() {
        Scanner scan = new Scanner(System.in);
 
        System.out.println("Welcome to QuickChat.");
        System.out.println("How many messages do you want to enter?");
        int max = scan.nextInt();
        scan.nextLine();
 
        int entered = 0;
        int option = 0;
 
        while (option != 3) {
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            option = scan.nextInt();
            scan.nextLine();
 
            if (option == 1) {
                if (entered >= max) {
                    System.out.println("You have already entered all " + max + " messages.");
                } else {
                    // keep asking until the number and the message are both fine
                    Message m;
                    boolean valid = false;
                    do {
                        System.out.println("Enter the recipient's cell number:");
                        String recipient = scan.nextLine();
                        System.out.println("Enter your message:");
                        String text = scan.nextLine();
 
                        m = new Message(recipient, text);
                        String cellCheck = m.checkRecipientCell();
                        String lengthCheck = m.checkMessageLength();
                        System.out.println(cellCheck);
                        System.out.println(lengthCheck);
 
                        valid = cellCheck.equals("Cell phone number successfully captured.")
                                && lengthCheck.equals("Message ready to send.");
                    } while (!valid);
 
                    System.out.println("Message ID generated: " + m.messageID);
                    System.out.println("Which action would you like to perform:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Delete Message");
                    System.out.println("3. Store Message");
                    int choice = scan.nextInt();
                    scan.nextLine();
 
                    System.out.println(m.sentMessage(choice));
 
                    if (choice == 1) {
                        System.out.println(m.getDetails());
                    } else if (choice == 2) {
                        int zero = scan.nextInt();
                        scan.nextLine();
                        if (zero == 0) {
                            System.out.println("Message deleted successfully");
                        }
                    }
                    entered = entered + 1;
                }
            } else if (option == 2) {
                System.out.println("Coming Soon.");
            }
        }
 
        System.out.println("The total number of messages sent: " + returnTotalMessages());
    }

    private String checkRecipientCell() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
 
   