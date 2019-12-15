#  지자체 협약 지원 API 개발 

### 개발 환경
* IDE : IntelliJ  
* java 버전 : 10
* framework : spring boot

### 기능 정리
* 데이터 파일에서 각 레코드를 데이터베이스에 저장
* 지원하는 지자체 목록 검색 (all?)
* 지원하는 지자체명 입력 >> 지자체의 지원정보를 출력 (하나)
* 지원하는 지자체 정보 수정 
* 출력 개수 입력 >> 지자체명 출력 (k 개)
    * 지원한도 내림차순, 동일하면 이차보전 평균 비율이 적은 순서
* 이차보전 컬럼에서 보전 비율이 가장 작은 기관명을 출력(하나)
    
### 문제 및 해결
* 목록 검색을 데이터가 많을 경우를 생각해서 page로 조회하도록 개발

* 수정 요청은 수정할 정보 외에 바뀌지 않은 모든 정보가 필요

* 지원금액 정렬 '추천금액 이내'가 가장 큰 값, 이차보전 평균 비율도 큰값으로 정렬

* Rest Docs 결과 인코딩 -> MediaType.APPLICATION_JSON_UTF8 해더추가 (하지만 해당 해더가 더 이상 사용되지 않음)

* Rest Docs 요청 응답만 자동화, 표 만들기는 수기로 작성

* 파일을 외부에서 받지 못함, 내부에 파일만 읽음

### 빌드 및 실행방법

~~~
프로젝트 클론
- git clone -b master https://github.com/yunheehyeon/kakaopay-pretask.git

프로젝트 폴더 이동
- cd kakaopay-pretask

빌드
- bash ./gradlew clean build

빌드 파일 있는 곳으로 이동
- cd build/libs

실행
java -jar (빌드 파일 이름)
~~~

[API 기능명세] (support.html)