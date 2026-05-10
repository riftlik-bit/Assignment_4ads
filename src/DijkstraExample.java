import java.util.*;

class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class DijkstraExample {

    private Map<String, List<Edge>> graph = new HashMap<>();

    public DijkstraExample() {

        graph.put("Glasgow", Arrays.asList(
                new Edge("Stirling", 50),
                new Edge("Edinburgh", 70)
        ));

        graph.put("Stirling", Arrays.asList(
                new Edge("Glasgow", 50),
                new Edge("Perth", 40),
                new Edge("Edinburgh", 50)
        ));

        graph.put("Perth", Arrays.asList(
                new Edge("Stirling", 40),
                new Edge("Edinburgh", 100),
                new Edge("Dundee", 60)
        ));

        graph.put("Edinburgh", Arrays.asList(
                new Edge("Glasgow", 70),
                new Edge("Stirling", 50),
                new Edge("Perth", 100)
        ));

        graph.put("Dundee", Arrays.asList(
                new Edge("Perth", 60)
        ));
    }

    public void dijkstra(String start, String end) {

        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        for (String city : graph.keySet()) {
            distance.put(city, Integer.MAX_VALUE);
        }

        distance.put(start, 0);

        PriorityQueue<String> pq =
                new PriorityQueue<>(Comparator.comparingInt(distance::get));

        pq.add(start);

        while (!pq.isEmpty()) {

            String current = pq.poll();

            for (Edge edge : graph.get(current)) {

                int newDist = distance.get(current) + edge.weight;

                if (newDist < distance.get(edge.destination)) {

                    distance.put(edge.destination, newDist);
                    previous.put(edge.destination, current);

                    pq.add(edge.destination);
                }
            }
        }

        System.out.println("Shortest distance: " + distance.get(end));

        List<String> path = new ArrayList<>();

        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);

        System.out.println("Path: " + String.join(" -> ", path));
    }

    public static void main(String[] args) {

        DijkstraExample d = new DijkstraExample();

        d.dijkstra("Edinburgh", "Dundee");
    }
}