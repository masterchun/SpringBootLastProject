<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript"
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area"
		style="background-image: url(/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>로그인</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="breadcumb-nav">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<%-- 검색기 --%>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- ****** Breadcumb Area End ****** -->

	<!-- ****** Archive Area Start ****** -->
	<section class="archive-area section_padding_80">
		<div class="container" style="max-width: 800px; margin-top: 40px;">
			<div class="row justify-content-center">
				<form action="/member/login_process" method="post">
					<table class="table">
						<tbody>
							<tr>
								<th class="text-center" width="20%">ID</th>
								<td width="80%"><input type="text" size="20"
									class="input-sm" name="userid" required></td>
							</tr>
							<tr>
								<th class="text-center" width="20%">Password</th>
								<td width="80%"><input type="password" size="20"
									class="input-sm" name="userpwd" required></td>
							</tr>
							<tr>
								<td colspan="2">자동로그인 : <input type="checkbox"
									name="remember-me">
								</td>
							</tr>
							<tr>
								<td colspan="2" style="color: red">${message }</td>
							</tr>
							<tr>
								<td colspan="2">
									<button class="btn-sm btn-warning" type="submit">로그인</button>
									<button class="btn-sm btn-warning" type="button"
										onclick="javascript:history.back()">취소</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</section>
	<script>
	      let joinApp=Vue.createApp({
         data(){
            return {
               userid:'',
               username:'',
               userpwd:'',
               userpwd1:'',
               sex:'',
               birthday:'',
               email:'',
               post:'',
               addr1:'',
               addr2:'',
               phone1:'',
               phone2:'',
               content:'',
               
               isReadOnly:false,
               idOk:'',
               post:'',
               addr1:'',
            }
         },
         methods:{
            // idCheck
            idCheck(){
               if(this.userid==='')
               {
                  this.$refs.userid.focus()
                  return
               }
               
               axios.get("/member/idCheck_vue/",{
                  params:{
                     userid:this.userid
                  }
               }).then(response=>{
                  console.log(response.data)
                  if(response.data===0)
                  {
                     this.idOk='사용 가능한 아이디입니다'
                     this.isReadOnly=true
                  }
                  else
                  {
                     this.idOk='이미 사용중인 아이디입니다'
                     this.userid=''
                     this.$refs.userid.focus()
                  }
               }).catch(error=>{
                  console.log(error.response)
               })
            },
            postFind(){
               let _this=this
               new daum.Postcode({
                  oncomplete:function(data)
                  {
                     _this.post=data.zonecode
                     _this.addr1=data.address
                  }
               }).open()
            },
            join(){
               if(this.userid==='')
               {
                  this.$refs.userid.focus()
                  return
               }
               if(this.userpwd==='')
               {
                  this.$refs.userpwd.focus()
                  return
               }
               if(this.userpwd1==='')
               {
                  this.$refs.userpwd1.focus()
                  return
               }
               if(this.userpwd!==this.userpwd1)
               {
                  this.userpwd=''
                  this.userpwd1=''
                  this.$refs.userpwd.focus()
                  return
               }
               if(this.username==='')
               {
                  this.$refs.username.focus()
                  return
               }
               if(this.birthday==='')
               {
                  this.$refs.birthday.focus()
                  return
               }
               if(this.email==='')
               {
                  this.$refs.email.focus()
                  return
               }
               
               this.$refs.frm.submit()
            }
            // join
         }
      })
      joinApp.mount('#join_section')
      </script>
</body>
</html>