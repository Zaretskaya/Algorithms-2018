package lesson1;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     *
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */


    public static String[] openFile(String inputFile) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;

        ArrayList<String> list = new ArrayList<>();
        while((str = reader.readLine()) != null ){
            if(!str.isEmpty()){
                list.add(str);
                System.out.println(str);
            }}
        String[] stringArr = list.toArray(new String[0]);
        return stringArr;
    }

    static public void sortAddresses(String inputName, String outputName) {

        String [][] masInName;
        String [] tmpInName;
        double [] tmpInNameD;
        int [] tmpInNameI;



        String [] sortFl;
        String [] sortFlsum;

        int countSTR = 0;
        int e = 0;
        int indexSMB = 0;
        int [] k;//динамический параметр, который отслеживает реальные адресы жильцов
        //в базе после операций сортировок исходных данных в файле inputName
        //значения его номеров помогает выбирать окончательные позиции в базе
        //НАПРИМЕР: [Садовая 5 - Сидоров Петр, Сидорова Мария] а не
        //Садовая 5 - Сидоров Петр или Садовая 5 - Сидоров Мария
        int kk = 0;
        try {
            tmpInName = openFile(inputName);
            Sorts.insertionSort(tmpInName);
            countSTR = tmpInName.length;
            masInName = new String [countSTR][3];
            //из формата: [Сидоров Петр - Садовая 5]; [Сидорова Мария - Садовая 5]
            //в формат: [Садовая 5 - Сидоров Петр, Сидорова Мария]
            k = new int [countSTR];
            masInName[0][0] = tmpInName[0];
            indexSMB = tmpInName[e].lastIndexOf("-");
            String address = tmpInName[0].substring(indexSMB + 2, tmpInName[0].length());
            String nameUs = tmpInName[0].substring(0,indexSMB - 1);
            for (e=0; e<countSTR; ++e){
                indexSMB = tmpInName[e].lastIndexOf("-");
                String addressTec = tmpInName[e].substring(indexSMB + 2, tmpInName[e].length());
                String nameUsTec = tmpInName[e].substring(0,indexSMB - 1);
                masInName[e][0] = addressTec;
                masInName[e][1] = nameUsTec;
                int d = masInName[e][0].compareTo(address);
                int f = nameUs.compareTo(nameUsTec);
                if (d == 0 & f != 0 ){
                    masInName[e][1] = masInName[e - 1][1] +", " + nameUsTec;
                    masInName[kk][2] = masInName[e][0] + " - " + masInName[e][1];
                    nameUs = nameUsTec;
                    address = addressTec;
                    k[kk] = e-1;
                    ++kk;
                }else{
                    if(address.compareTo(addressTec) != 0){
                        masInName[kk][2] = masInName[e][0] + " - " + nameUsTec;
                        address = addressTec;
                        k[kk] = e-2;
                        ++kk;
                    }
                    k[kk] = e-1;
                }
            }
            int tt = 0;
            int tecSTR = 0;
            sortFl = new String [countSTR];
            for (e=1; e<countSTR; ++e){
                tt = e+1;
                String t1 = masInName[e][2];
                String t2 = masInName[k[e]][2];
                if(tt < countSTR){String t3 = masInName[k[e+1]][2];
                    int a = t2.compareTo(t3);
                    if(masInName[e][2] != null & a != 0){++tecSTR;}}
                    //определяем значение счетчика окончательных записей [tecSTR] координат жильцов
            }
            sortFl = new String [tecSTR];
            tecSTR = 0;
            for (e=1; e<countSTR; ++e){
                tt = e+1;
                String t1 = masInName[e][2];
                String t2 = masInName[k[e]][2];
                if(tt < countSTR){String t3 = masInName[k[e+1]][2];
                    int a = t2.compareTo(t3);
                    if(masInName[e][2] != null & a != 0){sortFl[tecSTR] = masInName[k[e]][2];++tecSTR;}}
                    //записываем адресы жильцов в сортировочный массив
            }

            Sorts.insertionSort(sortFl);
            sortFlsum = new String [tecSTR];
            for (e=0; e<tecSTR; ++e){
                sortFlsum[e] = sortFl[e];

            }

            FileWriter fw = new FileWriter(outputName);
            for(int i = 0; i<sortFlsum.length; i++) {
                fw.write(sortFlsum[i]);
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        /* ресурсоемкость = O(n)
           трудоемкость = O(n*log(n))
         */
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) {
        try {
            Scanner sc = new Scanner(new FileReader(inputName));
            List<Double> list = new ArrayList<>();
            while (sc.hasNextLine())
                list.add(Double.parseDouble(sc.nextLine()));
            sc.close();
            Collections.sort(list);


            FileWriter fw = new FileWriter(outputName);
            for(int i = 0; i<list.size();i++) {
                fw.write(list.get(i).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.print("Файл не найден");
            e.printStackTrace();
        }
        /* ресурсоемкость = O(n)
           трудоемкость = O(n*log(n))
         */
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
       /* try {
            String[] tmpInNameI;
            tmpInNameI = openFile(inputName);//читаем содержимое inputName1 и записываем строки в массив


            Sorts.insertionSort(tmpInNameI);//предварительная сортировка строк из файла inputName1
            String [] tmpInNameIS;
            tmpInNameIS = new String [tmpInNameI.length];
            for(int h=0;h<tmpInNameI.length;++h){
                tmpInNameIS[h] = String.valueOf(tmpInNameI[h]);
            }

            FileWriter fw = new FileWriter(outputName);
            for(int i = 0; i<list.size();i++) {
                fw.write(list.get(i));
                fw.write("\n");
            }
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

*/

    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
