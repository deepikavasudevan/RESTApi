package deepikavasudevan.project;

import org.junit.*;

import java.util.AbstractMap;

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
        
        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
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

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
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

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveProjectName() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"name\": \"test project number 1\"," +
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

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveCreationDate() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"Date\": \"05112017 00:00:00\"," +
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

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
    }
}
