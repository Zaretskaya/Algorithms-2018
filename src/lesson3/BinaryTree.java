package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            // TODO
            throw new NotImplementedError();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        /* трудоемкость =
           ресурсоемкость =
        */
        TreeSet<T> treeSet = new TreeSet<>();

        if (root == null) return treeSet; // Если дерево пустое, возвращаем пустое множество

        int comparison = first().compareTo(toElement);
        if (comparison > 0) return treeSet; // Если первый элемент больше заданного, также возвращаем пустое множество

        comparison = last().compareTo(toElement); // Если последний элемент меньше заданного, то вернём все элементы

        if (comparison < 0) {
            addSet(treeSet, root);
            return treeSet; // Ищем максимальный элемент, меньший заданного
        }
        addHeadSet(treeSet, root, toElement);
        return treeSet;
    }

    public void addHeadSet(SortedSet<T> sortedSet, Node<T> node, T toElement) {
        int comparison = node.value.compareTo(toElement);
        if (comparison < 0) {
            sortedSet.add(node.value);
            if (node.left != null) addSet(sortedSet, node.left);
            if (node.right != null) {
                addHeadSet(sortedSet, node.right, toElement);
            }

        } else {
            if (node.left != null) addHeadSet(sortedSet, node.left, toElement);
        }
    }

    public void addSet(SortedSet<T> sortedSet, Node<T> node) {
        sortedSet.add(node.value);
        if (node.left != null) addSet(sortedSet, node.left);
        if (node.right != null) addSet(sortedSet, node.right);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
         /* трудоемкость =
           ресурсоемкость =
        */
        TreeSet<T> treeSet = new TreeSet<>();

        if (root == null) return treeSet;  // Если дерево пустое, возвращаем пустое множество

        int comparison = last().compareTo(fromElement);
        if (comparison < 0) return treeSet; // Если последний элемент меньше заданного, также возвращаем пустое множество

        comparison = first().compareTo(fromElement); // Если первый элемент больше или равен заданному, то вернём все элементы

        if (comparison >= 0) {
            addSet(treeSet, root);
            return treeSet; // Ищем минимальный элемент, больший или равный заданному
        }

        addTailSet(treeSet, root, fromElement);
        return treeSet;
    }


    public void addTailSet(SortedSet<T> sortedSet, Node<T> node, T fromElement) {
        int comparison = node.value.compareTo(fromElement);

        if (comparison >= 0){
            sortedSet.add(node.value);
            if (node.right != null)
                addSet(sortedSet, node.right);
        }
        else {
            if (node.left != null) addTailSet(sortedSet, node.left, fromElement);
        }
    }


    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
