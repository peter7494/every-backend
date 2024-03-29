## 프로젝트 배경

개발과 관련된 글을 작성할 수 있는 게시판 프로젝트입니다.

작성한 글을 프로그래밍 언어별로 찾아 볼 수 있습니다. 댓글 기능과 좋아요 기능이 있고

구독한 사용자의 글이 업로드되면 알람을 받을 수 있습니다.

## 프로젝트의 목표

간단한 게시판 프로젝트를 통해 공부한 내용을 체득하는 것이 목표입니다.

아래 내용들에 익숙해지기 위해 진행하게 되었습니다.

- 코틀린
    - 코틀린을 사용해 프로젝트를 진행하면서 자바와의 차이점을 느껴보고, 어떤 매력이 있어서 많은 개발자들이 자바에서 코틀린으로 넘어가고 있는지 알아보고자 합니다.
- 스프링 부트
- 스프링 시큐리티
- 카프카
    - 카프카의 메시지 기능을 사용해서 비동기 작업을 처리 기능을 구현합니다.
- 레디스
    - 자주 조회되는 정보를 캐싱하여 db의 부하를 줄여 성능 향상을 목표로 합니다.
- 헥사고날 아키텍처
- Jpa
- QueryDsl

## 구현해야 하는 기능

### 사용자 기능

- 이메일 회원가입 : 사용자는 이메일로 회원 가입을 할 수 있다.
- 소셜 회원가입 : 사용자는 지메일을 사용해서 회원가입을 할 수 있다.
- 이메일 사용자 인증 : 사용자는 회원가입 할 때 이메일을 등록하고 등록된 이메일로 받은 인증코드로 이메일 인증을 진행한다.
- 이메일 사용자 비밀번호 변경 : 사용자는 비밀번호를 잃어버렸을 때 이메일로 받은 인증코드로 비밀번호를 변경할 수 있다.
- 로그인 : 사용자는 가입한 정보로 로그인 할 수 있다. 인증은 jwt를 사용한다.
- 계정정보변경: 사용자는 자신의 계정 정보를 변경할 수 있다. (아이디 제외)
- 회원 탈퇴 : 사용자는 서비스 사용을 중지하기 위해 탈퇴를 할 수 있다. 단 탈퇴한 아이디는 30일 동안 재가입이 불가능하다.

### 게시판 기능

- 글 작성 : 사용자는 게시판에 글을 작성할 수 있고, 카테고리를 설정할 수 있다.
- 글 열람 : 사용자는 작성된 글을 최신 순으로 열람할 수 있고, 카테고리 별로 분류해서 볼 수 있다.
- 글 수정 : 사용자는 본인이 작성한 글을 수정할 수 있다.
- 글 삭제 : 사용자는 본인이 작성한 글을 삭제할 수 있다.

### 댓글 기능

- 댓글 작성 : 사용자는 게시글에 댓글을 작성할 수 있다.
- 댓글 수정 : 사용자는 본인이 작성한 댓글을 수정할 수 있다.
- 댓글 삭제 : 사용자는 본인이 작성한 댓글을 삭제할 수 있다.

### 좋아요 기능

- 좋아요 : 사용자는 마음에 드는 글에 좋아요를 표시할 수 있다.
- 좋아요 취소 : 사용자는 좋아요를 표시할 글의 좋아요를 취소할 수 있다.
- 좋아요 글 모아보기 : 사용자는 좋아요를 표시한 글을 모아서 볼 수 있다.

### 구독 기능

- 구독하기 : 사용자는 다른 사용자의 글을 구독할 수 있다.
- 구독취소 : 사용자는 구독하던 사용자의 글을 구독 취소 할 수 있다.
- 새글 알림 : 사용자의 글이 새로 작성되면 구독자들에게 알람을 전송한다.

### 랭킹 기능

- 카테고리별 추천 글 : 전날 조회수 + 좋아요 갯수를 기준으로 카테고리별 추천 글을 매일 아침 8시에 갱신해서 보여준다.
- 구독왕 : 구독자가 많은 사용자를 순서대로 보여준다.

## API

### 사용자 API

- POST /api/users : 사용자 회원 가입
- POST /api/users/email-verification : 이메일 인증 코드 전송
- POST /api/users/password-recovery : 비밀번호 복구 코드 전송
- PUT /api/users/password : 비밀번호 변경
- POST /api/users/login : 로그인
- PUT /api/users : 사용자 정보 수정
- DELETE /api/users : 사용자 계정 탈퇴

### 게시판 API

- POST /api/posts : 글 작성
- GET /api/posts : 글 목록 조회
- GET /api/posts/{postId} : 특정 글 조회
- PUT /api/posts/{postId} : 글 수정
- DELETE /api/posts/{postId} : 글 삭제

### 댓글 API

- POST /api/posts/{postId}/comments : 댓글 작성
- GET /api/posts/{postId}/comments : 게시물의 모든 댓글 조회
- GET /api/posts/{postId}/comments/{commentId} : 특정 댓글 조회
- PUT /api/posts/{postId}/comments/{commentId} : 댓글 수정
- DELETE /api/posts/{postId}/comments/{commentId} : 댓글 삭제

### 좋아요 API

- POST /api/posts/{postId}/likes : 글에 좋아요 처리
- DELETE /api/posts/{postId}/likes : 글에 좋아요 취소
- GET /api/users/likes : 사용자가 좋아요한 글 조회

### 구독 API

- POST /api/subscriptions/{userId} : 사용자 구독
- DELETE /api/subscriptions/{userId} : 사용자 구독 취소
- GET /api/subscriptions : 구독한 사용자의 새 글 알림

### 랭킹 API

- GET /api/rankings/posts : 카테고리별 추천 글 조회
- GET /api/rankings/users : 구독왕 조회

## ERD

![ERD](./erd.png)

### 커밋 규칙

- 태그
    1. **Feat:** 새로운 기능 추가할 때 사용합니다.
    2. **Fix:** 버그를 수정할 때 사용합니다.
    3. **Docs:** 문서를 수정할 때 사용합니다.
    4. **Style:** 코드의 스타일을 수정하거나 포맷을 변경할 때 사용합니다. (예: 들여쓰기 수정, 공백 제거)
    5. **Refactor:** 코드 리팩토링을 수행했을 때 사용합니다. (기능 변경 없이 코드 구조를 개선하는 경우)
    6. **Test:** 테스트 코드를 추가하거나 수정했을 때 사용합니다.
    7. **Chore:** 빌드 프로세스나 도구 관련 작업을 수행했을 때 사용합니다. (예: 패키지 매니저 설정 변경)