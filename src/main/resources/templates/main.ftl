<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#import "parts/actionWithArticle.ftl" as act>
<@c.page>

    <#if isActive>
        <@act.actionWithArticle "Add new article"/>
    </#if>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>Articles</h1></div>
    <div class="form-row" style="float: center">
        <div class="form-group col-md-6">
            <form method="get" action="/articles" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <#include "parts/articlesList.ftl"/>
</@c.page>