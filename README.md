#  WEEK2
> Flow Week2 3분반
* 전 세계의 여행 정보와 리뷰를 볼 수 있는 앱 입니다.  
* 정보를 얻고자 하는 국가의 명소를 선택하여 원하는 정보를 얻을 수 있습니다. 
* 다른 유저들이 남긴 리뷰와 별점을 볼 수 있고, 좋아요 수를 바탕으로 한 top5 관광지를 확인할 수 있습니다.
* 마이페이지에서 유저가 좋아요를 남긴 관광지 리스트와, 리뷰 리스트를 확인할 수 있습니다.


![Screenshot_20220712-211755](https://user-images.githubusercontent.com/108389320/178489021-16cc1eff-d92c-481f-abea-19171fccba55.jpg)

![Screenshot_20220712-211808](https://user-images.githubusercontent.com/108389320/178489035-fb279d5d-cbe3-4181-98ba-607839036770.jpg) 

![Screenshot_20220712-211819](https://user-images.githubusercontent.com/108389320/178489050-3937051d-2d5d-4d1d-a188-5b3502feaa68.jpg)

![Screenshot_20220712-211825](https://user-images.githubusercontent.com/108389320/178488947-ee6eab25-69f5-47f7-9592-74df5ef89c52.jpg)

![Screenshot_20220712-211831](https://user-images.githubusercontent.com/108389320/178488984-738856cb-273d-4a8f-ba8e-cd58a3dc3628.jpg)

![Screenshot_20220712-211836](https://user-images.githubusercontent.com/108389320/178488915-6b0f0237-23f9-4999-8fce-d340854a90a6.jpg)

![Screenshot_20220712-211840](https://user-images.githubusercontent.com/108389320/178488883-3e2ccf15-6879-4cad-ba1b-969573868ab5.jpg)

![Screenshot_20220712-211843](https://user-images.githubusercontent.com/108389320/178488844-a5508434-56d0-452a-a072-084960d9bb33.jpg)


***

### A. 개발 팀원    
* 한양대 컴퓨터 소프트웨어 학부 [박강우]  
* KAIST 전산학부 [박서영]
***

### B. 개발 환경  
* OS: Android (minSdk: 26, targetSdk: 32)  
* Language: Java  
* IDE: Android Studio  
* Target Device: Galaxy S7 SM-G930S
***

### C. 어플리케이션 소개  
### Frontend    
 
#### Major features   
1. 회원가입 및 로그인
    * 앱 자체 회원가입 화면에서는 아이디의 중복을 확인하고 valid한 입력의 회원등록을 진행합니다.
    * 로그인 화면에서는 카카오 로그인과 일반 로그인을 선택하여 로그인할 수 있습니다.

2. 메인 화면
    * 유저들이 좋아요한 Top5 관광지를 넘기며 확인할 수 있습니다.
    * 정보를 얻고싶은 대륙을 선택하여 해당 대륙의 국가 리스트로 이동할 수 있습니다.

3. 국가 및 명소 리스트
    * 대륙에 속한 국가 리스트에서 국가를 선택하면 국가에 속한 명소 리스트가 나오고, 이 리스트에서 관심있는 명소를 선택할 수 있습니다.
    * 명소 리스트에서는 각 명소의 이름, 사진, 유저의 좋아요 여부, 평균 별점을 확인할 수 있습니다.

4. 명소 화면
    * 명소의 세부 정보 화면에서 명소 이름, 명소의 위치, 위치에 따른 Google Map 뷰, 명소의 세부 정보, 유저들이 남긴 리뷰를 확인할 수 있습니다. 
    * 좋아요를 남기거나, +버튼을 눌러 리뷰와 별점을 추가할 수 있습니다.

5. 마이페이지
    * 홈과 명소 화면에서 마이페이지로 이동할 수 있는 버튼이 존재합니다.
    * 유저가 좋아요를 남긴 명소와, 리뷰를 남긴 내용을 각 탭에서 확인할 수 있습니다.
***
#### 기술 설명 
0. Client의 request방식
    * retrofit2를 이용하여 nodejs 서버에 POST 방식으로 request를 보내고, 돌아온 response를 해석합니다. 이와 관련된 기능을 retrofitAPI에 저장해놓았습니다.
    주로 request의 data type은 querystring, response의 data type은 Json 혹은 Json Array를 갖습니다.

1. 로그인 및 회원가입
    * 첫 화면에서 회원가입에서는 DB에 저장된 유저의 id와 비교하여 중복을 확인하고, 빈 입력을 확인하여 valid한 경우 DB에 가입 정보를 등록합니다.
    * 로그인 화면에서 등록되지 않은 id를 입력한 경우 invalid user, 비밀번호를 틀린 경우 wrong password 토스트 메세지를 띄우고, 로그인에 성공하는 순간 db에 저장된 데이터를 해싱하여 유저별 토큰을 발급합니다.

2. 메인 화면
    * 유저들의 Top5의 좋아요 수를 받은 명소들의 이미지와 이름을 viewpager를 이용해 띄웁니다.
    * 정보를 얻고싶은 대륙을 Gridview로 선택하게 하였습니다.

3. 국가 및 명소 리스트
    * RecyclerView를 이용하여 국가 리스트, 명소 리스트를 표기합니다.
    * 명소 리스트를 띄울 때, 모든 명소들의 이름을 DB에서 확인하여 이에 해당하는 평균 별점, 유저의 좋아요 여부를 response로 받습니다.

4. 명소 화면
    * 명소의 세부 정보 화면에서 Google Map SDK 를 이용하여 해당 명소의 지도 뷰를 띄웁니다. 
    * 좋아요를 남기거나, +버튼을 눌러 리뷰와 별점을 추가할 수 있습니다.

5. 마이페이지
    * 홈과 명소 화면에서 마이페이지 버튼을 클릭하면, Mypage Activity에서 좋아요와 리뷰 탭을 가진 Activity로 이동합니다.
    * 유저가 좋아요를 남긴 명소와, 리뷰를 남긴 내용을 얻기 위해 유저 토큰을 request로 보낸 결과 response를 이용합니다.


***
### Backend
 #### Major features  
* 여행지 정보, 또는 유저 정보를 저장하고 유저가 입력한 data를 저장하는 DB를 다루는 서버 입니다.
* DBMS는 SQL종류중 하나인 Mysql을 사용하였습니다.
* 서버 언어는 javascript를 사용하여 구현했습니다.
* 서버는 Nodejs의 library중 하나인 express-generater을 사용하여 구축하였습니다.
 
***
#### 기술 설명 
* 서버를 구축하는 과정에서 하나의 js파일에 전부 다하는 것이 아닌 크게 router,Controller, Service, query로 나눠서 구현하였습니다.
* router에서는 어떤 request를 받는지 확인하고 그에 맞는 Controller로 보냈습니다.
* Controller는 router에서 받은 request에 합당한 service의 function을 부르고 그 결과 값에 대해 response를 보냅니다.
* Service에서는 실제 우리가 response로 보내고자 하는 data를 query를 통해 DB에서 구해오고 그 값을 Controller에 보냅니다.
* Query에서는 현재 server에서 DB에 필요로하는 query문을 저장합니다.
* DB의 Table에는 나라를 저장하는 country, 장소 정보를 저장하는 places, user의 정보와 token을 저장하는 login, 유저가 관광지에 대해 좋아요를 눌렀을 때 추가되는 Favorite, 유저가 관광지에 대해서 review를 남겼을 때 추가되는 PostingList가 존재합니다.
* 여행지 정보에 대해서는 미리 DB Table에 저장하였습니다.
