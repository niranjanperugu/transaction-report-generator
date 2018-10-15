package com.cts.assignment.controller;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cts.assignment.domian.Records;
import com.cts.assignment.service.TransactionService;
import com.cts.assignment.util.CommonUtil;

/**
 * Customer Report Controller used for mapping report related requests
 * 
 * @author Niranjan Perugu
 *
 */
@Controller
public class CustomerReportController {

	@Autowired
	TransactionService transactionService;

	private static Logger applicationLog = LoggerFactory.getLogger(CustomerReportController.class);

	private static final String TRANSACTION_REPORT = "TransactionReport";

	private static final String CSV_REPORT = "CSVReport";

	@GetMapping("/")
	public String index() {
		return TRANSACTION_REPORT;
	}

	@PostMapping("/upload")
	public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file) throws JAXBException, IOException {

		ModelAndView modelAndView = new ModelAndView();
		if (file.isEmpty()) {
			modelAndView.setViewName(TRANSACTION_REPORT);
			return modelAndView;
		}

		try {

			StringReader fileContent = new StringReader(new String(file.getBytes()));
			Records records = null;

			if (file.getOriginalFilename().endsWith(".xml")) {
				records = (Records) CommonUtil.parseXml(fileContent, Records.class);
			} else {
				records = CommonUtil.parseCSV(fileContent);
			}
			
			
			applicationLog.info("Transaction Records : {}", records.getRecord());
			
			modelAndView = transactionService.initiateTransaction(records, modelAndView);
			
			applicationLog.info("Transaction Sucessfully Completed!! ");

		} catch (Exception e) {

			modelAndView.addObject("errorMessage", "Invalid File content. Please upload a valid file!!!!");
			applicationLog.error("Error occured while processing :{}", e.getMessage());
		}

		modelAndView.setViewName(TRANSACTION_REPORT);
		return modelAndView;
	}

	@GetMapping("/transactionReport")
	public String getTransactionReport() {
		return TRANSACTION_REPORT;
	}

	@GetMapping("/CSVReport")
	public String getCsvReport() {
		return CSV_REPORT;
	}

}