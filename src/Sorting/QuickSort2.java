package ParallelQuickSort;

/**
 * fully working ****************
 */
public class QuickSort2 <T extends Comparable<T>> {

    /**
     * wrapper calls the recursive method quicksort
     *
     * @param arr the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length == 0)
            return;
        else
            sort(arr, 0, arr.length - 1);
    }

    /**
     * checks if the part to find pivot for has same elements so no need to sort
     * otherwise counts the pivot to sort
     *
     * @param array the array or the part of the array to be sorted
     * @param low   the lower bound
     * @param high  the higher bound
     * @return the pivot
     */
    private static <T extends Comparable<T>> int findPivot(T[] array, int low, int high) {
        for (int i = low; i < high; i++) {
            if (array[i].compareTo(array[i + 1]) != 0) {
                return (int) (low + (high - low) / 2);
            }
        }
        return -1;
    }

    private static <T extends Comparable<T>> void sort(T[] arr, int low, int high){
        if (low >= high)
            return;

        int middle = findPivot(arr, low, high);
        if (middle != -1) {
            T pivot = arr[middle];

            // make left < pivot and right > pivot
            int i = low, j = high;
            while (i <= j) {
                while (arr[i].compareTo(pivot) < 0) {
                    i++;
                }

                while (arr[j].compareTo(pivot) > 0) {
                    j--;
                }

                if (i <= j) {
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            // recursively sort two sub parts
            if (low < j)
                sort(arr, low, j);

            if (high > i)
                sort(arr, i, high);
        }
    }
}
