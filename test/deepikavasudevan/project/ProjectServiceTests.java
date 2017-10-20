package deepikavasudevan.project;

import org.junit.*;

public class ProjectServiceTests {

    ProjectService projectService = new ProjectService();

    @Test
    public void shouldReturnTrueWhenInputJSONIsValid() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate \": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"targetCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"projectCost\": 5.5," +
                "    \"projectUrl\": \"http://www.unity3d.com\"," +
                "    \"targetKeys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        Assert.assertTrue(projectService.create(json));
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveNineFields() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"secondaryId\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate \": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"targetCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"projectCost\": 5.5," +
                "    \"projectUrl\": \"http://www.unity3d.com\"," +
                "    \"targetKeys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        Assert.assertFalse(projectService.create(json));
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveID() {
        String json = "{" +
                "    \"secondaryId\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate \": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"targetCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"projectCost\": 5.5," +
                "    \"projectUrl\": \"http://www.unity3d.com\"," +
                "    \"targetKeys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        Assert.assertFalse(projectService.create(json));
    }
}
