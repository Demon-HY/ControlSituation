package com.control.situation.event;

/**
 * test
 *
 * Created by Administrator on 2018/3/29 0029.
 */
public class TestEvent extends CommonEvent {

	public TestEvent(Object source) {
		super(source);
	}

	public TestEvent(Object source, String content) {
		super(source, content);
	}
}
