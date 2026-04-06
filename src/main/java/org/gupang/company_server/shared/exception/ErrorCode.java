package org.gupang.company_server.shared.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    // company error code
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 업체를 찾을 수 없습니다."),
    DUPLICATE_COMPANY_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 업체명입니다."),
    COMPANY_ID_REQUIRED(HttpStatus.BAD_REQUEST, "업체 ID는 필수 입력값입니다."),
    COMPANY_INFO_MISSING(HttpStatus.BAD_REQUEST, "업체 정보가 누락되었습니다."),

    // product error code
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "재고 차감은 현재 남아있는 재고 이하여야 합니다."),
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST,"상품정보를 찾을수 없습니다."),
    INVALID_STOCK_QUANTITY(HttpStatus.BAD_REQUEST, "차감할 재고는 1개 이상이어야 합니다."),
    PERMISSION_DENIED(HttpStatus.BAD_REQUEST,"접근 권한이 없습니다."),
    INVALID_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "상품 가격은 0보다 작을 수 없습니다."),
    UNAUTHORIZED_COMPANY(HttpStatus.BAD_REQUEST, "상품을 등록한 업체만 처리 가능합니다.");

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

