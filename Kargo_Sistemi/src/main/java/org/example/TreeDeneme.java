package org.example;

import java.util.*;

class Node
{
    String name;
    List<Node> children;

    Node(String name)
    {
        this.name = name;
        this.children = new ArrayList<>();
    }

    void addChild(Node child)
    {
        this.children.add(child);
    }
}

class Tree
{
    Node root;

    void setRoot(String name) {
        this.root = new Node(name);
    }
}
class TreeInitializer
{
    public static Tree initializeTree()
    {
        Tree tree = new Tree();
        tree.setRoot("Ankara");

        Node node2 = new Node("Istanbul");
        Node node3 = new Node("Denizli");
        Node node4 = new Node("Nigde");
        Node node5 = new Node("Diyarbakir");
        Node node6 = new Node("Erzurum");
        Node node7 = new Node("Tokat");
        Node node8 = new Node("Kastamonu");

        tree.root.addChild(node2);
        tree.root.addChild(node3);
        tree.root.addChild(node4);
        tree.root.addChild(node5);
        tree.root.addChild(node6);
        tree.root.addChild(node7);
        tree.root.addChild(node8);

        addCitiesToNode2(node2);
        addCitiesToNode3(node3);
        addCitiesToNode4(node4);
        addCitiesToNode5(node5);
        addCitiesToNode6(node6);
        addCitiesToNode7(node7);
        addCitiesToNode8(node8);

        return tree;
    }

    private static void addCitiesToNode2(Node node2) {
        node2.addChild(new Node("Izmit"));
        node2.addChild(new Node("Tekirdag"));
        node2.addChild(new Node("Kirklareli"));
        node2.addChild(new Node("Edirne"));
        node2.addChild(new Node("Canakkale"));
        node2.addChild(new Node("Balikesir"));
        node2.addChild(new Node("Bursa"));
        node2.addChild(new Node("Bilecik"));
        node2.addChild(new Node("Yalova"));
        node2.addChild(new Node("Adapazari"));
    }

    private static void addCitiesToNode3(Node node3) {
        node3.addChild(new Node("Izmir"));
        node3.addChild(new Node("Manisa"));
        node3.addChild(new Node("Kutahya"));
        node3.addChild(new Node("Usak"));
        node3.addChild(new Node("Aydin"));
        node3.addChild(new Node("Mugla"));
        node3.addChild(new Node("Afyonkarahisar"));
        node3.addChild(new Node("Burdur"));
        node3.addChild(new Node("Isparta"));
        node3.addChild(new Node("Antalya"));
    }

    private static void addCitiesToNode4(Node node4) {
        node4.addChild(new Node("Konya"));
        node4.addChild(new Node("Aksaray"));
        node4.addChild(new Node("Karaman"));
        node4.addChild(new Node("Mersin"));
        node4.addChild(new Node("Adana"));
        node4.addChild(new Node("Nevsehir"));
        node4.addChild(new Node("Kayseri"));
        node4.addChild(new Node("Osmaniye"));
        node4.addChild(new Node("Hatay"));
        node4.addChild(new Node("Gaziantep"));
        node4.addChild(new Node("Kilis"));
        node4.addChild(new Node("Kahramanmaras"));
    }

    private static void addCitiesToNode5(Node node5) {
        node5.addChild(new Node("Sanliurfa"));
        node5.addChild(new Node("Adiyaman"));
        node5.addChild(new Node("Malatya"));
        node5.addChild(new Node("Elazig"));
        node5.addChild(new Node("Mardin"));
        node5.addChild(new Node("Batman"));
        node5.addChild(new Node("Sirnak"));
        node5.addChild(new Node("Hakkari"));
        node5.addChild(new Node("Siirt"));
        node5.addChild(new Node("Bitlis"));
        node5.addChild(new Node("Van"));
    }

    private static void addCitiesToNode6(Node node6) {
        node6.addChild(new Node("Erzincan"));
        node6.addChild(new Node("Tunceli"));
        node6.addChild(new Node("Bingol"));
        node6.addChild(new Node("Mus"));
        node6.addChild(new Node("Agri"));
        node6.addChild(new Node("Igdir"));
        node6.addChild(new Node("Kars"));
        node6.addChild(new Node("Ardahan"));
        node6.addChild(new Node("Artvin"));
        node6.addChild(new Node("Rize"));
        node6.addChild(new Node("Trabzon"));
        node6.addChild(new Node("Bayburt"));
    }

    private static void addCitiesToNode7(Node node7) {
        node7.addChild(new Node("Sivas"));
        node7.addChild(new Node("Giresun"));
        node7.addChild(new Node("Gumushane"));
        node7.addChild(new Node("Ordu"));
        node7.addChild(new Node("Amasya"));
        node7.addChild(new Node("Yozgat"));
        node7.addChild(new Node("Samsun"));
        node7.addChild(new Node("Kirikkale"));
        node7.addChild(new Node("Kirsehir"));
    }

    private static void addCitiesToNode8(Node node8) {
        node8.addChild(new Node("Corum"));
        node8.addChild(new Node("Cankiri"));
        node8.addChild(new Node("Sinop"));
        node8.addChild(new Node("Karabuk"));
        node8.addChild(new Node("Bartin"));
        node8.addChild(new Node("Zonguldak"));
        node8.addChild(new Node("Duzce"));
        node8.addChild(new Node("Bolu"));
        node8.addChild(new Node("Eskisehir"));
    }

}
public class TreeDeneme
{
    public static List<String> findShortestPath(Node root, String target)
    {
        // BFS kullanarak en kısa yolu bul
        Queue<List<Node>> queue = new LinkedList<>();
        List<Node> startPath = new ArrayList<>();
        startPath.add(root);
        queue.add(startPath);

        while (!queue.isEmpty())
        {
            List<Node> currentPath = queue.poll();
            Node currentNode = currentPath.get(currentPath.size() - 1);

            // Hedef düğüm bulundu
            if (currentNode.name.equals(target))
            {
                List<String> resultPath = new ArrayList<>();
                for (Node node : currentPath)
                {
                    resultPath.add(node.name);
                }
                return resultPath;
            }

            // Çocuk düğümleri sıraya ekle
            for (Node child : currentNode.children)
            {
                List<Node> newPath = new ArrayList<>(currentPath);
                newPath.add(child);
                queue.add(newPath);
            }
        }

        // Hedef düğüm bulunamadı
        return Collections.emptyList();
    }

    public static List<String> getAllCityNames(Node root) {
        List<String> cityNames = new ArrayList<>();
        collectCityNames(root, cityNames);
        return cityNames;
    }

    private static void collectCityNames(Node node, List<String> cityNames) {
        if (node == null) return;

        cityNames.add(node.name);
        for (Node child : node.children) {
            collectCityNames(child, cityNames);
        }
    }

    public static boolean isCityValid(String cityName)
    {
        Tree tree = TreeInitializer.initializeTree();

        List<String> cityNames = getAllCityNames(tree.root);
        return cityNames.contains(cityName);
    }

}

