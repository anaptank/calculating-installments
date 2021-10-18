package services;

public class PaypalService implements OnlinePaymentService {
	
	private static final double PAYMENT_FEE_PERCENTAGE = 2;
	private static final double MONTHLY_INTEREST_PERCENTAGE = 1;

	@Override
	public double paymentFee(double amount) {
		return amount * PAYMENT_FEE_PERCENTAGE / 100;
	}

	@Override
	public double interest(double amount, int months) {
		return (amount * months * MONTHLY_INTEREST_PERCENTAGE) / 100;
	}

}
