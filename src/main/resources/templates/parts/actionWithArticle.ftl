<#macro actionWithArticle actionName>
<a class="btn btn-primary my-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    ${actionName}
</a>
<div class="collapse <#if article??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                       value="<#if article??>${article.title}</#if>"
                       name="title"
                       placeholder="Title" />
                <#if titleError??>
                    <div class="invalid-feedback">${titleError}</div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if article??>${article.text}</#if>"
                       name="text"
                       placeholder="Text" />
                <#if textError??>
                    <div class="invalid-feedback">${textError}</div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if article??>${article.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>
</#macro>