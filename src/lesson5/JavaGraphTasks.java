package lesson5;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        Set<Graph.Vertex> set = graph.getVertices();
        Set<Graph.Vertex> neighbors;
        for (Graph.Vertex vertex: graph.getVertices()) {
            if (set.contains(vertex)) {
                neighbors = graph.getNeighbors(vertex);
                for (Graph.Vertex neighbor: neighbors) {
                    set.remove(neighbor);
                }
            }
        }
        return set;
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        if (graph == null)
            return null;
        LinkedList<Path> setPathes = new LinkedList<>();
        List<Graph.Vertex> vertices;
        int lengthPath;

        // Собираем начала всех путей
        for (Graph.Vertex vertex: graph.getVertices())
            setPathes.add(new Path(vertex));

        // Ищем продолжения путей
        Iterator<Path> pathIterator = setPathes.iterator();
        Path currentPath;
        boolean setIsChanged; // Признак изменения множества
        while (pathIterator.hasNext()) {
            currentPath = pathIterator.next();
            setIsChanged = false;
            vertices = currentPath.getVertices();
            lengthPath = vertices.size();
            // Получаем конец пути и ищем его соседей
            Graph.Vertex lastVertex = vertices.get(lengthPath - 1);
            Set<Graph.Vertex> neighbors = graph.getNeighbors(lastVertex);
            // Если сосед не содержится в пути, то путь можно продлить этим соседом
            for (Graph.Vertex neighbor: neighbors) {
                if (!currentPath.contains(neighbor)) {
                    Path newPath = new Path(currentPath, graph, neighbor);
                    setIsChanged = true;
                    setPathes.add(newPath);
                }
            }
            if (setIsChanged) {
                setPathes.remove(currentPath);
                pathIterator = setPathes.iterator();
            }
        }
        Path pathMaxLength = setPathes.getFirst();
        for (Path path: setPathes)
            if (pathMaxLength.getLength() < path.getLength())
                pathMaxLength = path;
        return pathMaxLength;
    }
}
