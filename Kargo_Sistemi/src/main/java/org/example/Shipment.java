package org.example;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static org.example.TreeDeneme.findShortestPath;

public class Shipment
{
     String shipmentID;
     LocalDate givenDate;
     LocalDate deliveryDate;

    int deliveryTime;
    String city;

    Shipment next;

    public enum State
    {
        ISLEME_ALINDI,
        TESLIM_EDILDI,
        TESLIMATTA
    }

    private State durum;

    public Shipment(String shipmentID,String city,LocalDate givenDate)
    {
        this.shipmentID = shipmentID;
        this.city = city;
        this.deliveryTime = SetDeliveryTime(city);
        this.givenDate = givenDate;
        this.deliveryDate = FindDeliveryDate();
        this.durum = State.ISLEME_ALINDI;
        this.next = null;
    }

    public void Update(LocalDate currentDay)
    {
        if(currentDay.equals(givenDate))
        {
            durum=State.ISLEME_ALINDI;
        }
        else if(currentDay.equals(deliveryDate)||currentDay.isAfter(deliveryDate))
        {
            durum=State.TESLIM_EDILDI;
            deliveryTime = 0;
        }
        else if(currentDay.isAfter(givenDate)&&currentDay.isBefore(deliveryDate))
        {
            durum = State.TESLIMATTA;
            deliveryTime = (int)ChronoUnit.DAYS.between(currentDay,deliveryDate);
        }

    }

    private int SetDeliveryTime(String city)
    {
        Tree tree = TreeInitializer.initializeTree();
        List<String> shortestPath = TreeDeneme.findShortestPath(tree.root, city);
        return shortestPath.size();
    }

    public List<String> findShortestPath() {
        Tree tree = TreeInitializer.initializeTree();
        List<String> shortestPath = TreeDeneme.findShortestPath(tree.root, city);
        return shortestPath;
    }

    private LocalDate FindDeliveryDate()
    {
        return givenDate.plusDays(deliveryTime);
    }

    public void PrintInfo()
    {
        System.out.println("\nKargo Id :"+shipmentID);

        if(durum == State.ISLEME_ALINDI)
        {
            System.out.println("Kargo Durumu : Isleme Alindi");
            System.out.println("Kargo Teslim Süresi : "+deliveryTime+" gün");
        }
        else if(durum == State.TESLIMATTA)
        {
            System.out.println("Kargo Durumu : Teslimatta");
            System.out.println("Kargo Teslim Süresi : "+deliveryTime+" gün");
        }
        else if (durum == State.TESLIM_EDILDI)
        {
            System.out.println("Kargo Durumu : Teslim Edildi");

        }

    }

}
