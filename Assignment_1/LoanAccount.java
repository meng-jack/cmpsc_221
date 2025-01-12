public class LoanAccount
{
	static double annualInterestRate;
	private double principal;

	public LoanAccount(double principal)
	{
		this.principal=principal;
	}

	public static void setAnnualInterestRate(double annualInterestRate)
	{
		LoanAccount.annualInterestRate=annualInterestRate;
	}

	public double getPrincipal()
	{
		return principal;
	}


	public double calculateMonthlyPayment(int numberOfPayments)
	{
		double monthlyInterest=(annualInterestRate/100)/12;
		return (double)Math.round(principal*(monthlyInterest/(1-Math.pow(1+monthlyInterest,-numberOfPayments)))*100)
				/100;
	}
}
