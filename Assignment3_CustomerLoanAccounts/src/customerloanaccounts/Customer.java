// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package customerloanaccounts;

import java.util.ArrayList;

public class Customer
{
    private String firstName;
    private String lastName;
    private String ssn;
    private ArrayList<LoanAccount> loanAccounts;

    public Customer(String firstName,String lastName,String ssn)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.ssn=ssn;
        loanAccounts=new ArrayList<>();
    }

    public void addLoanAccount(LoanAccount account)
    {
        loanAccounts.add(account);
    }

    public void printMonthlyReport()
    {
        StringBuilder sb=new StringBuilder();
        for(LoanAccount account:loanAccounts)
            sb.append(account.toString());
        System.out.print(
            """
            Account Report for Customer: %s %s with SSN %s

            %s
            """.
                formatted(firstName,lastName,ssn,sb));
    }
}
