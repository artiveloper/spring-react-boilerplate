package org.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.constant.ErrorCode;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

    private Boolean success;
    private T data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse<T> success(T data, String message) {
        return CommonResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .errorCode(null)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static <T> CommonResponse<T> success() {
        return success(null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
        return CommonResponse.<T>builder()
                .success(false)
                .message(errorCode.getMessage())
                .errorCode(errorCode.getCode())
                .build();
    }

}
