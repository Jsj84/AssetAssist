    <!-- Sidebar -->
    <div id="sidebar-container" class="sidebar-expanded d-none d-md-block">
  
   
    <!-- Actual search box -->
  <div class="form-group has-search">
       <span class="menu-collapsed"><input type="text" class="form-control" placeholder="Search.."></span>
  
  </div>

      <ul class="list-group">
  
            <!-- Menu with submenu -->
            <a href="#submenu1" data-toggle="collapse" aria-expanded="false" class="bg-dark list-group-item list-group-item-action flex-column align-items-start">

              <span><i class="fas fa-chalkboard-teacher fas-fw mr-3"></i></span>
                <span class="menu-collapsed">Assets</span>     
            </a>
      
            <!-- Submenu content -->
            <div id='submenu1' class="collapse sidebar-submenu">
           
                <a href="${pageContext.request.contextPath}/productList" class="list-group-item list-group-item-action bg-dark text-white">
                    <span class="menu-collapsed">All Assets</span>
                </a>
             
                <a href="${pageContext.request.contextPath}/createProduct" class="list-group-item list-group-item-action bg-dark text-white">
                    <span class="menu-collapsed">New Asset</span>
                </a>
   
          
            </div>
      
            <a href="#submenu2" data-toggle="collapse" aria-expanded="false" class="bg-dark list-group-item list-group-item-action flex-column align-items-start">
                <div class="d-flex w-100 justify-content-start align-items-center">
                    <span class="fa fa-user fa-fw mr-3"></span>
                    <span class="menu-collapsed">Users</span>
                  
                </div>
            </a>
            <!-- Submenu content -->
            <div id='submenu2' class="collapse sidebar-submenu">
                <a href="${pageContext.request.contextPath}/userInfo" class="list-group-item list-group-item-action bg-dark text-white">
                    <span class="menu-collapsed">All Users</span>
                </a>
                <a href="${pageContext.request.contextPath}/newUser" class="list-group-item list-group-item-action bg-dark text-white">
                    <span class="menu-collapsed">New User</span>
                </a>
            </div>            
<!--             <a href="#" class="bg-dark list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-start align-items-center">
                    <span class="fa fa-tasks fa-fw mr-3"></span>
                    <span class="menu-collapsed">Tasks</span>    
                </div>
            </a>  -->
            <!-- Separator with title -->
            <li class="list-group-item sidebar-separator-title text-muted d-flex align-items-center menu-collapsed">
                <small>OPTIONS</small>
            </li>
            <!-- /END Separator -->
            <a href="#" class="bg-dark list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-start align-items-center">
                    <span class="fa fa-calendar fa-fw mr-3"></span>
                    <span class="menu-collapsed">Calendar</span>
                </div>
            </a>
            <a href="#" class="bg-dark list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-start align-items-center">
                    <span class="fa fa-envelope-o fa-fw mr-3"></span>
                    <span class="menu-collapsed">Messages <span class="badge badge-pill badge-primary ml-2">5</span></span>
                </div>
            </a>
            <!-- Separator without title -->
            <li class="list-group-item sidebar-separator menu-collapsed"></li>            
            <!-- /END Separator -->
            <a href="#" class="bg-dark list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-start align-items-center">
                    <span class="fa fa-question fa-fw mr-3"></span>
                    <span class="menu-collapsed">Help</span>
                </div>
            </a>
        <a href="#" data-toggle="sidebar-colapse" class="bg-dark list-group-item list-group-item-action flex-column align-items-center">
                <div class="d-flex w-100 justify-content-start align-items-center">
                    <span id="collapse-icon" class="fa fa-2x mr-3"></span>
                    <span id="collapse-text" class="menu-collapsed"></span>
                </div>
                <%-- <script src="${pageContext.request.contextPath}/js/sideMenu.js"></script>  --%>
            </a>
          
        </ul><!-- List Group END-->

    </div><!-- sidebar-container END -->
  