package io.renren.vas.util;

public class ImportException extends RuntimeException {

    private Integer code;

    public ImportException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ImportException(Integer code, java.lang.String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
