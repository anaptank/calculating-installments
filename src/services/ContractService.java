package services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
		
	public void processContract(Contract contract, Integer months) {
		double basicQuota = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
			double basicQuotaPlusInterest = basicQuota + onlinePaymentService.interest(basicQuota, i);
			double basicQuotaPlusPaymentFee = basicQuotaPlusInterest + onlinePaymentService.paymentFee(basicQuotaPlusInterest);
			
			Date dueDate = addMonths(contract.getDate(), i);
			contract.getInstallments().add(new Installment(dueDate, basicQuotaPlusPaymentFee));
		}
	}
	
	private Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
}
