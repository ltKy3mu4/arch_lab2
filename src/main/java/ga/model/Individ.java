package ga.model;

import java.util.Random;

public class Individ implements Comparable<Individ>{
    private double[] loadSchedule;
    private double[] priceSchedule;
    private double currentCharge, maxCharge;

    //TODO: ссылка на ЦФ
    private int[] actionSchedule;
    private int minActionVal = 0, maxActionVal = 4;
    private double ffValue;
    private Random r = new Random();
    private TargetFunction targetFunction;

    public Individ(double[] loadSchedule, double[] priceSchedule, double currentCharge, double maxCharge, TargetFunction tf) {
        this.loadSchedule = loadSchedule;
        this.priceSchedule = priceSchedule;
        this.currentCharge = currentCharge;
        this.maxCharge = maxCharge;
        generateNewGenotype();
        targetFunction = tf;
        calculateFFValue();
    }

    public Individ cross(Individ parent1){
        Individ parent2 = this;
        int exchangedGenesCount = r.nextInt(2) + 3;
        int initialPosToExchange = r.nextInt(actionSchedule.length - exchangedGenesCount);

        int[] childGenotype1 = new int[actionSchedule.length];
        System.arraycopy(parent1.actionSchedule, 0, childGenotype1, 0, actionSchedule.length);
        int[] childGenotype2 = new int[actionSchedule.length];
        System.arraycopy(parent2.actionSchedule, 0, childGenotype2, 0, actionSchedule.length);

        for (int i = 0; i < exchangedGenesCount; i++){
            childGenotype1[initialPosToExchange + i] = parent2.actionSchedule[initialPosToExchange + i];
            childGenotype2[initialPosToExchange + i] = parent1.actionSchedule[initialPosToExchange + i];
        }
        Individ child1 = createNew();
        child1.actionSchedule = childGenotype1;
        child1.calculateFFValue();

        Individ child2 = createNew();
        child2.actionSchedule = childGenotype2;
        child2.calculateFFValue();

        return child1.getFfValue() < child2.getFfValue() ? child1 : child2;

    }

    private void calculateFFValue(){
        this.ffValue = targetFunction.calculate(this);
    }


    public void mutate(int mutatedGenesCount){
        for (int i =0; i < mutatedGenesCount; i++){
            int randomGene = r.nextInt(actionSchedule.length);
            actionSchedule[randomGene] = generateNewGene();
        }
        calculateFFValue();
    }

    public Individ createNew(){
        return new Individ(loadSchedule, priceSchedule, currentCharge, maxCharge, targetFunction);
    }

    public double getFfValue() {
        return ffValue;
    }

    private void generateNewGenotype(){
        actionSchedule = new int[24];
        //[0;4]
        //[-2;3]
        //[2;5]
        for(int i=0; i <actionSchedule.length; i++){
            actionSchedule[i] = generateNewGene();
        }
    }

    private int generateNewGene() {
        return r.nextInt(maxActionVal +1 - minActionVal) + minActionVal;
    }

    public void setMinActionVal(int minActionVal) {
        this.minActionVal = minActionVal;
    }

    public void setMaxActionVal(int maxActionVal) {
        this.maxActionVal = maxActionVal;
    }

    public double[] getLoadSchedule() {
        return loadSchedule;
    }

    public double[] getPriceSchedule() {
        return priceSchedule;
    }

    public double getCurrentCharge() {
        return currentCharge;
    }

    public double getMaxCharge() {
        return maxCharge;
    }

    public int[] getActionSchedule() {
        return actionSchedule;
    }

    @Override
    public int compareTo(Individ o) {
        return Double.compare(this.getFfValue(), o.getFfValue());
    }
}
