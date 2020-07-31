# JA, DO 🍒

- 습관 등록 및 수행 여부 판단을 통한 습관 만들기 앱!



### 기능 명세

- 로그인
- 회원 가입
- 습관 등록
- 습관 수행
- 습관 별 일기 등록



### 프로젝트 환경 설정

- Tool: IntelliJ
- Github
- Spring Boot
  - Spring Security
  - Spring JPA
  - Lombok
- Unit
- H2 Database
- MySQL
  - 추후 사용 예정
- Swagger
- Push
  - FCM
- CI/CD
  - Cloud SourceCommit
  - SourceBuild
  - SourceDeploy



### 데이터베이스 설계

* User
  * user_seq
  * sns_type
  * token
  * nickname
  * habits
* Habit
  * habit_seq
  * title
  * duration
    * 30일, 60일...
  * do_day
    * "MONDAY;TUESDAY"
  * total_count
  * done_count
  * character_code
  * state
  * life
  * alarm_time
  * diaries
    * 습관 별 일기
  * user_seq (foreign_key: User)
  * create_date
* Diary
  * diary_seq
  * habit_seq (foreign Key: Habit)
  * content
  * create_date



### 프로젝트 진행 방법

1. 기능 구현
2. Swagger + Unit Test
3. Server Setting



### 앞으로의 진행

* FCM Push
* Bug fix
* CI/CD
* Unit Test(+ Integrity)
