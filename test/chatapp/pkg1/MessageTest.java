package chatapp.pkg1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Message class (Part 2).
 * Each test makes a Message, calls ONE method, and checks the answer with assertEquals.
 * assertEquals(expected, actual): the test passes if the two are the same.
 */
public class MessageTest {

    // ---------- Test data from the assignment ----------
    String number1 = "+27718693002";
    String text1 = "Hi Mike, can you join us for dinner tonight?";

    String number2 = "08575975889";
    String text2 = "Hi Keegan, did you receive the payment?";

    // ---------- 1. Message length ----------
    @Test
    public void messageLengthSuccess() {
        Message m = new Message(number1, text1);

        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void messageLengthFailure() {
        // 260 letters = 10 more than the limit of 250
        String longText = "a".repeat(260);
        Message m = new Message(number1, longText);

        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                m.checkMessageLength());
    }

    // ---------- 2. Recipient number ----------
    @Test
    public void recipientNumberSuccess() {
        Message m = new Message(number1, text1);

        // +27718693002 is +27 and 9 digits, so the answer must be true
        assertTrue(m.checkRecipientCell(number1));
    }

    @Test
    public void recipientNumberFailure() {
        Message m = new Message(number2, text2);

        // 08575975889 has no international code, so the answer must be false
        assertFalse(m.checkRecipientCell(number2));
    }

    // ---------- 3. Message hash ----------
    @Test
    public void messageHashTestCase1() {
        Message m = new Message(number1, text1);

        // the real ID is random, so we set our own to make the answer predictable
        m.messageID = "0012345678";
        m.messageNumber = 0;

        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    @Test
    public void messageHashTestCase2() {
        Message m = new Message(number2, text2);

        m.messageID = "0098765432";
        m.messageNumber = 1;

        assertEquals("00:1:HIPAYMENT", m.createMessageHash());
    }

    // ---------- 4. Message ID ----------
    @Test
    public void messageIDCreated() {
        Message m = new Message(number1, text1);

        assertEquals(10, m.messageID.length());   // ten digits
        assertTrue(m.checkMessageID());           // and not more than ten characters
    }

    // ---------- 5. Send, disregard, store ----------
    @Test
    public void messageSentSend() {
        Message m = new Message(number1, text1);

        assertEquals("Message successfully sent.", m.sentMessage(1));
    }

    @Test
    public void messageSentDisregard() {
        Message m = new Message(number2, text2);

        assertEquals("Press 0 to delete the message.", m.sentMessage(2));
    }

    @Test
    public void messageSentStore() {
        Message m = new Message(number1, text1);

        assertEquals("Message successfully stored.", m.sentMessage(3));
    }

    // ---------- 6. Total number of messages sent ----------
    @Test
    public void totalMessagesSent() {
        int before = Message.returnTotalMessages();   // how many were sent before this test

        Message first = new Message(number1, text1);
        Message second = new Message(number2, text2);

        first.sentMessage(1);    // Message 1: Send
        second.sentMessage(2);   // Message 2: Disregard (does not count)

        // only ONE message was really sent, so the total goes up by 1
        assertEquals(before + 1, Message.returnTotalMessages());
    }
}