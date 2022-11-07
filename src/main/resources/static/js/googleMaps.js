window.initMap = function () {
    const map = new google.maps.Map(document.getElementById("map"), {
      center: { lat: 37.5400456, lng: 126.9921017 },
      zoom: 10,
    });
  
    const librarys = [ // 도서관들의 위치 및 정보 배열
      { label: "L", 
        name: "<h1>2.28도서관</h1><hr><div>대구광역시 중구 2·28길 9</div><div>053-257-2280</div>", 
        lat: 35.8592504,
        lng: 128.5894055 },
      { label: "L", 
        name: "<h1>U보라작은도서관</h1><hr><div>경상남도 김해시 전하로176번길 71, 반도보라아파트 주민공동시설 2층</div><div>055-324-3335</div>",
        lat: 35.223558,
        lng: 128.874226 },
      { label: "L", 
        name: "<h1>가락몰도서관</h1><hr><div>서울특별시 송파구 양재대로 932</div><div>02-3435-0950</div>",
        lat: 37.492994,
        lng: 127.112326 },
      { label: "L", 
        name: "<h1>가수원도서관</h1><hr><div>대전광역시 서구 가수원로 91-11</div><div>042-288-4770</div>",
        lat: 36.3019,
        lng: 127.348812 },
      { label: "L", 
        name: "<h1>가슴따뜻한작은도서관</h1><hr><div>서울특별시 서대문구 응암로1길 10</div><div>02-330-8652</div>",
        lat: 37.5814481,
        lng: 126.9111617 },
      { label: "L", 
        name: "<h1>가오도서관</h1><hr><div>대전광역시 동구 동구청로 147</div><div>042-259-7051</div>",
        lat: 36.3124314,
        lng: 127.4553237 },
      { label: "L", 
        name: "<h1>가온누리작은도서관</h1><hr><div>울산광역시 북구 신천소공원로 15, 108동 1층</div><div>052-292-2774</div>",
        lat: 35.6454614,
        lng: 129.346506 },
      // 데이터 데이스에 라벨 = name, name = 도서관 이름 , 위도 와 경도는 데이터 베이스에서 가져오는 형식 (도서 정보 나루 api활용)
    ];

    const bounds = new google.maps.LatLngBounds();
    const infowindow = new google.maps.InfoWindow();

    librarys.forEach(({ label, name, lat, lng }) => { // 마커 보여주기
      const marker = new google.maps.Marker({
        position: { lat, lng },
        label,
        map: map,
      });
      bounds.extend(marker.position);

      marker.addListener("click", () => { // 마커를 클릭하면 name 보여주기
        map.panTo(marker.position);
        infowindow.setContent(name);
        infowindow.open({
          anchor: marker,
          map,
        });
      });
    });

    map.fitBounds(bounds); // 마커가 생긴곳으로 줌인
  };