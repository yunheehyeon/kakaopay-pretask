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

* 기능 명세는 프로젝트 실행시 /docs/supportRule.html 경로

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

## SupportRule
### 내부 파일 읽기
~~~
GET /api/fileread HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/json;charset=UTF-8
Host: localhost:51980
~~~
###지원하는 지자체 목록 검색


| 변수 | 설명 | 값 |
|:--------|:--------:|--------:|
| page | 페이지 번호 | 기본값 = 10, 최대값 = 50 |
| size | 요청 개수 | 내용 23 |
| direction | 정렬 기준(region_id) | 기본값 = ASC |

Request
~~~
GET /api/support?page=1&size=3&direction=ASC HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/json;charset=UTF-8
Host: localhost:51980
~~~

Response
~~~
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 12:48:26 GMT
Content-Length: 964

[ {
  "region" : "강릉시",
  "target" : "강릉시 소재 중소기업으로서 강릉시장이 추천한 자",
  "usage" : "운전",
  "limit" : "추천금액 이내",
  "rate" : "3%",
  "institute" : "강릉시",
  "mgmt" : "강릉지점",
  "reception" : "강릉시 소재 영업점"
}, {
  "region" : "강원도",
  "target" : "강원도 소재 중소기업으로서 강원도지사가 추천한 자",
  "usage" : "운전",
  "limit" : "8억원 이내",
  "rate" : "3%~5%",
  "institute" : "강원도",
  "mgmt" : "춘천지점",
  "reception" : "강원도 소재 영업점"
}, {
  "region" : "거제시",
  "target" : "거재시 소재 중소기업(소상공인 포함)으로서 거제시장이 추천한자",
  "usage" : "운전",
  "limit" : "3억원 이내",
  "rate" : "2.5%~5.0%",
  "institute" : "거제시, 경남신용보증재단",
  "mgmt" : "거제지점",
  "reception" : "거제시, 통영시에 소재영업점"
} ]
~~~
### 지자체명을 입력 받아 해당 지자체의 지원정보를 출력

Request
~~~
POST /api/support/ HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/json;charset=UTF-8
Content-Length: 30
Host: localhost:51980

{
  "region" : "성남시"
}
~~~

Response
~~~
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 12:48:25 GMT
Content-Length: 310

{
  "region" : "성남시",
  "target" : "성남시 소재 중소기업으로서 성남시장의 추천을 받은 자",
  "usage" : "운전 및 시설",
  "limit" : "5억원 이내",
  "rate" : "1.80%",
  "institute" : "성남시",
  "mgmt" : "성남하이테크지",
  "reception" : "전 영업점"
}
~~~
### 지원하는 지자체 정보 수정 기능

Request
~~~
PUT /api/support/ HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/json;charset=UTF-8
Content-Length: 298
Host: localhost:51980

{
  "region" : "성남시",
  "target" : "성남시 소재 중소기업으로서 성남시장의 추천을 받은 자",
  "usage" : "정보 변경",
  "limit" : "5억원 이내",
  "rate" : "1.80%",
  "institute" : "성남시",
  "mgmt" : "정보 변경",
  "reception" : "전 영업점"
}
~~~

Response
~~~
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 12:48:25 GMT
Content-Length: 298

{
  "region" : "성남시",
  "target" : "성남시 소재 중소기업으로서 성남시장의 추천을 받은 자",
  "usage" : "정보 변경",
  "limit" : "5억원 이내",
  "rate" : "1.80%",
  "institute" : "성남시",
  "mgmt" : "정보 변경",
  "reception" : "전 영업점"
}
~~~

### 지원한도 컬럼에서 지원금액으로 내림차순 정렬

Request
~~~
POST /api/support/limit/order HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/json;charset=UTF-8
Content-Length: 18
Host: localhost:51980

{
  "size" : 5
}
~~~
Response
~~~
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 12:48:25 GMT
Content-Length: 1612

[ {
  "region" : "한국장애인고용공단",
  "target" : "한국장애인고용공단의 추천을 받은 자",
  "usage" : "시설",
  "limit" : "추천금액 이내",
  "rate" : "4.00%",
  "institute" : "한국장애인고용공단",
  "mgmt" : "여신기획부",
  "reception" : "전영업점"
}, {
  "region" : "근로복지공단",
  "target" : "근로복지공단으로부터 “고용환경개선” 용도로 자금추천을 받은 자",
  "usage" : "시설",
  "limit" : "추천금액 이내",
  "rate" : "4.00%",
  "institute" : "근로복지공단",
  "mgmt" : "여신기획부",
  "reception" : "전영업점"
}, {
  "region" : "농림축산식품부",
  "target" : "농림수산식품부장관의 융자추천을 받은 기업",
  "usage" : "운전",
  "limit" : "추천금액 이내",
  "rate" : "3.53%",
  "institute" : "농림수산식품부",
  "mgmt" : "여신기획부",
  "reception" : "전영업점"
}, {
  "region" : "강릉시",
  "target" : "강릉시 소재 중소기업으로서 강릉시장이 추천한 자",
  "usage" : "운전",
  "limit" : "추천금액 이내",
  "rate" : "3%",
  "institute" : "강릉시",
  "mgmt" : "강릉지점",
  "reception" : "강릉시 소재 영업점"
}, {
  "region" : "한국방송통신전파진흥원",
  "target" : "한국방송통신전파진흥원으로부터 추천을 받은 기업",
  "usage" : "운전 및 시설",
  "limit" : "추천금액 이내",
  "rate" : "1.5%~3.86%",
  "institute" : "한국방송통신전파진흥원",
  "mgmt" : "여신기획부",
  "reception" : "전영업점"
} ]

~~~

### 이차보전 컬럼에서 보전 비율이 가장 작은 지차제 조회

Request
~~~
GET /api/support/minRate HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/json;charset=UTF-8
Host: localhost:51980
~~~
Response
~~~
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 12:48:26 GMT
Content-Length: 299

{
  "region" : "금천구",
  "target" : "금천구 소재 중소기업으로서 금천구청장이 추천한 자",
  "usage" : "운전",
  "limit" : "2억원 이내",
  "rate" : "0.1%~3.5%",
  "institute" : "금천구",
  "mgmt" : "가산디지털지점",
  "reception" : "전 영업점"
}
~~~