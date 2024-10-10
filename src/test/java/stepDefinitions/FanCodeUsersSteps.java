package stepDefinitions;

import api.APIClient;
import utils.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

public class FanCodeUsersSteps {

    private List<Map<String, Object>> users;
    private List<Map<String, Object>> todos;
    private List<Map<String, Object>> fancodeUsers;

    @Given("the users with todos tasks")
    public void getUsersAndTodos() {
        APIClient apiClient = new APIClient();
        users = apiClient.getUsers();
        todos = apiClient.getTodos();
    }

    @And("the users belong to the city FanCode")
    public void filterFanCodeUsers() {
        fancodeUsers = Utils.filterFanCodeUsers(users);
        assertTrue("No users found in FanCode city", fancodeUsers.size() > 0);
    }

    @Then("the completion percentage for each user should be greater than {int}%")
    public void checkCompletionPercentage(int percentage) {
        for (Map<String, Object> user : fancodeUsers) {
            int userId = (int) user.get("id");
            double completionPercentage = Utils.calculateCompletionPercentage(todos, userId);
            assertTrue("User " + user.get("name") + " has less than " + percentage + "% tasks completed", 
                completionPercentage > percentage);
        }
    }
}
