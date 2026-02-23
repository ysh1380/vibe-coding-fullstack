# 프로젝트 명세서: 최소 기능 스프링부트 애플리케이션

이 문서는 최소한의 기능을 갖춘 스프링부트 기반 웹 애플리케이션 프로젝트의 구성을 정의한다.

## 1. 프로젝트 개요
- **프로젝트 명**: vibeapp
- **설명**: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다.
- **그룹 ID**: `com.example`
- **아티팩트 ID**: `vibeapp`
- **패키지 경로**: `com.example.vibeapp`
- **메인 클래스**: `VibeApp`

## 2. 개발 환경 및 기술 스택
- **언어**: Java
- **JDK 버전**: 25 이상
- **프레임워크**: Spring Boot 4.0.1 이상
- **빌드 도구**: Gradle 9.3.0 이상 (Groovy DSL 사용)
- **설정 파일 형식**: YAML (`application.yml`)

## 3. 빌드 및 종속성 관리
### 3.1. 플러그인
- `org.springframework.boot`
- `io.spring.dependency-management`: Spring Boot 버전에 동기화하여 플러그인 추가

### 3.2. 의존성 (Dependencies)
- `spring-boot-starter-web`: REST API 및 웹 서비스 지원
- `spring-boot-starter-thymeleaf`: Thymeleaf 뷰 템플릿 엔진 지원

## 4. 주요 기능 및 엔드포인트
### 4.1. 웹 페이지 (Thymeleaf)
- **URL**: `/`
- **컨트롤러**: `HomeController`
- **뷰**: `home.html`
- **설명**: "Hello, Vibe!" 메시지를 출력하는 메인 홈 페이지

### 4.2. REST API
- **URL**: `/api/hello`
- **HTTP 메서드**: GET
- **반환값**: "Hello, Vibe!" (String)
- **설명**: 간단한 문자열을 반환하는 테스트용 API

## 5. 구성 상세 (Configuration)
`src/main/resources/application.yml` 파일을 사용하여 애플리케이션 설정을 관리한다.
