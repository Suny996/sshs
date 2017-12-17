<form type="condition" action="javascript:Model.btnQueryClick();"
	id="query${coder.className}Form" style="height: 44px;" class="form-inline">
	<#list coder.fields as field>
		<#if field.searchFlag=="true">
			<input type="text" name="${field.propertyName}" />
		</#if>
	</#list>
	<div type="buttonGroup">
		<button type="query"></button>
		<button type="reset"></button>
	</div>
</form>
<div id="content">
	<table type="bootstrap" id="${coder.classDeclare}ListTable" pagination=true
		url="${coder.modelName}/${coder.functionName}/getPageList.do">
		<thead>
			<tr>
				<th type="column" rowNumber="true" />
				<#list coder.fields as field>
					<#if field.listFlag=="true">
						<th type="column" field="${field.propertyName}" />
					</#if>
				</#list> 
				<th type="column" field="handle"><button text="edit"
						action=Model.showEditPage class=" icon-laptop"></button></th>
			</tr>
		</thead>
	</table>
</div>