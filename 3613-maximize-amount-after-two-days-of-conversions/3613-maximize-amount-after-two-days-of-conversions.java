class Solution {
    static class Pair {
        String currency;
        double rate;
        public Pair(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }
        @Override
        public String toString() {
            return "(" + currency + " " + rate + ")";
        }
    }
    public static double maxAmount(String initialCurrency, List<List<String>> currencyPairsDay1, double[] conversionRatesDay1, List<List<String>> currencyPairsDay2, double[] conversionRatesDay2) {
        Map<String, List<Pair>> g1 = buildGraph(currencyPairsDay1, conversionRatesDay1);
        Map<String, List<Pair>> g2 = buildGraph(currencyPairsDay2, conversionRatesDay2);
        Map<String, Double> current_maxi = new HashMap<>();
        dfs(initialCurrency, 1.0, g1, current_maxi);
        double maxi = 0.0;
        for (Map.Entry<String, Double> entry : current_maxi.entrySet()) {
            String currency = entry.getKey();
            double amount = entry.getValue();
            Map<String, Double> current_maxi2 = new HashMap<>();
            dfs(currency, amount, g2, current_maxi2);
            maxi = Math.max(maxi, current_maxi2.getOrDefault(initialCurrency, 0.0));
        }
        return maxi;
    }

    private static Map<String, List<Pair>> buildGraph(List<List<String>> currencyPairs, double[] conversionRates) {
        Map<String, List<Pair>> graph = new HashMap<>();
        for (int i = 0; i < currencyPairs.size(); i++) {
            String source = currencyPairs.get(i).get(0);
            String target = currencyPairs.get(i).get(1);
            double rate = conversionRates[i];
            graph.putIfAbsent(source, new ArrayList<>());
            graph.putIfAbsent(target, new ArrayList<>());
            graph.get(source).add(new Pair(target, rate));
            graph.get(target).add(new Pair(source, 1.0 / rate));
        }
        return graph;
    }

    private static void dfs(String currency, double amount, Map<String, List<Pair>> graph, Map<String, Double> map) {
        if (amount <= map.getOrDefault(currency, 0.0)) return;
        map.put(currency, amount);
        for (Pair v : graph.getOrDefault(currency, new ArrayList<>())) {
            dfs(v.currency, amount * v.rate, graph, map);
        }
    }
}