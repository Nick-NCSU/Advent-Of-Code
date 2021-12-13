package AOC.Y2021.D12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class part1_12 {
    public static void main(String[] args) {
        Dictionary<String, List<String>> graph = new Hashtable<>();
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D12/data.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("-");
                if(graph.get(line[0]) != null) {
                    List<String> list = graph.get(line[0]);
                    list.add(line[1]);
                    graph.put(line[0], list);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(line[1]);
                    graph.put(line[0], list);
                }
                if(graph.get(line[1]) != null) {
                    List<String> list = graph.get(line[1]);
                    list.add(line[0]);
                    graph.put(line[1], list);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(line[0]);
                    graph.put(line[1], list);
                }
            }
            scanner.close();
            System.out.println(dfs("start", new HashSet<>(Collections.singleton("start")), graph));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int dfs(String vertex, Set<String> visits, Dictionary<String, List<String>> graph) {
        if(vertex.equals("end")) {
            return 1;
        }
        int count = 0;
        for(String edge : graph.get(vertex)) {
            if(edge.toLowerCase().equals(edge) && !edge.equals("end")) {
                if(!visits.contains(edge)) {
                    Set<String> newVisits = new HashSet<>(visits);
                    newVisits.add(edge);
                    count += dfs(edge, newVisits, graph);
                }
            } else {
                count += dfs(edge, visits, graph);
            }
        }
        return count;
    }
}
