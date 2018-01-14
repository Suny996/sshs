<form type="condition" action="javascript:Model.btnQueryClick();"
	id="query${coder.className}Form" style="height: 44px;" class="form-inline" customise=true>
	<#list coder.fields as field>
		<#if field.searchFlag=="true">
			<input type="text" name="${field.propertyName}" <#if field_index gte 10 > ignore=true </#if>   preAddon="select.like,=,!=,like,not like" />
		</#if>
	</#list>
	<div type="buttonGroup">
		<button type="query"></button>
		<button type="reset"></button>
	</div>
</form>
<div id="content">
    <div class="btn-toolbar" role="toolbar" aria-label="..." id="${coder.classDeclare}toolbar">
		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default" icon="glyphicon glyphicon-plus" value="add" onclick="Model.add();"></button>
			<button type="button" class="btn btn-default" icon="glyphicon glyphicon-minus" value="delete" onclick="Model.bdelete();"></button>
		</div>
	</div>
	<table type="bootstrap" id="${coder.classDeclare}ListTable" pagination=true
		url="${coder.modelName}/${coder.functionName}/pageList.do" data-toolbar="${coder.classDeclare}toolbar">
		<thead>
			<tr>
				<th type="column" rowNumber="true" />
				<#list coder.fields as field>
					<#if field.listFlag=="true">
						<th type="column" field="${field.propertyName}" />
					</#if>
				</#list> 
				<th type="column" field="handle"><button text="edit"
						action=Model.edit class=" icon-laptop"></button></th>
			</tr>
		</thead>
	</table>
</div>