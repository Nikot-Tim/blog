<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <#if isActive>
        <a class="btn btn-primary my-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add new message
        </a>
        <div class="collapse" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control" name="title" placeholder="Title" />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="text" placeholder="Message">
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>
    <div class="mx-auto text-center" style="max-width: 100%; max-height: 100%;"><h1>Messages list</h1></div>

    <#list articles as article>
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
            <div class="card-footer text-muted " type="date-time">
                Last updated ${article.createdAt?string('dd.MM.yyyy HH:mm:ss')}
            </div>
        </div>
    <#else>
        No articles
    </#list>

</@c.page>