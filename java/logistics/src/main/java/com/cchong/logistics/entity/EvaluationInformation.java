package com.cchong.logistics.entity;

public class EvaluationInformation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.e_id
     *
     * @mbg.generated
     */
    private Integer eId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.d_id
     *
     * @mbg.generated
     */
    private Integer dId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.d_name
     *
     * @mbg.generated
     */
    private String dName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.s_id
     *
     * @mbg.generated
     */
    private Integer sId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.s_name
     *
     * @mbg.generated
     */
    private String sName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.fraction
     *
     * @mbg.generated
     */
    private String fraction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column evaluation_information.e_content
     *
     * @mbg.generated
     */
    private String eContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.e_id
     *
     * @return the value of evaluation_information.e_id
     *
     * @mbg.generated
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.e_id
     *
     * @param eId the value for evaluation_information.e_id
     *
     * @mbg.generated
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.d_id
     *
     * @return the value of evaluation_information.d_id
     *
     * @mbg.generated
     */
    public Integer getdId() {
        return dId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.d_id
     *
     * @param dId the value for evaluation_information.d_id
     *
     * @mbg.generated
     */
    public void setdId(Integer dId) {
        this.dId = dId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.d_name
     *
     * @return the value of evaluation_information.d_name
     *
     * @mbg.generated
     */
    public String getdName() {
        return dName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.d_name
     *
     * @param dName the value for evaluation_information.d_name
     *
     * @mbg.generated
     */
    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.s_id
     *
     * @return the value of evaluation_information.s_id
     *
     * @mbg.generated
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.s_id
     *
     * @param sId the value for evaluation_information.s_id
     *
     * @mbg.generated
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.s_name
     *
     * @return the value of evaluation_information.s_name
     *
     * @mbg.generated
     */
    public String getsName() {
        return sName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.s_name
     *
     * @param sName the value for evaluation_information.s_name
     *
     * @mbg.generated
     */
    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.fraction
     *
     * @return the value of evaluation_information.fraction
     *
     * @mbg.generated
     */
    public String getFraction() {
        return fraction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.fraction
     *
     * @param fraction the value for evaluation_information.fraction
     *
     * @mbg.generated
     */
    public void setFraction(String fraction) {
        this.fraction = fraction == null ? null : fraction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evaluation_information.e_content
     *
     * @return the value of evaluation_information.e_content
     *
     * @mbg.generated
     */
    public String geteContent() {
        return eContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evaluation_information.e_content
     *
     * @param eContent the value for evaluation_information.e_content
     *
     * @mbg.generated
     */
    public void seteContent(String eContent) {
        this.eContent = eContent == null ? null : eContent.trim();
    }
}