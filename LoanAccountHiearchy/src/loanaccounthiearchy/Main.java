// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package loanaccounthiearchy;

public final class Main
{
    public static void main(String[] args)
    {
        // Create three different loan objects, one of each type.
        CarLoan carLoan=new CarLoan(25000.00,4.25,72,"IRQ3458977");
        Address propertyAddress=new Address("321 Main Street","State College",
                                            "PA","16801");
        PrimaryMortgage propertyLoan=new PrimaryMortgage(250000.00,3.1,360,35.12,
                                                         propertyAddress);
        UnsecuredLoan unsecuredLoan=new UnsecuredLoan(5000.00,10.75,48);
        //Print out the load information for each loan using the toString() method.
        System.out.format("%n%s%s%s%n",carLoan,propertyLoan,unsecuredLoan);
    }
}
