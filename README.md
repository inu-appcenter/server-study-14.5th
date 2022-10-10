# AppCenter server study 14.5기
> Since 2022.10.10

## 👨‍💻 운영진

<p>
    <a href="https://github.com/nahyeon99">
      <img src="https://avatars.githubusercontent.com/u/69833665?v=4" width="100">
    </a>
    <a href="https://github.com/gs97ahn">
      <img src="https://avatars.githubusercontent.com/u/84578465?v=4" width="100">
    </a>
</p>

## 👨‍💻  스터디원

<p>
    <a href="https://github.com/Juser0">
      <img src="https://avatars.githubusercontent.com/u/108407945?v=4" width="100">
    </a>
    <a href="https://github.com/jd-hyun">
      <img src="https://avatars.githubusercontent.com/u/98350310?v=4" width="100">
    </a>
    <a href="https://github.com/eunki96">
      <img src="https://avatars.githubusercontent.com/u/114793764?v=4" width="100">
    </a>
    <a href="https://github.com/dbswl067">
      <img src="https://avatars.githubusercontent.com/u/76262688?v=4" width="100">
    </a>
</p>

---

## 📝 규칙

- `커밋 컨벤션`

    - Feat: 새로운 기능 추가
    - Fix: 버그 수정
    - Docs: 문서 수정
    - Style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    - Refactor: 코드 리팩토링
    - Test: 테스트 코드, 리팩토링 테스트 코드 추가
    - Chore: 빌드 업무 수정, 패키지 매니저 수정
<br><br>
      
- `issue 규칙`
    - 참고: [https://velog.io/@junh0328/협업을-위한-깃허브-이슈-작성하기](https://velog.io/@junh0328/%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-%EA%B9%83%ED%97%88%EB%B8%8C-%EC%9D%B4%EC%8A%88-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0)
    - 레이블 참고:
      [https://github.com/modolee/github-initial-settings](https://github.com/modolee/github-initial-settings)
    - 제목 참고: [https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)
      <br><br>
    - 템플릿
        - issue 제목
            - 예시: **[Feat] 이슈 정리**
        - issue 템플릿

            ```markdown
            ## 📋 이슈 내용
            
            ## ✅ 체크리스트
            
            ## 📚 레퍼런스
            
            ```
        - 제목 예시
            - [Add] UI button 구현
    <br><br>
- `branch 규칙`
    - 각자의 영어 이름을 딴 branch 명을 사용한다.
    - 예시: 
    ```
  git checkout -b <브랜치명>      
  git checkout -b nahyeon
    ```
    
- `commit message 규칙`
    - 참고: [https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)
    - [종류] 메시지 - #이슈번호
    - 예시
        - [Feat] todo-list 회원 API 엔티티 구현 - #2
        - [Fix] todo-list 회원 단건 조회 서비스 에러 수정 - #2
    <br><br>
- `PR 규칙`
    - PR 템플릿

        ```markdown
        ## 📋 이슈 번호
        
        ## 🛠 구현 사항
        
        ## 📚 기타
        
        ```

---

## 📚 스터디 주제

### todo-list ERD 설계 및 DB 연동
- ##### 2주차 2022-10-17 (월) - 2022-10-23 (일)

1. `todo-list 프로젝트 시작`
    1. tool: ERD cloud 등
2. JDBC, MySQL를 이용한 DB 연동
3. JPA를 활용하기 전 DB(repository) 작성
4. JPA를 활용한 DB(repository) 작성
5. Entity 작성

---

### todo-list API 명세서 작성 / 로직 구현
- ##### 3,4주차 2022-10-24 (월) - 2022-11-06 (일)

1. todo-list API 명세서 작성<br>
    a. 문서 작성 (예시: 노션)<br>
    b. swagger 띄우기<br>
2. service 구현
3. controller 구현
4. 구현한 서버 시현 <br>
    a. swagger <br>
    b. postman <br>
    c. 그 외 <br>
   
---

### todo-list Login API 구현
- ##### 5,6주차 2022-11-07 (월) - 2022-11-20 (일)

`5주차` <br>
- session, cookie의 특징과 차이
- Access Token을 활용한 인증(JWT)
- Oauth 원리와 과정
- spring security + jwt

`6주차` <br>
- Login API 명세서
- Login Entity, Repository
- Login Service
- Login Controller
- Login API 시현

---

### todo-list Photo API 구현
- ##### 7주차 2022-11-21 (월) - 2022-11-27 (일)

- local storage, aws S3 등 원하는 방식으로 구현

`required`
- 회원 프로필 사진 넣기

`optional`
- todo 마다 사진 넣기
- 그 외 하고 싶은 기능 추가

---

### 게시판 프로젝트 구현
- ##### 8,9주차 2022-11-28 (월) - 2022-12-11 (일)

- `구현 기능`
    - 회원 가입
    - 회원 수정
    - 회원 탈퇴
    - 회원 조회
    - 카테고리 생성
    - 카테고리 삭제
    - 카테고리 리스트 조회
    - 게시글 생성
    - 게시글 수정
    - 게시글 삭제
    - 게시글 ID 조회
    - 카테고리 ID로 게시글 리스트 조회
    - 댓글 생성
    - 댓글 수정
    - 댓글 삭제

---