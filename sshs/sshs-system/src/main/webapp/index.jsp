<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<base href="http://localhost:8080/system/">
<script type="text/javascript" src="scripts/jquery/jquery.js"></script>

<script type="text/javascript" src="scripts/requirejs/require.js">
	
</script>


</head>
<body>
	<h2>Hello World!</h2>
	<button id="btn" onclick="Model.test1();">send</button>
	<button id="btn2">send2</button>
</body>
<script type="text/javascript">
/* 	var Model;
	require.config({
		paths : {
			'jquery' : 'scripts/jquery/jquery',
			'index' : 'index'
		}
	}); */
	var Model;
	/* require.config({
		paths : {
			'jquery' : 'scripts/jquery/jquery'
		}
	}); */
	require([ "/system/system/coder/mainList.js.dw" ], function(model) {
		Model = model;
		alert("yyyy:" + Model + "||" + Model.btnQueryClick);
	});
	/* require([ "/system/system/coder/mainList.js.dw" ], function(index) {
		alert("xxxxx:" + Model + "||" + index);
		Model = index;
		alert("yyyy:" + Model + "||" + Model.btnQueryClick);
	}); */
</script>
</html>
