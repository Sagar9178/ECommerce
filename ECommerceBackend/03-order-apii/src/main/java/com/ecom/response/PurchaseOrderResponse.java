package com.ecom.response;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class PurchaseOrderResponse {
	private String razorPayOrderId;
	private String orderstatus;
	private String orderTrackingNumber;
	
}
