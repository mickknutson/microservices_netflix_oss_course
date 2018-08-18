package com.baselogic.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.baselogic.amqp.MessageSender;
import com.baselogic.domain.Order;

@Controller
public class RequestHandlerController {

	@Autowired
	private MessageSender messageSender;

	@GetMapping("/input.html")
	public String getIndex() {
		System.out.println("getIndex was called");
		return "/input";
	}

	@GetMapping("/success.html")
	public String getSuccess() {
		System.out.println("getIndex was called");
		return "/success";
	}

	@GetMapping("/") 
	public String getRoot() {
		return "redirect:/input.html";
	}
	

	@PostMapping("/submitMessage")
	public String handleRequest(HttpServletRequest request) throws ServletException, IOException {
		Order submitData = new Order();
		submitData.setId(Long.parseLong(request.getParameter("id")));
		submitData.setProduct(request.getParameter("product"));
		submitData.setCustomerName(request.getParameter("customerName"));
		System.out.println("Order submitted to RequestHandlerServlet: "
				+ submitData);
		// We want to send a message here
		messageSender.sendOrderMessage(submitData);

		return "/success";
	}

}
