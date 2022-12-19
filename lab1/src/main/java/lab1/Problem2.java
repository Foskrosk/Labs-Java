package lab1;

public class Problem2 {

    /**
     * Метод segregateEvenAndOddNumbers разделяет четные и нечетные числа в массиве array, т.у. возвращает массив,
     * в котором сперва записаны все четные числа массива array в порядке их следования, а затем все нечетные числа
     * массива array в порядке их следования.
     *
     * @param array массив положительных чисел
     * @return массив с разделенными четными и нечетными числами
     *
     * ПРИМЕР:
     * Вход: array = [2, 1, 5, 6, 8]
     * Выход: [2, 6, 8, 1, 5]
     */
    public static int[] segregateEvenAndOddNumbers(int[] array) {
        int evenCount = 0;

        for (int i = 0; i < array.length; i++)
            if (array[i] % 2 == 0)
                evenCount++;

        if (evenCount == 0)
            return array;

        int[] even = new int[array.length];
        int[] odd = new int[array.length - evenCount + 1];

        int j = 0, k = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0)
                even[j++] = array[i];
            else
                odd[k++] = array[i];
        }

        for (int i = j; i < array.length; i++)
            even[i] = odd[i - odd.length];

        return even;
    }
}
