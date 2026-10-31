package Assignments;
public class ContestLeaderboard {

    static class Entry {
        String name;
        int marks;

        Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    static Entry[] leaderboard(Entry[] entries) {
        int n = entries.length;
        int[] count = new int[101];

        Entry[] result = new Entry[n];

        for (Entry e : entries)
            count[100 - e.marks]++;

        for (int i = 1; i < 101; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            result[--count[key]] = entries[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);

        for (Entry e : result) {
            System.out.println(e.name + " " + e.marks);
        }
    }
}
