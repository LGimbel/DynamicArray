
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        System.out.println("Hello world!");
        Array<Integer> myArray = new Array<>(2);
        myArray.add(1);
        myArray.add(2);
        myArray.add(3);
        myArray.add(4);
        myArray.add(12,1);
        myArray.resize(10);
        for(int i = 0; i < 5; i++){
            myArray.add(i);
        }
        myArray.printArray();
        System.out.println("\n\n");
        System.out.println(myArray.get(2));

    }



}

class Array<E>{
    private int size;
    private int lastIndex;
    private Object[] dataArray;
    private int currentRightMostItem = 0;
    private boolean hasAdded = false;


    public Array(int startSize){
        this.size = startSize;
        this.lastIndex = startSize - 1;
        this.dataArray = new Object[startSize];

    }
    public void add(E data){
        //add data by storing in a temp array which is a clone of the current array and creating a new array that is one size bigger than the size
        //this is very inefficient, but I wasn't sure how to do it otherwise.
        if (currentRightMostItem + 1 > lastIndex){
            Object[] tempArray = dataArray.clone();
            Object[] newArray = new Object[size + 1];
            for(int i = 0; i < size; i++){
                newArray[i] = tempArray[i];
            }
            newArray[size] = data;
            size ++;
            currentRightMostItem = size;
            lastIndex = size-1;
            //finally set the instance array to the new array
            dataArray = newArray;

        }
        else {
            if(hasAdded){
                dataArray[currentRightMostItem + 1] = data;
                currentRightMostItem ++;
            }
            else{
                dataArray[currentRightMostItem] = data;
                hasAdded = true;
            }
            }

    }
    public void add(E data,int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();// i think this is the correct exception
        } else {
            if (currentRightMostItem + 1 > lastIndex) {
                Object[] newArray = Arrays.copyOf(dataArray,dataArray.length + 1);
                for (int i = size - 1; i >= index; i--) {
                    newArray[i + 1] = newArray[i];
                }
                newArray[index] = data;
                size++;
                currentRightMostItem = size;
                lastIndex = size - 1;
                //finally set the instance array to the new array
                dataArray = newArray;

            }
            else{
                Object[] reference = Arrays.copyOf(dataArray,dataArray.length);
                for (int i = size - 1; i > index;i--){
                    reference[i + 1] = reference[i];
                }
                reference[index] = data;
                dataArray = reference;
                currentRightMostItem ++;
            }
        }
    }
    public void resize(int newSize){
        Object[] newArray = Arrays.copyOf(dataArray,newSize);
        dataArray = newArray;
    }


    public int getSize() {
        return size;
    }
    public void printArray(){
        for (Object o : dataArray){
            System.out.println(o);
        }
    }
    public void set(E data,int index){
        dataArray[index] = data;
    }
    public Object get(int index){
        return dataArray[index];
    }

}

