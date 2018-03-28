package com.control.situation.utils.exception;

//@javadoc

/**
 * 未初始化异常
 * @author monitor
 *
 */
public class UnInitilizedException extends Exception {

    private static final long serialVersionUID = 1230554992550126245L;

    public UnInitilizedException() {
        super();
    }
    
    public UnInitilizedException(String message) {
        super(message);
    }
    
    public UnInitilizedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UnInitilizedException(Throwable cause) {
        super(cause);
    }

}
