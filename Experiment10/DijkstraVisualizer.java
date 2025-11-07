import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import javax.swing.*;

public class DijkstraVisualizer extends JPanel implements MouseListener {

    static class Node {
        int id, x, y;
        Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    private final Map<Integer, Node> nodes = new HashMap<>();
    private final Map<Integer, List<int[]>> edges = new HashMap<>();  

    private Integer start = null, end = null;
    private List<Integer> shortestPath = new ArrayList<>();

    public DijkstraVisualizer() {

        nodes.put(1, new Node(1, 100, 100));
        nodes.put(2, new Node(2, 300, 100));
        nodes.put(3, new Node(3, 500, 100));
        nodes.put(4, new Node(4, 200, 300));
        nodes.put(5, new Node(5, 400, 300));


        addEdge(1, 2, 4); addEdge(2, 1, 4);
        addEdge(2, 3, 2); addEdge(3, 2, 2);
        addEdge(1, 4, 5); addEdge(4, 1, 5);
        addEdge(2, 4, 1); addEdge(4, 2, 1);
        addEdge(2, 5, 3); addEdge(5, 2, 3);
        addEdge(4, 5, 2); addEdge(5, 4, 2);
        addEdge(3, 5, 1); addEdge(5, 3, 1);

        addMouseListener(this);
    }

    private void addEdge(int a, int b, int w) {
        edges.computeIfAbsent(a, k -> new ArrayList<>()).add(new int[]{b, w});
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(2));

        // Draw edges
        for (var entry : edges.entrySet()) {
            int a = entry.getKey();
            for (int[] arr : entry.getValue()) {
                int b = arr[0], w = arr[1];
                Node A = nodes.get(a), B = nodes.get(b);

                // Default color
                g2.setColor(Color.LIGHT_GRAY);

                // Highlight if edge is part of shortest path
                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    if ((shortestPath.get(i) == a && shortestPath.get(i + 1) == b) ||
                        (shortestPath.get(i) == b && shortestPath.get(i + 1) == a)) {
                        g2.setColor(Color.RED);
                        break;
                    }
                }

                g2.drawLine(A.x, A.y, B.x, B.y);
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(w), (A.x + B.x) / 2, (A.y + B.y) / 2);
            }
        }


        for (Node n : nodes.values()) {
            int radius = 28;

            if (Objects.equals(n.id, start)) g2.setColor(Color.GREEN);
            else if (Objects.equals(n.id, end)) g2.setColor(Color.RED);
            else g2.setColor(Color.CYAN);

            g2.fillOval(n.x - radius / 2, n.y - radius / 2, radius, radius);
            g2.setColor(Color.BLACK);
            g2.drawOval(n.x - radius / 2, n.y - radius / 2, radius, radius);
            g2.drawString("N" + n.id, n.x - 8, n.y + 4);
        }
    }

    private void runDijkstra() {
        shortestPath.clear();
        if (start == null || end == null) return;

        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int id : nodes.keySet()) dist.put(id, Integer.MAX_VALUE);

        dist.put(start, 0);
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int node = arr[0], cost = arr[1];

            if (cost > dist.get(node)) continue;
            if (node == end) break;

            for (int[] e : edges.get(node)) {
                int next = e[0], w = e[1];
                int newDist = cost + w;

                if (newDist < dist.get(next)) {
                    dist.put(next, newDist);
                    prev.put(next, node);
                    pq.add(new int[]{next, newDist});
                }
            }
        }


        for (Integer at = end; at != null; at = prev.get(at))
            shortestPath.add(0, at);

        repaint();
        JOptionPane.showMessageDialog(this,
                "Shortest Path: " + shortestPath + "\nDistance: " + dist.get(end));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Node n : nodes.values()) {
            if (e.getPoint().distance(n.x, n.y) < 30) {

                if (start == null) {
                    start = n.id;
                } else if (end == null) {
                    end = n.id;
                    runDijkstra();
                } else {
                    // reset on 3rd click
                    start = n.id;
                    end = null;
                    shortestPath.clear();
                }

                repaint();
                break;
            }
        }
    }


    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dijkstra Visualizer - Click Start then End Node");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);
        frame.add(new DijkstraVisualizer());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
