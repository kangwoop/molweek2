#  WEEK1
> Flow Week1 3분반
* 연락처를 열고 수정, 추가 등을 할 수 있습니다.  
* 귀여운 쿼카 사진을 볼 수 있는 갤러리 입니다.  
* 영어 단어장으로 자신이 암기하고 싶은 단어를 확인할 수 있습니다.  
  
  
![TAB](https://user-images.githubusercontent.com/108389017/177318423-0d593b7e-f03e-483c-88a3-0314d966cc7c.png)

***

### A. 개발 팀원    
* DGIST 기초학부 [오선무]  
* KAIST 전산학부 [박서영]
***

### B. 개발 환경  
* OS: Android (minSdk: 26, targetSdk: 32)  
* Language: Java  
* IDE: Android Studio  
* Target Device: Galaxy S7 SM-G930S
***

### C. 어플리케이션 소개  
### TAB 1 - 연락처    
![Contact](https://user-images.githubusercontent.com/108389017/177317400-e6c2b15f-8d8d-4b9a-8ac7-3812ee6e1293.png)

 
#### Major features   
* 상단에 위치한 “Show Contact” 버튼을 통해 연락처 권한을 받아오고 연락처 데이터를 보여줍니다.  
* 상단에 위치한 “ADD” 버튼을 통해 연락처를 추가할 수 있습니다.  
  * "ADD" 버튼을 누르면 추가할 연락처의 정보를 입력할 수 있습니다.  
* 각각의 연락처를 클릭하면 그 연락처의 상세 정보를 확인할 수 있습니다.
* 각각의 연락처를 길게 클릭하면 연락처의 상세 정보를 수정할 수 있습니다. 
***
#### 기술 설명  
* `ListView`를 통해 저장된 연락처 정보를 게시합니다.
* 연락처는 실제 핸드폰의 Contact data를 받아 게시합니다.
* 연락처는 실제 핸드폰의 Contact 앱에 접근하여 수정/추가/삭제 합니다.
***

### TAB 2 - Quokka Album
![Gallery](https://user-images.githubusercontent.com/108389017/177317736-0545b726-8e42-4c4c-97df-ec9e348a84c7.png) 
  
#### Major features   
* 귀여운 쿼카 사진들을 저장하는 갤러리 입니다.  
* 서로 다른 종류의 귀여운 쿼카들이 저장되어 있습니다.  
* 귀여운 쿼카 사진을 클릭하면 크게 확대해서 전체 이미지로 볼 수 있습니다.
***
#### 기술 설명  
* `GridView`를 이용하여 grid 형식으로 이미지를 배열했습니다.
* 이미지는 drawable폴더에 저장하여 정적으로 관리하였습니다.
* 이미지를 클릭하면 Intent객체를 생성하고, Mainctivity로부터 FullImageActivity로 context가 이동하여 전체화면 이미지를 뜨우게 됩니다.


***

### TAB 3 - 단어장(MyVoca)  
![Voca](https://user-images.githubusercontent.com/108389017/177317932-eace17b6-ed8f-492d-ab1d-07e74fe2e537.png)

#### Major features   
* 영어단어와 뜻을 외웠는지 확인할 수 있는 영어단어장 입니다.  
* 스와이프를 통해 다음 단어를 확인할 수 있고 화면을 클릭하면 영어 단어 <-> 뜻을 바꾸어서 화면에 보여줍니다.
* Tab3로 전환하면 생기는 메뉴 옵션에서 단어 추가,삭제, 그리고 저장하는 동작을 선택할 수 있습니다.
* 추가 기능의 경우 팝업을 띄워 영어단어와 뜻을 입력받은 후, 단어장의 맨 뒤에 추가합니다.
* 삭제 기능의 경우 현재 보고 있는 단어를 삭제하고 화면을 refresh합니다.
* 저장 기능의 경우 앱을 종료하여도 이전에 추가해놓은 단어들이 유지되도록 하는 기능입니다.
***

#### 기술 설명  
1. 영어 단어장 생성 
* JSON 형식으로 App의 로컬 데이터에 영어 단어와 뜻을 저장합니다.
* Tab3가 처음 선택되었을 때, 이 로컬 데이터에 접근하여 json 파일을 파싱한 뒤, Word 클래스의 객체들로 만들어진 ArrayList에 추가하는 작업이 실행됩니다.


2. 화면 넘기기 / 단어 반전하기
* ViewPager2를 이용하여 화면을 넘기는 것을 구현하였습니다.
    #### [ViewPager2 샘플 코드](https://github.com/android/views-widgets-samples/tree/master/ViewPager2)  
* TextView객체에 setText 메서드에 화면이 클릭될 떄 마다 다른 문자열이 전달되도록 하였습니다. (ViewHolderPage.java)

3. 메뉴 옵션
* Tab3에 해당하는 fragment에 setHsOptionsMenu함수로 menu를 활성화해줍니다. 이 경우 Tab3를 선택할 때에만 메뉴 기능이 활성화됩니다.
* Option discription
    * 단어 추가/삭제 함수를 ViewpagerApapter의 메서드로 추가하여, wordlist에 동적으로 관리한 후 notifyDataSetChanged로 refresh해주었고, 해당 작업에 대한 Toast 문구를 띄웠습니다.
    * Add - 현재 추가하고자 하는 Word객체를 wordlist의 맨 뒤에 추가하고, 사용자가 보는 화면의 마지막에 추가됩니다.
    * Delete - 현재 보고있는 view의 item을 얻어와 해당 단어를 삭제합니다.
    * Save - Word클래스로 관리하던 객체들을 Json객체로 변한한 뒤, 이를 다시 Jsonlist로 만들어 로컬 데이터로 json file에 write합니다.