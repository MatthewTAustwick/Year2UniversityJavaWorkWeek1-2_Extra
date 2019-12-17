package searcher;

import java.util.Arrays;

public class CleverSearcher<T extends Comparable<? super T>> extends Searcher<T>{


    CleverSearcher(T[] array, int k) {
        super(array, k);
    }

    @Override
    public T findElement() throws IndexingError { //Creates the findElement() method defined in "Searcher"
        T[] array = getArray(); //This makes sure that "array" contains the actual array designated in the constructor.
        int k = getIndex(); //This creates "k" which is a denotation of the current index of any of the integers in the array.
        if (k <= 0 || k > array.length) { //This checks to make sure that the given index is within the boundaries of the array and does not exceed it or go below it.
            throw new IndexingError(); //Outputs an error which is defined by its own class - this only occurs if the given index is NOT in the array.
        }
        //Creates a new array called "helperArray" which currently contains the memory needed to store k numbers but is designed to take integers.
        T helperArray[] = (T[]) new Object[k];
        int index = 0; //This line just helps efficiency as "index"  is always set to 0 so it can be used in multiple for each loops (see below).
        for (; index < k; index++){ //For as long as index is less than k, run the code inside.
            helperArray[index] = array[index]; //This copies the current number in the indexth position from the big array into the smaller array.
        }
        Arrays.sort(helperArray); //Sorts the small array from smallest to largest.
        for (; index < array.length; index++){ //For as long as index is less than the length of the big array, run the code inside.
            if (array[index].compareTo(helperArray[0]) > 0){ //If the current number in the big array is bigger than the SMALLEST number in the small array...
                helperArray[0] = array[index]; //Replace the SMALLEST number in the small array with the current number from the big array
                Arrays.sort(helperArray); //Resort the small array so you have the numbers in order, once again.
            }
        } //By the end of this iterative process, you will have the top k largest numbers inside of the small array as they will all be bigger than the large array

        return helperArray[0]; //Ergo, the smallest out of the small array WILL BE the kth largest number
    }
}