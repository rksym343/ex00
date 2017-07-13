<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#modDiv{
		width : 300px;
		height : 100px;
		background-color: gray;
		position : absolute;
		top: 50%;
		left : 50%;
		padding : 10px;
		margin-top: -100px;
		margin-left : -200px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	function getList(){
		var bno = $("input[name='board_bno']").val();
		$.ajax({
			url: "replies/all/"+bno, //서버 주소
			type: "get" , //method
			dataType: "json", // 받아올 data 타입
			success: function(data) {
				console.log(data);
				var str= "";
				
				$(data).each(function(i, obj) {
					/* str = obj.rno + " : " + obj.replyer + " : " + obj.replytext;
					var pTag = $("<p>");
					var btn = $("<button>");
					btn.html("삭제");
					btn.addClass("btnDel");
					pTag.html(str);
					btn.val(obj.rno);
					pTag.append(btn);
					$("#result").append(pTag); */
					
					str += obj.rno + " : " + obj.replyer + " : " + obj.replytext
						+ "<button class='btnDel' data-rno='"+obj.rno+"'>삭제</button>"
						+ "<button class='btnModal' data-rno='"+obj.rno+"' data-text='"+obj.replytext+"'>수정</button>"
						+ "<br>"; 
				})
				$("#result").html(str);
				
			}
		})
	}	// getList
	
	function getListPage(page){
		// /{bno}/{page}
		//var bno = $("input[name='board_bno']").val();
		$.ajax({
			url : "replies/"+"900"+"/"+page,
			type :"get",
			dataType : "json",
			success : function(data) {
				console.log(data);
				var str = "";
				$(data.list).each(function(i, obj) {
					str += obj.rno + " : " + obj.replyer + " : " + obj.replytext
					+ "<button class='btnDel' data-rno='"+obj.rno+"'>삭제</button>"
					+ "<button class='btnModal' data-rno='"+obj.rno+"' data-text='"+obj.replytext+"'>수정</button>"
					+ "<br>"; 
				})
				$("#result").html(str);
				
				var str2 = "";
				for(var i = data.pageMaker.startPage; i < data.pageMaker.endPage+1; i++){
					str2 += "<a href='"+i+"'>"+i+"</a>     ";
					
				}
				$("#pageNation").html(str2);
				
			}
		})
	} // getListPage

	$(function() {
		
		$(document).on("click", "#pageNation a", function(event) {
			event.preventDefault(); // a tag 전송 막기
			
			var page = $(this).attr("href");
			//alert(page);
			getListPage(page);
		})
		
		$("#btnList").click(function() {
			// getList();
			 getListPage(1);
		});
		
		$("#btnAdd").click(function() {
			var bno_data = $("input[name='bno']").val();
			var replyer_data = $("input[name='replyer']").val();
			var replytext_data = $("input[name='replytext']").val();
			var sendData = {bno:bno_data, replyer:replyer_data, replytext:replytext_data};
			
			$.ajax({
				url:"replies",
				type:"post",
				dataType:"text",
				data: JSON.stringify(sendData),
				headers :{"Content-Type":"application/json"},
				success: function(data) {
					// 1. controller에 @RequstBody
					// 2. JSON.stringify 스트링으로 변환해야 잘간다(그냥 바로 보내면 제대로 안간다..)... server에는 RequestBody여야~
					// 3.headers :{"Content-Type":"application/json"} 
					// 이렇게 쌍을 맞추어야 잘 갑니다
					if(data == "SUCCESS"){
						alert("추가되었습니다.");
					}
					getList();
				}
			})
		});
		
		$(document).on("click", ".btnDel", function() {
			//alert("==");
			//var rno_data = $(this).val();
			var rno_data = $(this).attr("data-rno");
			
			$.ajax({
				url:"replies/"+rno_data,
				type:"delete",
				dataType:"text",
				data: JSON.stringify({rno:rno_data}),
				headers :{"Content-Type":"application/json"},
				success: function(data) {
					if(data == "SUCCESS"){
						alert("삭제되었습니다.");
					}
					$("#result").empty();
					getList();
				}
			})
		})	//btnDel 동작
		
		$(document).on("click", ".btnModal", function() {
			var rno_data = $(this).attr("data-rno");
			var text_data = $(this).attr("data-text");
			$(".modal-title").html(rno_data);
			$("#replytext").val(text_data);
			$("#modDiv").show(1000);
		}) // btnModal 동작
		
		
		$("#btnMod").click(function() {
			// btnMod는 동적추가가 아니라 안 보일뿐 이미 생성되어 있으므로 $(document) 할 필요 없음
			var rno_data = $(".modal-title").html();
			var replytext_data = $("#replytext").val();
			var sendData = {replytext:replytext_data};
			
			$.ajax({
				url:"replies/"+rno_data,
				type:"PUT",
				dataType:"text",
				data: JSON.stringify(sendData),
				headers :{"Content-Type":"application/json"},
				success: function(data) {
					if(data == "SUCCESS"){
						alert("수정되었습니다.");
						$("#modDiv").hide(500);
					}
					getList();
				}
			})
		});	// btnMod 동작
		
		$("#btnBoard").click(function() {
			var bno = $("input[name='board_bno']").val();
			$.ajax({
				url: "replies/"+bno,
				type: "get",
				dataType : "json",
				success: function(data){
					console.log(data);
					if(data.BoardVO == null){
						alert("해당 게시글이 존재하지 않습니다.");
						$("#boardResult").empty();
						$("#boardResult").css("border", "none");
					}else{
						var divTag = $("<div>");
						var bno= "<p style='font-weight:bold; color:red'> BNO : "+data.BoardVO.bno+"</p>";
						var title=	"<p style='font-weight:bold'> TITLE : "+data.BoardVO.title+"</p>";
						var regdate= "<p> REGDATE : "+ new Date(data.BoardVO.regdate)+"</p>";
						var writer= "<p> WRITER : "+data.BoardVO.writer+"</p>";
						var content= "<p> CONTENT : "+data.BoardVO.content+"</p>";
						divTag.append(bno);
						divTag.append(title);
						divTag.append(regdate);
						divTag.append(writer);
						divTag.append(content);
						$("#boardResult").html(divTag);
						$("#boardResult").css("border", "1px solid gray");
					}
				}
			})
		}); 	//btnBoard 동작
		
	});
</script>
</head>
<body>

<h1>Ajax Test Page</h1>

<div>
	게시글 번호 : <input type="text" name="bno" placeholder="bno"><br>
	댓글 작성자 : <input type="text" name="replyer" placeholder="replyer"><br>
	댓글 내용용 : <input type="text" name="replytext" placeholder="replytext"><br>
	<button id="btnAdd">add</button>
</div>
<hr>
<div>
	
	
</div>

<hr>

게시글 번호 : <input type="text" name="board_bno" placeholder="bno">
	<button id="btnBoard">글 가져오기</button>
	<button id="btnList">댓글 가져오기</button><br>

<div id="boardResult">
</div>

<div id="result">
	
</div>

<div >
	<p id="test">
	<div id="pageNation"></div>
</div>


<div id="modDiv" style="display: none">
	<div class="modal-title"></div> <!-- rno -->
	<div>
		<input type="text" id="replytext">
	</div>
	<div>
		<button id="btnMod">수정</button>
	</div>
</div>

</body>
</html>