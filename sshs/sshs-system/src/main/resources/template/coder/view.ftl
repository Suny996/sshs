<form id="${coder.className}Form" method="post" action="javascript:Model.save();">
<#list coder.fields as field>
	<input type="text" name="${field.propertyName}" <#if field.requiredFlag=="true">required="true"</#if> />
</#list>
<div type="buttonGroup" style="text-align:center;" columns=12>
	<button type="submit"></button>
	<button type="reset"></button>
</div>
</form>