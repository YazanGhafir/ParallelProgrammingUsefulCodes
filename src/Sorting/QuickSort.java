package ParallelQuickSort;

/**
 * NOT working ****************
 */
public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array.length > 1) {
            sort(array, 0, array.length - 1);
        }
    }

    private static <T extends Comparable<T>> int findPivot(T[] array, int low, int high) {
        for (int i = low; i < high; i++) {
            if (array[i].compareTo(array[i + 1]) != 0) {
                return (int) (low + (high - low) / 2);
            }
        }
        return -1;  //TODO test
    }


    private static <T extends Comparable<T>> int partition(T[] array, int low, int high, int pivot) {
        while (low < high) {
            while ((low < high) && (array[low].compareTo(array[pivot]) < 0)) {
                low++;
            }
            while ((low < high) && ((array[high].compareTo(array[pivot]) >= 0))) {
                high--;
            }
            if (low < high) {
                swap(array, low, high);  //TODO test
            }
        }
        return low; //low = high
    }



    private static <T extends Comparable<T>> void swap(T[] array, int low, int high) {
        T tmp = array[low];
        array[low] = array[high];
        array[high] = tmp;
    }

    private static <T extends Comparable<T>> void sort(T[] array, int low, int high) {
        int pivot, middle;
        if (high < 0) return;
        pivot = findPivot(array, low, high);
        if (pivot != -1) {
            swap(array, pivot, high);
            pivot = high;
            middle = partition(array, low, high - 1,pivot);
            swap(array, middle, pivot);
            sort(array, low, middle-1);
            sort(array, middle, high);
        }
    }


    public static void main(String[] args) {
        Integer[] arr = {5, 9, 2, 6, 3};
        QuickSortTest.display(arr);
        System.out.println();
        QuickSort.sort(arr);
        QuickSortTest.display(arr);
    }
}
