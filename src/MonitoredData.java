import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.stream.Collectors;

public class MonitoredData {
    private Calendar startTime;
    private Calendar endTime;
    private String activity;
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MonitoredData(String[] data) {
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
        activity = data[4];
        try {
            startTime.setTime(format.parse(data[0]));
            endTime.setTime(format.parse(data[2]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Map<String, Long>> getActivityPerDay(List<MonitoredData> list, List<Day> distinctDays) {
        int index = 1;
        var retMap = new HashMap<Integer, Map<String, Long>>();
        for(Day day : distinctDays) {
            Map<String,Long> map = list.stream().filter(monitoredData -> monitoredData.getStartDay().equals(day) ||
                    monitoredData.getEndDay().equals(day)).collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
            retMap.put(index ++, map);
        }
        return retMap;
    }

    public static List<String> filterShortActivities(List<MonitoredData> list) {
        var distinctActivities = list.stream().map(monitoredData -> monitoredData.getActivity()).distinct().collect(Collectors.toList());
        var retList = new ArrayList<String>();
        int under5min = 0;
        int over5min = 0;
        for(String activity : distinctActivities) {
            var activities = list.stream().filter(monitoredData -> monitoredData.getActivity().equals(activity)).collect(Collectors.toList());
            for(MonitoredData md : activities) {
                if ((md.getDuration() > 300)) {
                    over5min++;
                } else {
                    under5min++;
                }
            }
            if((float)(under5min / (under5min + over5min)) < 0.9) retList.add(activity);
            under5min = 0;
            over5min = 0;
        }
        return retList;
    }
    public Day getStartDay() {
        return new Day(startTime.get(Calendar.DAY_OF_MONTH), startTime.get(Calendar.MONTH),
                startTime.get(Calendar.YEAR));
    }

    public Day getEndDay() {
        return new Day(endTime.get(Calendar.DAY_OF_MONTH), endTime.get(Calendar.MONTH),
                endTime.get(Calendar.YEAR));
    }

    public Day[] getDays() {
        Day[] days = new Day[2];
        days[0] = getStartDay();
        days[1] = getEndDay();
        return days;
    }

    public String getActivity() {
        return activity;
    }

    public int getDuration() {
        return (int)((endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000);
    }
    @Override
    public String toString() {
        return format.format(startTime.getTime()) + '\t' + format.format(endTime.getTime()) + '\t' + activity;
    }


}
