<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#import "parts/actionWithArticle.ftl" as act>
<@c.page>
    <#if isActive>
        <@act.actionWithArticle "Add new article"/>
    </#if>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>Messages list</h1></div>
    <#include "parts/articlesList.ftl"/>
</@c.page>