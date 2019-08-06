<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <#if isActive>
        <a class="btn btn-primary my-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add new cargo
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
    <div>Messages list</div>
    <div class="card-columns">
        <#list articles as article>
            <div class="card text-white text-center bg-dark my-3">
                <div class="card-body md-3">
                    <h5  class="card-title">${article.title}</h5>
                    <p class="card-text">${article.text}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li type="date" class="list-group-item text-success">Created at: ${article.createdAt}</li>
                </ul>
                <div class="card-body">
                    <p>Author: <a> ${article.authorName}</a>
                    </p>
                </div>
            </div>
        <#else>
            No articles
        </#list>
    </div>
</@c.page>