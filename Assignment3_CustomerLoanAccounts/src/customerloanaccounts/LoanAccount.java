// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package customerloanaccounts;

public class LoanAccount
{
    private double principal;
    private double annualInterestRate;
    private int months;

    public LoanAccount(double principal,double annualInterestRate,int months)
    {
        this.months=months;
        this.principal=principal;
        this.annualInterestRate=annualInterestRate;
    }

    public double calculateMonthlyPayment()
    {
        double monthlyInterest=(annualInterestRate/100.0d)/12.0d;
        return (double)Math.round(principal*(monthlyInterest/(1-Math.pow(
            1+monthlyInterest,-months)))*100.0d)
            /100.0d;
    }

    public double getPrincipal()
    {
        return principal;
    }

    public double annualInterestRate()
    {
        return annualInterestRate;
    }

    public int getMonths()
    {
        return months;
    }

    @Override
    public String toString()
    {
        return """
               Principal: $%.2f
               Annual Interest Rate: %.2f%%
               Term of Loan in Months: %d
               Monthly Payment: $%.2f
               """.
            formatted(
                principal,
                annualInterestRate,
                months,
                calculateMonthlyPayment()
            );
    }

}
