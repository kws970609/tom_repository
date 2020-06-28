<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}

* {
  box-sizing: border-box;
}

/* style the container */
.container {
  position: relative;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px 0 30px 0;
} 

/* style inputs and link buttons */
input,
.btn {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 4px;
  margin: 5px 0;
  opacity: 0.85;
  display: inline-block;
  font-size: 17px;
  line-height: 20px;
  text-decoration: none; /* remove underline from anchors */
}

input:hover,
.btn:hover {
  opacity: 1;
}

/* add appropriate colors to fb, twitter and google buttons */
.fb {
  background-color: #3B5998;
  color: white;
}

.twitter {
  background-color: #55ACEE;
  color: white;
}

.google {
  background-color: #dd4b39;
  color: white;
}

/* style the submit button */
input[type=button] {
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

/* Two-column layout */
.col {
  float: left;
  width: 50%;
  margin: auto;
  padding: 0 50px;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* vertical line */
.vl {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  border: 2px solid #ddd;
  height: 175px;
}

/* text inside the vertical line */
.vl-innertext {
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  background-color: #f1f1f1;
  border: 1px solid #ccc;
  border-radius: 50%;
  padding: 8px 10px;
}

/* hide some text on medium and large screens */
.hide-md-lg {
  display: none;
}

/* bottom container */
.bottom-container {
  text-align: center;
  background-color: #666;
  border-radius: 0px 0px 4px 4px;
}

/* Responsive layout - when the screen is less than 650px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 650px) {
  .col {
    width: 100%;
    margin-top: 0;
  }
  /* hide the vertical line */
  .vl {
    display: none;
  }
  /* show the hidden text on small screens */
  .hide-md-lg {
    display: block;
    text-align: center;
  }
}
</style>
<script>
//자바스크립트는 클라이언트에게 다운로드 받아지기때문에, 코드가 노출된다따라서, 
//직접 db에 접속하는 기능이 없다
//단지 서버에 요청을 시도할 수만 있다..
function loginCheck(){
	//서버에 로그인 요청!!
	//get or post 방식중 하나를 선택하자!!
	//만일 개발자가 요청방식을 결정하지 않으면 디폴트는 get이다!!
	
	/*
	HyperTextTransferProtocol 
	HTML과 같은 전자문서를 주고 받을때 어떻게 주고받을지에 대한 규칙, 약속
	프로토콜(=통신할때 미리 정해놓은 약속)이란?
	
	GET방식이란?
	-전자 문서 주고 받을때, 특히 데이터 요청시 URL header 를 통해 전달되며,
	데이터량에 한계가 있고, 데이터가 노출되 진다..(good or bad?)
	ex) 뉴스기사 링크..
	현실에서의 편지봉투와 같다...
	
	Post방식이란?
	-HTTP 요청시 URL Header가 아닌 Body를 통해 데이터를 전송하는 방식을 말한다
	body에 데이터가 전송될경우 노출되지 않으며, 데이터량에 한계가 없다... 
	현실에서의 소포상자에 담겨진 내용물과 같다!!
	ex) 이미지, 동영상과 같이 파일을 서버로 전송할때
		노출시키고 싶지않은 정보를 전송할때, 회원가입, 로그인 etc...
			
	*/
	form1.method="get";
	form1.action="/login"; //요청을 받아서 처리할 서버측의 서블릿
	form1.submit(); //서버로 폼양식의 데이터 모두 전송!!
					//이때 html 컴포넌트는 모두 팔미터 변수화되어 데이터가 스트링으로 전송된다
}
</script>
</head>
<body>

<h2>Responsive Social Login Form</h2>
<p>Resize the browser window to see the responsive effect. When the screen is less than 650px wide, make the two columns stack on top of each other instead of next to each other.</p>

<div class="container">
  <form name="form1">
    <div class="row">
      <h2 style="text-align:center">Login with Social Media or Manually</h2>
      <div class="vl">
        <span class="vl-innertext">or</span>
      </div>

      <div class="col">
        <a href="#" class="fb btn">
          <i class="fa fa-facebook fa-fw"></i> Login with Facebook
         </a>
        <a href="#" class="twitter btn">
          <i class="fa fa-twitter fa-fw"></i> Login with Twitter
        </a>
        <a href="#" class="google btn"><i class="fa fa-google fa-fw">
          </i> Login with Google+
        </a>
      </div>

      <div class="col">
        <div class="hide-md-lg">
          <p>Or sign in manually:</p>
        </div>
        <input type="text" name="id" placeholder="Username" required>
        <input type="password" name="pass" placeholder="Password" required>
        <input type="button" value="Login" onClick="loginCheck()">
      </div>
      
    </div>
  </form>
</div>

<div class="bottom-container">
  <div class="row">
    <div class="col">
      <a href="#" style="color:white" class="btn">Sign up</a>
    </div>
    <div class="col">
      <a href="#" style="color:white" class="btn">Forgot password?</a>
    </div>
  </div>
</div>

</body>
</html>
