package hexlet.code.util;

public class NamedRoutes {
    public static String mainPath() {
        return "/";
    }

    public static String urlsPath() {
        return "/urls";
    }

    public static String urlPath(Long id) {
        return id.toString();
    }

    public static String urlPath(String id) {
        return "/urls/" + id;
    }
}
