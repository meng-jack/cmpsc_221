// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package loanaccounthiearchy;

public class CarLoan extends LoanAccount
{
    private String vehicleVIN;

    public CarLoan(double principal,double annualInterest,int months,String vehicleVIN)
    {
        super(principal,annualInterest,months);
        this.vehicleVIN=vehicleVIN;
    }

    @Override
    public String toString()
    {
        return """
               Car Loan with:
               %sVehicle VIN: %s

               """.
            formatted(super.toString(),vehicleVIN);
    }
}
