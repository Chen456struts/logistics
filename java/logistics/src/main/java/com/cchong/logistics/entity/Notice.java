package com.cchong.logistics.entity;

public class Notice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.infro_id
     *
     * @mbg.generated
     */
    private Integer infroId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.n_content
     *
     * @mbg.generated
     */
    private String nContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.direction
     *
     * @mbg.generated
     */
    private String direction;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.infro_id
     *
     * @return the value of notice.infro_id
     *
     * @mbg.generated
     */
    public Integer getInfroId() {
        return infroId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.infro_id
     *
     * @param infroId the value for notice.infro_id
     *
     * @mbg.generated
     */
    public void setInfroId(Integer infroId) {
        this.infroId = infroId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.n_content
     *
     * @return the value of notice.n_content
     *
     * @mbg.generated
     */
    public String getnContent() {
        return nContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.n_content
     *
     * @param nContent the value for notice.n_content
     *
     * @mbg.generated
     */
    public void setnContent(String nContent) {
        this.nContent = nContent == null ? null : nContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.direction
     *
     * @return the value of notice.direction
     *
     * @mbg.generated
     */
    public String getDirection() {
        return direction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.direction
     *
     * @param direction the value for notice.direction
     *
     * @mbg.generated
     */
    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }
}