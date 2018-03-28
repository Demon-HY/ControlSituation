package com.control.situation.utils.exception;

import com.control.situation.utils.returns.RetCode;

/**
 * 逻辑错误异常
 *
 * @author monitor
 */
public class LogicalException extends Exception {

    private static final long serialVersionUID = -2247331292377127222L;

    public String stat;
    public String errMsg;

    public LogicalException(String stat) {
        super(stat + "\t" + RetCode.getMsgByStat(stat));
        this.stat = stat;
        this.errMsg = RetCode.getMsgByStat(stat);
    }

    public LogicalException(String stat, String errMsg) {
        super(stat + "\t" + errMsg);
        this.stat = stat;
        this.errMsg = errMsg;
    }
}
