package sg.edu.nus.iss.shop.controller;

import java.util.Random;

import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;
import sg.edu.nus.iss.shop.model.nondomain.AdhesiveLabelPrinter;
import sg.edu.nus.iss.shop.model.nondomain.ReceiptPrinter;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;

public class PrinterManager {
	private static PrinterManager pm = new PrinterManager();

	private PrinterManager() {

	}

	public static PrinterManager getInstance() {
		return pm;
	}

	public void PrintTransaction(Transaction t) {
		String text = "";
		double cashFromPoints = TransactionManager.getInstance()
				.convertPointsToCash(t.getLoyaltyPointsUsed());
		double change = t.getAmountReceived() - t.getCashPayed();
		double payedAmount = t.getCashPayed();
		text += "         UNIVERSITY SOUVENIR STORE          \n";
		text += "      NATIONAL UNIVERSITY OF SINGAPORE      \n";
		text += "                                            \n";
		text += " ========================================== \n";
		text += " DATE: " + t.getDate() + "                  \n";
		text += " RECEIPT NUMBER: " + t.getId() + "          \n";
		if (t.getCustomer() instanceof Member) {
			Member m = (Member) t.getCustomer();
			text += " MEMBER: " + m.getId() + "          \n";
			text += " LOYALTY POINTS: " + m.getLoyalPoints() + "          \n";
		}
		text += " ========================================== \n";
		text += " DETAILS                                    \n";
		text += "    NAME                                    \n";
		// ruler"123456789 123456789 123456789 123456789 1234
		// qu NAME----------------------- PRICE----
		for (TransactionDetail td : t.getTransactionDetails()) {
			text += " " + formatString("" + td.getQuantity(), 2, true) + " "
					+ formatString(td.getProduct().getName(), 25, true) + " "
					+ formatString("" + td.getTotalPrice(), 10, false) + "\n";
		}
		text += " ========================================== \n";
		text += " SUBTOTAL           "
				+ formatString(PriceHelper.getPriceDisplay(t.getTotalPrice()),
						20, false) + " \n";
		text += " DISCOUNT           "
				+ formatString(PriceHelper.getPriceDisplay(t.getDiscount()),
						20, false) + " \n";
		text += " TOTAL              "
				+ formatString(PriceHelper.getPriceDisplay(t.getFinalPrice()),
						20, false) + " \n";
		text += " ------------------------------------------ \n";
		text += " PAYMENT \n";
		text += "    POINTS USED: " + t.getLoyaltyPointsUsed() + " \n";
		text += "    CASH FROM POINTS:    "
				+ formatString(PriceHelper.getPriceDisplay(cashFromPoints), 15,
						false) + " \n";
		text += "    TOTAL AMOUNT PAYED:  "
				+ formatString(PriceHelper.getPriceDisplay(payedAmount), 15,
						false) + " \n";
		text += " ------------------------------------------ \n";
		text += "    AMOUNT RECEIVED:     "
				+ formatString(PriceHelper.getPriceDisplay(t.getAmountReceived()),
						15, false) + " \n";
		text += "    CHANGE:              "
				+ formatString(PriceHelper.getPriceDisplay(change), 15, false)
				+ " \n";
		text += " ========================================== \n";
		text += " Thank you for buying at the souvenir store \n";
		text += "    Please come back, we need more money    \n";
		text += "             Have a nice day                \n";

		ReceiptPrinter rp = new ReceiptPrinter();
		rp.print(text);
	}

	private String formatString(String text, int finalLength, boolean left) {
		String newText = "";
		if (text.length() <= finalLength) {
			if (left) {
				newText = text + createBlanks(finalLength - text.length());
			} else {
				newText = createBlanks(finalLength - text.length()) + text;
			}
		} else {
			newText = text.substring(0, finalLength);
		}
		return newText;
	}

	private String createBlanks(int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += " ";
		}
		return result;
	}
	
	public void PrintProductBarCode(Product p){
		AdhesiveLabelPrinter alp = new AdhesiveLabelPrinter();
		alp.print(p.getBarcodeNumber());
	}
}
