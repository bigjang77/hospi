 // DOM이 완전히 로드된 후 실행되는 코드
 document.addEventListener('DOMContentLoaded', () => {

    // 병원 찾기 버튼에 이벤트 리스너 추가
    document.querySelector("#btn-submit").addEventListener("click", (e) => {
      e.preventDefault(); // 새로고침 방지

      let sidoNm = document.querySelector("#sidoNm").value;
      let sgguNm = document.querySelector("#sgguNm").value;

      console.log(sidoNm);
      console.log(sgguNm);

      getHospital(sidoNm, sgguNm);
    });

    // 병원 정보 가져오기 함수
    let getHospital = async (sidoNm, sgguNm) => {
      let response = await fetch(`http://localhost:8000/api/hospital?sidoNm=${sidoNm}&sgguNm=${sgguNm}`);
      let responsePasing = await response.json();
      console.log(responsePasing);
      setHospital(responsePasing);
    };

    // 병원 정보를 테이블에 반영하는 함수
    let setHospital = (responsePasing) => {
      let tbodyHospitalDom = document.querySelector("#tbody-hospital");
      tbodyHospitalDom.innerHTML = '';  // 기존 내용 초기화

      responsePasing.forEach((e) => {
        let trEL = document.createElement("tr");
        let tdEL1 = document.createElement("td");
        let tdEL2 = document.createElement("td");

        tdEL1.innerHTML = e.yadmNm;
        tdEL2.innerHTML = e.addr;

        trEL.append(tdEL1);
        trEL.append(tdEL2);

        tbodyHospitalDom.append(trEL);
      });
    };

    // 구군 선택 드롭다운을 동적으로 설정하는 함수
    let setSgguNm = (responsePasing) => {
      let sgguNmDom = document.querySelector("#sgguNm");
      sgguNmDom.innerHTML = '';  // 기존 옵션 비우기

      responsePasing.forEach((e) => {
        let optionEL = document.createElement("option");
        optionEL.text = e;  // 구군 이름을 옵션에 설정
        sgguNmDom.append(optionEL);
      });
    };

    // 시도 선택에 따른 구군 목록 가져오기
    let getSgguNm = async (sidoNm) => {
      let response = await fetch(`http://localhost:8000/api/sgguNm?sidoNm=${sidoNm}`);
      let responsePasing = await response.json();
      setSgguNm(responsePasing);
    };

    // 시도 선택 드롭다운에서 값이 변경될 때마다 구군 목록을 가져옴
    let sidoNmDom = document.querySelector("#sidoNm");
    sidoNmDom.addEventListener("change", (e) => {
      let sidoNm = e.target.value;
      getSgguNm(sidoNm);
    });
  });