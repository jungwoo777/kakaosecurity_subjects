package com.example.kakaosecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 메인 함수
 * 
 * 로컬로 실행 후 
 * http://localhost:8080/swagger-ui/index.html#/
 * 브라우저에 위 주소로 된 swagger ui를 실행하여 수행
 * 
 * h2는 in-memory방식으로 구성
 * 프로젝트 시작과 동시에 초기 데이터 구성
 * 
 * @author jungwoo
 *
 */
@SpringBootApplication
public class KakaosecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaosecurityApplication.class, args);
	} 

}
