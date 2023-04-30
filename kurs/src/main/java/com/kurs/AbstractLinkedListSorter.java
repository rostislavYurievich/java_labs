package com.kurs;

import java.util.LinkedList;

abstract public class AbstractLinkedListSorter {
    class Node {
        int data = 0;
        Node next;
     
        public Node(int data) {
          this.data = data;
        }
     }
    abstract public LinkedList<Info> sort(LinkedList<Info> unsortedList);
}
