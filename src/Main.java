import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var monitoredDataList = ActivityReader.readActivities(args[0]);
        TaskWriter.task1(monitoredDataList);
        var distinctDays = monitoredDataList.stream().flatMap(monitoredData -> Arrays.stream(monitoredData.getDays())).distinct().collect(Collectors.toList());
        TaskWriter.task2(distinctDays);
        var activityPerMonitoredPeriod = monitoredDataList.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
        TaskWriter.task3(activityPerMonitoredPeriod);
        var activityPerDay = MonitoredData.getActivityPerDay(monitoredDataList, distinctDays);
        TaskWriter.task4(activityPerDay);
        var entireDuration = monitoredDataList.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summarizingInt(MonitoredData::getDuration)));
        TaskWriter.task5(entireDuration);
        var filterActivities = MonitoredData.filterShortActivities(monitoredDataList);
        TaskWriter.task6(filterActivities);
    }


}
