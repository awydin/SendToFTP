package com.aydin.ftpreport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aydin.ftpreport.utils.ReportFieldName;
import com.aydin.ftpreport.utils.ReportInfo;

/**
 * 
 * 
 * @author paakro@hotmail.com
 *
 */

@Entity
@Table(name = "View_name")
@ReportInfo()
public class AllCouponsReportEntity
{




    public AllCouponsReportEntity(String couponNumber, String couponCode, String couponName, Long amount)
    {
        super();
        this.couponNumber = couponNumber;
        this.couponCode = couponCode;
        this.couponName = couponName;
        this.amount = amount;
    }


    @Id
    @Column(name = "COUPON_NUMBER")
    @ReportFieldName(fieldName = "coupon number")
    private String couponNumber;
    
    @Column(name = "COUPON_CODE")
    @ReportFieldName(fieldName = "coupon code")
    private String couponCode;
    
    @Column(name = "COUPON_NAME")
    @ReportFieldName(fieldName = "coupon name")
    private String couponName;
    
    
    @Column(name = "COUPON_AMOUNT")
    @ReportFieldName(fieldName = "coupon amount")
    private Long amount;


    public String getCouponNumber()
    {
        return couponNumber;
    }


    public void setCouponNumber(String couponNumber)
    {
        this.couponNumber = couponNumber;
    }


    public String getCouponCode()
    {
        return couponCode;
    }


    public void setCouponCode(String couponCode)
    {
        this.couponCode = couponCode;
    }


    public String getCouponName()
    {
        return couponName;
    }


    public void setCouponName(String couponName)
    {
        this.couponName = couponName;
    }


    public Long getAmount()
    {
        return amount;
    }


    public void setAmount(Long amount)
    {
        this.amount = amount;
    }
    
    
}
