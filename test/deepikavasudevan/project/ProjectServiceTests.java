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
                "    \"expiryDate\": \"05202017 00:00:00\"," +
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
        Assert.assertTrue(result.getKey());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveNineFields() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"secondaryId\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
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
                "    \"expiryDate\": \"05202017 00:00:00\"," +
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
        Assert.assertEquals("Request does not contain ID", result.getValue());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveProjectName() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"name\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
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
        Assert.assertEquals("Request does not contain projectName", result.getValue());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveCreationDate() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"Date\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
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
        Assert.assertEquals("Request does not contain creationDate", result.getValue());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveExpiryDate() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"Date \": \"05202017 00:00:00\"," +
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
        Assert.assertEquals("Request does not contain expiryDate", result.getValue());

    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveEnabled() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
                "    \"disabled\": true," +
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
        Assert.assertEquals("Request does not contain enabled", result.getValue());

    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveTargetCountries() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"marketCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"projectCost\": 5.5," +
                "    \"projectUrl\": \"http://www.unity3d.com\"," +
                "    \"targetKeys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
        Assert.assertEquals("Request does not contain targetCountries", result.getValue());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveProjectCost() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"targetCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"Cost\": 5.5," +
                "    \"projectUrl\": \"http://www.unity3d.com\"," +
                "    \"targetKeys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
        Assert.assertEquals("Request does not contain projectCost", result.getValue());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveProjectUrl() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"targetCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"projectCost\": 5.5," +
                "    \"Url\": \"http://www.unity3d.com\"," +
                "    \"targetKeys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
        Assert.assertEquals("Request does not contain projectUrl", result.getValue());
    }

    @Test
    public void shouldReturnFalseWhenInputJSONDoesNotHaveTargetKeys() {
        String json = "{" +
                "    \"id\": \"1\"," +
                "    \"projectName\": \"test project number 1\"," +
                "    \"creationDate\": \"05112017 00:00:00\"," +
                "    \"expiryDate\": \"05202017 00:00:00\"," +
                "    \"enabled\": true," +
                "    \"targetCountries\": [\"USA\", \"CANADA\", \"MEXICO\", \"BRAZIL\"]," +
                "    \"projectCost\": 5.5," +
                "    \"projectUrl\": \"http://www.unity3d.com\"," +
                "    \"Keys\": [{" +
                "            \"number\": 25," +
                "            \"keyword\": \"movie\"" +
                "        }]" +
                "}";

        AbstractMap.SimpleEntry<Boolean, String> result = projectService.create(json);
        Assert.assertFalse(result.getKey());
        Assert.assertEquals("Request does not contain targetKeys", result.getValue());
    }
}