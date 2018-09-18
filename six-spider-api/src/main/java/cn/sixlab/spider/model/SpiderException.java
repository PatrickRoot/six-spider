package cn.sixlab.spider.model;

public class SpiderException extends RuntimeException {
    public SpiderException() {
        super();
    }

    public SpiderException(String message) {
        super(message);
    }

    public SpiderException(String message, Throwable cause) {
        super(message, cause);
    }

}
