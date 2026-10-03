import java.util.HashMap;

public class P3_MostPopularCanteenOrder {

    public static String mostPopular(String[] orders) {

        HashMap<String, Integer> countMap = new HashMap<>();

        for (String item : orders) {
            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = countMap.get(orders[0]);

        for (String item : orders) {

            int count = countMap.get(item);

            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }

        return "(\"" + bestItem + "\", " + bestCount + ")";
    }

    public static void main(String[] args) {

        String[] orders = {
            "dosa",
            "idli",
            "vada",
            "dosa",
            "idli",
            "dosa",
            "tea"
        };

        String result = mostPopular(orders);

        System.out.println(result);
    }
}