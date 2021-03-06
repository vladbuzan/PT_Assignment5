import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityReader {

    public static List<MonitoredData> readActivities(String relativePath) {
        Path path = Paths.get(relativePath);
        try {
            var readData = Files.lines(path).map(str->str.split("\t")).map(MonitoredData::new).collect(Collectors.toList());
            return readData;
        } catch (Exception e) {
            return null;
        }
    }




}
