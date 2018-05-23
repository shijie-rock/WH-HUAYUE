/*移动端通用js*/
//滚动到指定位置
function scrollTo(scrollHeight){
	if(scrollHeight!=null){
		$('html, body').animate({  
	        scrollTop:scrollHeight
	    },100);  
	}
	//scrollTopAndFoot();
}

//滚动条判断是否到底顶部或者底部
//废弃
function scrollTopAndFoot(){
	var a = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	  if(document.body.scrollTop==0&&document.documentElement.scrollTop==0){
	     console.log("到达顶端");
	  }
	  if(a+Math.floor(b)==c || a+Math.ceil(b)==c){
		  console.log("到达底部");
	  }
}