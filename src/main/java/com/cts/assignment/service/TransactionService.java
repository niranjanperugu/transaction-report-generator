package com.cts.assignment.service;

import javax.xml.bind.JAXBException;

import org.springframework.web.servlet.ModelAndView;

import com.cts.assignment.domian.Records;

/**
 * Interface for TransactionService
 * 
 * @author Niranjan Babu
 *
 */
public interface TransactionService {

	/**
	 * This method is used to perform the transaction
	 *  
	 * @param records
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws JAXBException
	 */
	public ModelAndView initiateTransaction(Records records, ModelAndView modelAndView) throws JAXBException;
}
