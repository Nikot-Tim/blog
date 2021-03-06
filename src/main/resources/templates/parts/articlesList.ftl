<#include "security.ftl">
<#import "pager.ftl" as p>
    <@p.pager url page "Articles"/>
<#list page.content as article>
    <#if !isActive && article.statuses?seq_contains("PUBLIC")>
        <div class="card text-white bg-dark my-3 mx-auto" style="max-width: 700px;">
            <div class="card-header md-3 text-center" style="height: 50px;">
                ${article.title}
                <#if currentUserId == article.author.id>
                    <a class="btn btn-danger btn-sm" style="float: right" href="/articles/delete/${article.id}">Delete</a>
                </#if>
            </div>
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <p class="card-text text-center">${article.text}#${article.tag}</p>
                    <footer class="blockquote-footer text-right">Author: <cite title="Source Title"><a href="/user-articles/${article.authorId}">
                                ${article.authorName}</a></cite></footer>
                </blockquote>
            </div>
            <div class="card-footer text-muted" type="date-time">
                Last updated ${article.updatedAt?string('dd.MM.yyyy HH:mm:ss')}
                <#if currentUserId == article.author.id>
                    <a class="btn btn-light btn-sm" style="float: right" href="/articles/${article.id}">Edit</a>
                </#if>
            </div>
        </div>
    </#if>
    <#if isActive>
        <div class="card text-white bg-dark my-3 mx-auto" style="max-width: 700px;">
            <div class="card-header md-3 text-center" style="height: 50px;">
                ${article.title}
                <#if currentUserId == article.author.id>
                    <a class="btn btn-danger btn-sm" style="float: right" href="/articles/delete/${article.id}">Delete</a>
                </#if>
            </div>
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <p class="card-text text-center">${article.text}#${article.tag}</p>
                    <footer class="blockquote-footer text-right">Author: <cite title="Source Title"><a href="/user-articles/${article.authorId}">
                            ${article.authorName}</a></cite></footer>
                </blockquote>
            </div>
            <div class="card-footer text-muted" type="date-time">
                Last updated ${article.updatedAt?string('dd.MM.yyyy HH:mm:ss')}
                <#if isActive>
                    <a href="/articles/${article.id}/comments" class="btn btn-outline-warning">View comments</a>
                </#if>
                <#if currentUserId == article.author.id>
                    <a class="btn btn-light btn-sm" style="float: right" href="/articles/${article.id}">Edit</a>
                </#if>
            </div>
        </div>
    </#if>
<#else>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h2>No articles</h2></div>
</#list>