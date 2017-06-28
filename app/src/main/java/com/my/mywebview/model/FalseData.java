package com.my.mywebview.model;

/** 绑定的银行卡信息 */
public class FalseData {
	/** 银行编号 CMB */
	public String bnkNo;
	/** 银行中文全称 招商银行 */
	public String bnkName;
	/** 银行中文全称 招商银行 */
	public String imgUrl;
	/** 单笔限额 分为单位 */
	public String stokAmtLimit;
	/** 单日限额 分为单位 */
	public String dayAmtLimit;
	/** 热线电话 */
	public String hotline;

	public String getBnkNo() {
		return bnkNo;
	}

	public void setBnkNo(String bnkNo) {
		this.bnkNo = bnkNo;
	}

	public String getBnkName() {
		return bnkName;
	}

	public void setBnkName(String bnkName) {
		this.bnkName = bnkName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStokAmtLimit() {
		return stokAmtLimit;
	}

	public void setStokAmtLimit(String stokAmtLimit) {
		this.stokAmtLimit = stokAmtLimit;
	}

	public String getDayAmtLimit() {
		return dayAmtLimit;
	}

	public void setDayAmtLimit(String dayAmtLimit) {
		this.dayAmtLimit = dayAmtLimit;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public FalseData(String info) {

	}

	public FalseData() {

	}
}
