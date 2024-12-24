package org.example;

public class Customer {

    String customerID;
    String name;
    String surName;

    Customer next;

    Shipment HeadShipment;
    ShipmentStack PastShipments;

    public Customer(String customerID, String name,String surName)
    {
        PastShipments = new ShipmentStack(5);
        this.customerID = customerID;
        this.name = name;
        this.surName=surName;
        this.HeadShipment = null;
        this.next=null;
    }

    public void PrintLastShipmentsInfo()
    {
        PastShipments.printStack();
    }

    public void PrintInfo()
    {
        System.out.println("\nMusteri Id : "+customerID+"\nAdi :"+name+"\nSoyadi :"+surName);
    }

    public void AddShipment(Shipment shipment)
    {
        AddLinkedList(shipment);
        PastShipments.push(shipment);
    }

    public void displayLastShipments()
    {
        PastShipments.printStack();
    }

    private void AddLinkedList(Shipment shipment)
    {
        if(HeadShipment==null)
        {
            HeadShipment=shipment;
            return;
        }
        Shipment last = HeadShipment;
        while (last.next != null)
        {
            last = last.next;
        }

        last.next = shipment;
    }
}
