package lab1;

import java.util.Arrays;

public class Problem4 {

    /**
     * Метод isGeometricProgression определяет, является ли данная последовательность чисел numbers геометрической
     * прогрессией (возможно, при перестановке элементов)
     *
     * @param numbers строка, содержащая n положительных целых чисел, разделенных запятой
     * @return true, если последовательность является геометрической прогрессией
     *         false, если последовательность не является геометрической прогрессией
     *
     * ПРИМЕР1:
     * Вход: numbers = "1,2,4,8,16"
     * Выход: true
     *
     * ПРИМЕР2:
     * Вход: numbers = "16,2,8,1,4"
     * Выход: true (так как в результате перестановки элементов можно получить геометрическую прогрессию [1,2,4,8,16])
     *
     * ПРИМЕР3:
     * Вход: numbers = "2,3,5"
     * Выход: false
     */
    public static boolean isGeometricProgression(String numbers) {
        if (numbers.length() == 0)
            return false;

        double[] nums = Arrays.stream(numbers.split(",")).mapToDouble(str -> Double.parseDouble(str)).toArray();

        if (nums.length < 2)
            return false;

        Arrays.sort(nums);

        double q = nums[1] / nums[0];

        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] * q != nums[i + 1])
                return false;

        return true;
    }
}
