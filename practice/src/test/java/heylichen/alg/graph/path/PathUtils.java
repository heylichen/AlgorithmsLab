package heylichen.alg.graph.path;

public class PathUtils {
    public static final String viewPath(Iterable<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : path) {
            sb.append(integer).append(" ");
        }
        return sb.toString();
    }
}
