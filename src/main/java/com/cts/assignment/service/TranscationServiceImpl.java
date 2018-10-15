package com.cts.assignment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.cts.assignment.domian.Record;
import com.cts.assignment.domian.Records;

@Service
public class TranscationServiceImpl implements TransactionService {

	List<Record> validRecords = null;
	List<Record> inValidRecords = null;

	@Override
	public ModelAndView initiateTransaction(Records records, ModelAndView modelAndView) throws JAXBException {
		
		validRecords = new ArrayList<>();
		inValidRecords = new ArrayList<>();
		
		records.getRecord().forEach(record -> validateTransaction(record));
		
		modelAndView.addObject("validTransactions", validRecords);
		modelAndView.addObject("inValidTransactions", inValidRecords);
		
		return modelAndView;
	}

	/**
	 * This method is used to validate the transaction 
	 * 
	 * @param record
	 */
	private void validateTransaction(Record record) {

		double endBalence = Double.parseDouble(record.getStartBalance()) + Double.parseDouble(record.getMutation());
		
		//precession conververion for accurate value
		endBalence = BigDecimal.valueOf(endBalence).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		boolean isReferenceExists = validRecords.stream()
				.anyMatch(validRecord -> validRecord.getReference().equals(record.getReference()));
		
		if (endBalence == Double.parseDouble(record.getEndBalance()) && !isReferenceExists) {
			validRecords.add(record);
		}

		else {
			inValidRecords.add(record);
		}
	}
}
