package com.firststringboot.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户id
     */
    private Long pkUserId;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 性别 0：男，1：女
     */
    private String sex;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 头像
     */
    private String headerImage;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * qq账号
     */
    private String qq;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 帖子数
     */
    private Integer totalTotal;

    /**
     * 动态数
     */
    private Integer dynamicTotal;

    /**
     * 游客 0：是，1：不是
     */
    private Boolean isTourist;

    /**
     * 假删除 0：未删除，1：已删除
     */
    private Boolean isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(Long pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage == null ? null : headerImage.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getTotalTotal() {
        return totalTotal;
    }

    public void setTotalTotal(Integer totalTotal) {
        this.totalTotal = totalTotal;
    }

    public Integer getDynamicTotal() {
        return dynamicTotal;
    }

    public void setDynamicTotal(Integer dynamicTotal) {
        this.dynamicTotal = dynamicTotal;
    }

    public Boolean getIsTourist() {
        return isTourist;
    }

    public void setIsTourist(Boolean isTourist) {
        this.isTourist = isTourist;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}