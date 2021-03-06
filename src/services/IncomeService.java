package services;

import domain.Income;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Comparator;

public class IncomeService {

    private ArrayList<Income> incomesList = new ArrayList<>();

    public boolean insertIncome(Income value) {
      return incomesList.add(value);
    }

    public ArrayList<Income> getAllIncomes(){
        return incomesList;
    }

    public int numberOfIncomes(){
        return incomesList.size();
    }

    private Float calculateAverage(){
        return incomesList.isEmpty() ? 0.0f : calculateSum(incomesList) / incomesList.size();
    }

    public Float calculateSum(ArrayList<Income> list){
        float sum = 0.0f;
        for (Income element : list) {
            sum += element.getValue();
        }
        return sum;
    }

    public ArrayList<Income> getIncomesOfGivenDate(LocalDateTime someDate){
        ArrayList<Income> filteredList = new ArrayList<>();
        for (Income income: incomesList) {
            if (income.getIncomeDate().getMonth().equals(someDate.getMonth())){
                filteredList.add(income);
            }
        }
        return filteredList;
    }

    @Override
    public String toString() {
        Comparator<Income> valueComparator = new Comparator<Income>() {
            @Override

            public int compare(Income o1, Income o2) {
              return o1.getValue().compareTo(o1.getValue());
            }
        };

        incomesList.sort(valueComparator);
        return "incomesList=" +  incomesList + "\naverage of "+calculateAverage();
    }


}
