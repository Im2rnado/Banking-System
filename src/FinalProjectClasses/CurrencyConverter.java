/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */

package FinalProjectClasses;
import java.util.LinkedHashMap;

public class CurrencyConverter {
    int id; // Unique identifier for the currency converter instance
    private String baseCurrency;
    private LinkedHashMap<String, Double> exchangeRates; // Stores exchange rates for other currencies.

    // Constructors
    public CurrencyConverter(int id, String baseCurrency, LinkedHashMap<String, Double> exchangeRates) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.exchangeRates = exchangeRates;
    }

    public CurrencyConverter(int id, String baseCurrency) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.exchangeRates = new LinkedHashMap<>();
    }

    public CurrencyConverter(int id) {
        this.id = id;
        this.baseCurrency = "USD"; // Default base currency
        this.exchangeRates = new LinkedHashMap<>();
    }

    // Methods
    public void addExchangeRate(String currencyCode, double rate) {
        exchangeRates.put(currencyCode, rate);
        System.out.println("Added exchange rate: " + currencyCode + " = " + rate);
    }

    public void updateExchangeRate(String currencyCode, double newRate) {
        if (exchangeRates.containsKey(currencyCode)) {
            exchangeRates.put(currencyCode, newRate);
            System.out.println("Updated exchange rate: " + currencyCode + " = " + newRate);
        } else {
            System.out.println("Currency not found.");
        }
    }

    public double convert(double amount, String sourceCurrency, String targetCurrency) {
        if (!exchangeRates.containsKey(sourceCurrency)) {
            System.out.println("Invalid source currency code.");
            return -1; // Indicating error
        }
        if (!exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid target currency code.");
            return -1; // Indicating error
        }
        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);
        double convertedAmount = amount * (targetRate / sourceRate);
        System.out.printf("Converted %.2f %s to %.2f %s%n", amount, sourceCurrency, convertedAmount, targetCurrency);
        return convertedAmount;
    }

    public double getExchangeRate(String targetCurrency) {
        if (!exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid target currency code.");
            return -1; // Indicating error
        }
        double rate = exchangeRates.get(targetCurrency);
        System.out.printf("Exchange rate from %s to %s: %.3f%n", baseCurrency, targetCurrency, rate);
        return rate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public LinkedHashMap<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(LinkedHashMap<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
