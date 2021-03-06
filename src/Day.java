public class Day {
    private int day;
    private int month;
    private int year;

    public Day(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if (!(o instanceof Day)) {
            return false;
        }
        Day comp = (Day)o;
        return((comp.day == day) && (comp.month == month) && (comp.year == year));
    }

    @Override
    public int hashCode() {
        return day + month + year;
    }
}
