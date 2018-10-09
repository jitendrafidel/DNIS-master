<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Comlink | DNIS Management</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="description" content="Avenger Admin Theme">
<meta name="author" content="KaijuThemes">

<link
	href='http://fonts.googleapis.com/css?family=RobotoDraft:300,400,400italic,500,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,400italic,600,700'
	rel='stylesheet' type='text/css'>

<!--[if lt IE 10]>
        <script type="text/javascript" src="assets/js/media.match.min.js"></script>
        <script type="text/javascript" src="assets/js/placeholder.min.js"></script>
    <![endif]-->

<link type="text/css"
	href="assets/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link type="text/css" href="assets/css/styles.css" rel="stylesheet">
<!-- Core CSS with all styles -->

<link type="text/css"
	href="assets/plugins/jstree/dist/themes/avenger/style.min.css"
	rel="stylesheet">
<!-- jsTree -->
<link type="text/css" href="assets/plugins/codeprettifier/prettify.css"
	rel="stylesheet">
<!-- Code Prettifier -->
<link type="text/css"
	href="assets/plugins/iCheck/skins/minimal/blue.css" rel="stylesheet">
<!-- iCheck -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
<!--[if lt IE 9]>
        <link type="text/css" href="assets/css/ie8.css" rel="stylesheet">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The following CSS are included as plugins and can be removed if unused-->

<link type="text/css"
	href="assets/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet">
<link type="text/css"
	href="assets/plugins/datatables/dataTables.fontAwesome.css"
	rel="stylesheet">
<link type="text/css" href="assets/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet">
<link type="text/css" href="assets/plugins/datatables/dataTables.fontAwesome.css" rel="stylesheet">
</head>
<body class="infobar-offcanvas">

	
	<header id="topnav"
		class="navbar navbar-midnightblue navbar-fixed-top clearfix"
		role="banner"><a style="margin-left:10px;" class="navbar-brand" href="dashboard.html">Comlink</a>

	<ul class="nav navbar-nav toolbar pull-right">
		<li class="dropdown toolbar-icon-bg"><a href="#"
			class="dropdown-toggle" data-toggle='dropdown'><span
				class="icon-bg"><i class="fa fa-fw fa-user"></i></span></a>
			<ul class="dropdown-menu userinfo arrow">
				<li><a href="changepassword.html"><span class="pull-left">Change
							Password</span> </a></li>
				<li><a href="logout"><span class="pull-left">Sign
							Out</span> <i class="pull-right fa fa-sign-out"></i></a></li>
			</ul></li>
	</ul>
	</header>
	<div id="wrapper">
		<div id="layout-static">
			<div class="static-sidebar-wrapper sidebar-midnightblue">
				<div class="static-sidebar">
					<div class="sidebar">
						<div class="widget stay-on-collapse" id="widget-welcomebox">
							<div class="widget-body welcome-box tabular">
								<div class="tabular-row">
									<div class="tabular-cell welcome-avatar">
										<a href="#"><img src="assets/demo/avatar/avatar_02.png"
											class="avatar"></a>
									</div>
									<div class="tabular-cell welcome-options">
										<span class="welcome-text">Welcome,</span> <a href="#"
											class="name">${sessionuser.firstName}</a>
									</div>
								</div>
							</div>
						</div>
						<div class="widget stay-on-collapse" id="widget-sidebar">
							<nav role="navigation" class="widget-body">
							<ul class="acc-menu">
								<li class="nav-separator">Explore</li>
								<li><a href="dashboard"><i class="fa fa-home"></i><span>Dashboard</span></a></li>
								<c:if test="${sessionuser.type=='Admin'}">
									<li><a href="user"><i class="fa fa-users"></i><span>Users</span></a></li>
									<li><a href="log"><i class="fa fa-bar-chart"></i><span>Log</span></a></li>
								</c:if>
								<li><a href="dnismanagement"><i class="fa fa-cog"></i><span>DNIS
											Management</span></a></li>
							</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<div class="static-content-wrapper">
				<div class="static-content">
					<div class="page-content">
						<div class="page-heading">
							<h1>DNIS Management</h1>
						</div>
						<div class="container-fluid">
							<h6>${message}</h6>
							<div class="row">
								<div class="clearfix">
									<form modelAttribute="fileBean" method="Post"
										enctype="multipart/form-data" id="formFile">
										<div class="col-sm-12">

											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label">Staus
													: </label> <input type="hidden" name="action" value="add">
												<input type="text" placeholder="Search by Status"
													class="form-control" name="Status" value="${status}">
											</div>
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label">Prefix+
													DNIS : </label> <input type="hidden" name="action"
													value="hi Search me"> <input type="text"
													placeholder="Enter Prefix+ DNIS"
													class="form-control" name="prefixdnis" value="${prefixdnis}">
											</div>
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label"> Date
												of Modification	  : </label> <input type="text"
													placeholder="Enter Date
												of Modification" class="form-control"
													name="datem" value="${datem}">
											</div>
										</div>
										<div class="col-sm-12">
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label"> File
												Name: </label> <input type="text"
													placeholder="Enter File Name" class="form-control"
													name="file" value="${file}">
											</div>
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label">  Gateway
												Group Name: </label> <input type="text"
													placeholder="Enter GatewayGroupName"
													class="form-control" name="gatewayname" value="${gatewayname}">
											</div>
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label"> DID
												Number: </label> <input type="text"
													placeholder="Enter DID Number"
													class="form-control" name="did" value="${did}">
											</div></div>
											<div class="col-sm-12">
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label">Ticket
													Order Number : </label> <input type="text"
													placeholder="Enter Ticket Order Number"
													class="form-control" name="ticnumber" value="${ticnumber}">
											</div>
											 <div class="col-sm-4 p">
													<a href="#myModaledit"  
														class="btn btn-primary " data-toggle="modal">Select Action</a>
														</div>
														</div>
														
												  <div aria-hidden="true" aria-labelledby="myModalLabel"role="dialog" tabindex="-1" id="myModaledit" class="modal fade">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button aria-hidden="true" data-dismiss="modal"
														class="close" type="button">×</button>
													<h2 class="modal-title">Add Action</h2>
													
												</div>
												
													<form class="form-horizontal" action="addaction" method="post" >
													<div class="modal-body clearfix">
														<input type="hidden" name="action" value="add">
														<div class="form-group clearfix">
															<label class="col-sm-3 control-label" for="inputPassword">Ticket
																Order Number:</label>
															<div class="col-sm-8">
																<input type="text" name="tic_number"
																	placeholder="Enter Ticket Number" class="form-control" required/>
															</div>
														</div>
														 	<div class="form-group">
														 	
															<label class="col-sm-3 control-label">Action:</label>
															<div class="col-sm-8">
																<select class="form-control" name="add_action">
																	<option>ADD</option>
																	<option>MODIFY</option>
																	<option>DELETE</option>
																	<option>UNDELETE</option>
																</select>
															</div>
															 
														</div></div> 
												
												<div class="modal-footer">
													<button class="btn btn-primary" id="submit" type="submit">Add Action</button> 
													<button data-dismiss="modal" class="btn btn-default"
														type="button">Cancel</button> 

											 </form>
												</div> </div> 
												  											</div>
											<!-- /.modal-content -->
										
										<!-- /.modal-dialog -->
								 
											<div class="col-sm-12 p clearfix">

												<div class="col-sm-2">
													<input type="submit" value="Search"
														formAction="dnismanagement"
														class="btn btn-primary col-sm-12">
												</div>
												&nbsp;&nbsp;&nbsp;

												<div class="col-sm-2">
													<input type="submit" value="Download Report"
														formAction="downloadReport"
														class="btn btn-primary col-sm-12">
												</div>

												<div class="col-sm-3">

													<input style="padding: 3px !important; text-align:left;" type="file"
														class="btn btn-primary col-sm-12" name="file"
														value="Choose File To Upload">
												</div>

												<div class="col-sm-2">
													<input type="submit" value="File Upload"
														formAction="uploadFile" class="btn btn-primary  col-sm-12">&nbsp;&nbsp;&nbsp;
												</div>

												<div class="col-sm-2">
													<input type="submit" value="Create Config"
														formAction="downloadConfig"
														class="btn btn-primary  col-sm-12">&nbsp;&nbsp;&nbsp;
												</div>

												<div class="clearfix"></div></div>
											</form></div>	</div></div></div>
										</div>
									
									<br> <br>
									</div>
									<div class="panel panel-default" style="overflow:auto;">
			<div class="panel-heading" style="overflow:auto;">
				<h2>Data Tables</h2>
				<div class="panel-ctrls">
				</div>
			</div>
			<div class="panel-body" style="overflow:auto;">
				<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%" style="overflow:auto;">
					<thead style="overflow:auto;">
						<tr>
							<th style="">RecordID</th>
														<th style="padding-right: 60px;">DNIS</th>
														<th style="padding-right: 60px;">Prefix</th>
														<th style="padding-right: 60px;">MappedNumber</th>
														<th style="padding-right: 60px;">CustomerName</th>
														<th style="padding-right: 60px;">RoutingIP1</th>
														<th style="padding-right: 60px;">RoutingIP2</th>
														<th style="padding-right: 60px;">RoutingIP3</th>
														<th style="padding-right: 60px;">RoutingIP4</th>
														<th style="padding-right: 60px;">RoutingIP5</th>
														<th style="padding-right: 60px;">RoutingIP6</th>
														<th style="padding-right: 60px;">DateCreated</th>
														<th style="padding-right: 60px;">DateModified</th>
														<th style="padding-right: 60px;">TicketOrderNum</th>
														<th style="padding-right: 60px;">DTFS</th>
														<th style="padding-right: 170px;">Status</th>
														<th style="padding-right: 60px;">File Name</th>
														<th style="padding-right: 60px;">User Id</th>
														<th style="padding-right: 60px;">DID Number</th>
														<th style="padding-right: 60px;">GatewayGroupName</th>
														<th style="padding-right: 170px;">InitialActionBy</th>
														<th style="padding-right: 60px;">CurrentAction</th>
							
						</tr>
					</thead>
					<tbody style="overflow:auto;">
													<c:forEach var="dnis" items="${dnisList}">
														<tr>
															<td>${dnis.recordid}</td>
															<td>${dnis.DNIS}</td>
															<td>${dnis.prefix}</td>
															<td>${dnis.mnumber}</td>
															<td>${dnis.cname}</td>
															<td>${dnis.rip1}</td>
															<td>${dnis.rip2}</td>
															<td>${dnis.rip3}</td>
															<td>${dnis.rip4}</td>
															<td>${dnis.rip5}</td>
															<td>${dnis.rip6}</td>
															<td>${dnis.datec}</td>
															<td>${dnis.datem}</td>
															<td>${dnis.ticketno}</td>
															<td>${dnis.dtfs}</td>
															<td>${dnis.status}</td>
															<td>${dnis.file}</td>
															<td>${dnis.userId}</td>
															<td>${dnis.didNumber}</td>
															<td>${dnis.gatewayName}</td>
															<td>${dnis.initialAction}</td>
															<td>${dnis.currentAction}</td>
															
															<td><a href="" data-toggle="modal">Add</a>&nbsp;
																| &nbsp;<a href="">Modify</a>&nbsp;| &nbsp;<a href="">Delete</a></td>
															<td><input type="file" /></td>
														</tr>
													</c:forEach>
												</tbody>
																</table>
				<div class="panel-footer"></div>
			</div>
		</div>

								
							</div>
						</div>
						<!-- .container-fluid -->
					</div>
					<!-- #page-content -->
				</div>
				<footer role="contentinfo">
				<div class="clearfix">
					<ul class="list-unstyled list-inline pull-left">
						<li><h6 style="margin: 0;">&copy; 2016 Comlink</h6></li>
					</ul>
					<button class="pull-right btn btn-link btn-xs hidden-print"
						id="back-to-top">
						<i class="fa fa-arrow-up"></i>
					</button>
				</div>
				</footer>
			</div>
		</div>
	</div>
	<div class="infobar-wrapper scroll-pane">
		<div class="infobar scroll-content"></div>
	</div>
	<!-- Switcher -->
	
	<!-- /Switcher -->
	<!-- Load site level scripts -->

	<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script> -->

	<script type="text/javascript" src="assets/js/jquery-1.10.2.min.js"></script>
	<!-- Load jQuery -->
	<script type="text/javascript" src="assets/js/jqueryui-1.9.2.min.js"></script>
	<!-- Load jQueryUI -->

	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	<!-- Load Bootstrap -->


	<script type="text/javascript"
		src="assets/plugins/easypiechart/jquery.easypiechart.js"></script>
	<!-- EasyPieChart-->
	<script type="text/javascript"
		src="assets/plugins/sparklines/jquery.sparklines.min.js"></script>
	<!-- Sparkline -->
	<script type="text/javascript"
		src="assets/plugins/jstree/dist/jstree.min.js"></script>
	<!-- jsTree -->

	<script type="text/javascript"
		src="assets/plugins/codeprettifier/prettify.js"></script>
	<!-- Code Prettifier  -->
	<script type="text/javascript"
		src="assets/plugins/bootstrap-switch/bootstrap-switch.js"></script>
	<!-- Swith/Toggle Button -->

	<script type="text/javascript"
		src="assets/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js"></script>
	<!-- Bootstrap Tabdrop -->

	<script type="text/javascript"
		src="assets/plugins/iCheck/icheck.min.js"></script>
	<!-- iCheck -->

	<script type="text/javascript" src="assets/js/enquire.min.js"></script>
	<!-- Enquire for Responsiveness -->

	<script type="text/javascript" src="assets/plugins/bootbox/bootbox.js"></script>
	<!-- Bootbox -->

	<script type="text/javascript"
		src="assets/plugins/simpleWeather/jquery.simpleWeather.min.js"></script>
	<!-- Weather plugin-->

	<script type="text/javascript"
		src="assets/plugins/nanoScroller/js/jquery.nanoscroller.min.js"></script>
	<!-- nano scroller -->

	<script type="text/javascript"
		src="assets/plugins/jquery-mousewheel/jquery.mousewheel.min.js"></script>
	<!-- Mousewheel support needed for jScrollPane -->

	<script type="text/javascript" src="assets/js/application.js"></script>
	<script type="text/javascript" src="assets/demo/demo.js"></script>
	<script type="text/javascript" src="assets/demo/demo-switcher.js"></script>
	<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>


	<script>
		function downloadReport() {
			window.open("downloadReport");
		}

		function uploadFile() {
			window.open("uploadFile");
		}

		function search() {
			window.open("dnismanagement");
		}
		
		function downloadConfig() {
			window.open("downloadConfig");
		}
	</script>

	<!-- End loading site level scripts -->

	<!-- Load page level scripts-->
	<script type="text/javascript"
		src="assets/plugins/jquery-chained/jquery.chained.min.js"></script>
	<!-- Chained Select Boxes -->
	<script type="text/javascript"
		src="assets/plugins/datatables/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="assets/demo/demo-datatables.js"></script>

	<!-- End loading page level scripts-->
<script type="text/javascript" src="assets/plugins/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="assets/demo/demo-datatables.js"></script>
</body>
</html>