package com.mini.project.infra;

public class ApiResponseUtil {
	public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Request successful", data);
    }

    public static ApiResponse<Object> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
