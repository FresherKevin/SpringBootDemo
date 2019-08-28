package cn.nh.kevin.demo.Enum;

import lombok.Getter;
import lombok.Setter;

/**
 * 标题:
 * 描述:
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-27 09:59
 */
public enum ResultEnum {
	SUCCESS("100", "成功"), FAIL("400", "失败");
	private String num;
	private String text;

	ResultEnum(String tnum, String ttext) {
		this.num = tnum;
		this.text = ttext;

	}

}
