/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package functionalprogramming;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author acv
 */
public class FunctionalProgrammingTemplate
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        // create the ArrayList of Invoices
        List<Invoice> invoices=List.of(
            new Invoice(83,"Electric sander",7,57.98),
            new Invoice(24,"Power saw",18,99.99),
            new Invoice(7,"Sledge hammer",11,21.50),
            new Invoice(77,"Hammer",76,11.99),
            new Invoice(39,"Lawn mower",3,79.50),
            new Invoice(68,"Screw driver",106,6.99),
            new Invoice(56,"Jig saw",21,11.00),
            new Invoice(3,"Wrench",34,7.50));
        //Display the table of invoices using Invoice toString().
        //Print table header.
        System.out.println(
            "Part number\tPart description\tQuantity\tPrice per item\t%8s".
                formatted("Value")
        );
        invoices.stream()
            .forEach(System.out::print);
        //a)Use streams to sort Invoice object by partDecsription, then display the results.
        System.out.println(
            "\nInvoices sorted by Part description\nPart number\tPart description\tQuantity\tPrice per item\t%8s".
                formatted("Value"));
        invoices.stream().sorted((a,b)->a.getPartDescription().compareTo(b.
            getPartDescription())).collect(Collectors.toList()).forEach(
            System.out::print);
        //b)Use streams to sort Invoice object by price, then display the results.
        System.out.println(
            "\nInvoices sorted by Price\nPart number\tPart description\tQuantity\tPrice per item\t%8s".
                formatted("Value"));
        invoices.stream().sorted(
            (a,b)->a.getPricePerItem()>b.getPricePerItem()?1:a.getPricePerItem()<b.
            getPricePerItem()?-1:0).collect(Collectors.toList()).forEach(
            System.out::print);
        //c)Use streams to map each Invoice to its partDescription and quantity,
        //  then display the results.
        System.out.println(
            "\nPart Description and Quantity for each Invoice\nPart description\tQuantity");
        invoices.stream().sorted(
            (Invoice a,Invoice b)->a.getQuantity()>b.getQuantity()?1:a.
            getQuantity()<b.getQuantity()?-1:0).
            map((a)->String.
            format("%-11s\t%16d%n",a.getPartDescription(),a.getQuantity())).
            forEach(
                System.out::print);
    }

}
