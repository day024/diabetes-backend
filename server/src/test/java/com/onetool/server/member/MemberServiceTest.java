package com.onetool.server.member;

import com.onetool.server.member.service.MemberService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberServiceTest {

    private ExtractableResponse<Response> memberCreateResponse;

    @Autowired
    private MemberService memberService;

    @Test
    void create_member() {
        // given
        final Map<String, Object> params = Map.of(
                "email", "admin@example.com",
                "password", "1234",
                "name", "홍길동",
                "birthDate", "2001-03-26",
                "development_field", "백엔드",
                "phoneNum", "010-0000-0000",
                "isNative", true
        );

        // when
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(params)
                .when().post("/users/signup")
                .then().log().all()
                .extract();

        // then
        final JsonPath result = response.jsonPath();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(result.getString("email")).isEqualTo("admin@example.com"),
                () -> assertThat(result.getString("name")).isEqualTo("홍길동")
        );
    }


    @Test
    @DisplayName("로그인 후 회원 정보 조회 성공 테스트")
    void loginAndGetMemberSuccess() {
        // given (회원 가입)
        final Map<String, Object> signupParams = Map.of(
                "email", "admin@example.com",
                "password", "1234",
                "name", "홍길동",
                "birthDate", "2001-03-26",
                "development_field", "백엔드",
                "phoneNum", "010-0000-0000",
                "isNative", true
        );

        // 회원 가입 요청
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(signupParams)
                .when().post("/users/signup")
                .then().log().all()
                .statusCode(201);

        // given (로그인)
        final Map<String, String> loginParams = Map.of(
                "email", "admin@example.com",
                "password", "1234"
        );

        // 로그인 요청
        ExtractableResponse<Response> loginResponse = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginParams)
                .when().post("/users/login")
                .then().log().all()
                .statusCode(200)
                .extract();

        // 로그인 응답에서 토큰 추출
        String token = loginResponse.asString();
        assertThat(token).isNotEmpty();

        // when (회원 정보 조회)
        ExtractableResponse<Response> memberResponse = RestAssured.given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when().get("/users")
                .then().log().all()
                .statusCode(200)
                .extract();

        // 응답 본문 출력 (디버깅용)
        System.out.println("Member response body: " + memberResponse.body().asString());

        // then (조회 결과 검증)
        assertThat(memberResponse.jsonPath().getString("email")).isEqualTo("admin@example.com");
        assertThat(memberResponse.jsonPath().getString("name")).isEqualTo("홍길동");
        assertThat(memberResponse.jsonPath().getString("birthDate")).isEqualTo("2001-03-26");
        assertThat(memberResponse.jsonPath().getString("development_field")).isEqualTo("백엔드");
        assertThat(memberResponse.jsonPath().getString("phoneNum")).isEqualTo("010-0000-0000");
        assertThat(memberResponse.jsonPath().getBoolean("isNative")).isTrue();
        assertThat(memberResponse.jsonPath().getString("user_registered_at")).isEqualTo(LocalDate.now().toString());
    }


    @Test
    @DisplayName("멤버가 삭제되는지 확인")
    void memberDelete() {
        // given (회원 가입)
        final Map<String, Object> signupParams = Map.of(
                "email", "admin@example.com",
                "password", "1234",
                "name", "홍길동",
                "birthDate", "2001-03-26",
                "development_field", "백엔드",
                "phoneNum", "010-0000-0000",
                "isNative", true
        );

        // 회원 가입 요청
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(signupParams)
                .when().post("/users/signup")
                .then().log().all()
                .statusCode(201);

        // given (로그인)
        final Map<String, String> loginParams = Map.of(
                "email", "admin@example.com",
                "password", "1234"
        );

        // 로그인 요청
        ExtractableResponse<Response> loginResponse = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginParams)
                .when().post("/users/login")
                .then().log().all()
                .statusCode(200)
                .extract();

        // 로그인 응답에서 토큰 추출
        String token = loginResponse.asString();
        assertThat(token).isNotEmpty();

        // Delete 요청 시 사용될 요청 본문
        final Map<String, Object> deleteParams = Map.of(
                "id", 1,
                "password", "1234"
        );

        // when (회원 삭제 요청)
        final ExtractableResponse<Response> deleteResponse = RestAssured.given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)  // 본문을 JSON으로 설정
                .body(deleteParams)  // 요청 본문에 삭제 정보 포함
                .when().delete("/users/1")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(deleteResponse.body().asString()).isEqualTo("정상적으로 탈퇴되었습니다.")
        );
    }


    @Test
    @DisplayName("이메일 찾기 테스트")
    void find_email() {
        create_member();

        // given
        final Map<String, Object> params = Map.of(
                "name", "홍길동",
                "birth_date", "2001-03-26"
        );

        // when
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(params)
                .when().post("/users/email")
                .then().log().all()
                .extract();

        // then
        assertThat(response.body().toString()).isEqualTo("sungwon326@naver.com");
    }
}