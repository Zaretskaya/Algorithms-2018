package lesson6;


import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        // Если в последовательности меньше двух элементов, то её и возвращаем
        if (list.size() < 2 )
            return new ArrayList<>(list);

        // Если последовательность возрастающая, то её и вернём
        if (isIncreasedSequence(list))
            return new ArrayList<>(list);

        // Текущаяя возрастающая посоедовательность
        List<Integer> sequence = new ArrayList<>();
        // Последовательность максимальной длины
        List<Integer> maxLengthSequence;
        sequence.add(list.get(0));
        // Список всех возастающих последовательностей
        List<List<Integer>> sequences = new ArrayList<>();
        sequences.add(sequence);
        Integer item;
        int lastIndex;
        // Ищем последовательности максимальной длины, заканчивающиеся в элементе с индексом i
        for (int i = 1; i < list.size(); i ++) {
            item = list.get(i);
            maxLengthSequence = new ArrayList<>(sequences.get(0));
            for (int j = 0; j < i; j ++) {
                sequence = new ArrayList<>(sequences.get(j));
                lastIndex = sequence.size() - 1;
                if (sequence.get(lastIndex) > item) {
                    for (int k = 0; k < sequence.size();) {
                        if (sequence.get(k) > item)
                            sequence.remove(k);
                        else
                            k++;
                    }
                }
                sequence.add(item);
                if (maxLengthSequence.size() < sequence.size()) {
                    maxLengthSequence = new ArrayList<>(sequence);
                }
            }
            sequences.add(maxLengthSequence);
        }
        // Среди всех последовательностей ищем самую длинную
        maxLengthSequence = new ArrayList<>(sequences.get(0));
        for (int i = 1; i < sequences.size(); i ++) {
            sequence = new ArrayList<>(sequences.get(i));
            if (maxLengthSequence.size() < sequence.size()){
                maxLengthSequence = new ArrayList<>(sequence);
            }
        }

        return maxLengthSequence;
    }


    public static boolean isIncreasedSequence(List<Integer> list) {
        boolean isIncreasing = true;
        for (int index = 1; index < list.size(); index ++) {
            if (list.get(index - 1) >= list.get(index)) {
                isIncreasing = false;
                break;
            }
        }
        return isIncreasing;
    }




    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        ArrayList<ArrayList<Integer>> field = new ArrayList<ArrayList<Integer>>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String string;
        ArrayList<Integer> array;
        int minCost = Integer.MAX_VALUE;
        int cost;
        // Читаем файл в массив
        while ((string = reader.readLine()) != null) {
            array = new ArrayList<>();
            for (String subString : string.split(" "))
                array.add(Integer.parseInt(subString));
            field.add(array);
        }
        Path path = new Path(field);
        while (path.calculateNextPath()) {
            cost = path.getCost();
            minCost = Integer.min(minCost, cost);
        }
        cost = minCost;
        return minCost;
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
