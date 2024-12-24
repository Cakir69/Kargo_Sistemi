package org.example;

public class ShipmentStack {

    private Shipment[] stackArray;
    private int top;
    private int capacity;

    public ShipmentStack(int capacity)
    {
        this.capacity = capacity;
        this.stackArray = new Shipment[capacity];
        this.top = -1;
    }

    public Shipment[] GetArray()
    {
        return stackArray;
    }

    public void push(Shipment shipment)
    {
        if (isFull()) {
            shiftLeft();
        }
        stackArray[++top] = shipment;
    }


    private void shiftLeft()
    {
        for (int i = 1; i <= top; i++)
        {
            stackArray[i - 1] = stackArray[i];
        }
        top--;
    }

    public Shipment pop()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top--];
    }

    public boolean isFull()
    {
        return top == capacity - 1;
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public void printStack()
    {
        if (isEmpty())
        {
            //  System.out.println("Stack is empty");
            return;
        }
        for (int i = 0; i <= top; i++)
        {
            stackArray[i].PrintInfo();
        }
    }

  /* public static void main(String[] args) {
        ShipmentStack q = new ShipmentStack(5);

        // deQueue is not possible on empty queue
        q.Pop();

        // enQueue 5 elements
        q.Push(new Shipment(1,"sd","sd"));
        q.Push(new Shipment(2,"sd","sd"));
        q.Push(new Shipment(3,"sd","sd"));
        q.Push(new Shipment(4,"sd","sd"));
        q.Push(new Shipment(5,"sd","sd"));

        // 6th element can't be added to because the queue is full
        q.Push(new Shipment(1,"sd","sd"));

        q.display();

        // deQueue removes element entered first i.e. 1
        q.Pop();

        // Now we have just 4 elements
        q.display();

    }*/


}
