// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package loanaccounthiearchy;

public class PrimaryMortgage extends LoanAccount
{
    private double PMIMonthlyAmount;
    private Address address;

    public PrimaryMortgage(double principal,double annualInterest,int months,
        double PMIMonthlyAmount,Address address)
    {
        super(principal,annualInterest,months);
        this.PMIMonthlyAmount=PMIMonthlyAmount;
        this.address=address;
    }

    @Override
    public String toString()
    {
        return """
               Primary Mortgage Loan with:
               %sPMI Monthly Amount: $%.2f
               %s
               """.
            formatted(super.toString(),PMIMonthlyAmount,address);
    }
}
