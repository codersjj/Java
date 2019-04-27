/*
 * 本js实现检验验证码的功能，暂时不使用它。
 */


function showCheck(a){
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.clearRect(0,0,1000,1000);
	ctx.font = "87px 'Comic Sans MS'"; // 验证码字体大小和字体类型
	ctx.fillText(a,0,100);
	ctx.fillStyle = "black"; // 更换验证码后的验证码颜色
}
var code ;    
function createCode(){       
    code = "";      
    var codeLength = 4;
    var selectChar = new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');      
    for(var i=0;i<codeLength;i++) {
       var charIndex = Math.floor(Math.random()*selectChar.length);      
      code +=selectChar[charIndex];
    }      
    if(code.length != codeLength){      
      createCode();      
    }
    showCheck(code);
}
function validate () {
    var inputCode = document.getElementById("J_codetext").value.toUpperCase(); // toUpperCase() 方法用于把字符串转换为大写。
    var codeToUp=code.toUpperCase();
    if(inputCode.length <=0) {
      document.getElementById("J_codetext").setAttribute("placeholder","输入验证码");
      createCode();
      return false;
    }
    else if(inputCode != codeToUp ){
      document.getElementById("J_codetext").value="";
      document.getElementById("J_codetext").setAttribute("placeholder","验证码错误");
      createCode();
      return false;
    }
    else {
      /*window.open(document.getElementById("J_down").getAttribute("data-link"));
      document.getElementById("J_codetext").value="";
      createCode();
      return true;*/
    	
//    	window.open("loginSuccess.jsp","_blank","width=380, height=300,left=500,top=200");
    	
    	openWin();
    }
    
    function openWin() {
    	var url = 'http://127.0.0.1:8080/login_sjj/loginSuccess.jsp'; // 转向网页的地址; 
    	var name = '登录页面'; // 网页名称，可为空; 
    	var iWidth = 380; // 弹出窗口的宽度; 
    	var iHeight = 300; // 弹出窗口的高度; 
    	// 获得窗口的垂直位置 
    	var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
    	// 获得窗口的水平位置 
    	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
    	window.open(url, name, 'height=' + iHeight + ',innerHeight=' + iHeight
    			+ ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop
    			+ ',left=' + iLeft);
    }

}