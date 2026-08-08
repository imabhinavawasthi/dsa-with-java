package SessionHeaps;

import java.util.ArrayList;

public class HeapsBasics {
    static class MinHeap {
        ArrayList<Integer> arr;
        MinHeap() {
            arr = new ArrayList<>();
        }
        public void initialize() {
            this.arr.add(20);
            this.arr.add(25);
            this.arr.add(70);
            this.arr.add(30);
            this.arr.add(60);
        }
        public int getMin() {
            if(this.arr.isEmpty()) return -1;
            return this.arr.getFirst();
        }
        public void insert(int x) {
            this.arr.add(x);
            int i = this.arr.size()-1;

            while(i>0) {
                int parent = (i-1) / 2;
                if(this.arr.get(i)<=this.arr.get(parent)) {
                    int temp = this.arr.get(i);
                    this.arr.set(i, this.arr.get(parent));
                    this.arr.set(parent, temp);
                }
                i = parent;
            }
        }
        public void remove() {
            int last = this.arr.size()-1;
            int temp = this.arr.getFirst();
            this.arr.set(0, this.arr.get(last));
            this.arr.set(last, temp);
            this.arr.removeLast();

            int i = 0;
            while(i<this.arr.size()) {
                int left = 2*i+1;
                int right = 2*i+2;
                int smallest = i;

                if(left<this.arr.size() && this.arr.get(left)<this.arr.get(smallest)) {
                    smallest = left;
                }
                if(right<this.arr.size() && this.arr.get(right)<this.arr.get(smallest)) {
                    smallest = right;
                }
                temp = this.arr.get(i);
                this.arr.set(i, this.arr.get(smallest));
                this.arr.set(smallest, temp);

                if(i==smallest) break;
                i = smallest;
            }
        }
    }
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.initialize();

        minHeap.insert(40);
        minHeap.remove();

        System.out.println(minHeap.arr);
        System.out.println(minHeap.getMin());
    }
}
