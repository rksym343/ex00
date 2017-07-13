<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#dropBox{
		width: 400px;
		height : 300px;
		border : 1px solid gray;
		overflow : auto;
	}
	img{
		width : 100px;
		height : 130px;
	}
	#result{
		width : 400px;
		height : 300px;
		border : 1px solid red;
		overflow : auto;
	}
	
	#result .item{
		width : 100px;
		margin : 3px;
		float : left;
		position: relative;
	}
	
	#result button.del{
		position: absolute;
		top : 10px;
		right : 10px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body>

	<form id="f1" action="dragUpload" method="post" enctype="multipart/form-data">
		<input type="text" name="writer" placeholder="작성자">
		<input type="submit" value="제출">
	</form>
	
	<div id="dropBox">
		
	</div>
	
	
	<div id="result">
		
	</div>
	
	<script type="text/javascript">
	
		var formData = new FormData(); // form의 정보랑 지금 이하 드래그한 파일들 정보랑 다 같이 담아서 보낼 수 있는 것
		
		
	// 드래그드롭시 자동으로 해당 이미지 경로로 링크 되는 것을 막습니다.
		$("#dropBox").on("dragenter dragover", function(event) {
			event.preventDefault();
			// 브라우저에서 파일을 끌어다 놓아도 아무런 동작을 하지 않게 처리함.
		})
		
		$("#dropBox").on("drop", function(event) {
			event.preventDefault();
			// 브라우저에서 파일을 끌어다 놓아도 아무런 동작을 하지 않게 처리함.
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			console.log(file);
			
			var reader = new FileReader();
			// load 이벤트가 발생이되면 FileReader 객체가 실행되면서 , 파일안의 데이터를 읽어서 반환한다.
			reader.addEventListener("load", function(e) {
				// 이미지 큰 것들은 load 이벤트 걸어야 함.... 작은 것은 상관없으나...
				// 이 펑션은 load 끝나면 실행된다
				var imgObj = $("<img>");
				imgObj.attr("src", reader.result);
				
				$("#dropBox").append(imgObj);
			}, false); // 여기서 false는 이벤트 부모먼저 실행? 자식먼저 실행? 을 정하는 것?!?!?
					
			if(file){
				reader.readAsDataURL(file); // reader의 load이벤트를 발생시킴
			}		
			if(formData == null){
				formData = new FormData();
			}
			formData.append("files", file); // files에 파일들을 자꾸자꾸자꾸자꾸 추가함.. 받는 곳에서는 List로 받을 수 있다.
			
		})
		
		$("#f1").submit(function(event) {
			event.preventDefault();
			
			var writer = $("input[name='writer']").val();
			
			formData.append("writer", writer); // key, value
			
			$.ajax({
				url: "dragDropUpload",
				type: "post",
				processData:false,
				contentType: false,
				
				/* 
				contentType을 false를 해줌으로 써 브라우저로 하여금 FormData를 사용하여 전송 시 
				자동으로 content-Type을 multipart/formdata로 세팅하고
				correct boundary를 붙여 데이터를 보낼 수 있게 해줍니다.

				
				ProcessData의 경우도 마찬가지로 data를 serialize를 하여 Query String으로 변경하기 때문에 
				이를 막기 위해 false로 세팅해 줍니다.
				(ProcessData : data를 query string의 형태로 변경시키는 옵션. default 값은 true)
				
				http://hellowk1.blogspot.kr/2015/07/formdata-file-submit-with-formdata.html
				 
				*/
				
				data: formData,
				success:function(data){
					console.log(data);
					console.log("---------------");
					$(data).each(function(i, obj) {
						console.log(obj);
						
					// 1. 사진 삭제 합시다. / 사진 위에 x 표시 뜨도록 css 처리 합니다~
						
						var imgDiv = $("<div>").addClass("item"); // 이미지(imgObj)와 버튼(btnImgDel)이 들어갈 태그
						
						
						var imgObj = $("<img>");
						imgObj.attr("src", "displayFile?filename="+obj);
						$(imgDiv).append(imgObj);
						
						var btnImgDel = $("<button>").addClass("del").text("X");
						btnImgDel.attr("data-src", obj); //filename을 삭제시 쉽게 뽑기 위해 처리...
						
						$(imgDiv).append(btnImgDel);
						
						$("#result").append(imgDiv);
					});
				}
			})
		});
	
	
		$(document).on("click", "button.del", function() {
			var filename = $(this).attr("data-src");
			console.log(filename);
			
			$.ajax({
				url : "deleteFile",
				type : "post",
				data :{filename : filename},
				dataType : "text", // 수행 후 돌아오는 데이터 타입.. 여기서는 "SUCCESS" ,"FAIL"
				success : function (data) {
					
					if(data == "SUCCESS"){
						$(".item").each(function(i, obj) {
							if($(obj).find("img").attr("data-src") == data){
								alert(i);
								$(this).remove();
							}
						})
					}
				}
				
			});
		});
	</script>

</body>
</html>