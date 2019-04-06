package com.cchong.logistics.entity;

import java.util.Date;

public class EmployeeInformation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.e_no
     *
     * @mbg.generated
     */
    private Integer eNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.e_name
     *
     * @mbg.generated
     */
    private String eName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.e_sex
     *
     * @mbg.generated
     */
    private String eSex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.e_birthhday
     *
     * @mbg.generated
     */
    private Date eBirthhday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.e_phone
     *
     * @mbg.generated
     */
    private String ePhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.e_password
     *
     * @mbg.generated
     */
    private String ePassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.firm_name
     *
     * @mbg.generated
     */
    private String firmName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_information.remarks
     *
     * @mbg.generated
     */
    private String remarks;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.e_no
     *
     * @return the value of employee_information.e_no
     *
     * @mbg.generated
     */
    public Integer geteNo() {
        return eNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.e_no
     *
     * @param eNo the value for employee_information.e_no
     *
     * @mbg.generated
     */
    public void seteNo(Integer eNo) {
        this.eNo = eNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.e_name
     *
     * @return the value of employee_information.e_name
     *
     * @mbg.generated
     */
    public String geteName() {
        return eName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.e_name
     *
     * @param eName the value for employee_information.e_name
     *
     * @mbg.generated
     */
    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.e_sex
     *
     * @return the value of employee_information.e_sex
     *
     * @mbg.generated
     */
    public String geteSex() {
        return eSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.e_sex
     *
     * @param eSex the value for employee_information.e_sex
     *
     * @mbg.generated
     */
    public void seteSex(String eSex) {
        this.eSex = eSex == null ? null : eSex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.e_birthhday
     *
     * @return the value of employee_information.e_birthhday
     *
     * @mbg.generated
     */
    public Date geteBirthhday() {
        return eBirthhday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.e_birthhday
     *
     * @param eBirthhday the value for employee_information.e_birthhday
     *
     * @mbg.generated
     */
    public void seteBirthhday(Date eBirthhday) {
        this.eBirthhday = eBirthhday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.e_phone
     *
     * @return the value of employee_information.e_phone
     *
     * @mbg.generated
     */
    public String getePhone() {
        return ePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.e_phone
     *
     * @param ePhone the value for employee_information.e_phone
     *
     * @mbg.generated
     */
    public void setePhone(String ePhone) {
        this.ePhone = ePhone == null ? null : ePhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.e_password
     *
     * @return the value of employee_information.e_password
     *
     * @mbg.generated
     */
    public String getePassword() {
        return ePassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.e_password
     *
     * @param ePassword the value for employee_information.e_password
     *
     * @mbg.generated
     */
    public void setePassword(String ePassword) {
        this.ePassword = ePassword == null ? null : ePassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.firm_name
     *
     * @return the value of employee_information.firm_name
     *
     * @mbg.generated
     */
    public String getFirmName() {
        return firmName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.firm_name
     *
     * @param firmName the value for employee_information.firm_name
     *
     * @mbg.generated
     */
    public void setFirmName(String firmName) {
        this.firmName = firmName == null ? null : firmName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_information.remarks
     *
     * @return the value of employee_information.remarks
     *
     * @mbg.generated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_information.remarks
     *
     * @param remarks the value for employee_information.remarks
     *
     * @mbg.generated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}