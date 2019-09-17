<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#import "parts/pager.ftl" as p>
<@c.page>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>Article</h1></div>
    <div class="card text-white bg-dark my-3 mx-auto" style="max-width: 700px;">
        <div class="card-header md-3 text-center" style="height: 50px;">
            ${article.title}
        </div>
        <div class="card-body">
            <blockquote class="blockquote mb-0">
                <p class="card-text text-center">${article.text}</p>
                <footer class="blockquote-footer text-right">Author: <cite title="Source Title"> ${article.authorName}</cite></footer>
            </blockquote>
        </div>
        <div class="card-footer text-muted" type="date-time">
            Last updated ${article.updatedAt?string('dd.MM.yyyy HH:mm:ss')}
        </div>
    </div>

    <#if isActive>
        <#include "parts/actionWithComment.ftl"/>
    </#if>
<#if page.hasContent()>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>Comments</h1></div>
    <@p.pager url page "Comments"/>
<#else>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>No comments</h1></div>
</#if>
    <#list page.content as comment>
        <#if comment??>
            <div class="card mb-3">
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>${comment.message}</p>
                        <footer class="blockquote-footer">${comment.authorName}
                            <#if currentUserId == comment.author.id || currentUserId == article.author.id>
                                <a class="btn btn-danger btn-sm" style="float: right" href="/articles/${article.id}/comments/${comment.id}">Delete</a>
                            </#if>
                        </footer>
                    </blockquote>
                </div>
            </div>
        </#if>
    </#list>
</@c.page>