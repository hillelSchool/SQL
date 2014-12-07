package logParser2;

public class UserData {

    private String[] parameters;
    private String pathToOriginFile;
    private String pathToRulesFile;
    private String pathToMapReplacementFile;
    private String pathToDestinationFile;

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getPathToOriginFile() {
        return pathToOriginFile;
    }

    public void setPathToOriginFile(String pathToOriginFile) {
        this.pathToOriginFile = pathToOriginFile;
    }

    public String getPathToRulesFile() {
        return pathToRulesFile;
    }

    public void setPathToRulesFile(String pathToRegexFile) {
        this.pathToRulesFile = pathToRegexFile;
    }

    public String getPathToMapReplacementFile() {
        return pathToMapReplacementFile;
    }

    public void setPathToMapReplacementFile(String pathToMapReplacementFile) {
        this.pathToMapReplacementFile = pathToMapReplacementFile;
    }

    public String getPathToDestinationFile() {
        return pathToDestinationFile;
    }

    public void setPathToDestinationFile(String pathToDestinationFile) {
        this.pathToDestinationFile = pathToDestinationFile;
    }
}
