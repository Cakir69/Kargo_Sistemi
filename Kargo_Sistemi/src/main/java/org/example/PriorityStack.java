package org.example;
import java.time.LocalDate;

public class PriorityStack
{
    private Shipment[] heap; // Elemanları saklayacak dizi
    public int size;   // Kuyruğun mevcut boyutu
    private int capacity; // Maksimum kapasite

    public PriorityStack(int capacity)
    {
        this.capacity = capacity;
        this.heap = new Shipment[capacity];
        this.size = 0;
    }

    public Shipment[] GetArray()
    {
        return heap;
    }
    // Eleman ekleme
    public void push(Shipment value) {
        if (size == capacity) {
            throw new IllegalStateException("Priority Queue is full");
        }

        // Yeni elemanı sona ekle
        heap[size] = value;
        size++;

        // Heap'i yukarı doğru düzenle
        heapifyUp(size - 1);
    }

    // En küçük elemanı çıkar ve döndür
    public Shipment pop() {
        if (size == 0) {
            return null;
        }

        Shipment root = heap[0]; // Kök eleman
        heap[0] = heap[size - 1]; // Son elemanı köke taşı
        size--;

        // Heap'i aşağı doğru düzenle
        heapifyDown(0);

        return root;
    }

    // En küçük elemanı sadece gör
    public Shipment peek() {
        if (size == 0) {
            throw new IllegalStateException("Priority Queue is empty");
        }
        return heap[0];
    }

    // Yukarı doğru düzenleme
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // Eğer ebeveyn eleman daha küçükse dur
            if (heap[parentIndex].deliveryTime <= heap[index].deliveryTime) {
                break;
            }

            // Değiş tokuş yap
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // Aşağı doğru düzenleme
    private void heapifyDown(int index) {
        while (index < size / 2) { // Yaprak olmayan düğümler için
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallerChild = leftChild;

            // Sağ çocuk varsa ve daha küçükse
            if (rightChild < size && heap[rightChild].deliveryTime < heap[leftChild].deliveryTime) {
                smallerChild = rightChild;
            }

            // Eğer mevcut eleman daha küçükse dur
            if (heap[index].deliveryTime <= heap[smallerChild].deliveryTime) {
                break;
            }

            // Değiş tokuş yap
            swap(index, smallerChild);
            index = smallerChild;
        }
    }

    // Elemanları takas et
    private void swap(int i, int j) {
        Shipment temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Kuyruğun boş olup olmadığını kontrol et
    public boolean isEmpty() {
        return size == 0;
    }

    // Kuyruğun dolu olup olmadığını kontrol et
    public boolean isFull() {
        return size == capacity;
    }

    public void printStack() {
        if (size == 0)
        {
            return;
        }
        for (int i = 0; i < size; i++)
        {
            heap[i].PrintInfo();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        PriorityStack pq = new PriorityStack(1000);

        Shipment kisi=new Shipment("1","Antlya",LocalDate.now());
        Shipment kisi1=new Shipment("2","Ankara",LocalDate.now());
        Shipment kisi2=new Shipment("3","İstanbul",LocalDate.now());
        Shipment kisi3=new Shipment("4","Diyarbakır",LocalDate.now());
        Shipment kisi4=new Shipment("5","Yalova",LocalDate.now());

        // Eleman ekle
        pq.push(kisi);
        pq.push(kisi1);
        pq.push(kisi2);
        pq.push(kisi3);
        pq.push(kisi4);


        pq.printStack();

        pq.pop();

        pq.printStack();
        pq.pop();

        pq.printStack();
        pq.pop();

        pq.printStack();
        pq.pop();

        pq.printStack();
    }
}
