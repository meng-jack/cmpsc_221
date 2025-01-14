// Jiaming (Jack) Meng
//
// Authored on January 14, 2025
package loanaccounthiearchy;

public record Address(String street,String city,String state,String zipcode)
{

    @Override
    public String toString()
    {
        return """
               Property Address:
               \t%s
               \t%s, %s %s
               """.
            formatted(street,city,state,zipcode);
    }
}
