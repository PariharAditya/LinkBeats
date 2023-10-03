package com.musicplayer;

public class CircularDoublyLinkedList {
    public static class Node {
        private String data;
        private Node next;
        private Node previous;

        public Node(String data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public String getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node head;

    public CircularDoublyLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addNode(String data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = newNode;
            head.next = head;
            head.previous = head;
        } else {
            Node last = head.previous;

            // Connect the new node
            newNode.next = head;
            newNode.previous = last;

            // Adjust pointers of surrounding nodes
            last.next = newNode;
            head.previous = newNode;
        }
    }

    public void removeNode(String data) {
        if (isEmpty()) {
            System.out.println("Playlist is empty. Cannot remove node.");
            return;
        }

        Node current = head;

        do {
            if (current.data.equals(data)) {
                Node previousNode = current.previous;
                Node nextNode = current.next;

                previousNode.next = nextNode;
                nextNode.previous = previousNode;

                if (current == head) {
                    head = nextNode;
                }

                System.out.println("Removed: " + data);
                return;
            }

            current = current.next;
        } while (current != head);

        System.out.println("Song not found: " + data);
    }

    public Node getFirst() {
        return head;
    }
}
