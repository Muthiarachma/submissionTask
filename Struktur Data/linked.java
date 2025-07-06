package vincentlow.parkee.parkingpos;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SingleLinkedList {
    Node head;

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void deleteByValue(int data) {
        if (head == null) return;

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class linked {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();

        list.insertAtEnd(10);
        list.insertAtBeginning(5);
        list.insertAtEnd(20);
        list.insertAtEnd(25);

        System.out.println("Linked List awal:");
        list.display(); 

        System.out.println("Hapus nilai 10:");
        list.deleteByValue(10);
        list.display(); 

        System.out.println("Hapus nilai 5:");
        list.deleteByValue(5);
        list.display();

        System.out.println("Hapus nilai 100 (tidak ada):");
        list.deleteByValue(100);
        list.display(); 
    }
}
