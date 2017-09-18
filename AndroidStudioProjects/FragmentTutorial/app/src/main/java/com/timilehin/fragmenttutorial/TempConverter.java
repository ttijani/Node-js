package com.timilehin.fragmenttutorial;

/**
 * Created by tijanioluwatimilehin on 8/12/17.
 */

public class TempConverter
{

    public static float
    getTempInCelcius(float temp)
    {

        return ((temp - 32) * 5 / 9);

    }

    public static float
    getTempInFahrenheit(float temp)
    {

        return ((temp * 9) / 5) + 32;

    }

}
