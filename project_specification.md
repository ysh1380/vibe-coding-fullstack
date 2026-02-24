# 프로젝트 명세서: 바이브 코딩 프리미엄 웹 애플리케이션

이 문서는 바이브 코딩(Vibe Coding) 프리미엄 커뮤니티 및 게시판 기능을 갖춘 스프링부트 기반 웹 애플리케이션 프로젝트의 구성을 정의한다.

## 1. 프로젝트 개요
- **프로젝트 명**: vibeapp
- **설명**: 삼성 프리미엄 스타일의 디자인을 갖춘 커뮤니티 게시판 플랫폼
- **그룹 ID**: `com.example`
- **아티팩트 ID**: `vibeapp`
- **패키지 구조 (기능형)**:
  - `com.example.vibeapp`: 메인 애플리케이션 클래스
  - `com.example.vibeapp.home`: 홈 화면 및 공통 기능
  - `com.example.vibeapp.post`: 게시판 CRUD 및 페이징 기능
- **메인 클래스**: `VibeApp`

## 2. 개발 환경 및 기술 스택
- **언어**: Java 25
- **프레임워크**: Spring Boot 4.0.1
- **빌드 도구**: Gradle 9.3.0 (Groovy DSL)
- **템플릿 엔진**: Thymeleaf
- **프론트엔드 스타일**: Tailwind CSS (Premium Samsung Design Language 적용)
- **데이터 저장**: In-memory List (현재 단계)

## 3. 패키지 및 디렉토리 구조 상세
### 3.1. 소스 코드 (`src/main/java`)
- `com.example.vibeapp`
  - `VibeApp.java`: 스프링 부트 애플리케이션 진입점
- `com.example.vibeapp.home`
  - `HomeController.java`: 메인 페이지 컨트롤러
- `com.example.vibeapp.post`
  - `Post.java`: 게시글 엔티티 (번호, 제목, 내용, 작성일, 수정일, 조회수)
  - `PostController.java`: 게시판 CRUD 웹 요청 처리
  - `PostService.java`: 비즈니스 로직 및 페이징 처리
  - `PostRepository.java`: 인메모리 데이터 관리 및 저장소

### 3.2. 뷰 템플릿 (`src/main/resources/templates`)
- `home/home.html`: 메인 대시보드 및 서비스 소개
- `post/`
  - `posts.html`: 게시글 목록 (5개 단위 페이징)
  - `post_detail.html`: 게시글 상세 조회 (조회수 증가, 수정/삭제 링크)
  - `post_new_form.html`: 새 게시글 작성 폼
  - `post_edit_form.html`: 기존 게시글 수정 폼

## 4. 주요 기능 및 엔드포인트
### 4.1. 게시판 기능 (Board Features)
| 기능 | URL | 메서드 | 뷰 경로 | 설명 |
| :-- | :-- | :-- | :-- | :-- |
| 목록 조회 | `/posts` | GET | `post/posts` | 페이징 처리(5개/P)된 목록 출력 |
| 상세 조회 | `/posts/{no}` | GET | `post/post_detail` | 게시글 내용 조회 및 조회수 증가 |
| 새 글 작성 폼 | `/posts/new` | GET | `post/post_new_form` | 작성 화면 이동 |
| 새 글 등록 | `/posts/add` | POST | N/A | 게시글 데이터 저장 후 목록 이동 |
| 수정 폼 | `/posts/{no}/edit` | GET | `post/post_edit_form` | 기존 데이터 로드 및 수정 화면 이동 |
| 수정 저장 | `/posts/{no}/save` | POST | N/A | 변경 정보 반영 후 상세 페이지 이동 |
| 삭제 처리 | `/posts/{no}/delete` | GET | N/A | 데이터 제거 후 목록 이동 |

### 4.2. 시스템 및 테스트 API
- **메인 홈**: `/` (GET, `home/home`)
- **테스트용 API**: `/api/hello` (GET) - "Hello, Vibe!" 반환

## 5. UI/UX 디자인 가이드라인
- **디자인 컨셉**: Samsung Premium Design (Modern, Minimal, Vibrant)
- **주요 요소**:
  - `full-rounded` 버튼 및 카드 레이아웃
  - `Premium Shadow` 및 `Backdrop Blur` 효과
  - HSL 미세 조정된 세련된 블루 포인트 컬러 (`#0376ff`)
  - 인터렉티브 호버 애니메이션 및 마이크로 인터렉션 반영
