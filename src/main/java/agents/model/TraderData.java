package agents.model;

import ga.GAStarter;
import ga.model.Individ;
import helpers.DfHelper;
import helpers.TimeHelper;
import jade.core.AID;
import jade.core.Agent;

import java.util.List;

public class TraderData {

    private GAStarter ga = new GAStarter();
    private int[] actualSchedule = null;
    private double[] loadSchedule = new double[24];
    private double[] priceSchedule = new double[24];
    private double maxCharge;
    private double currentCharge;
    private double balance = 100;
    private AID gridAgent = null;
    private Agent me;

    public TraderData(Agent me) {
        this.me = me;
    }

    public void reduceAction(){
        actualSchedule[TimeHelper.getCurrentHour()] = actualSchedule[TimeHelper.getCurrentHour()] - 1;
    }
    public void changeBalance(double adding){
        balance += adding;
    }

    public void changeCurrentCharge(double adding){
        currentCharge += adding;
        if (currentCharge <= 0)
            currentCharge = 0;
        if (currentCharge > maxCharge)
            currentCharge = maxCharge;
    }

    public AID getGridAgent() {
        if (gridAgent == null) {
            List<AID> agents = DfHelper.searchForAgents(Services.GRID_PRICE.toString(),me);
            if (agents.size() == 0){
                throw new RuntimeException("Grid agent was not found");
            }
            gridAgent = agents.get(0);
        }
        return gridAgent;
    }

    public double getBalance() {
        return balance;
    }


    public double[] getPriceSchedule() {
        return priceSchedule;
    }

    public void setPriceSchedule(double[] priceSchedule) {
        this.priceSchedule = priceSchedule;
    }

    public double getMaxCharge() {
        return maxCharge;
    }

    public void setMaxCharge(double maxCharge) {
        this.maxCharge = maxCharge;
    }

    public double getCurrentCharge() {
        return currentCharge;
    }

    public void setCurrentCharge(double currentCharge) {
        this.currentCharge = currentCharge;
    }

    public double[] getLoadSchedule() {
        return loadSchedule;
    }

    public void setLoadSchedule(double[] loadSchedule) {
        this.loadSchedule = loadSchedule;
    }

    public GAStarter getGa() {
        return ga;
    }

    public void setGa(GAStarter ga) {
        this.ga = ga;
    }

    public int[] getActualSchedule() {
        return actualSchedule;
    }

    public void updateActionSchedule(){
        Individ start = ga.start(arrayToCurrentHour(loadSchedule), arrayToCurrentHour(priceSchedule), currentCharge, maxCharge);
        actualSchedule = start.getActionSchedule();
    }

    public void setActualSchedule(int[] actualSchedule) {
        this.actualSchedule = actualSchedule;
    }

    private double[] arrayToCurrentHour(double[] src){
        int hour = TimeHelper.getCurrentHour();
        double[] res = new double[src.length];
        for (int i=0; i < 24;i++){
            if (hour+i < 24 ){
                res[i] = src[hour+i];
            } else {
                res[i] = src[hour + i - 24];
            }
        }
        return res;
    }
}
