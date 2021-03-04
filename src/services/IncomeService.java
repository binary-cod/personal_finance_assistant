package services;

import java.util.ArrayList;

public class IncomeService {

    private float[] incomes;
    private int incomeLevel;

    public IncomeService (int size) {
        incomes = new float[size];
        incomeLevel = 0;
    }
    public boolean insertIncome(float value) {
      if (incomeLevel < incomes.length) {
          incomes[incomeLevel] = value;
          incomeLevel++;
          return true;
      }
      return false;
    }

    public float[] getAllIncomes(){
        return incomes;
    }

    public int numberOfIncomes(){
        return incomeLevel;
    }
}
