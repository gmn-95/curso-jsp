<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<jsp:include page="principal/head.jsp"></jsp:include>
	<link type="text/css" href="css/styleLogin.css">
	
	
	<title>Curso JSP</title>
	
</head>

<body themebg-pattern="theme1">
	
  
   <section class="login-block">
        <!-- Container-fluid starts -->
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <!-- Authentication card start -->
                    
                        <form action="<%=request.getContextPath() %>/ServletLogin" method="post" class="needs-validation" novalidate>
                            <input type="hidden" value="<%= request.getParameter("url") %>" name="url">
                            <div class="auth-box card">
                                <div class="card-block">
                                    <div class="row m-b-20">
                                        <div class="col-md-12">
                                            <h3 class="text-center">Faça o Login</h3>
                                        </div>
                                    </div>
                                    <div class="form-group form-primary">
                                        <label for="login" class="question-label">Login</label>
                                        <input name="login" type="text" class="form-control" placeholder= "${msg}" required="required" autocomplete="off" name=login>
                                    </div>
                                    <div class="form-group form-primary">
                                        <label for="senha" class="question-label">Senha</label>
                                        <input name="senha" type="password" class="form-control " placeholder= "${msg}" required="required" autocomplete="off" name=senha>
                                    </div>
                                    <div class="row m-t-30">
                                        <div class="col-md-12">
                                            <button class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20" type="submit">Acessar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- end of form -->
                </div>
                <!-- end of col-sm-12 -->
            </div>
            <!-- end of row -->
        </div>
        <!-- end of container-fluid -->
    </section>
	
	
	<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
    <script type="text/javascript">
    (function () {
      'use strict'

      var forms = document.querySelectorAll('.needs-validation')

      Array.prototype.slice.call(forms)
        .forEach(function (form) {
          form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
              event.preventDefault()
              event.stopPropagation()
            }

            form.classList.add('was-validated')
          }, false)
        })
    })()
    
    </script>
    
 

</html>