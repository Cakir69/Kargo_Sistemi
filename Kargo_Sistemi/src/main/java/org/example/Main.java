package org.example;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

import static org.example.TreeDeneme.isCityValid;


class Main {

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        SystemManager systemManager = new SystemManager(1000);

        while (true) {
            System.out.println("\n=== Kargo Takip Sistemi ===");
            System.out.println("1. Yeni Müşteri Ekle");
            System.out.println("2. Kargo Gönderimi Ekle");
            System.out.println("3. Kargo Durumu Sorgula");
            System.out.println("4. Gönderim Geçmişini Görüntüle");
            System.out.println("5. Tüm Kargoları Listele (Sıralı)");
            System.out.println("6. Teslimat Rotalarını Göster");
            System.out.println("7. Islemi Bitir ve Gun Atla");
            System.out.println("8. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Musteri Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Musteri Soyadı: ");
                    String surname = scanner.nextLine();
                    Customer Csample =systemManager.AddCustomer(name,surname);
                    Csample.PrintInfo();
                    System.out.println("LUTFEN MUSTERI ID'INIZI NOT ALINIZ.TEKRAR GOSTERILMEYECEK !!!");
                    System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                    scanner.nextLine();
                    break;

                case 2:
                    System.out.print("Müşteri ID: ");
                    String customerId=scanner.nextLine();

                    System.out.println("Kargonun Gideceği Şehir: ");
                    String city=scanner.nextLine();

                    if(!isCityValid(city))
                    {
                        System.out.println("Sehir ismı yanlis ! Lutfen turkce karakter yazmamaya ve sehrin basharfini buyuk yapmaya dikkat ediniz.(Izmir,Istanbul vb..)");
                        System.out.println("Devam etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();

                        break;
                    }

                    Shipment Ssample= systemManager.AddShipment(customerId, city);

                    if(Ssample==null)
                    {
                        System.out.println("Musteri ID yanlis veya boyle bir musteri yok !");

                        System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();

                        break;
                    }


                    System.out.println("Kargonuz Başarıyla Gönderildi !");
                    Ssample.PrintInfo();

                    System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                    scanner.nextLine();

                    break;

                case 3:
                    System.out.print("Kargo ID ile durumu sorgulamak için ID girin: ");
                    String shipmentId = scanner.nextLine();
                    Shipment Ssample2=systemManager.FindDeliveredShipment(shipmentId);
                    if(Ssample2==null)
                    {
                        System.out.println("Shipment ID yanlis veya boyle bir shipment yok !");

                        System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();

                        break;
                    }
                    Ssample2.PrintInfo();
                    System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.print("Gönderim geçmişi için müşteri ID girin: ");
                    String historyId = scanner.nextLine();
                    Customer Csample2=systemManager.FindCustomer(historyId);
                    if(Csample2==null)
                    {
                        System.out.println("Musteri ID yanlis veya boyle bir musteri yok !");

                        System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();

                        break;
                    }

                    Csample2.PrintLastShipmentsInfo();
                    System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                    scanner.nextLine();

                    break;

                case 5:
                    systemManager.PrintAllShipments();
                    System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                    scanner.nextLine();
                    break;

                case 6:
                    System.out.println("Kargonun teslimat rotasını görmek için kargo ID girin: ");
                    String shipmentId2 = scanner.nextLine();
                    Shipment Ssample3=systemManager.FindDeliveredShipment(shipmentId2);
                    if(Ssample3==null)
                    {
                        System.out.println("Shipment ID yanlis veya boyle bir shipment yok !");

                        System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();

                        break;
                    }

                    if (Ssample3.findShortestPath().isEmpty())
                    {
                        System.out.println("Hedef düğüm bulunamadı.");
                        System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();
                        break;
                    }
                    else
                    {
                        System.out.println("En kısa yol: " + String.join(" -> ", Ssample3.findShortestPath()));
                        System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                        scanner.nextLine();
                        break;
                    }
                case 7:
                    System.out.println("Islemı Bitirdiniz.Lutfen Atlamak Istediginiz Gun Sayisini Giriniz.");
                    int skipDay = scanner.nextInt();
                    systemManager.SystemUpdate(skipDay);
                    System.out.println("Devam Etmek icin herhangi bir tusa basiniz...");
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.println("Sistemden Cikiliyor...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }

}