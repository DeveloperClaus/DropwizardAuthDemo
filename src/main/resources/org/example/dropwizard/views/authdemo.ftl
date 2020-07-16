<#-- @ftlvariable name="" type="org.example.dropwizard.views.AuthDemoView" -->

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
      <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <title>Dropwizard project to demonstrate authorization (and questions about it)</title>
  </head>  
  
  <body>
  <style>
    body {
     padding-top: 1.5em;
    }
  </style>	
  <div class="container">
    <div class="row">
      <div class="card col-sm-12 col-xl-4">
        <div class="card-header"><h5><b>List of known users</b></h5></div>
        <div class="card-body table-responsive">
		  <table class="table table-hover table-sm">
          <#list allUsers as all> 
            <tr>
	          <th>${all.username}</th>
            <tr>	            
          </#list>
	      </table>
	    </div>
      </div>
    <div class="col-sm-12 col-xl-8">
    <div class="row">
      <div class="card col-sm-12 col-xl-6">
        <div class="card-header"><h5><b>Login (prefered way)</b></h5></div>
          <div class="card-body">
            <p>The following should be a form based authorization. Chose any user from the list (no password required) and enter it in the field.</p>
            <#if withMessage>
              <p style="color:red">${message}</p>
            <#else>
              <br/>
            </#if>
		    <form class="form-inline my-2 my-lg-0" method='POST' action='login'>
              <input type="text" class="form-control col-8" name="userName" id="userName" aria-describedby="usernameHelp" placeholder="enter username">
              <input class="btn btn-primary" type="submit" value="Login">
		    </form>
        </div>
      </div>
      <div class="card col-sm-12 col-xl-6">
        <div class="card-header"><h5><b>Login (with unwanted pop-up)</b></h5></div>
          <div class="card-body">
            <p>This is classic basic authentification. A popup by the browser will open. To login, enter any of the usernames from the list and enter any password (password will not be validated).</p>
            <a class="btn btn-primary" href="restrictedlogin" role="button">Login with pop-up</a>
        </div>
      </div>
      <div class="card col-sm-12 col-xl-6">
        <div class="card-header"><h5><b>Reload</b></h5></div>
          <div class="card-body">
            <p>Reloading the page should change nothing, your authorization should stay the same. However, you keep your authorization only if you did the login with pop-up.</p>
            <#if authorized>
              <p>Btw, you are logged in as ${authorizedUserName}</p>
              <#if persistedAuthorized>
                <p>And it will stay this way.</p>
              <#else>
                <p>But on reload this information is lost.</p>
              </#if>
            <#else>
              <p>However, you are not logged in anyways.
              <#if persistedAuthorized>
                <p>At least it looks like that, on reload you are back again.</p>
              </#if> 
            </#if>            
            <a class="btn btn-primary" href="/auth" role="button">Reload</a>
         </div> 
      </div>
      <div class="card col-sm-12 col-xl-6">
        <div class="card-header"><h5><b>Logout</b></h5></div>
          <div class="card-body">
            <p>How can we logout the user?</p>
            <br/>
            <br/>
            <br/>
            <br/>
            <a class="btn btn-primary" href="logout" role="button">Logout</a>
         </div> 
      </div>
    </div>
  </div>
  <!-- Freemarker-Version: ${.version} -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>