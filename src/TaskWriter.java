import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public class TaskWriter {

    public static void task1(List<MonitoredData> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task1.txt"));
            for (MonitoredData monitoredData : list) {
                try {
                    bw.write(monitoredData.toString() + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task2(List<Day> distinctDays) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task2.txt"));
            bw.write(Integer.toString(distinctDays.size()));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task3(Map<String, Long> map) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task3.txt"));
            for(Map.Entry<String, Long> entry : map.entrySet()) {
                bw.write(entry.getKey() + "  " + entry.getValue() + '\n');
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task4(Map<Integer, Map<String, Long>> map) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task4.txt"));
            map.forEach((index, maps)-> {
                try {
                    bw.write("Day #" + index + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                maps.forEach((activity, occurrences)-> {
                    try {
                        bw.write(activity + " " + occurrences + '\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task5(Map<String, IntSummaryStatistics> map) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task5.txt"));
            for(Map.Entry<String, IntSummaryStatistics> entry : map.entrySet()) {
                bw.write(entry.getKey() + "  " + entry.getValue().getSum() + '\n');
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task6(List<String> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task6.txt"));
            for(String activity : list) {
                bw.write(activity + '\n');
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
