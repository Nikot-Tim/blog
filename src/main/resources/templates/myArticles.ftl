<#import "parts/common.ftl" as c>
<#import "parts/actionWithArticle.ftl" as act>
<@c.page>
    <@act.actionWithArticle "Article editor"/>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>My articles</h1></div>
    <#include "parts/articlesList.ftl"/>
</@c.page>