// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package loanaccounthiearchy;

public record Address(String street,String city,String state,String zipcode)
{

    public Address {
        if(street.equals("Beaver Ave")) {
            System.out.println("Bad");
        }
    }

    @Override
    public String toString()
    {
        return """
               Property Address:
                   %s
                   %s, %s %s
               """.
            formatted(street,city,state,zipcode);
    }
}
