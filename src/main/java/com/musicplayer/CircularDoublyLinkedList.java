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
        
        public Node getPrevious() {
            return previous;
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

    public Node getFirst() {
        return head;
    }
    
    public void playNextSong() {
        if (isEmpty()) {
            System.out.println("Playlist is empty. Cannot play next song.");
            return;
        }

        // Get the currently playing song (head)
        Node currentSong = head;

        // Play the next song (the one after the current song)
        Node nextSong = currentSong.next;

        // System.out.println("Playing next song: " + nextSong.getData());

        // Update the currently playing song to the next song
        head = nextSong;
    }
    
    public String getCurrentSong() {
        if (isEmpty()) {
            return "No song is currently playing";
        }

        // Get the currently playing song (head)
        return head.getData();
    }
    
    public void playPreviousSong() {
        if (isEmpty()) {
            System.out.println("Playlist is empty. Cannot play previous song.");
            return;
        }

        // Get the currently playing song (head)
        Node currentSong = head;

        // Play the previous song (the one before the current song)
        Node previousSong = currentSong.getPrevious();

        System.out.println("Playing previous song: " + previousSong.getData());

        // Update the currently playing song to the previous song
        head = previousSong;
    }

}
