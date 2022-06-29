package com.VendingMachine.DTO;

public class CoinMath {

    public double calculate(Coin operator, double total) {

        switch (operator) {
            case ONE_PENCE:
                return total + 0.01;
            case TWO_PENCE:
                return total + 0.02;
            case FIVE_PENCE:
                return total + 0.05;
            case TEN_PENCE:
                return total + 0.10;
            case TWENTY_PENCE:
                return total + 0.20;
            case FIFTY_PENCE:
                return total + 0.50;
            case ONE_POUND:
                return total + 1.00;
            case TWO_POUND:
                return total + 2.00;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
