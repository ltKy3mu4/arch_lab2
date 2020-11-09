package ga.model;

public class TargetFunction {

    public double calculate(Individ individ){
        double energyExchangeValue = 1000;
        double zeroChargePenalty = 10000;
        double maxChargePenalty = 3000;

        double[] loadSchedule = individ.getLoadSchedule();
        double[] priceSchedule = individ.getPriceSchedule();
        double currentCharge = individ.getCurrentCharge();
        double maxCharge = individ.getMaxCharge();

        int[] actionSchedule = individ.getActionSchedule();

        double ffValue = 0;

        double expectedCharge = currentCharge;
        for (int i =0; i < actionSchedule.length; i++){
            expectedCharge = expectedCharge - loadSchedule[i] + actionSchedule[i] * energyExchangeValue;
            ffValue += priceSchedule[i]*actionSchedule[i];

            if (expectedCharge <= 0){
                expectedCharge = 0;
                ffValue += zeroChargePenalty;
            }

            if (expectedCharge > maxCharge){
                expectedCharge = maxCharge;
                ffValue += maxChargePenalty;
            }
        }
        return ffValue;
    }


}
