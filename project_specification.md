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
| 기능 | URL | 메서드 | 뷰 경로 | 주요 처리 로직 |
| :-- | :-- | :-- | :-- | :-- |
| 목록 조회 | `/posts` | GET | `post/posts` | 최신순 정렬 및 페이지 단위(5개) 데이터 반환 |
| 상세 조회 | `/posts/{no}` | GET | `post/post_detail` | 특정 게시글 검색 및 조회수(Views) 1 증가 |
| 작성 화면 | `/posts/new` | GET | `post/post_new_form` | 신규 게시글 작성을 위한 입력 폼 노출 |
| 게시글 등록 | `/posts/add` | POST | N/A | 신규 게시글 유효성 검증 및 데이터 저장소 저장 |
| 수정 화면 | `/posts/{no}/edit` | GET | `post/post_edit_form` | 기존 데이터 로드 및 수정 입력 인터페이스 제공 |
| 수정 반영 | `/posts/{no}/save` | POST | N/A | 변경된 제목 및 내용 업데이트 및 수정일시 기록 |
| 게시글 삭제 | `/posts/{no}/delete` | GET | N/A | 식별자(no) 기반 데이터 영구 제거 |

### 4.2. 공통 및 시스템 서비스
- **브랜드 홈**: `/` (GET, `home/home`) - 바이브 코딩 서비스 소개 및 서버 시간 출력
- **상태 확인 API**: `/api/hello` (GET) - 시스템 가동 상태 확인용 헬로 월드 응답

## 5. UI/UX 디자인 가이드라인
- **디자인 철학**: Samsung Premium Design Language (Simplicity, Modernity, Vibrancy)
- **핵심 UI 컴포넌트**:
  - **Cards**: `bg-white/80 backdrop-blur-md` 기반의 투명도 있는 프리미엄 카드
  - **Typography**: `Inter` 및 `Outfit` 폰트 기반의 가독성 높은 헤드라인
  - **Interaction**: 버튼 클릭 및 호버 시 `transition-all duration-300` 기반의 부드러운 스케일 애니메이션
  - **Color Palette**: `#0376ff` (Vibrant Blue), `#18181b` (Deep Charcoal), `#f4f4f5` (Zinc Silver)
