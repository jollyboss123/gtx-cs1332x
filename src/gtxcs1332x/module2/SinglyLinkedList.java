package gtxcs1332x.module2;

/**
 * @author jolly
 */
public class SinglyLinkedList {
    private static class Node {
        private int data;
        private Node next;

        private Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        private Node(int data) {
            this(data, null);
        }

        @Override
        public String toString() {
            return Integer.toString(data);
        }
    }

    private Node head;
    private int size; // for edge cases checking in O(1) T

    public void addToFront(int data) {
        Node newNode = new Node(data);
        newNode.next = head; // point to current head
        head = newNode; // redirect the head reference to point to the new node
        size++;
    }

    public void addToBack(int data) {
        if (size == 0) {
            head = new Node(data);
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node(data);
        }
        size++;
    }

    public void removeFromFront() {
        if (size != 0) {
            head = head.next;
            size--;
        }
    }

    public void removeFromBack() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            head = null;
            size--;
            return;
        }

        Node curr = head;
        // iterate to the 2nd last node
        while (curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        // to keep track of the node we are looking
        // new pointer is important because we cannot accidentally destroy
        // the head of the SinglyLinkedList
        // if the head pointer moves to the next node we lose all access
        // to the old head
        Node curr = head;
        while (curr != null) {
            answer.append(curr);
            if (curr.next != null) {
                answer.append("->");
            }
            curr = curr.next;
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        var sll = new SinglyLinkedList();
        sll.addToFront(1);
        sll.addToBack(2);
        System.out.println(sll);
        sll.removeFromFront();
        System.out.println(sll);
        sll.removeFromBack();
        System.out.println(sll);
        sll.removeFromBack();
        System.out.println(sll.size);
        sll.removeFromFront();
        System.out.println(sll.size);
    }
}
