<form type="condition" action="javascript:Model.btnQueryClick();"
	id="queryForm" style="height: 44px;" class="form-inline">
	<#list coder.fields as field>
		<input type="text" name="${field.propertyName}" />
	</#list>

	<div type="buttonGroup">
		<button type="query"></button>
		<button type="reset"></button>
	</div>
</form>
<div id="content">
	<table type="bootstrap" id="${coder.className}ListTable" pagination=true
		url="${coder.modelName}/${coder.functionName}/getPageList.do">
		<thead>
			<tr>
				<th type="column" rowNumber="true" />
				<#list coder.fields as field>
					<th type="column" field="${field.propertyName}" />
				</#list> 
				<th type="column" field="handle"><button text="edit"
						action=Model.showEditPage class=" icon-laptop"></button></th>
			</tr>
		</thead>
	</table>
</div>