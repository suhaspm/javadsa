public class BinaryHeap {
    int arr[];
    int sizeOfTree;

    public BinaryHeap(int size){
        arr = new int[size+1];
        sizeOfTree = 0;
    }

    public boolean isEmpty(){
        return sizeOfTree == 0;
    }

    public Integer peek(){
        if(isEmpty())
            return null;
        else
            return arr[1];
    }

    public int sizeOfBH(){
        return sizeOfTree;
    }

    public void levelOrder(){
        for (int i = 0; i < sizeOfTree; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println("\n");
    }

    public void insertInHeap(int value, String type){
        arr[sizeOfTree+1] = value;
        sizeOfTree++;
        heapifyBottomToTop(sizeOfTree, type);
        System.out.println("Inserted "+value+ " successfully in to the heap");
    }

    public void heapifyBottomToTop(int index, String type){
        int parent = index / 2;
        if(index <= 1) return;
        if(type == "Min"){
            if(arr[index] < arr[parent]){
                int tmp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = tmp;
            }
        }
        else if(type == "Max"){
            if(arr[index] > arr[parent]){
                int tmp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = tmp;
            }
        }
        heapifyBottomToTop(parent, type);
    }

    public void heapifyTopToBottom(int index, String type){
        int left = index * 2;
        int right = index * 2 +1;
        int swapChild = 0;
        if(sizeOfTree < left) return;

        if(type == "Max"){
            if(sizeOfTree==left){
                if(arr[index] < arr[left]){
                    int tmp = arr[index];
                    arr[index] = arr[left];
                    arr[left] = tmp;
                }
                return;
            }
            else{
                if(arr[left]>arr[right])
                    swapChild = left;
                else swapChild = right;
            }
            if(arr[index]<arr[swapChild]){
                int tmp = arr[index];
                arr[index] = arr[swapChild];
                arr[swapChild] = tmp;
            }
        }
        else if(type == "Min"){
            if(sizeOfTree==left){
                if(arr[index] > arr[left]){
                    int tmp = arr[index];
                    arr[index] = arr[left];
                    arr[left] = tmp;
                }
                return;
            }
            else{
                if(arr[left]<arr[right])
                    swapChild = left;
                else swapChild = right;
            }
            if(arr[index]>arr[swapChild]){
                int tmp = arr[index];
                arr[index] = arr[swapChild];
                arr[swapChild] = tmp;
            }
        }
        heapifyTopToBottom(swapChild, type);
    }

    public int extractFromHeap(String type){
        if(isEmpty())
            return -1;
        else{
            int extractedValue = arr[1];
            arr[1] = arr[sizeOfTree];
            sizeOfTree--;
            heapifyTopToBottom(1, type);
            return extractedValue;
        }
    }



    // delete
    public void deleteBH() {
        arr = null;
        System.out.println("BH has been successfully deleted");
    }
}
