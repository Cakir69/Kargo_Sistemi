package org.example;

public class SearchSystem {

    public static Shipment[] SortedList(Shipment[] shipments)
    {
        Shipment[] Ids = shipments.clone();

        int actualSize = 0;
        for (Shipment shipment : shipments)
        {
            if (shipment == null)
            {
                break;
            }
            actualSize++;
        }

        for (int i = 0; i < actualSize - 1; i++)
        {
            for (int j = 0; j < actualSize - 1 - i; j++)
            {
                int num1 = Integer.parseInt(Ids[j].shipmentID.substring(Ids[j].shipmentID.length() - 4));
                int num2 = Integer.parseInt(Ids[j + 1].shipmentID.substring(Ids[j + 1].shipmentID.length() - 4));

                if (num1 > num2)
                {
                    Shipment temp = Ids[j];
                    Ids[j] = Ids[j + 1];
                    Ids[j + 1] = temp;
                }
            }
        }
        return Ids;
    }
    public static Shipment[] SortedList(Shipment[] shipments, int size)
    {
        Shipment[] activeShipments = new Shipment[size];
        System.arraycopy(shipments, 0, activeShipments, 0, size);

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                int num1 = Integer.parseInt(activeShipments[j].shipmentID.substring(activeShipments[j].shipmentID.length() - 4));
                int num2 = Integer.parseInt(activeShipments[j + 1].shipmentID.substring(activeShipments[j + 1].shipmentID.length() - 4));

                if (num1 > num2) {
                    Shipment temp = activeShipments[j];
                    activeShipments[j] = activeShipments[j + 1];
                    activeShipments[j + 1] = temp;
                }
            }
        }

        return activeShipments;
    }

    public static Shipment BinarySearch(Shipment[] sortedShipments, String targetId)
    {
        int targetNum;

        try
        {
            targetNum = Integer.parseInt(targetId.substring(targetId.length() - 4));
        }
        catch (Exception e)
        {
            return null;
        }

        int actualSize = 0;

        try
        {
            for (Shipment shipment : sortedShipments)
            {
                if (shipment == null)
                {
                    break;
                }
                actualSize++;
            }

            int left = 0, right = actualSize - 1;

            while (left <= right)
            {
                int mid = left + (right - left) / 2;

                if (sortedShipments[mid] == null)
                {
                    break;
                }

                int midNum = Integer.parseInt(sortedShipments[mid].shipmentID.substring(sortedShipments[mid].shipmentID.length() - 4));

                if (midNum == targetNum)
                {
                    return sortedShipments[mid];
                }
                else if (midNum < targetNum)
                {
                    left = mid + 1;
                }
                else
                {
                    right = mid - 1;
                }
            }

            return null;
        }
        catch (Exception e)
        {
            return null;
        }

    }

   /* public static Shipment BinarySearch(Shipment[] sortedShipments, String targetId)
    {
        int targetNum;

        try
        {
            targetNum = Integer.parseInt(targetId.substring(targetId.length() - 4));
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        int actualSize = 0;

        for (Shipment shipment : sortedShipments)
        {
            if (shipment == null)
            {
                break;
            }
            actualSize++;
        }

        int left = 0, right = actualSize - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;

            if (sortedShipments[mid] == null)
            {
                break;
            }

            int midNum = Integer.parseInt(sortedShipments[mid].shipmentID.substring(sortedShipments[mid].shipmentID.length() - 4));

            if (midNum == targetNum)
            {
                return sortedShipments[mid];
            }
            else if (midNum < targetNum)
            {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }

        return null;
    }*/

}



