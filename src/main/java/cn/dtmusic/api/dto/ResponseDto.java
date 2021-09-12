package cn.dtmusic.api.dto;


/**
 * @ description:
 * @ date:      2020/10/8
 * @ time:      15:59
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public class ResponseDto {
    public static final byte SUCCESS = 0;
    public static final byte FAIL = 1;

    private Object result;
    private byte errorCode;
    private String errorInfo;

    public ResponseDto() {
    }

    public ResponseDto(Object result, byte errorCode, String errorInfo) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public byte getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(byte errorCode) {
        if (errorCode != ResponseDto.SUCCESS && errorCode != ResponseDto.FAIL) {
            this.errorCode = ResponseDto.FAIL;
        } else {
            this.errorCode = errorCode;
        }
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
