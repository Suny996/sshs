<form id="${coder.classDeclare}Form" method="post" action="javascript:Model.save();">
<#list coder.fields as field>
	<input type="text" name="${field.propertyName}" <#if field.requiredFlag=="true">required="true"</#if>  <#if field.primaryKeyFlag=="1"> readOnly="true"  placeholder="generateOnSave"</#if>/>
</#list>
<div type="buttonGroup" style="text-align:center;" columns=12>
	<button type="submit"></button>
	<button type="return"></button>
</div>
</form>