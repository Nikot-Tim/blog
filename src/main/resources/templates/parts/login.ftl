<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       value="<#if user??>${user.username}</#if>"
                       placeholder="User name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">${usernameError}</div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">${passwordError}</div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Confirm password: </label>
                <div class="col-sm-6">
                    <input type="password" name="confirmPassword"
                           class="form-control ${(confirmPasswordError??)?string('is-invalid', '')}"
                           placeholder="Confirm password"/>
                    <#if confirmPasswordError??>
                        <div class="invalid-feedback">${confirmPasswordError}</div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> First Name: </label>
                <div class="col-sm-6">
                    <input type="text" name="firstName"
                           class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.firstName}</#if>"
                           placeholder="First Name"/>
                    <#if firstNameError??>
                        <div class="invalid-feedback">${firstNameError}</div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Last Name: </label>
                <div class="col-sm-6">
                    <input type="text" name="lastName"
                           class="form-control ${(lastNameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.lastName}</#if>"
                           placeholder="Last Name"/>
                    <#if lastNameError??>
                        <div class="invalid-feedback">${lastNameError}</div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email: </label>
                <div class="col-sm-6">
                    <input type="email" name="email"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.email}</#if>"
                           placeholder="some@some.some"/>
                    <#if emailError??>
                        <div class="invalid-feedback">${emailError}</div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Sign Out</button>
        </form>
    </div>
</#macro>