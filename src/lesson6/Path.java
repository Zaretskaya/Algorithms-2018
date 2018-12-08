package lesson6;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {
    ArrayList<ArrayList<Integer>> field;
    Point start, end;
    LinkedList<Point> currentPath;
    int length, height;

    public Path(ArrayList<ArrayList<Integer>> field) {
        this.field = field;
        this.length = field.get(0).size();
        this.height = field.size();
        this.start = new Point();
        this.end = new Point(length - 1, height - 1);
        this.currentPath = new LinkedList<>();
    }

    public Point getNextPoint(LinkedList<Point> path, int backwardSteps) {
        Point nextPoint = null;
        Point lastPoint;
        if (backwardSteps == 0 ) {
            // Ищем продолжение пути. Для этого проверяем доступность пути вправо и вниз
            lastPoint = path.getLast();
            if (lastPoint.getX() < length - 1) {
                nextPoint = new Point(lastPoint.getX() + 1, lastPoint.getY());
            } else if (lastPoint.getY() < height - 1) {
                nextPoint = new Point(lastPoint.getX(), lastPoint.getY() + 1);
            }
        } else {
            // Ищем другой вариант пути
            int lastIndex = path.size() - 1 - backwardSteps;
            if (lastIndex < 0) {
                return nextPoint;
            }
            lastPoint = path.get(lastIndex);
            Point otherPoint = path.get(lastIndex + 1);
            if (otherPoint.getX() > lastPoint.getX() && otherPoint.getY() == lastPoint.getY()) {
                // Было направление вправо, меняем на вправо-вниз (если доступно)
                if (lastPoint.getY() < height - 1) {
                    nextPoint = new Point(lastPoint.getX() + 1, lastPoint.getY() + 1);
                }
            } else {
                if (otherPoint.getX() > lastPoint.getX() && otherPoint.getY() > lastPoint.getY()) {
                    // Было направление вправо-вниз, меняем на вниз (если доступно)
                    if (lastPoint.getY() < height - 1) {
                        nextPoint = new Point(lastPoint.getX(), lastPoint.getY() + 1);
                    }
                }
                // Было направление вниз. Других вариантов движения нет. Возвращаемся на шаг назад

            }
        }

        return nextPoint;
    }

    public boolean calculateNextPath() {
        LinkedList<Point> nextPath;
        int backwardSteps;
        Point nextPoint; // Следующая точка пути
        if (currentPath.isEmpty()) {
            nextPath = new LinkedList<>();
            backwardSteps = 0;
            nextPath.add(new Point(start));
        }
        else {
            nextPath = new LinkedList<>(currentPath);
            backwardSteps = 1;
        }
        boolean pathIsFinded = true;

        // Начинаем путь

        do {
            nextPoint = this.getNextPoint(nextPath, backwardSteps);
            if (nextPoint != null) {
                if (backwardSteps > 0) {
                    int size = nextPath.size();
                    for (int index = 0; index < backwardSteps; index++)
                        nextPath.removeLast();
                    backwardSteps = 0;
                }
                nextPath.add(nextPoint);

            } else {
                if (backwardSteps < nextPath.size()) {
                    backwardSteps ++;
                } else {
                    pathIsFinded = false;
                    break;
                }
            }
        } while (!end.equals(nextPoint));
        currentPath = nextPath;
        return pathIsFinded;
    }

    public int getCost() {
        int cost = 0;
        int x, y;
        for (int index = 1; index < currentPath.size(); index ++) {
            x = currentPath.get(index).getX();
            y = currentPath.get(index).getY();
            cost += field.get(y).get(x);
        }
        return cost;
    }

}
