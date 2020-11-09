package ga;

import ga.model.Individ;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GAStarterTest {

    @Test
    public void GATest(){
        double[] loadSchedule = generateLoadSchedule(350);
        double[] priceSchedule = getPriceSchedule();
        GAStarter ga =new GAStarter();
        Individ ind = ga.start(loadSchedule, priceSchedule, 3500, 16000);

        System.out.println(ind.getFfValue());
        System.out.println(Arrays.toString(ind.getActionSchedule()));
    }
    private double[] getPriceSchedule(){
        return new double[]{
                1.5, 1.5, 1.5, 1.5, 1.5, 1.5,
                3.5, 3.5, 3.5, 3.5, 2.5, 2.5,
                3.5, 3.5, 3.5, 3.5, 5,   5,
                5,   5,   5,   5,   3.5, 3.5
        };
    }

    private double[] generateLoadSchedule(double basicVal){
        double[] coefs = new double[] {
            1,   1,   1,   1.2, 1.2, 1.5,
            1.5, 2,   2.5, 2.5, 2,   2.5,
            3,   3.5, 3,   3.5, 4,   4,
            4.5, 5,   6,   6.5, 5,   3.5
        };
        double[] loads = new double[coefs.length];
        for (int i =0; i < loads.length; i++){
            loads[i] = coefs[i]*basicVal;
        }
        return loads;

    }

}