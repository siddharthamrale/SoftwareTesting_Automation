package utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    // Filters users who belong to FanCode city based on latitude and longitude
    public static List<Map<String, Object>> filterFanCodeUsers(List<Map<String, Object>> users) {
        return users.stream()
                .filter(user -> {
                    Map<String, Object> address = (Map<String, Object>) user.get("address");
                    Map<String, String> geo = (Map<String, String>) address.get("geo");
                    double lat = Double.parseDouble(geo.get("lat"));
                    double lng = Double.parseDouble(geo.get("lng"));
                    return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
                })
                .collect(Collectors.toList());
    }

    // Calculates the completion percentage for a user based on todos
    public static double calculateCompletionPercentage(List<Map<String, Object>> todos, int userId) {
        List<Map<String, Object>> userTodos = todos.stream()
                .filter(todo -> (int) todo.get("userId") == userId)
                .collect(Collectors.toList());

        long completedTodos = userTodos.stream()
                .filter(todo -> (boolean) todo.get("completed"))
                .count();

        if (userTodos.isEmpty()) {
            return 0;
        }

        return (double) completedTodos / userTodos.size() * 100;
    }
}
