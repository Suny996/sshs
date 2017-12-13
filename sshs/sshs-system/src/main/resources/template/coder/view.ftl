<form id="${coder.className}Form">
<#list coder.fields as field>
	<input type="text" name="${field.propertyName}" />
</#list>
<div type="buttonGroup">
	<button type="submit" onclick="javascript:Model.save();"></button>
	<button type="reset"></button>
</div>
</form>