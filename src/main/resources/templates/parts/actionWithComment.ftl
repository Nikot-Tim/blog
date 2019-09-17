<a class="btn btn-primary my-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add comment
</a>
<div class="collapse <#if comment??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(messageError??)?string('is-invalid', '')}"
                       value="<#if comment??>${comment.message}</#if>"
                       name="message"
                       placeholder="Message" />
                <#if messageError??>
                    <div class="invalid-feedback">${messageError}</div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if comment??>${comment.id?if_exists}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>
