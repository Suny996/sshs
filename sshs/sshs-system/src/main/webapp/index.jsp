<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<!-- <script type="text/javascript" src="scripts/prototype/prototype.js">
	
</script> -->
<script type="text/javascript" src="scripts/requirejs/require.js">
	
</script>

<script type="text/javascript">
	var Model;
	require.config({
		paths : {
			'jquery' : 'scripts/jquery/jquery',
			'index' : 'index'
		}
	});

	require([ "index" ], function(index) {
		alert(Model+"||"+index);
		Model = index;
		alert(Model+"||"+index.test1);
	}); 
	
	require([ "jquery" ], function($) {
		 
	}); 
</script>
</head>
<body>
	<h2>Hello World!</h2>
	<button id="btn" onclick="Model.test1();">send</button>
	<button id="btn2">send2</button>
</body>
</html>
