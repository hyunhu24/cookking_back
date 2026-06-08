package com.springboot.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404,"Member not found"),
    MEMBER_EXISTS(409,"Member exists"),
    MEMBER_NOT_OWNER(403, "You are not the owner of this resource"),
    MEMBER_NAME_EXISTS(409, "Member Name exists"),
    MEMBER_PHONE_NUMBER_EXISTS(409, "Member Phone Number exists"),
    USER_NOT_LOGGED_IN(401, "You are not logged in"),
    LOGOUT_ERROR(409, "logout error"),
    INVALID_REFRESH_TOKEN(400, "유효하지 않은 리플래시 토큰입니다."),
    UNAUTHORIZED_ACCESS(403, "인증이 필요합니다."),
    ACCESS_DENIED(403, "권한이 없습니다."),
    MENU_NOT_FOUND(404, "메뉴가 존재하지 않습니다."),
    RECIPEBOARD_NOT_FOUND(404, "레시피 게시글이 존재하지 않습니다."),
    MEMBER_NOT_MATCH(403, "작성자만 접근할 수 있습니다."),
    INVALID_KEYWORD(400, "검색어가 유효하지 않습니다."),
    COLLECTION_NOT_FOUND(404, "도감이 존재하지 않습니다."),
    COLLECTION_ITEM_NOT_FOUND(404, "도감 메뉴가 존재하지 않습니다."),
    DUPLICATE_COLLECTION_MENU(409, "도감에 동일한 메뉴명이 이미 존재합니다."),
    CHALLENGE_NOT_FOUND(404, "도전과제가 존재하지 않습니다."),
    CHALLENGE_CATEGORY_NOT_FOUND(404, "도전과제 카테고리가 존재하지 않습니다."),
    EMAIL_VERIFY_FAILED(400, "이메일 인증이 필요합니다"),
    EMAIL_NOT_VERIFIED(400, "이메일 인증이 완료되지 않았습니다."),
    EMAIL_CODE_MISMATCH(400, "인증번호가 일치하지 않거나 만료되었습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 일치하지 않습니다."),
    PASSWORD_DUPLICATED(400, "이미 사용 중인 비밀번호입니다."),
    BOOKMARK_NOT_FOUND(404, "해당 북마크를 찾을 수 없습니다."),
    RECIPE_STEP_NOT_FOUND(404, "레시피 스텝이 존재하지 않습니다."),
    RECIPE_STEP_EXISTS(409, "레시피 스텝이 이미 존재합니다."),
    DUPLICATE_COLLECTION_CATEGORY(409, "도감 카테고리가 이미 존재합니다"),
    IMAGE_REQUIRED(400, "아직 이미지를 업로드 하지 않았습니다"),
    DUPLICATE_LOGIN_ID(409, "해당 아이디는 이미 존재합니다."),
    CAMERA_IMAGE_NOT_FOUND(404, "카메라 이미지를 찾을 수 없습니다."),
    MEMBER_ACCESS_DENIED(403, "접근이 거부되었습니다."),
    COLLECTION_CATEGORY_NAME_EXISTS(409, "도감 카테고리 이름이 중복됩니다."),
    INGREDIENT_NOT_FOUND(404, "재료가 존재하지 않습니다."),
    INGREDIENT_EXISTS(409, "재료가 이미 존재합니다."),
    INGREDIENT_TYPE_NOT_FOUND(404, "재료 타입이 존재하지 않습니다."),
    INGREDIENT_TYPE_NOT_MATCH(409, "재료 타입이 일치하지 않습니다."),
    INSUFFICIENT_MAIN_INGREDIENTS(400, "주 재료는 2개 이상이어야 합니다."),
    MENU_CATEGORY_EXISTS(409, "메뉴 카테고리가 이미 존재합니다."),
    MENU_CATEGORY_NOT_FOUND(404, "메뉴 카테고리가 존재하지 않습니다."),
    DUPLICATE_MENU(409, "메뉴가 이미 존재합니다."),
    PROFILE_IMAGE_NOT_FOUND(404, "프로필 이미지가 존재하지 않습니다."),
    TITLE_NOT_FOUND(404, "타이틀이 존재하지 않습니다."),
    INVALID_PAYMENT_AMOUNT(400, "유효하지 않은 결제 금액입니다."),
    MEMBER_CHALLENGE_NOT_FOUND(404, "회원 도전과제가 존재하지 않습니다."),
    PAYMENT_NOT_FOUND(404, "결제 정보가 존재하지 않습니다."),
    PAYMENT_MASTER_NOT_FOUND(404, "결제 상품 정보(PaymentMaster)를 찾을 수 없습니다."),
    PAYMENT_FAILED(500, "결제 승인에 실패했습니다."),
    PROFILE_NOT_FOUND(404, "프로필이 존재하지 않습니다."),
    INSUFFICIENT_RICE_POINT(400, "보유한 밥풀이 부족합니다."),
    PROFILE_ALREADY_OWNED(409, "이미 보유 중인 프로필입니다."),
    PROFILE_NOT_OWNED(400, "보유중인 프로필이 아닙니다."),
    TITLE_NOT_OWNED(400, "칭호를 보유하고 있지 않습니다."),
    LEVEL_UP_CONDITION_NOT_MET(400, "레벨업 조건이 충족되지 않았습니다. 목표 수치를 달성했는지 확인해주세요."),
    ALREADY_MAX_LEVEL(409, "이미 최대 레벨에 도달했습니다.");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int statusCode, String message){
        this.message = message;
        this.status = statusCode;
    }
}
