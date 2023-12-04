$( document ).ready(function() {
    $.ajax({
        url:'http://localhost:8080/apitest',
        // url: "https://beautyinfo0.cafe24.com/api/v1/d/styles",
        type:'GET',
        //data: ,
        dataType:'json', // 리턴해주는 타입을 지정해줘야함
        beforeSend:function(xhr) {
            console.log("ajax호출전");
        },// 서버 요청 전 호출 되는 함수 return false; 일 경우 요청 중단
        success: function(data) {
			
			var mediaPaths = data.media_paths;
			
			var imageGrid = $('#image-grid');
			for(var i=0; i<mediaPaths.length; i++){
				var path = "https://beautyinfo0.cafe24.com/storages/"+mediaPaths[i];
				
				var gridItem = '<div class="grid-item"><img src="' + path + '" alt="이미지" class="grid-img"></div>';
        		 imageGrid.append(gridItem); // 테이블에 새로운 행 추가
				console.log(path);
			}
			console.log(data);
			console.log("호출성공");
            //console.log(str);
        },// 요청 완료 시
        error:function(jqXHR) {
            console.log("실패입니다.");
        }// 요청 실패.
    });
});
