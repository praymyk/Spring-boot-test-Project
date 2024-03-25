<h1> 스프링 부트 3.2.3 + Gradle + Intelij 환경 프로젝트 연습 22</h1>


<h3> 스프링 부트 환경에서 웹페이지 구현 연습 </h3> 
<p> pring Legacy + Maven 환경에서 웹페이지 구현을 진행한 만큼 스프링 부트 환경에서 프로젝트 진행을 해보기 위한 연습 <br>
  기본적인 CRUD 기능을 연습할수 있도록 H2 DB+JPA 환경에서 게시글 작성 기능 구현 목표
</p>
<p><img align="center" width="800" alt="image" src="https://raw.githubusercontent.com/praymyk/Spring-boot-test-Project/master/README/spring%20initializr.png" border-radius="20px"></p>

<br/>

<h2> 개발 주요 목표</h2>

<pre>
🟪 Spring Boot 3 이상의 환경에서 홈페이지 구현에 익숙해지기

🟪 Maven이 아닌 Grandle을 이용한 라이브러리 관리 경험

🟪 기본적인 CRUD 기능 구현 연습을 위한 간단한 페이지 구현

🟪 H2 DB, JPA를 이용 클라이언트 요청 > DTO > Controller > Entity > DB 과정 체험

🟪 InteliJ 터미널을 이용한 Git 관리 
</pre>

<br/>

<h2> 느낀점 </h2>
<p>
  ✔️ 초기 세팅이 편함!<br>
  <pre>
  1. Spring Initializr를 통해서 프로젝트를 생성하니 초기 의존성(Dependency) 설정이 편한 느낌이였다. <br>
  - 라이브러리를 추가로 등록하는 경우에도 Maven에 비해 gradle이 가독성이 좀더 좋았습니다.(버전 관리를 자동으로 해줘서 좋았다..) <br>
  2. WAS 내장으로 톰캣 서버를 셋팅하던 과정이 생략됨.</pre>
  <br>
  ✔️ Entity가 추가된 MVC패턴<br>
  <pre>
  1. 이전에는 클라인트 요청 > VO가공 > Dao(Repository) > DB 직행하는 구조로 프로젝트를 진행했었다. <br>
     스프링부트 연습과정에는 클라이언트 > DTO > Entity > Repository > DB 구조로 실습을 진행했는데 <br>
     DTO와 VO의 차이에 대해서 별도로 확인해볼 필요를 느꼈습니다. <br>
     또한, Entity와 DTO를 분리하지않고 그대로 View에 노출 시킬 경우 발생하는 문제에대해서 다시한번 돌아보게 됨.<br>
  2. Sprign Legacy + JDBC에는 Table을 생성한 상태에서 DB와 상호작용이 진행됐지만 JPA는 @Entity 어노테이션 클래스 생성시<br>
     내부적으로 CREATE TABLE 쿼리문이 작성됨을 확인.</pre>
  <br>
  ✔️ JPA Repository 기능 + 쿼리문 로그 기능?
  <pre>
  1. JPA의 CrudRepository 인터페이스에서 기본 CRUD 쿼리문을 메소드로 지원하는 것을 확인했습니다.<br>
  2. DB 테이블을 조인해서 조회할 필요가 있는경우 어떤식으로 쿼리문을 커스텀할수 있을지 여유시간에 좀더 살펴볼 예정!<br>
  3. jpa는 application.properties 에서 로깅 설정을 할 경우 intelij 내 로그에서 실행된 쿼리문을 확인하기 용이한걸 학습했습니다.<br>
     #JPA 로깅 설정
     #디버그 레벨 쿼리 출력
     - logging.level.org.hiernate.SQL=DEBUG
     #쿼리 로그 줄바꿈하기
     - spring.jpa.properties.hibernate.format_sql=ture
     #매개변수 값 보여 주기
     - logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
     오류발생시 문제 파악에 용이하기 때문에 다음 JDBC 환경에서도 동일한 기능이 있을지 체크해볼 예정.</pre>
</p>
