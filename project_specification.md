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
- **프론트엔드 스타일**: Tailwind CSS (Premium Samsung Design Language 적용)
- **데이터 저장**: H2 Database (File Mode: `./data/testdb`)
- **영속성 프레임워크**: MyBatis 4.0.0 (SQL Mapper 연동)
- **주요 패턴 및 기능**:
  - **DTO 패턴 (Java Record)**: Layer 간 데이터 전송을 위해 간결하고 불변성을 보장하는 Java `record` 타입으로 DTO 정의
  - **트랜잭션 관리 (Transaction Management)**: `@Transactional`을 통한 게시글-태그 등록/수정의 원자성 보장
  - **Bean Validation**: 입력값 유효성 검증 (제목 필수, 최대 100자 제한)
  - **로깅 시스템**: 파일 기반 로깅 (`./logs/app.log`) 및 MyBatis 파라미터 로깅 활성화

## 3. 패키지 및 디렉토리 구조 상세
### 3.1. 소스 코드 (`src/main/java`)
- `com.example.vibeapp`
  - `VibeApp.java`: 스프링 부트 애플리케이션 진입점
- `com.example.vibeapp.home`
  - `HomeController.java`: 메인 페이지 컨트롤러
- `com.example.vibeapp.post`
  - `Post.java`: 게시글 엔티티
  - `PostTag.java`: 게시글 태그 엔티티
  - `PostController.java`: 게시판 CRUD 웹 요청 처리
  - `PostService.java`: 비즈니스 로직, 페이징 및 태그 처리 (트랜잭션 적용)
  - `PostRepository.java`: MyBatis Mapper 인터페이스 (게시글)
  - `PostTagRepository.java`: MyBatis Mapper 인터페이스 (태그)

### 3.2. 뷰 템플릿 (`src/main/resources/templates`)
- `home/home.html`: 메인 대시보드 및 서비스 소개
- `post/`
  - `posts.html`: 게시글 목록 (5개 단위 페이징)
  - `post_detail.html`: 게시글 상세 조회 (조회수 증가, 수정/삭제 링크)
  - `post_new_form.html`: 새 게시글 작성 폼
  - `post_edit_form.html`: 기존 게시글 수정 폼

## 4. 주요 기능 및 엔드포인트
#### 3.2.1. 테이블 설계: `POSTS`
이 테이블은 게시글의 핵심 데이터를 저장합니다.

| 컬럼명 | 데이터 타입 | 제약 조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `no` | `BIGINT` | `PK`, `AUTO_INCREMENT` | 게시글 고유 번호 |
| `title` | `VARCHAR(200)` | `NOT NULL` | 게시글 제목 (최대 200자) |
| `content` | `CLOB` | `NOT NULL` | 게시글 내용 (최대 10MB) |
| `created_at` | `TIMESTAMP` | `DEFAULT CURRENT_TIMESTAMP` | 최초 작성 일시 |
| `updated_at` | `TIMESTAMP` | `DEFAULT NULL` | 최종 수정 일시 |
| `views` | `INT` | `DEFAULT 0` | 조회수 |

#### 3.2.2. 테이블 설계: `POST_TAGS`
게시글별 태그 정보를 저장합니다.

| 컬럼명 | 데이터 타입 | 제약 조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `id` | `BIGINT` | `PK`, `AUTO_INCREMENT` | 태그 고유 ID |
| `post_no` | `BIGINT` | `FK (POSTS.no)`, `NOT NULL` | 게시글 번호 |
| `tag_name` | `VARCHAR(50)` | `NOT NULL` | 태그 이름 (최대 50자) |

### 4.1. 게시판 기능 (Board Features)
| 기능 | URL | 메서드 | 뷰 경로 | 주요 처리 로직 |
| :-- | :-- | :-- | :-- | :-- |
| 목록 조회 | `/posts` | GET | `post/posts` | 최신순 정렬 및 페이지 단위(5개) 데이터 반환 |
| 상세 조회 | `/posts/{no}` | GET | `post/post_detail` | 특정 게시글 검색, 태그 목록 로드 및 조회수(Views) 1 증가 |
| 작성 화면 | `/posts/new` | GET | `post/post_new_form` | 신규 게시글 및 태그 입력을 위한 폼 노출 |
| 게시글 등록 | `/posts/add` | POST | N/A | 게시글 및 태그 유효성 검증 후 트랜잭션 내 일괄 저장 |
| 수정 화면 | `/posts/{no}/edit` | GET | `post/post_edit_form` | 기존 데이터 및 태그 로드 (쉼표 구분 형식 변환) |
| 수정 반영 | `/posts/{no}/save` | POST | N/A | 게시글 업데이트 및 태그 교체(삭제 후 재등록) 일괄 트랜잭션 처리 |
| 게시글 삭제 | `/posts/{no}/delete` | GET | N/A | 식별자(no) 기반 데이터 및 연관 태그(Cascade) 영구 제거 |

### 4.2. 공통 및 시스템 서비스
- **브랜드 홈**: `/` (GET, `home/home`) - 바이브 코딩 서비스 소개 및 서버 시간 출력
- **상태 확인 API**: `/api/hello` (GET) - 시스템 가동 상태 확인용 헬로 월드 응답

## 5. UI/UX 디자인 가이드라인
- **디자인 철학**: Samsung Premium Design Language (Simplicity, Modernity, Vibrancy)
- **핵심 UI 컴포넌트**:
  - **Cards**: `bg-white/80 backdrop-blur-md` 기반의 투명도 있는 프리미엄 카드
  - **Typography**: `Inter` 및 `Outfit` 폰트 기반의 가독성 높은 헤드라인
  - **Interaction**: 버튼 클릭 및 호버 시 `transition-all duration-300` 기반의 부드러운 스케일 애니메이션
  - **Tags**: 상세 페이지 내 Chip 형태의 태그 전시 및 `#` 프리픽스 적용
  - **Color Palette**: `#0376ff` (Vibrant Blue), `#18181b` (Deep Charcoal), `#f4f4f5` (Zinc Silver)
