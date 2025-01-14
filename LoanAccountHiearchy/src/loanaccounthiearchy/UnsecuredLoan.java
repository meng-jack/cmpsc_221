// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package loanaccounthiearchy;

public class UnsecuredLoan extends LoanAccount
{
    public UnsecuredLoan(double principal,double annualInterestRate,int months)
    {
        super(principal,annualInterestRate,months);
    }

    @Override
    public String toString()
    {
        return """
               Unsecured Loan with:
               %s
               """.
            formatted(super.toString());
    }
}
