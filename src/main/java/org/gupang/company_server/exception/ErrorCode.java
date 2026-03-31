package org.gupang.company_server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gupang.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements BaseErrorCode{
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 업체를 찾을 수 없습니다."),
    DUPLICATE_COMPANY_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 업체명입니다.");

    private final HttpStatus httpStatus; // BaseErrorCode 규격에 맞춰 HttpStatus 사용
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

