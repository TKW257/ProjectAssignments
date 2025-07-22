/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentDTO {
    private int paymentId;
    private int orderId;
    private Timestamp paymentDate;
    private String paymentMethod;
    private BigDecimal amount;
    private String paymentStatus;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentId, int orderId, Timestamp paymentDate, String paymentMethod, BigDecimal amount, String paymentStatus) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Timestamp getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Timestamp paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
