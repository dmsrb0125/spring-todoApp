# Todo APP 만들기
회원가입, 로그인 기능이 있는 투두앱 백엔드 서버 만들기

## 학습 목표
1. 회원가입, 로그인을 구현할 수 있어요.
2. 인증/인가를 이해하고 JWT를 활용하여 할일 및 댓글을 처리할 수 있어요.
3. JPA 연관관계를 이해하고 회원과 할일 그리고 댓글을 구현할 수 있어요.
4. 할일을 완료처리하며 상태를 관리 할 수 있어요.

## 설계와 ERD

### 설계 구조
- entity
  - User 
  - Todo 
  - Comment
  - UserRoleEnum 

- controller
  - HomeController 
  - UserController 
  - TodoController 
  - CommentController
  
- service
  - UserService
  - TodoService
  - CommentService

- repository
  - UserRepository
  - TodoRepository
  - CommentRepository
  
- dto
  - LoginRequestDto
  - SignupRequestDto
  - TodoRequestDto 
  - CommentRequestDto
  
### 인증 방식
- jwt
- security


### ERD Diagram
![ERD](src/main/resources/static/images/erd.png)
