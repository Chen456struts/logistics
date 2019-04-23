package com.cchong.logistics.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ShipperInformation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_id
     *
     * @mbg.generated
     */
    private Integer sId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_name
     *
     * @mbg.generated
     */
    private String sName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_sex
     *
     * @mbg.generated
     */
    private String sSex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_phone
     *
     * @mbg.generated
     */
    private String sPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_password
     *
     * @mbg.generated
     */
    private String sPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_uuid
     *
     * @mbg.generated
     */
    private String sUuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.s_balance
     *
     * @mbg.generated
     */
    private BigDecimal sBalance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shipper_information.register_date
     *
     * @mbg.generated
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_id
     *
     * @return the value of shipper_information.s_id
     *
     * @mbg.generated
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_id
     *
     * @param sId the value for shipper_information.s_id
     *
     * @mbg.generated
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_name
     *
     * @return the value of shipper_information.s_name
     *
     * @mbg.generated
     */
    public String getsName() {
        return sName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_name
     *
     * @param sName the value for shipper_information.s_name
     *
     * @mbg.generated
     */
    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_sex
     *
     * @return the value of shipper_information.s_sex
     *
     * @mbg.generated
     */
    public String getsSex() {
        return sSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_sex
     *
     * @param sSex the value for shipper_information.s_sex
     *
     * @mbg.generated
     */
    public void setsSex(String sSex) {
        this.sSex = sSex == null ? null : sSex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_phone
     *
     * @return the value of shipper_information.s_phone
     *
     * @mbg.generated
     */
    public String getsPhone() {
        return sPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_phone
     *
     * @param sPhone the value for shipper_information.s_phone
     *
     * @mbg.generated
     */
    public void setsPhone(String sPhone) {
        this.sPhone = sPhone == null ? null : sPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_password
     *
     * @return the value of shipper_information.s_password
     *
     * @mbg.generated
     */
    public String getsPassword() {
        return sPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_password
     *
     * @param sPassword the value for shipper_information.s_password
     *
     * @mbg.generated
     */
    public void setsPassword(String sPassword) {
        this.sPassword = sPassword == null ? null : sPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_uuid
     *
     * @return the value of shipper_information.s_uuid
     *
     * @mbg.generated
     */
    public String getsUuid() {
        return sUuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_uuid
     *
     * @param sUuid the value for shipper_information.s_uuid
     *
     * @mbg.generated
     */
    public void setsUuid(String sUuid) {
        this.sUuid = sUuid == null ? null : sUuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.s_balance
     *
     * @return the value of shipper_information.s_balance
     *
     * @mbg.generated
     */
    public BigDecimal getsBalance() {
        return sBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.s_balance
     *
     * @param sBalance the value for shipper_information.s_balance
     *
     * @mbg.generated
     */
    public void setsBalance(BigDecimal sBalance) {
        this.sBalance = sBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shipper_information.register_date
     *
     * @return the value of shipper_information.register_date
     *
     * @mbg.generated
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shipper_information.register_date
     *
     * @param registerDate the value for shipper_information.register_date
     *
     * @mbg.generated
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}