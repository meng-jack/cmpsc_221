Loan Account Class:

Create class LoanAccount. Use a static variable annualInterestRate to store the annual interest rate for all account
holders. Each object of the class contains a private instance variable principal indicating the amount the person is
borrowing. Provide method: public double calculateMonthlyPayment(int numberOfPayments) to calculate the monthly payment
by using the following formula:

double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberOfPayments)));

where

monthlyInterest = annualInterestRate/12. Remember, annualInterestRate must be converted to a percentage before using.

Provide a static method setAnnualInterestRate that sets the annualInterestRate to a new value. Set the initial loan
amount (Principal) for a new loan through the constructor.

Write a program to test class LoanAccount. Instantiate two LoanAccount objects, loan1 and loan2, with principal loan
amounts of $5000.00 and $31000.00, respectively. Set annualInterestRate to 1%, then calculate the monthly payment for
each loan for 36 months, 60 months, and 72 months and print the monthly payment amounts for both loans. Next, set the
annualInterestRate to 5%, and output the same information again. Make sure your dollar amounts are displayed to two
decimal places.

Put the code to test the class in the main method of the LoanAccount Class. The output of your program should look like
the following:
 
![image](Assignment1LoanAccount.png)