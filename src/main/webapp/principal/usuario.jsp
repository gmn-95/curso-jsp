<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->  
  
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  
                  <jsp:include page="navbar-mainMenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                  
                      <!-- Page-header start -->
                      <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                      
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                    	<span id="msg">${msg}</span>
                                    	<br>
                                    	<br>
		                                 <div class="row">
		                                    <div class="col-sm-12">
		                                        <!-- Basic Form Inputs card start -->
		                                        <div class="card">
		                                            <div class="card-block">
		                                            	<h4 class="sub-title">Cad. Usuário</h4>
		                                            	
		                                            	<form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
		                                            		
		                                            		<input type="hidden" name="acao" id="acao" value="">
		                                            		
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label" style="color: #495057">ID:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default input-group mb-4">
                                                            	<div class="input-group-prepend">
	                                                            	<c:if test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
	                                                            		<a href="<%= request.getContextPath()%>/ServletUsuarioController?acao=dowloadFoto&id=${modelLogin.id}">
			                                                            	<img alt="Imagem do Usuário" id="fotobase64" src="${modelLogin.fotoUser}" width="70px">
	                                                            		</a>
	                                                            	</c:if>
	                                                            	<c:if test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
	                                                            		<img alt="Imagem do Usuário" id="fotobase64"  src="assets/images/do-utilizador.png" width="70px">
	                                                            	</c:if>
                                                            	<span class="form-bar"></span>
                                                            	</div>
                                                            	<input type="file" id="filefoto" name="filefoto" accept="image/*" onchange="visualizarImage('fotobase64', 'filefoto')" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                             <div class="form-group form-default form-static-label">
															    <select class="form-control" name="perfil" arial-label="Default select example" required="required">
															      <option  disabled="disabled" selected="selected">[Selecione o Perfil]</option>
															      
															      <option value="ADMIN" <% 
															      
															    	   ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																       modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																      	
																      if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
															    		  out.println(" ");
															    		  out.print("selected=\"selected\"");
															    		  out.println(" ");
														    		  }%>>Admin</option>
															      
															      <option value="SECRETARIO" <% 
															      	
														    		  modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															      
																      if(modelLogin != null && modelLogin.getPerfil().equals("SECRETARIO")){
															    		  out.println(" ");
															    		  out.print("selected=\"selected\"");
															    		  out.println(" ");
														      		  }%>>Secretário</option>
															      
															      <option value="AUXILIAR" <% 
															      	  
														    		  modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															      
																      if(modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
																    		  out.println(" ");
																    		  	out.print("selected=\"selected\"");
																    		  out.println(" ");
															          }%>>Auxiliar</option>
															    </select>
															    <span class="form-bar"></span>
															    <label class="float-label" style="color: #495057">Perfil</label>
															  </div>
															  
														  	<div class="form-group form-default">
                                                                <input onblur="pesquisaCep()" type="text" name="cep" id="cep" class="form-control" required="required" value="${modelLogin.cep}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">CEP</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="logradouro" lang="pt-br" id="logradouro" class="form-control" required="required" value="${modelLogin.logradouro}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Logradouro</label>
                                                            </div>
														  	
														  	<div class="form-group form-default">
                                                                <input type="text" name="bairro" id="bairro" lang="pt-br" class="form-control" required="required" value="${modelLogin.bairro}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Bairro</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="localidade" id="localidade" lang="pt-br" class="form-control" required="required" value="${modelLogin.localidade}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Localidade</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="uf" id="uf" class="form-control" required="required" value="${modelLogin.uf}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">UF</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="numero" id="numero" class="form-control" required="required" value="${modelLogin.numero}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Número</label>
                                                            </div>
														  	
                                                            <div class="form-group form-default">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" value="${modelLogin.login}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modelLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail (exa@gmail.com)</label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modelLogin.senha}">
                                                                
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                            	<input type="radio" name="sexo" value="MASCULINO"<% 
															      
																       modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																      	
																      if(modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
															    		  out.println(" ");
															    		  out.print("checked=\"checked\"");
															    		  out.println(" ");
														    		  }%>>
                                                            	<label class="custom-control-label">Masculino</label>
                                                            	<input type="radio" name="sexo" value="FEMININO"<% 
															      
																       modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																      	
																      if(modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
															    		  out.println(" ");
															    		  out.print("checked=\"checked\"");
															    		  out.println(" ");
														    		  }%>>
                                                            	<label class="custom-control-label" value="FEMININO">Feminino</label> 
                                                            	
                                                            </div>
                                                            
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
												           	<button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDeleteComAjax()">Excluir</button>
												           	<button type="button" class="btn btn-secondary waves-effect waves-light" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
                                                        </form>
			                                         </div>
		                                         </div>
	                                         </div>
                                         </div>
                                         <div class="row">
		                                    <div class="col-sm-12">
		                                        <!-- Basic Form Inputs card start -->
		                                        <div class="card" >
		                                            <div class="card-block">
		                                            	<h4 class="sub-title">Usuários</h4>
				                                         <div style="height: 300px; overflow: scroll;" >
															<table class="table" id="tabelaResultadosView">
															  <thead>
															    <tr>
															      <th scope="col">ID</th>
															      <th scope="col">Nome</th>
															      <th scope="col">Ver</th>
															    </tr>
															  </thead>
															  <tbody>
															    	<c:forEach items="${modelLogins}" var="ml">
															    		<tr>
															    			<td><c:out value="${ml.id}"></c:out></td>
															    			<td><c:out value="${ml.nome}"></c:out></td>
															    			<td> <a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}" class="btn btn-success waves-effect waves-light" >Ver</a></td>
															    		</tr>
															    	</c:forEach>
															  </tbody>
															</table>
														</div>
														<div class="card"></div>
															<nav aria-label="Page navigation example">
															  <ul class="pagination">
															  	<% 
															  		int totalPagina = (int) request.getAttribute("totalPagina");
															  		for(int p = 0; p < totalPagina; p++){
															  			String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 5);
															  			out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+ url +"\">" + (p + 1) +"</a></li>");
															  		}
															  	
															  	%>
															  </ul>
															</nav>
													</div>
												</div>
											</div>
										</div>
                                    </div>
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="javaScriptFile.jsp"></jsp:include>
    
    
	    <!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de usuário</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      
	        	<div class="input-group mb-3">
				  <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2" autocomplete="off">
				  <div class="input-group-append">
				    <button type="button" class="btn btn-success waves-effect waves-light" onclick="buscarUsuario()">Buscar</button>
				  </div>
				</div>
				
				<div style="height: 300px; overflow: scroll;" >
					<table class="table" id="tabelaResultados">
					  <thead>
					    <tr>
					      <th scope="col">ID</th>
					      <th scope="col">Nome</th>
					      <th scope="col">Ver</th>
					    </tr>
					  </thead>
					  <tbody>
					    
					  </tbody>
					</table>
				</div>
				
				<nav aria-label="Page navigation example">
					<ul class="pagination" id="ulPaginacaoUserAjax">
					</ul>
				</nav>	        	
	      	<span id="totalResultados"></span> 
	      </div>
				
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
    
    <script type="text/javascript">
    
    	function pesquisaCep() {
    		
			var cep = $("#cep").val();
			
			$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function (dados) {
				
				if(!("erro" in dados)){
					//Atualiza os campos com os valores da consulta.
					$("#cep").val(dados.cep);
                    $("#logradouro").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#localidade").val(dados.localidade);
                    $("#uf").val(dados.uf);
				}
				
			});
		}
    	
    	function visualizarImage(fotobase64, filefoto) {
			
    		var preview = document.getElementById(fotobase64);
			var fileUser = document.getElementById(filefoto).files[0];
			var reader = new FileReader();
			
			reader.onloadend = function () {
				preview.src = reader.result; /*Carrega a foto na tela*/
			};
			
			if(fileUser){
				reader.readAsDataURL(fileUser); /*Preview da imagem*/
			}
			else{
				preview.rc = '';
			}
			
		}
    
    	function buscarUsuario() {
    		var nomeBusca = document.getElementById('nomeBusca').value;
    		
    		if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){
    			
    			var urlAction = document.getElementById('formUser').action;
    			
				$.ajax({
					
    				method: "get",
    				url: urlAction,
    				data: "nomeBusca=" + nomeBusca + '&acao=buscarUsuarioAjax',
    				success: function(response, textStatus, xhr){
    					
    					var json = JSON.parse(response);
    					
    					$('#tabelaResultados > tbody > tr').remove();
    					$("#ulPaginacaoUserAjax > li").remove();
    					
    					for(var p = 0; p < json.length; p++){
    						$('#tabelaResultados > tbody').append('<tr> <td>'+ json[p].id +'</td> <td>' + json[p].nome + '</td> <td> <button onclick="verEditar('+ json[p].id +')" type="button" class="btn btn-info">Ver</button> </td> </tr>');
    					}
    					
    					document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
    					
    					var totalPagina = xhr.getResponseHeader("totalPagina");
    					
    					for(var p = 0; p < totalPagina; p++){
    						
    						var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUsuarioAjaxPage&pagina=' + (p * 5);
    						$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p + 1) + '</a></li>');
    					}
    					
    				}
    				
    			}).fail(function(xhr, status, errorThrown){
    				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
    			});
    		}
			
		}
    	
    	function buscaUserPagAjax(url) {
    		
    		var urlAction = document.getElementById('formUser').action;
    		var nomeBusca = document.getElementById('nomeBusca').value;
    		
    		$.ajax({
				
				method: "get",
				url: urlAction,
				data: url,
				success: function(response, textStatus, xhr){
					
					var json = JSON.parse(response);
					
					$('#tabelaResultados > tbody > tr').remove();
					$("#ulPaginacaoUserAjax > li").remove();
					
					for(var p = 0; p < json.length; p++){
						$('#tabelaResultados > tbody').append('<tr> <td>'+ json[p].id +'</td> <td>' + json[p].nome + '</td> <td> <button onclick="verEditar('+ json[p].id +')" type="button" class="btn btn-info">Ver</button> </td> </tr>');
					}
					
					document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
					
					var totalPagina = xhr.getResponseHeader("totalPagina");
					
					for(var p = 0; p < totalPagina; p++){
						
						var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUsuarioAjaxPage&pagina=' + (p * 5);
						
						$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p + 1) + '</a></li>');
					}
					
				}
				
			}).fail(function(xhr, status, errorThrown){
				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
			});
    		
		}
    	
    	
    	function verEditar(id) {
    		var urlAction = document.getElementById('formUser').action;
    		
    		window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}
    
    	function criarDeleteComAjax() {
    		
    		if(confirm('Deseja realmente excluir os dados?')){
    			
    			var urlAction = document.getElementById('formUser').action;
    			var idUsuario = document.getElementById('id').value;
    			
    			$.ajax({
					
    				method: "get",
    				url: urlAction,
    				data: "id=" + idUsuario + '&acao=deletarAjax',
    				success: function(response){
    					limparForm();
    					document.getElementById('msg').textContent = response;
    				}
    				
    			}).fail(function(xhr, status, errorThrown){
    				alert('Erro ao deletar usuário por id: ' + xhr.responseText);
    			});
    			
    		}
			
		}
    	
    
    	function criarDelete() {
    		
    		if(confirm('Deseja realmente excluir os dados?')){
    			
	    		document.getElementById("formUser").method = 'get';
	    		document.getElementById("acao").value = 'deletar';
	    		document.getElementById("formUser").submit();
			
    			
    		}
		}
    
    	
    	function limparForm() {
			var elementos = document.getElementById("formUser").elements; // retorna os elementos html dentro do form
			
			for(p = 0; p < elementos.length; p++){
				elementos[p].value = '';
			}
    	}
    	
    	
    </script>
    
	</body>

</html>
    