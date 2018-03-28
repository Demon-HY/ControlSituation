package com.control.situation.utils.exception;


import com.control.situation.utils.returns.RetCode;

/**
 * 参数错误异常
 *
 * @author monitor
 */
public class ParamException extends LogicalException {

    private static final long serialVersionUID = -2434647353423193324L;

    public ParamException() {
        super(RetCode.ERR_BAD_PARAMS);
    }

    public ParamException(String msg) {
        super(RetCode.ERR_BAD_PARAMS, msg);
    }

}
