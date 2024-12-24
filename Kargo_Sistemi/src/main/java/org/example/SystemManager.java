package org.example;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import static org.example.TreeDeneme.findShortestPath;

public class SystemManager
{
    public LocalDate currentDay;

    private Customer HeadCustomer;

    ShipmentStack DeliveredShipments;
    PriorityStack NotDeliveredShipments;

    private int customerCount = 0;
    private int shipmentCount = 0;

    public SystemManager(int capacity)
    {
        DeliveredShipments = new ShipmentStack(capacity);
        NotDeliveredShipments = new PriorityStack(capacity);
        currentDay=LocalDate.now();
    }

    public Customer AddCustomer(String name, String surname)
    {
        String id=IdMaker.CreateID(customerCount);
        customerCount++;

        Customer newCustomer=new Customer(id,name,surname);
        if (HeadCustomer == null)
        {
            HeadCustomer = newCustomer;
            return newCustomer;
        }

        Customer last = HeadCustomer;

        while (last.next != null)
        {
            last = last.next;
        }

        last.next = newCustomer;
        return newCustomer;
    }

    // kargonun gideceği şehir eklenecek
    public Shipment AddShipment(String musteri_id,String city)
    {
        Customer sender=FindCustomer(musteri_id);

        if(sender==null){
            return null;
        }


        String id=IdMaker.CreateID(shipmentCount);
        shipmentCount++;

        Shipment newShipment = new Shipment(id, city,currentDay);

        sender.AddShipment(newShipment);

        NotDeliveredShipments.push(newShipment);

        return newShipment;
    }

    public Shipment FindDeliveredShipment(String shipment_id)
    {
        Shipment[] Nsorted = SearchSystem.SortedList(NotDeliveredShipments.GetArray(),NotDeliveredShipments.size);
        Shipment Nshipment=SearchSystem.BinarySearch(Nsorted,shipment_id);

        Shipment[] Dsorted = SearchSystem.SortedList(DeliveredShipments.GetArray());
        Shipment Dshipment=SearchSystem.BinarySearch(Dsorted,shipment_id);

        if (Nshipment != null && Nshipment.shipmentID.equals(shipment_id))
        {
            return Nshipment;
        }
        else if (Dshipment != null && Dshipment.shipmentID.equals(shipment_id))
        {
            return Dshipment;
        }
        else
        {
            return null;
        }
    }

    public void PrintAllShipments()
    {
        DeliveredShipments.printStack();
        NotDeliveredShipments.printStack();
    }

    public Customer FindCustomer(String customer_id)
    {
        if (HeadCustomer == null)
        {
            return null;
        }

        Customer pointer = HeadCustomer;

        while (pointer != null)
        {
            if (pointer.customerID.equals(customer_id))
            {
                return pointer;
            }
            pointer = pointer.next;
        }


        return null;
    }

    public void SystemUpdate(int skipDay)
    {
        currentDay=currentDay.plusDays(skipDay);
        for (Shipment shipment : NotDeliveredShipments.GetArray())
        {
            if(shipment!=null)
            {
                shipment.Update(currentDay);
                if(shipment.deliveryTime==0)
                {
                    NotDeliveredShipments.pop();
                    DeliveredShipments.push(shipment);
                }
            }
        }
    }


}
