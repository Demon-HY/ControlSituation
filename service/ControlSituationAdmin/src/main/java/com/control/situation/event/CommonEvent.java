package com.control.situation.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义spring事件基类，所有的事件继承该基类
 * <p>
 * Created by Administrator on 2018/3/29 0029.
 */
public class CommonEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1394667650154523969L;

	/**
	 * 事件接收的内容
	 */
	private String content;

	public CommonEvent(Object source) {
		super(source);
	}

	public CommonEvent(Object source, String content) {
		super(source);
		this.content = content;
	}

	/**
	 * spring容器事件，封装所有的运行时异常事件
	 */
	public class BatchSendExMsgEmailEvent extends CommonEvent {
		public BatchSendExMsgEmailEvent(Object source) {
			super(source);
		}


	}
}
