package com.peels.dto;

import lombok.Data;

/**
 * 分页查询公众监督员反馈信息的请求数据
 */
@Data
public class AfPageRequestDto extends PageRequestDto{

	//业务查询条件的请求参数
	private Integer provinceId;
	private Integer cityId;
	private Integer estimatedGrade;
	private String afDate;
	private Integer state;

}
