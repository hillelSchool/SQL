package logParser2;

// -e 1 test.record ./src/main/java/logParser2/regex map.txt new.txt
// -d new.txt map.txt test.txt

public class Main {
    public static void main(String[] args) {

        UserData userData = new CreateUserData().processingParameter(args);

        OperationWithFile operationWithFile = new OperationWithFile();
        Data data = operationWithFile.readDataFromFiles(userData);

        OutputData outputData = new DataProcessing().transformationData(userData, data);

        operationWithFile.writeModifyDataToFile(userData, outputData);
    }
}
