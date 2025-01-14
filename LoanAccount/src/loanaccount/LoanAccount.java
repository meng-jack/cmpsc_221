// Jiaming (Jack) Meng
//
// Authored on January 13, 2025
package loanaccount;

import static java.lang.System.*; // no more System.!

public class LoanAccount
{
    private static double annualInterestRate;
    private double principal;

    /**
     * @param principal The amount the person is borrowing
     */
    public LoanAccount(double principal)
    {
        this.principal=principal;
    }

    /**
     * A setter for the static var annualInterestRate
     *
     * @param annualInterestRate new value
     */
    public static void setAnnualInterestRate(double annualInterestRate)
    {
        LoanAccount.annualInterestRate=annualInterestRate;
    }

    /**
     * @return The principal value
     */
    public double getPrincipal()
    {
        return principal;
    }

    /**
     * A very simple way to calculate monthly payment using the principal value.
     *
     * @param numberOfPayments Number of payments by month
     * @return The monthly payment in dollars
     */
    public double calculateMonthlyPayment(int numberOfPayments)
    {
        double monthlyInterest=annualInterestRate/12.0d;
        return (double)Math.round(principal*(monthlyInterest/(1-Math.pow(
            1+monthlyInterest,-numberOfPayments)))*100.0d)
            /100.0d;
    }

    public static void main(String[] args)
    {
        LoanAccount loan1=new LoanAccount(5000.0);
        LoanAccount loan2=new LoanAccount(31000.0);
        LoanAccount.setAnnualInterestRate(0.01); // there is really no point in doing this, we are in the same scope, so we get access to private scopes
        String messageFormat="""
              Monthly payments for loan1 of $5000.00 and loan2 $31000.00 for 3, 5, and 6 year loans at %d%% interest.
              Loan\t3 years\t5 years\t6 years
              Loan1\t%.2f\t%.2f\t%.2f
              Loan2\t%.2f\t%.2f\t%.2f
              """;// string blocks from se15, so to avoid manual unnecessary formatting work
        out.println(
            messageFormat.formatted(
                1, // for one percent
                loan1.calculateMonthlyPayment(36),
                loan1.calculateMonthlyPayment(60),
                loan1.calculateMonthlyPayment(72),
                loan2.calculateMonthlyPayment(36),
                loan2.calculateMonthlyPayment(60),
                loan2.calculateMonthlyPayment(72)
            )
        );
        LoanAccount.setAnnualInterestRate(0.05);
        out.print(
            messageFormat.formatted(
                5,
                loan1.calculateMonthlyPayment(36),
                loan1.calculateMonthlyPayment(60),
                loan1.calculateMonthlyPayment(72),
                loan2.calculateMonthlyPayment(36),
                loan2.calculateMonthlyPayment(60),
                loan2.calculateMonthlyPayment(72)
            )
        );
    }
}
