package agents.model;

import java.util.List;

public class PricesAnswerMsg {
    private List<Double> prices;

    public PricesAnswerMsg() {
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public PricesAnswerMsg(List<Double> prices) {
        this.prices = prices;
    }
}
