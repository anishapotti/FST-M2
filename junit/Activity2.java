package activities;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Activity2 {
    @Test
    public void notEnoughFunds(){
        BankAccount bank= new BankAccount(100);
        bank.withdraw(1000);
        assertThrows(NotEnoughFundsException.class, () -> bank.withdraw(1000));
    }
    @Test
    public void enoughFunds(){
        BankAccount bank= new BankAccount(100);
        bank.withdraw(5);
        assertDoesNotThrow(()-> bank.withdraw(5));
    }
}
